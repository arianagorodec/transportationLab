package com.gorodeckaya.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Scanner;

@Controller
public class BagController {
    @GetMapping("/bag")
    public String bag(Model model) {
        model.addAttribute("result", 0);
        return "bag";
    }
    @PostMapping("/bag")
    public String calculate(@RequestParam("weightC") String weightC,
                            @RequestParam("weightCargo") String weightCargo,
                            Model model){
        int result = getMaxWeight(Integer.parseInt(weightC), weightCargo);
        model.addAttribute("result", result);
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
