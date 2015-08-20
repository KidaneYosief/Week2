package com.android4dev.helpers;

/***
 * @author ZeNan Gao
 * @version 08/07/2015
 * @since 1.0
 */
public class ProductInfo {
    int mPrice;
    String pUrl;
    String currentPrice;
    int pid;

    public ProductInfo(int mPrice,String currentPrice,String pUrl,int pid){
        this.mPrice = mPrice;
        this.currentPrice = currentPrice;
        this.pUrl = pUrl;
        this.pid = pid;
    }

    public int getmPrice(){return mPrice;}
    public String getCurrentPrice(){return currentPrice;}
    public String getpUrl(){return pUrl;}
    public int getPid(){return pid;}
}
