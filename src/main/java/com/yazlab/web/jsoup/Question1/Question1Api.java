package com.yazlab.web.jsoup.Question1;


import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api1/soru1")
public class Question1Api {

    //api su anda calisiyor
    @GetMapping("/frekansbul")
    public List<WordFrequency> get(@RequestParam("url") String url){
        System.out.println("Question1 Api Run\n\n\n");

        Question1 soru=new Question1();
        return soru.freakansBul(url);
    }
}
