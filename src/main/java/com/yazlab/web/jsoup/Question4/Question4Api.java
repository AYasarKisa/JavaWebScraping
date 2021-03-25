package com.yazlab.web.jsoup.Question4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api1/soru4")
public class Question4Api {

    @GetMapping("/indexandsort")
    public List<UrlTree> get(@RequestParam String mainUrl, @RequestParam List<String> urlSet){
        System.out.println("Question4 Api Run\n\n\n");
        Question4 question4=new Question4();
       return question4.indexlemeYap(mainUrl,urlSet);
    }
}
