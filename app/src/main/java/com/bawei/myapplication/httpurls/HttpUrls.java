package com.bawei.myapplication.httpurls;

import java.net.URLEncoder;

/**
 * author : Eaves
 * desc   : 获取网络接口
 * date   : 2019/11/27
 */
public class HttpUrls {
    public static String getHttpUrl(String tagName){

        return "http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?keyword="+ URLEncoder.encode(tagName) +"&page=1&count=5";
    }
}
