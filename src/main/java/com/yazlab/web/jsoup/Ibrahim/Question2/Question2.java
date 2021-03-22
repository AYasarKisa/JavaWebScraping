package com.yazlab.web.jsoup.Ibrahim.Question2;

import com.yazlab.web.jsoup.Broadcast;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Question2 {

    static int numberOfSentences=0;
    private List<Keywords> keywords = new ArrayList<>();
    private ArrayList<String> sentences = new ArrayList<>();

    public List<Keywords> extractKeywords(String url){
        List<Broadcast> broadcastList = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements broadcasts = document.select("body");

            //paragraph into sentences regex
            Pattern re = Pattern.compile("[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)", Pattern.MULTILINE | Pattern.COMMENTS);
            Matcher reMatcher = re.matcher(broadcasts.text());
            while (reMatcher.find()) {
                sentences.add(reMatcher.group().toString().toLowerCase());
            }
            numberOfSentences = sentences.size();

            for(int i=0 ; i<numberOfSentences ; i++){
                findRepetition(sentences.get(i));
            }

            topElements();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return keywords;
    }

    public void findRepetition(String sentence){
        String[] words = sentence.split("\\s+");
        HashMap<String,Integer> keywordList = new HashMap<String, Integer>();

        //without turkish char
        /*for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll("[^\\w]", "");
        } */
        int sentenceLength = words.length;
        int reWord=0;
        for(String word: words){
            word = word.toLowerCase();
            if(keywordList.get(word) == null){
                keywordList.put(word,1);
            }
            else{
                int value =(int)keywordList.get(word);
                ++value;
                keywordList.replace(word, value);
            }
            for(String s: sentences){
                if(s.contains(word)) reWord++;
            }
        }
        double TF, IDF;
        for (Map.Entry list : keywordList.entrySet()) {
            TF = (int)list.getValue()/(float)sentenceLength;
            IDF = (double) Math.log10(numberOfSentences / (int)reWord);
            keywords.add(new Keywords(list.getKey().toString(),list.getValue().toString(), (TF*IDF)));
        }
    }

    public void topElements(){
        int n = keywords.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (keywords.get(j).getKeywords() < keywords.get(j+1).getKeywords())
                {
                    // swap arr[j+1] and arr[j]
                    Keywords temp = keywords.get(j);
                    keywords.set(j,keywords.get(j+1));
                    keywords.set(j+1, temp);
                }
    }

}
