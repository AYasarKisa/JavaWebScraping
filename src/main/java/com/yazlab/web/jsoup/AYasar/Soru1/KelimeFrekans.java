package com.yazlab.web.jsoup.AYasar.Soru1;

public class KelimeFrekans {
    private String kelime;
    private int freakans;

    public KelimeFrekans(String kelime){
        this.kelime=kelime;
        this.freakans=0;
    }
    public void freakansArtir(){ freakans++;}
    public String getKelime() {
        return kelime;
    }

    public void setKelime(String kelime) {
        this.kelime = kelime;
    }

    public int getFreakans() {
        return freakans;
    }

    public void setFreakans(int freakans) {
        this.freakans = freakans;
    }
}
