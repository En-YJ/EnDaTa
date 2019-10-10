package com.example.responsibilityhome;

public class RealEstateItem {
    private byte[] image;
    private String title;
    private String desc;

    public byte[] getImage() {
        return this.image;
    }
    public String getTitle() {
        return this.title;
    }
    public String getDesc() { return desc; }

    public RealEstateItem(byte[] image, String title, String desc) {
        this.image = image;
        this.title = title;
        this.desc = desc;
    }

}
