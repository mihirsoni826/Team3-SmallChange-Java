package com.smallchange;

import com.smallchange.buytrade.sellTradeServiceImp;
import com.smallchange.services.SellTradeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.smallchange.*;

@SpringBootApplication()
public class SmallChangeJavaApplication {

	public static void main(String[] args) {

//		SellTradeService s= new sellTradeServiceImp();

//		s.sellTrade(null);
		SpringApplication.run(SmallChangeJavaApplication.class, args);
	}

}
