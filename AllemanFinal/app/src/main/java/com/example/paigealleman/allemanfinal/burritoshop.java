package com.example.paigealleman.allemanfinal;

/**
 * Created by paigealleman on 12/17/17.
 */

public class burritoshop {
    private String burritoShop;
    private String burritoShopURL;

    private void setBurritoInfo(Integer location){
        switch(location){
            case 0: // the hill
                burritoShop = "Illegal Petes";
                burritoShopURL = "http://illegalpetes.com/";
                break;
            case 1: // 29th street
                burritoShop = "Chipotle";
                burritoShopURL = "https://www.chipotle.com/";
                break;
            case 2: // pearl street
                burritoShop = "Bartaco";
                burritoShopURL = "https://bartaco.com/";
                break;
        }

    }

    public void setBurritoShop(Integer location){ setBurritoInfo(location);}

    public void setBurritoShopURL(Integer location){setBurritoInfo(location);}

    public String getBurritoShop() {return burritoShop;}

    public String getBurritoShopURL() {return burritoShopURL;}


}
