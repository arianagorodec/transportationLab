package com.gorodeckaya.controller;

import com.gorodeckaya.entity.Backpack;
import com.gorodeckaya.entity.Deal;
import com.gorodeckaya.entity.Partner;
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
    public String calculate(@RequestParam("select_route") String fromTo,
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
        Comparator<Deal> comparator = new Comparator<Deal>() {
            @Override
            public int compare(Deal deal, Deal deal1) {
                return deal.getDistRoutes().getCities().compareTo(deal1.getDistRoutes().getCities());
            }
        };
        deals.sort(comparator);
        Deal deal = deals.get(0);
        List<Deal> dealList = new ArrayList<>();
        List<List<Deal>> allResultList = new ArrayList<>();
        for (Deal i: deals) {
            if(i.getDistRoutes().getCities().equals(deal.getDistRoutes().getCities())) {
                dealList.add(i);
            }
            else {
                Backpack backpack = new Backpack(dealList.get(0).getDistRoutes().getTypeTransportation().getWeight());
                backpack.makeAllSets(dealList);
                allResultList.add(backpack.GetBestSet());
                deal = i;
                dealList = new ArrayList<>();
                dealList.add(i);
            }
        }
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


}
