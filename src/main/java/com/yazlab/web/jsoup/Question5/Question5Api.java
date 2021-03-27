package com.yazlab.web.jsoup.Question5;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api1/soru5")
public class Question5Api {

    @GetMapping("/synonyms")
    public ArrayList<Object> get(@RequestParam String mainUrl, @RequestParam List<String> urlSet){
        System.out.println("Question5 Api Run\n\n\n");
        Question5 question5=new Question5();
        return question5.synonymsWords(mainUrl,urlSet);
    }

}
