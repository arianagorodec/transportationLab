package com.gorodeckaya.controller;

import com.gorodeckaya.entity.*;
import com.gorodeckaya.entity.enums.TransportEnum;
import com.gorodeckaya.entity.enums.TypeGoodsEnum;
import com.gorodeckaya.service.RouteService;
import com.gorodeckaya.service.impl.ClientServiceImpl;
import com.gorodeckaya.service.impl.DealServiceImpl;
import com.gorodeckaya.service.impl.DistRoutesServiceImpl;
import com.gorodeckaya.service.impl.RouteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientController {
    @Autowired
    ClientServiceImpl clientService;
    @Autowired
    RouteServiceImpl routeService;
    @Autowired
    DistRoutesServiceImpl distRoutesService;
    @Autowired
    DealServiceImpl dealService;

    @GetMapping("/client")
    public String clientEnter(Model model) {
        Client client = clientService.getInfoClient();
        model.addAttribute("name", client.getName());
        model.addAttribute("surname", client.getSurname());
        model.addAttribute("mobphone", client.getMobphone());
        model.addAttribute("email", client.getEmail());
        List<Deal> deals = new ArrayList<>();
        for (Deal deal:client.getDeals()){
            deal.getDistRoutes().setCities(deal.getCity_from()+"-"+deal.getDistRoutes().getCities()+"-"+deal.getCity_to());
            deals.add(deal);
        }
        model.addAttribute("deals",  deals);
        model.addAttribute("citiesFrom",  routeService.getUniqFrom());
        model.addAttribute("citiesTo",  routeService.getUniqTo());
        model.addAttribute("check", 0);
        return "client";
    }
    @PostMapping("/findRoute")
    public String addRoute(@RequestParam("city_from") String city_from,
                           @RequestParam("city_to") String city_to,
                           @RequestParam("address_to") String address_to,
                           @RequestParam("address_from") String address_from,
                           @RequestParam("weight") double weight,
                           @RequestParam("type_transport") TransportEnum type,
                           @RequestParam("type_goods") TypeGoodsEnum type_goods,
                           @RequestParam("size") double size,
                           Model model){
        Deal deal = new Deal();
        deal.setCity_from(city_from);
        deal.setCity_to(city_to);
        deal.setAddress_from(address_from);
        deal.setAddress_to(address_to);
        deal.setWeight(weight);
        if(type_goods.equals("NOTHING"))
            deal.setType_goods(null);
        else
            deal.setType_goods(type_goods);
        deal.setSize(size);
        deal.setClient(clientService.getInfoClient());
        List<Deal> newdeals = findAllRoute(deal);

        Client client = clientService.getInfoClient();
        model.addAttribute("name", client.getName());
        model.addAttribute("surname", client.getSurname());
        model.addAttribute("mobphone", client.getMobphone());
        model.addAttribute("email", client.getEmail());
        List<Deal> deals = new ArrayList<>();
        for (Deal dealSearch:client.getDeals()){
            dealSearch.getDistRoutes().setCities(dealSearch.getCity_from()+"-"+dealSearch.getDistRoutes().getCities()+"-"+dealSearch.getCity_to());
            deals.add(dealSearch);
        }

        model.addAttribute("newDeals",  newdeals);
        model.addAttribute("myDeal",  deal);
        model.addAttribute("check", 1);
        model.addAttribute("deals",  deals);
        return "client";
    }

    public List<Deal> findAllRoute(Deal deal) {
        List<Route> routes = routeService.getAllbyFromTo(deal.getCity_from(), deal.getCity_to());
        List<Deal> deals = new ArrayList<>();
        for (Route r : routes) {
            for (DistRoutes distRoutes : r.getDistRoutes()) {
                Deal newDeal = new Deal();
                newDeal.setCity_from(r.getFrom());
                newDeal.setCity_to(r.getTo());
                newDeal.setAddress_from(deal.getAddress_from());
                newDeal.setAddress_to(deal.getAddress_to());
                newDeal.setSize(deal.getSize());
                newDeal.setWeight(deal.getWeight());
                DistRoutes newDistRoutes = new DistRoutes();
                newDistRoutes.setId(distRoutes.getId());
                newDistRoutes.setCities(distRoutes.getCities());
                newDistRoutes.setTypeTransportation(distRoutes.getTypeTransportation());
                newDistRoutes.setRoute(r);
                newDeal.setDistRoutes(newDistRoutes);
                if (deal.getType_goods() != null)
                    newDeal.setPrice(distRoutes.getTypeTransportation().getPrice() * distRoutes.getTypeTransportation().getDistance() + distRoutes.getTypeTransportation().getPrice() * distRoutes.getTypeTransportation().getDistance() * distRoutes.getTypeTransportation().getPercent());
                else
                    newDeal.setPrice(distRoutes.getTypeTransportation().getPrice() * distRoutes.getTypeTransportation().getDistance());
                newDeal.setTime(distRoutes.getTypeTransportation().getTime());
                deals.add(newDeal);
            }
        }
        return deals;
    }
    @PostMapping("/addDeal")
    public String addRoute(@RequestParam("id") long id,
                           @RequestParam("address_to") String address_to,
                           @RequestParam("address_from") String address_from,
                           @RequestParam("weight") double weight,
                           @RequestParam("type_goods") TypeGoodsEnum type_goods,
                           @RequestParam("size") double size,
                           Model model) {
//        System.out.println(myDeal);
        System.out.println(id);
        DistRoutes distRoutes = distRoutesService.findDistRoutesId(id);
        Deal deal = new Deal();
        deal.setClient(clientService.getInfoClient());
        deal.setDistRoutes(distRoutes);
        deal.setCity_from(distRoutes.getRoute().getFrom());
        deal.setCity_to(distRoutes.getRoute().getTo());
        deal.setAddress_from(address_from);
        deal.setAddress_to(address_to);
        deal.setWeight(weight);
        deal.setType_goods(type_goods);
        deal.setSize(size);
        deal.setTime(distRoutes.getTypeTransportation().getTime());
        if(type_goods!=null)
            deal.setPrice(distRoutes.getTypeTransportation().getPrice() * distRoutes.getTypeTransportation().getDistance() + distRoutes.getTypeTransportation().getPrice() * distRoutes.getTypeTransportation().getDistance() * distRoutes.getTypeTransportation().getPercent());
        else
            deal.setPrice(distRoutes.getTypeTransportation().getPrice() * distRoutes.getTypeTransportation().getDistance());
        deal.setClient(clientService.getInfoClient());
        dealService.addDeal(deal);
        return "redirect:/client";
    }
}

