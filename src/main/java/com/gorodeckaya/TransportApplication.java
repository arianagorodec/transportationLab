package com.gorodeckaya;

import com.gorodeckaya.entity.Deal;
import com.gorodeckaya.entity.enums.TransportEnum;
import com.gorodeckaya.entity.enums.TypeGoodsEnum;
import com.gorodeckaya.logic.FindRoute;
import com.gorodeckaya.service.impl.RouteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TransportApplication {

	@Autowired
	RouteServiceImpl routeService;

	public static void main(String[] args) {
		SpringApplication.run(TransportApplication.class, args);
	}

}
