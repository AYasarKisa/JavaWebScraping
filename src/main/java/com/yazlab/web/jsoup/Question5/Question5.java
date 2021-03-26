package com.yazlab.web.jsoup.Question5;

import com.yazlab.web.jsoup.Question2.Keywords;
import com.yazlab.web.jsoup.Question2.Question2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Question5 {

    private HashMap<String, String> synonymsList = new HashMap<>();
    private List<Keywords> keywords = new ArrayList<>();
    private List<Synonyms> synonyms = new ArrayList<>();
    private String []words = new String[7];
    private String []sWords = new String[7];

    public List<Synonyms> synonymsWords(String mainUrl, List<String> urlList){

        readFiles();

        findKeywords(mainUrl);
        findSynonyms();

        return synonyms;
    }

    public void findKeywords(String mainUrl){
        Question2 question2 = new Question2();
        keywords = question2.extractKeywords(mainUrl);
    }

    public void readFiles(){
        File f = new File("src/main/java/com/yazlab/web/jsoup/Question5/kelime.txt");
        Scanner file = null;
        try {
            file = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] vocabulary;
        while(file.hasNextLine()) {
            vocabulary=file.nextLine().split("\\s+");
            if(vocabulary.length!=2 ){
                continue;
            }
            else{
                if(synonymsList.get(vocabulary[0])==null){
                    synonymsList.put(vocabulary[0].trim().toLowerCase(),vocabulary[1].trim().toLowerCase());
                }
            }
        }
    }

    public void findSynonyms(){
        String []words = new String[7];
        for(int i=0; i<7; i++){
            words[i] = keywords.get(i).getWord();
        }

        for(int i=0;i<words.length;i++){
            if(synonymsList.get(words[i]) != null){
                sWords[i] = synonymsList.get(words[i]);
            }
            else{
                sWords[i] = words[i];
            }
            System.out.println(words[i] + " " + sWords[i]);
        }
    }
}
