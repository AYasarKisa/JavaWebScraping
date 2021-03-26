package com.yazlab.web.jsoup;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {

        public void testEt() throws IOException, JSONException {
            File f = new File("src/main/java/com/yazlab/web/jsoup/kelime.txt");
            Scanner dosya = new Scanner(f);
            HashMap<String,String> esAnlam=new HashMap<>();
            SimpleDateFormat formatter2= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date2 = new Date(System.currentTimeMillis());


            String[] kelime;
            while(dosya.hasNextLine()) {
                kelime=dosya.nextLine().split("\\s+");
                if(kelime.length!=2 ){
                    continue;
                }
                else{
                    System.out.println(kelime[0]+" "+kelime[1]);
                    if(esAnlam.get(kelime[0])==null){
                        esAnlam.put(kelime[0].trim().toLowerCase(),kelime[1].trim().toLowerCase());
                    }


                }
            }

            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            System.out.println(formatter2.format(date2));
            System.out.println(formatter.format(date));


        }
    }

