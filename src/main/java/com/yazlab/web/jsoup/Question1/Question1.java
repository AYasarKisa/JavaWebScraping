package com.yazlab.web.jsoup.Question1;

import com.yazlab.web.jsoup.Broadcast;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Question1 {

    private List<WordFrequency> kelimeList = new ArrayList<>();

    /*
     * freakansBul calistiginda:
     * ilk olarak aldigi url i inceler ve belirlenen html tag ine gore parcalar ve bize
     * string olarak doner
     * Daha sonra her dondugu cumle icin cradcastList e ekleme yapar
     * Daha sonra ise bu listede gezerek aldigi stringleri sira ile
     * kelimelereBol fonksiyonuna gonderir
     * Hepsi bitince de kelimeList i apiye doner
     */
    public List<WordFrequency> freakansBul(String url) {
        List<Broadcast> broadcastList = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements broadcasts = document.select("body");
            kelimelereBol(broadcasts.text());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kelimeList;
    }

    /*
     * kelimelerBol metodu ise aldigi stringi
     * ilk once kelimelere boluyor
     * daha sonra ise bu kelimelerin kelimeList te olup olmadigini
     * kontrol ediyor( tum kelimeler kucuk harf olarak kontrol edilmistir)
     * Eger kelime kelimeList te varsa kelimeList teki kelimenin freakansı 1 artiyor
     * Eger yok ise bu kelimeyi kelimeListe ekliyor
     */
    private void kelimelereBol(String text) {
        String[] kelimeler;
        kelimeler = text.split(" ");
        for (String kelime : kelimeler) {
            int i = 0;
            Boolean kontrol = false;
            kelime = kelime.toLowerCase();
            for (i = 0; i < kelimeList.size(); i++) {
                if (kelime.equals(kelimeList.get(i).getKelime())) {
                    kelimeList.get(i).freakansArtir();
                    kontrol = true;
                    break;
                }
            }
            if (!kontrol) {
                kelimeList.add(new WordFrequency(kelime));
            }
        }
    }
}
