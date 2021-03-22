package com.yazlab.web.jsoup.AYasar.Soru1;


import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api1/soru1")
public class Soru1Api {

    //api su anda calisiyor
    @GetMapping("/frekansbul")
    public List<KelimeFrekans> get(@RequestParam("url") String url){
        Soru1 soru=new Soru1();
        return soru.freakansBul(url);
    }
}
