package com.yazlab.web.jsoup;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Api {

    @GetMapping("/test")
    public List<Broadcast> get(){
        //selam
        Test test=new Test();
        System.out.println("Ratatat");
        return test.deneme();
    }
}
