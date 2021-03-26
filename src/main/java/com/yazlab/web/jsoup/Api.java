package com.yazlab.web.jsoup;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/api")
public class Api {

    @GetMapping("/test")
    public String  get(@RequestParam String url) throws IOException {

        Test test=new Test();
        test.testEt();
        return "Ratata";
    }


}
