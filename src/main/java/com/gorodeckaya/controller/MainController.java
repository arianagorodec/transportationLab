package com.gorodeckaya.controller;

import com.gorodeckaya.entity.Deal;
import com.gorodeckaya.entity.DistRoutes;
import com.gorodeckaya.entity.Route;
import com.gorodeckaya.entity.User;
import com.gorodeckaya.entity.enums.TransportEnum;
import com.gorodeckaya.entity.enums.TypeGoodsEnum;
import com.gorodeckaya.logic.FindRoute;
import com.gorodeckaya.service.impl.RouteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    RouteServiceImpl routeService;

    public void findAllRoute(Deal deal) {
        System.out.println(deal);
        List<Route> routes = routeService.getAllbyFromTo(deal.getCity_from(), deal.getCity_to());
        for (Route r : routes) {
            System.out.println(r.getFrom() + "  " + r.getTo());
            System.out.println(r.getPartner().getCompany() + "  " + r.getPartner().getCompany());
            for (DistRoutes distRoutes : r.getDistRoutes()) {
                System.out.println(distRoutes.getCities());
                for (TransportEnum te : deal.getTypeTransportationList()) {
                    if (te.equals(distRoutes.getTypeTransportation().getType())) {
                        System.out.println(" t:" + distRoutes.getTypeTransportation().getTime() + " d:" + distRoutes.getTypeTransportation().getDistance());
                        if (deal.getType_goods() != null)
                            System.out.println(" type:" + distRoutes.getTypeTransportation().getType() + " pr:" + distRoutes.getTypeTransportation().getPrice() * distRoutes.getTypeTransportation().getDistance() + distRoutes.getTypeTransportation().getPrice() * distRoutes.getTypeTransportation().getDistance() * distRoutes.getTypeTransportation().getPercent());
                        else
                            System.out.println(" type:" + distRoutes.getTypeTransportation().getType() + " pr:" + distRoutes.getTypeTransportation().getPrice() * distRoutes.getTypeTransportation().getDistance());
                    }
                }
            }
        }
    }

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("partnerForm", new User());
        model.addAttribute("clientForm", new User());
        return "/index";
    }
//    @GetMapping("/")
//    public String registration(Model model) {
//        FindRoute findRoute = new FindRoute();
//        Deal deal = new Deal();
//        deal.setCity_from("Минск");
//        deal.setCity_to("Токио");
//        deal.setType_goods(TypeGoodsEnum.CRUMBLY);
//        List<TransportEnum> list = new ArrayList<>();
//        list.add(TransportEnum.PLANE);
//        deal.setTypeTransportationList(list);
//        findAllRoute(deal);
//        return "/";
//    }
}
