package com.yazlab.web.jsoup;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {

    public Test() {

    }

    public List<Broadcast> deneme() {
        List<Broadcast> broadcastList = new ArrayList<>();
        try {
            Document document = Jsoup.connect("https://ozguryazilim.com.tr/biz/kariyer/").get();
            Elements broadcasts = document.select("p");

            for (Element element : broadcasts) {
                Broadcast broadcast = new Broadcast();
                //broadcast.setTime(element.text());
                broadcastList.add(broadcast);
            }

            for (Broadcast broadcast : broadcastList) {


                //System.out.println(broadcast.getTime());



            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return broadcastList;
    }
}
