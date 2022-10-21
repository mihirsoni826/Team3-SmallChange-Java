package com.smallchange.buytrade;

import com.smallchange.db.DbSellService;
import com.smallchange.db.IDbServiceImpl;
import com.smallchange.entities.sellModel;
import com.smallchange.services.SellTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class sellTradeServiceImp implements SellTradeService {

    @Autowired
    DbSellService db;

    @Override
    public String sellTrade(sellModel reqBody) {

        String ticker=reqBody.getTicker();

        try {
            Double res=  db.getQuantity(ticker) - reqBody.getQuantity();
            if(res<0)
            {
               return (" quantity not available");
            }
            else if(res > 0)
            {
                if(db.updateQua(res, ticker) > 0)
                {
                   return ("Sell Succesfull");
                }
            }
            else
            {
                if(db.deleteSec(ticker)>0)
                {
                   return ("Sell Succesfull");
                }

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }



        return "Failed";
    }
}
