package com.yazlab.web.jsoup.Ibrahim.Question2;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api1/soru2")
public class Question2Api {

    @GetMapping("/keywords")
    public List<Keywords> get(@RequestParam("url") String url){
        Question2 question2 = new Question2();
        return question2.extractKeywords(url);
    }
}
