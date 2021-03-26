package com.yazlab.web.jsoup.Question5;

import com.yazlab.web.jsoup.Question2.Keywords;
import com.yazlab.web.jsoup.Question2.Question2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Question5 {

    private HashMap<String, ArrayList<String>> synonymsList = new HashMap<>();
    private List<Keywords> keywords = new ArrayList<>();
    private List<Synonyms> synonyms = new ArrayList<>();
    private String []words = new String[7];
    private HashMap<String,ArrayList<String>> sWords = new HashMap<>();

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
                    synonymsList.put(vocabulary[0].trim().toLowerCase(),new ArrayList<String>());
                }
                synonymsList.get(vocabulary[0]).add(vocabulary[1]);
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
                sWords.put(words[i],synonymsList.get(words[i]));
            }
            else{
                sWords.put(words[i],new ArrayList<>());
                sWords.get(words[i]).add(words[i]);
            }
        }

        for(Map.Entry<String, ArrayList<String>> entry : sWords.entrySet()) {
            String key = entry.getKey();
            ArrayList value = entry.getValue();
            for(int i=0; i<value.size(); i++){
                System.out.println("key:"+key+" value:"+value.get(i));
            }
        }
    }
}
