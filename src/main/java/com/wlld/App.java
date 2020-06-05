package com.wlld;


import com.shareData.chainMarket.HttpServer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        HttpServer httpServer = new HttpServer();
        httpServer.start(8080, "com.wlld.controller", 1024,
                null, null);
    }
}
