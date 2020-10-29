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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        List<DistRoutes> routeList = new ArrayList<>();
        for (Route r: partner.getRoutes()) {
            for (DistRoutes distRoutes: r.getDistRoutes()) {
                distRoutes.setCities(r.getFrom()+"-"+distRoutes.getCities()+"-"+r.getTo());
                routeList.add(distRoutes);
            }
        }

        model.addAttribute("distRoutes",  routeList);
        return "partner";
    }
    @PostMapping("/partner")
    public String addRoute(@RequestParam("route") String fullRoute,
                           @RequestParam("weight") double weight,
                           @RequestParam("type_transport") TransportEnum type,
                           @RequestParam("distance") double distance,
                           @RequestParam("time") double time,
                           @RequestParam("percent") double percent,
                           @RequestParam("price") double price,
                           Model model){
        TypeTransportation typeTransportation = new TypeTransportation();
        typeTransportation.setPrice(price);
        typeTransportation.setTime(time);
        typeTransportation.setPercent(percent);
        typeTransportation.setDescription("");
        typeTransportation.setDistance(distance);
        typeTransportation.setType(type);
        typeTransportation.setWeight(weight);
        typeTransportationService.addTypeTransportation(typeTransportation);

//        Route route = new Route();
        String[] cities = fullRoute.split("-");
        String distroute="";
        for (int i=1; i<cities.length-1; i++){
            if(i==cities.length-2){
                distroute+=cities[i];
            }
            else
                distroute+=cities[i]+"-";
        }
        Route route = routeService.getbyFromToIdPartner(cities[0],cities[cities.length-1],partnerService.getInfoPartner().getId());
        if(route==null) {
            route = new Route();
            route.setFrom(cities[0]);
            route.setTo(cities[cities.length - 1]);
            route.setPartner(partnerService.getInfoPartner());
            routeService.addRoute(route);
        }

        DistRoutes distRoutes = new DistRoutes();
        distRoutes.setCities(distroute);
        distRoutes.setTypeTransportation(typeTransportation);
        distRoutes.setRoute(route);
        distRoutesService.addDistRoutes(distRoutes);

        return "redirect:/partner";
    }
}
