package com.gorodeckaya.controller;

import com.gorodeckaya.entity.DistRoutes;
import com.gorodeckaya.entity.Partner;
import com.gorodeckaya.entity.Route;
import com.gorodeckaya.entity.TypeTransportation;
import com.gorodeckaya.entity.enums.TransportEnum;
import com.gorodeckaya.service.impl.DistRoutesServiceImpl;
import com.gorodeckaya.service.impl.PartnerServiceImpl;
import com.gorodeckaya.service.impl.RouteServiceImpl;
import com.gorodeckaya.service.impl.TypeTransportationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PartnerController {
    @Autowired
    PartnerServiceImpl partnerService;
    @Autowired
    RouteServiceImpl routeService;
    @Autowired
    TypeTransportationServiceImpl typeTransportationService;
    @Autowired
    DistRoutesServiceImpl distRoutesService;
    @GetMapping("/partner")
    public String partnerMain(Model model) {
        Partner partner = partnerService.getInfoPartner();
        model.addAttribute("company", partner.getCompany());
        model.addAttribute("address", partner.getAddress());
        model.addAttribute("name", partner.getName());
        model.addAttribute("surname", partner.getSurname());
        model.addAttribute("mobphone", partner.getMobphone());
        model.addAttribute("email", partner.getEmail());
        List<Route> routeList = new ArrayList<>();
        for (Route r: partner.getRoutes()) {
            String cities = "";
            String transport = "";
            String percent = "";
            Double time = 0.0;
            Double price = 0.0;
            cities+=r.getFrom();
            boolean check = true;
            for (DistRoutes distRoutes: r.getDistRoutes()) {
                if(distRoutes.getCities().equals(r.getFrom()) && check){
                    transport+=distRoutes.getTypeTransportation().getType()+" ";
                    percent+=distRoutes.getTypeTransportation().getPercent()+" ";
                    time+= distRoutes.getTypeTransportation().getTime();
                    price+=distRoutes.getTypeTransportation().getPrice();
                    check=false;
                }
                else {
                    cities += "-" + distRoutes.getCities();
                    transport += distRoutes.getTypeTransportation().getType() + " ";
                    percent += distRoutes.getTypeTransportation().getPercent() + " ";
                    time += distRoutes.getTypeTransportation().getTime();
                    price += distRoutes.getTypeTransportation().getPrice();
                }
            }
            cities+="-"+r.getTo();
            r.setCities(cities);
            r.setTransports(transport);
            r.setPercent(percent);
            r.setTime(time);
            r.setPrice(price);
            routeList.add(r);
        }

        model.addAttribute("routes",  routeList);
        return "partner";
    }
    @PostMapping("/partner")
    public String addRoute(@RequestParam("city_from") String city_from,
                           @RequestParam("city_to") String city_to,
                           @RequestParam("weight") double weight,
                           @RequestParam("type_transport") TransportEnum type,
                           @RequestParam("distance") double distance,
                           @RequestParam("time") double time,
                           @RequestParam("percent") double percent,
                           @RequestParam("price") double price,
                           HttpServletRequest request,
                           Model model){

        Route route = new Route();
        route.setFrom(city_from);
        route.setTo(city_to);
        route.setPartner(partnerService.getInfoPartner());
        routeService.addRoute(route);

        TypeTransportation typeTransportation = new TypeTransportation();
        typeTransportation.setPrice(price);
        typeTransportation.setTime(time);
        typeTransportation.setPercent(percent);
        typeTransportation.setDescription("");
        typeTransportation.setDistance(distance);
        typeTransportation.setType(type);
        typeTransportation.setWeight(weight);
        typeTransportationService.addTypeTransportation(typeTransportation);

        DistRoutes distRoutes = new DistRoutes();
        distRoutes.setCities(city_from);
        distRoutes.setTypeTransportation(typeTransportation);
        distRoutes.setRoute(route);
        distRoutesService.addDistRoutes(distRoutes);

        for(int i=1;i<3;i++) {
            if(!request.getParameter("dist_city" + i).isEmpty()) {
                typeTransportation = new TypeTransportation();
                typeTransportation.setPrice(Double.parseDouble(request.getParameter("price" + i)));
                typeTransportation.setTime((Double.parseDouble(request.getParameter("time" + i))));
                typeTransportation.setPercent((Double.parseDouble(request.getParameter("percent" + i))));
                typeTransportation.setDescription("");
                typeTransportation.setDistance((Double.parseDouble(request.getParameter("distance" + i))));
                typeTransportation.setType(request.getParameter("type_transport" + i));
                typeTransportation.setWeight((Double.parseDouble(request.getParameter("weight" + i))));
                typeTransportationService.addTypeTransportation(typeTransportation);

                distRoutes = new DistRoutes();
                distRoutes.setCities(request.getParameter("dist_city" + i));
                distRoutes.setTypeTransportation(typeTransportation);
                distRoutes.setRoute(route);
                distRoutesService.addDistRoutes(distRoutes);
            }
        }


        return "redirect:/partner";
    }
}
