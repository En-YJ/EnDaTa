package com.example.responsibilityhome.Model;

import java.util.ArrayList;

public class DataProvision {
    private String company;
    private ArrayList<DataElement> datas;

    public DataProvision(String company, ArrayList<DataElement> datas) {
        this.company = company;
        this.datas = datas;
    }

    @Override
    public String toString() {
        String str = "";
        for(int i=0; i<datas.size(); i++) {
            str += datas.get(i).name;
            if (i != datas.size() - 1)
                str += ", ";
        }
        return str;
    }

    public String getCompany() {
        return company;
    }


    public void setCompany(String company) {
        this.company = company;
    }

    public ArrayList<DataElement> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<DataElement> datas) {
        this.datas = datas;
    }
}

