package com.gorodeckaya.controller;

import com.gorodeckaya.entity.Deal;
import com.gorodeckaya.entity.Partner;
import com.gorodeckaya.service.impl.DealServiceImpl;
import com.gorodeckaya.service.impl.PartnerServiceImpl;
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
        Deal deal = new Deal();
        List<Deal> deals = dealService.getAllByPartnerAndFromTo(partner.getId(),route[0],route[1]);
        model.addAttribute("check", 1);
        model.addAttribute("deals", dealService.getAllDistinctByPartner(partner.getId()));
        model.addAttribute("result", deal);
        model.addAttribute("allDeals", deals);
        return "bag";
    }

    /*
Задача на программирование: рюкзак без повторов
Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        число золотых слитков
                    (каждый можно использовать только один раз).
Следующая строка содержит n целых чисел, задающих веса каждого из слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000
Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.
Sample Input:
10 3
1 4 8
Sample Output:
9
*/

    private static int calculate(int[] weights, int neededWeight) {
        int n = weights.length;
        int[][] dp = new int[(int) (neededWeight + 1)][n + 1];
        for (int j = 1; j <= n; j++) {
            for (int w = 1; w <= neededWeight; w++) {
                if (weights[j - 1] <= w) {
                    dp[w][j] = Math.max(dp[w][j - 1], dp[(int) (w - weights[j - 1])][j - 1]+weights[j-1]);
                } else {
                    dp[w][j] = dp[w][j - 1];
                }
            }
        }
        return dp[neededWeight][n];
    }

    private static int getMaxWeight(int w, String weightCargo) {
        String[] cargo = weightCargo.split("-");
        int[] gold=new int[cargo.length];
        for (int i = 0; i < cargo.length; i++) {
            gold[i]= Integer.parseInt(cargo[i]);
        }
        return calculate(gold,w);
    }


}
