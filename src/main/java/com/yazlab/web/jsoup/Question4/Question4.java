package com.yazlab.web.jsoup.Question4;

import com.yazlab.web.jsoup.Question2.Keywords;
import com.yazlab.web.jsoup.Question2.Question2;
import com.yazlab.web.jsoup.Question3.Question3;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Question4 {


    private String mainUrls;
    private List<UrlTree> urls = new ArrayList<>();


    public List<UrlTree> indexlemeYap(String mainUrls, List<String> urlSet) {
        this.mainUrls = mainUrls;
        for (int i = 0; i < urlSet.size(); i++) {
            UrlTree url = new UrlTree(urlSet.get(i), 1);
            this.urls.add(url);
        }

        try {

            getAllUrl();
            kontrolEt();
            calculateScore();
            keywordFrequency();


        } catch (Exception e) {

            e.printStackTrace();
        }
        return urls;
    }

    public void getAllUrl() throws IOException {

        for (int i = 0; i < urls.size(); i++) {
            Document document = Jsoup.connect(urls.get(i).getUrl()).get();
            Elements links = document.select("a");
            Element link;


            for (int j = 0; j < links.size(); j++) {

                link = links.get(j);
                UrlTree linkClass = new UrlTree(link.attr("href"), 2);
                linkClass.setUpperUrl(urls.get(i));
                if (canAddSubUrl(linkClass)) {
                    urls.get(i).addSubUrl(linkClass);

                }

                if (urls.get(i).getSubUrl().size() == 4) {
                    break;
                }
            }

            for (int j = 0; j < urls.get(i).getSubUrl().size(); j++) {
                if (!urls.get(i).getSubUrl().get(j).getUrl().contains("www") || !urls.get(i).getSubUrl().get(j).getUrl().contains("https")) {
                    continue;
                }
                if (urls.get(i).getSubUrl().get(j).getUrl().contains("facebook") || urls.get(i).getSubUrl().get(j).getUrl().contains("linkedin") || urls.get(i).getSubUrl().get(j).getUrl().contains("twitter") || urls.get(i).getSubUrl().get(j).getUrl().contains("youtube")) {
                    continue;
                }

                document = Jsoup.connect(urls.get(i).getSubUrl().get(j).getUrl()).get();
                links = document.select("a");

                for (int k = 0; k < links.size(); k++) {

                    link = links.get(k);
                    UrlTree linkClass = new UrlTree(link.attr("href"), 3);
                    linkClass.setUpperUrl(urls.get(i).getSubUrl().get(j));
                    if (canAddSubUrl(linkClass)) {
                        urls.get(i).getSubUrl().get(j).addSubUrl(linkClass);

                    }
                    if (urls.get(i).getSubUrl().get(j).getSubUrl().size() == 4) {
                        break;
                    }

                }


            }


        }
    }

    public Boolean canAddSubUrl(UrlTree url) {
        if (url.getUrl().contains("facebook") || url.getUrl().contains("linkedin") || url.getUrl().contains("twitter") || url.getUrl().contains("youtube")) {
            return false;
        }
        if (url.getUrl().contains("www.") || url.getUrl().contains("http://") || url.getUrl().contains("https://")) {

            if (url.getLevel() == 2) {
                if (url.getUpperUrl().getSubUrl().isEmpty()) {
                    return true;
                }
                for (int i = 0; i < url.getUpperUrl().getSubUrl().size(); i++) {
                    if (url.getUrl().equals(url.getUpperUrl().getSubUrl().get(i).getUrl())) {
                        return false;
                    }
                }
                return true;
            }
            if (url.getLevel() == 3) {
                for (int i = 0; i < url.getUpperUrl().getUpperUrl().getSubUrl().size(); i++) {
                    if (url.getUrl().equals(url.getUpperUrl().getUpperUrl().getSubUrl().get(i).getUrl())) {
                        return false;
                    }
                }
                for (int i = 0; i < url.getUpperUrl().getUpperUrl().getSubUrl().size(); i++) {
                    if (!url.getUpperUrl().getUpperUrl().getSubUrl().get(i).getSubUrl().isEmpty()) {
                        for (int j = 0; j < url.getUpperUrl().getUpperUrl().getSubUrl().get(i).getSubUrl().size(); j++) {
                            if (url.getUrl().equals(url.getUpperUrl().getUpperUrl().getSubUrl().get(i).getSubUrl().get(j).getUrl())) {
                                return false;
                            }
                        }
                    }

                }

                return true;

            }

        }

        return false;


    }

    public void kontrolEt() {
        for (int i = 0; i < urls.size(); i++) {
            for (int j = 0; j < urls.get(i).getSubUrl().size(); j++) {
                for (int k = 0; k < urls.get(i).getSubUrl().get(j).getSubUrl().size(); k++) {
                    System.out.println(urls.get(i).getUrl() + "-----" + urls.get(i).getSubUrl().get(j).getUrl() + "----" + urls.get(i).getSubUrl().get(j).getSubUrl().get(k).getUrl());
                }
            }
        }
    }


    public void calculateScore() {
        System.out.println("calculateIndividualScore---Basladi");
        calculateIndividualScore();
        System.out.println("calculateIndividualScore---Bitti");
        System.out.println("calculateTotalScore---Basladi");
        calculateTotalScore();
        System.out.println("calculateTotalScore---Bitti");

        puanGoster();
    }

    public void calculateIndividualScore() {
        Question3 question3 = new Question3();

        System.out.println("Calculate IndividualScore");
        for (int i = 0; i < urls.size(); i++) {
            urls.get(i).setIndividualScore(question3.similarity(mainUrls, urls.get(i).getUrl()).get(0).getSimilarity());
            urls.get(i).setAllWordFrequency(question3.getUrlFrequencyExport());
            for (int j = 0; j < urls.get(i).getSubUrl().size(); j++) {
                urls.get(i).getSubUrl().get(j).setIndividualScore(question3.similarity(mainUrls, urls.get(i).getSubUrl().get(j).getUrl()).get(0).getSimilarity());
                urls.get(i).getSubUrl().get(j).setAllWordFrequency(question3.getUrlFrequencyExport());
                for (int k = 0; k < urls.get(i).getSubUrl().get(j).getSubUrl().size(); k++) {
                    urls.get(i).getSubUrl().get(j).getSubUrl().get(k).setIndividualScore(question3.similarity(mainUrls, urls.get(i).getSubUrl().get(j).getSubUrl().get(k).getUrl()).get(0).getSimilarity());
                    urls.get(i).getSubUrl().get(j).getSubUrl().get(k).setAllWordFrequency(question3.getUrlFrequencyExport());
                    System.out.println("Site  :" + (i) + "---" + (j) + "---" + (k) + "---");
                }
            }
        }
    }

    public void calculateTotalScore() {
        /*
         * 1-2 arasinda puan bolumu 60-40
         * 2-3 arasinda puan bolumu 70-30
         */

        double scoreLevel2 = 0;
        double scoreLevel3 = 0;

        for (int i = 0; i < urls.size(); i++) {
            for (int j = 0; j < urls.get(i).getSubUrl().size(); j++) {
                for (int k = 0; k < urls.get(i).getSubUrl().get(j).getSubUrl().size(); k++) {
                    scoreLevel3 += urls.get(i).getSubUrl().get(j).getSubUrl().get(k).getIndividualScore();
                }
                scoreLevel3 /= urls.get(i).getSubUrl().get(j).getSubUrl().size();

                urls.get(i).getSubUrl().get(j).setTotalScore(((urls.get(i).getSubUrl().get(j).getIndividualScore() * 70) / 100) + ((scoreLevel3 * 30) / 100));
                scoreLevel2 += urls.get(i).getSubUrl().get(j).getTotalScore();
            }

            scoreLevel2 /= urls.get(i).getSubUrl().size();
            urls.get(i).setTotalScore(((urls.get(i).getIndividualScore() * 60) / 100) + ((scoreLevel2 * 40) / 100));
        }
    }

    public void puanGoster() {
        for (int i = 0; i < urls.size(); i++) {
            for (int j = 0; j < urls.get(i).getSubUrl().size(); j++) {
                for (int k = 0; k < urls.get(i).getSubUrl().get(j).getSubUrl().size(); k++) {
                    System.out.println("IndiviualScore: " + urls.get(i).getIndividualScore() + "|||||" + urls.get(i).getSubUrl().get(j).getIndividualScore() + "|||||" + urls.get(i).getSubUrl().get(j).getSubUrl().get(k).getIndividualScore());
                    System.out.println("TotalScore    :  " + urls.get(i).getTotalScore() + "|||||" + urls.get(i).getSubUrl().get(j).getTotalScore());
                }
            }
        }
    }


    public void keywordFrequency() {
        /*
         * calculateIndividual metodu calismadan calismaz
         */
        Question2 question2 = new Question2();
        List<Keywords> keywords;

        keywords = question2.extractKeywords(mainUrls);
        for (int i = 0; i < urls.size(); i++) {
            compareKeyword(keywords, urls.get(i));
            for (int j = 0; j < urls.get(i).getSubUrl().size(); j++) {
                compareKeyword(keywords, urls.get(i).getSubUrl().get(j));
                for (int k = 0; k < urls.get(i).getSubUrl().get(j).getSubUrl().size(); k++) {
                    compareKeyword(keywords, urls.get(i).getSubUrl().get(j).getSubUrl().get(k));
                }
            }
        }


    }

    public void compareKeyword(List<Keywords> keywords, UrlTree url) {
        for (int i = 0; i < keywords.size(); i++) {

            if (i == 7) {
                break;
            }
            KeywordFrequency keywordsFrequeny = new KeywordFrequency();
            keywordsFrequeny.setKeyword(keywords.get(i).getWord());
            keywordsFrequeny.setFrequency(0);
            url.addKeywordFrequency(keywordsFrequeny);
        }


        for (int i = 0; i < url.getKeywordFrequency().size(); i++) {

            for (int j = 0; j < url.getAllWordFrequency().size(); j++) {
                if (url.getKeywordFrequency().get(i).getKeyword().equals(url.getAllWordFrequency().get(j).getKelime())) {
                    url.getKeywordFrequency().get(i).setFrequency(url.getAllWordFrequency().get(j).getFreakans());
                }
            }
        }
    }


}
