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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            String cities = "";
            String transport = "";
            boolean check = true;
            cities+=deal.getCity_from()+"-";
            for(DistRoutes distRoutes: deal.getRoute().getDistRoutes()) {
                if(distRoutes.getCities().equals(deal.getCity_from()) && check)
                    check=false;
                else
                    cities += distRoutes.getCities() + "-";
                transport += distRoutes.getTypeTransportation().getType()+" ";
            }
            cities+=deal.getCity_to();
            deal.getRoute().setCities(cities);
            deal.getRoute().setTransports(transport);
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
        Route route = new Route();
        Set<DistRoutes> distRoutesSet = new HashSet<>();
        DistRoutes distRoutes = new DistRoutes();
        TypeTransportation typeTransportation = new TypeTransportation();
        typeTransportation.setType(type);
        distRoutes.setTypeTransportation(typeTransportation);
        distRoutesSet.add(distRoutes);
        route.setDistRoutes(distRoutesSet);
        deal.setRoute(route);
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
        for (Deal deal1:client.getDeals()){
            String cities = "";
            String transport = "";
            Double time = 0.0;
            Double price = 0.0;
            cities+=deal1.getCity_from()+" ";
            for(DistRoutes distRoutes1: deal1.getRoute().getDistRoutes()) {
                cities += distRoutes1.getCities() + " ";
                transport += distRoutes1.getTypeTransportation().getType()+" ";
                time += distRoutes1.getTypeTransportation().getTime();
                price += distRoutes1.getTypeTransportation().getPrice();
            }
            deal1.getRoute().setCities(cities);
            deal1.getRoute().setTransports(transport);
            deal1.getRoute().setTime(time);
            deal1.getRoute().setPrice(price);
            deals.add(deal1);
        }

        model.addAttribute("newDeals",  newdeals);
        model.addAttribute("myDeal",  deal);
        model.addAttribute("check", 1);
        model.addAttribute("deals",  deals);
        model.addAttribute("citiesFrom",  routeService.getUniqFrom());
        model.addAttribute("citiesTo",  routeService.getUniqTo());

        return "client";
    }

    public List<Deal> findAllRoute(Deal deal) {
        List<Route> routes = routeService.getAllbyFromTo(deal.getCity_from(), deal.getCity_to());
        List<Deal> deals = new ArrayList<>();
        for (Route r : routes) {
            TransportEnum transportEnum = defineTypeTransportation(r.getDistRoutes());
            for (DistRoutes dealDistRoute : deal.getRoute().getDistRoutes()) {
                if (transportEnum.equals(dealDistRoute.getTypeTransportation().getType())) {
                    Deal newDeal = new Deal();
                    newDeal.setCity_from(r.getFrom());
                    newDeal.setCity_to(r.getTo());
                    newDeal.setAddress_from(deal.getAddress_from());
                    newDeal.setAddress_to(deal.getAddress_to());
                    newDeal.setSize(deal.getSize());
                    newDeal.setWeight(deal.getWeight());
                    newDeal.setPrice(0.0);
                    newDeal.setTime(0.0);
                    String cities = "";
                    boolean check = true;
                    cities+=newDeal.getCity_from()+"-";
                    for (DistRoutes distRoutes : r.getDistRoutes()) {
                        if(distRoutes.getCities().equals(r.getFrom()) && check)
                            check=false;
                        else
                            cities+=distRoutes.getCities()+"-";
                        if (deal.getType_goods() != null)
                            newDeal.setPrice(newDeal.getPrice()+distRoutes.getTypeTransportation().getPrice() * distRoutes.getTypeTransportation().getDistance() + distRoutes.getTypeTransportation().getPrice() * distRoutes.getTypeTransportation().getDistance() * distRoutes.getTypeTransportation().getPercent());
                        else
                            newDeal.setPrice(newDeal.getPrice()+distRoutes.getTypeTransportation().getPrice() * distRoutes.getTypeTransportation().getDistance());
                        newDeal.setTime(newDeal.getTime()+distRoutes.getTypeTransportation().getTime());
                    }
                    cities+=newDeal.getCity_to();
                    r.setCities(cities);
                    r.setTransports(transportEnum.toString());
                    newDeal.setRoute(r);
                    deals.add(newDeal);
                }
            }
        }
        return deals;
    }

    public static TransportEnum defineTypeTransportation(Set<DistRoutes> distRouteSet) {
        int[] typeTransport = new int[6];
        for (DistRoutes distRoutes: distRouteSet){
            typeTransport[distRoutes.getTypeTransportation().getType().ordinal()]++;
        }
        if (typeTransport[0]==distRouteSet.size())
            return TransportEnum.TRUCK;
        else if (typeTransport[1]==distRouteSet.size())
            return TransportEnum.PLANE;
        else if (typeTransport[2]==distRouteSet.size())
            return TransportEnum.TRAIN;
        else if (typeTransport[3]==distRouteSet.size())
            return TransportEnum.AUTO;
        else if (typeTransport[4]==distRouteSet.size())
            return TransportEnum.SHIP;
        else
            return TransportEnum.MIXED;
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
        Route route = routeService.findById(id);
        Deal deal = new Deal();
        Client client = clientService.getInfoClient();
        deal.setClient(client);
        deal.setRoute(route);
        deal.setCity_from(route.getFrom());
        deal.setCity_to(route.getTo());
        deal.setAddress_from(address_from);
        deal.setAddress_to(address_to);
        deal.setWeight(weight);
        deal.setType_goods(type_goods);
        deal.setSize(size);

        deal.setPrice(0.0);
        deal.setTime(0.0);

        for (DistRoutes distRoutes : route.getDistRoutes()) {
            if (deal.getType_goods() != null)
                deal.setPrice(deal.getPrice()+distRoutes.getTypeTransportation().getPrice() * distRoutes.getTypeTransportation().getDistance() + distRoutes.getTypeTransportation().getPrice() * distRoutes.getTypeTransportation().getDistance() * distRoutes.getTypeTransportation().getPercent());
            else
                deal.setPrice(deal.getPrice()+distRoutes.getTypeTransportation().getPrice() * distRoutes.getTypeTransportation().getDistance());
            deal.setTime(deal.getTime()+distRoutes.getTypeTransportation().getTime());
        }
        deal.setClient(clientService.getInfoClient());
        dealService.addDeal(deal);
        return "redirect:/client";
    }
}

