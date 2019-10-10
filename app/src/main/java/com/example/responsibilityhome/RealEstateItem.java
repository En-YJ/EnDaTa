package com.example.responsibilityhome;

public class RealEstateItem {
    private byte[] image;
    private String title;
    private String charter;
    //private String monthly;

    public byte[] getImage() {
        return this.image;
    }
    public String getTitle() {
        return this.title;
    }
    public String getCharter() { return charter; }
    //public String getMonthly(){return monthly;}

    public RealEstateItem(byte[] image, String title, String charter) {
        this.image = image;
        this.title = title;
        this.charter = charter;
        //this.monthly = monthly;
    }

}
