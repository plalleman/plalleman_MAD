package com.example.paigealleman.lab6;

/**
 * Created by paigealleman on 12/12/17.
 */

public class SushiShop {

    private String sushiShop;
    private String sushiShopURL;

    private void setSushiInfo(Integer sushiPrice){
        switch(sushiPrice){
            case 0: //low cost per person
                sushiShop = "Jaws";
                sushiShopURL = "https://www.facebook.com/jawsfoco/";
                break;

            case 1: //slightly higher cost
                sushiShop = "Nimos";
                sushiShopURL = "http://www.nimossushi.com/";
                break;
            case 2: //expensive
                sushiShop = "Suehiro";
                sushiShopURL = "http://www.suehirojapaneserestaurant.com/";
                break;
        }
    }

    public void setSushiShop(Integer sushiPrice){
        setSushiInfo(sushiPrice);
    }

    public void setCoffeeShopURL(Integer sushiPrice){
        setSushiInfo(sushiPrice);
    }

    public String getSushiShop(){
        return sushiShop;
    }

    public String getSushiShopURL(){
        return sushiShopURL;
    }
}
