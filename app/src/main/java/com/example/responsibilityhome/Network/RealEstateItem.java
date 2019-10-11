package com.example.responsibilityhome.Network;

public class RealEstateItem {
    private byte[] image;
    private String title;
    private String charter;
    private String monthly;
    private String dong;
    private String m2;
    private String buildYear;

    public byte[] getImage() {
        return this.image;
    }
    public String getTitle() {
        return this.title;
    }
    public String getCharter() { return charter; }
    public String getMonthly(){return monthly;}
    public String getDong(){return dong;}
    public String getM2(){return m2;}
    public String getBuildYear(){return buildYear;}

    //이미지, 아파트명, 보증금, 월세금, 법정동, 면적, 건축년도
    public RealEstateItem(byte[] image, String title, String charter, String monthly, String dong, String m2, String buildYear) {
        this.image = image;
        this.title = title;
        this.charter = charter;
        this.monthly = monthly;
        this.dong = dong;
        this.m2 = m2;
        this.buildYear = buildYear;
    }

}
