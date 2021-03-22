package com.yazlab.web.jsoup.Question3;


import com.yazlab.web.jsoup.Broadcast;
import com.yazlab.web.jsoup.Ibrahim.Question2.Keywords;
import com.yazlab.web.jsoup.Ibrahim.Question2.Question2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question3 {

    private List<Similarity> similarUrl = new ArrayList<>();
    public List<Similarity> similarity(String url1, String url2){

        List<Broadcast> broadcastList = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url2).get();
            Elements broadcasts = document.select("body");

            String textUrl2 = broadcasts.text();
            Question2 question2 = new Question2();
            List<Keywords> keywords = new ArrayList<>();
            keywords = question2.extractKeywords(url1);

            int i=0 ;
            while (i<5) {
                System.out.println(dist(textUrl2, keywords.get(i).getWord()));
                i++;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return  similarUrl;
    }

    public double dist( String x, String y ) {

        int[][] dp = new int[x.length() + 1][y.length() + 1];

        for (int i = 0; i <= x.length(); i++) {
            for (int j = 0; j <= y.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                }
                else if (j == 0) {
                    dp[i][j] = i;
                }
                else {
                    dp[i][j] = min(dp[i - 1][j - 1]
                                    + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)),
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1);
                }
            }
        }

        return dp[x.length()][y.length()]/x.length();
    }

    public static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    public static int min(int... numbers) {
        return Arrays.stream(numbers)
                .min().orElse(Integer.MAX_VALUE);
    }
}
