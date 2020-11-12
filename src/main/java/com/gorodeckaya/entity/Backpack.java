package com.gorodeckaya.entity;


//import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Backpack {
    private List<Deal> bestItems = null;

    private double maxW;

    private double bestPrice;

    public Backpack(double _maxW)
    {
        maxW = _maxW;
    }

    //вычисляет общий вес набора предметов
    private double CalcWeigth(List<Deal> items)
    {
        double sumW = 0;

        for (Deal i: items) {
            sumW += i.getWeight();
        }

        return sumW;
    }

    //вычисляет общую стоимость набора предметов
    private double CalcPrice(List<Deal> items)
    {
        double sumPrice = 0;

        for (Deal i: items)
        {
            sumPrice += i.getPrice();
        }

        return sumPrice;
    }

    //проверка, является ли данный набор лучшим решением задачи
    private void CheckSet(List<Deal> items)
    {
        if (bestItems == null)
        {
            if (CalcWeigth(items) <= maxW)
            {
                bestItems = items;
                bestPrice = CalcPrice(items);
            }
        }
        else
        {
            if(CalcWeigth(items) <= maxW && CalcPrice(items) > bestPrice)
            {
                bestItems = items;
                bestPrice = CalcPrice(items);
            }
        }
    }

//создание всех наборов перестановок значений
    public void makeAllSets(List<Deal> items)
    {
        if (items.size() > 0)
            CheckSet(items);

        for (int i = 0; i < items.size(); i++)
        {
            List<Deal> newSet = new ArrayList<>(items);
            System.out.println("2"+newSet);
            newSet.remove(i);

            makeAllSets(newSet);
        }
    }

    //возвращает решение задачи (набор предметов)
    public List<Deal> GetBestSet()
    {
        return bestItems;
    }
}
