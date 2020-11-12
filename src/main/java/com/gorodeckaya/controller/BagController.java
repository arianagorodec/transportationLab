package com.gorodeckaya.controller;

import com.gorodeckaya.entity.Backpack;
import com.gorodeckaya.entity.Deal;
import com.gorodeckaya.entity.DistRoutes;
import com.gorodeckaya.entity.Partner;
import com.gorodeckaya.entity.enums.TransportEnum;
import com.gorodeckaya.service.impl.DealServiceImpl;
import com.gorodeckaya.service.impl.PartnerServiceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BagController {
    @Autowired
    DealServiceImpl dealService;
    @Autowired
    PartnerServiceImpl partnerService;
    @GetMapping("/bag")
    public String bag(Model model) {
        Partner partner = partnerService.getInfoPartner();
        model.addAttribute("result", 0);
        model.addAttribute("deals", dealService.getAllDistinctByPartner(partner.getId()));
        model.addAttribute("check", 0);
        model.addAttribute("allDeals", null);
        return "bag";
    }
    @PostMapping("/bag")
    public String calculates(@RequestParam("select_route") String fromTo,
                            Model model){
//        int result = getMaxWeight(Integer.parseInt(weightC), weightCargo);
        String[] route = fromTo.split("-");
        Partner partner = partnerService.getInfoPartner();
        List<Deal> deals = dealService.getAllByPartnerAndFromTo(partner.getId(),route[0],route[1]);
        List<Deal> result = calculate(deals);

        model.addAttribute("check", 1);
        model.addAttribute("deals", dealService.getAllDistinctByPartner(partner.getId()));
        model.addAttribute("maxPrice", maxPrice(result));
        model.addAttribute("result", result);
        model.addAttribute("allDeals", deals);
        return "bag";
    }

    private static List<Deal> calculate(List<Deal> deals) {
        for (Deal fullDeal: deals){
            String cities = "";
            TransportEnum transportEnum = ClientController.defineTypeTransportation(fullDeal.getRoute().getDistRoutes());
            fullDeal.getRoute().setTransports(transportEnum.toString());
            boolean check = true;
            cities+=fullDeal.getCity_from()+"-";
            for(DistRoutes distRoutes: fullDeal.getRoute().getDistRoutes()) {
                if(distRoutes.getCities().equals(fullDeal.getCity_from()) && check)
                    check=false;
                else
                    cities += distRoutes.getCities() + "-";
            }
            cities+=fullDeal.getCity_to();
            fullDeal.getRoute().setCities(cities);
        }
        Comparator<Deal> comparator = new Comparator<Deal>() {
            @Override
            public int compare(Deal deal, Deal deal1) {
                return deal.getRoute().getCities().compareTo(deal1.getRoute().getCities());
            }
        };
        deals.sort(comparator);
        Deal deal = deals.get(0);
        List<Deal> dealList = new ArrayList<>();
        List<List<Deal>> allResultList = new ArrayList<>();
        for (Deal i: deals) {
            if(i.getRoute().getCities().equals(deal.getRoute().getCities())) {
                dealList.add(i);
            }
            else {
                dealList.add(i);
                Backpack backpack = new Backpack(minWeightOnRoute(i));
                backpack.makeAllSets(dealList);
                allResultList.add(backpack.GetBestSet());
                deal = i;
                dealList = new ArrayList<>();
                dealList.add(i);
            }
        }
        Backpack backpack = new Backpack(minWeightOnRoute(deal));
        backpack.makeAllSets(dealList);
        allResultList.add(backpack.GetBestSet());
        List<Deal> resultList = maxPriceDeal(allResultList);
        return resultList;
    }

    private static List<Deal> maxPriceDeal(List<List<Deal>> allResultList) {
        List<Deal> resultList = new ArrayList<>();
        double maxPrice = 0;
        for (List<Deal> j: allResultList){
            double price = 0;
            for (Deal d: j){
                price+=d.getPrice();
            }
            if(maxPrice<price) {
                maxPrice = price;
                resultList=j;
            }
        }
        return resultList;
    }
    private static double maxPrice(List<Deal> resList) {
        double maxPrice = 0;
            for (Deal d: resList){
                maxPrice+=d.getPrice();
            }
        return maxPrice;
    }

    private static double minWeightOnRoute(Deal deal) {
        double minWeight = 0;
        for (DistRoutes d: deal.getRoute().getDistRoutes()){
            double weight = d.getTypeTransportation().getWeight();
            if(minWeight<weight) {
                minWeight = weight;
            }
        }
        return minWeight;
    }


}
