package com.yazlab.web.jsoup.Question3;

import com.yazlab.web.jsoup.Ibrahim.Question2.Keywords;
import com.yazlab.web.jsoup.Ibrahim.Question2.Question2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api1/soru3")
public class Question3Api {

    @GetMapping("/similarity")
    public List<Similarity> get(@RequestParam("url1") String url1, @RequestParam("url2") String url2){
        Question3 question3 = new Question3();
        return question3.similarity(url1,url2);
    }
}
