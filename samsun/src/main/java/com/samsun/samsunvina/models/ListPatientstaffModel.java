package com.samsun.samsunvina.models;



public class ListPatientstaffModel {
 private String chart_no;
 private String name;
 private String created_date;
 private String clinic;

    public ListPatientstaffModel() {
    }


    public ListPatientstaffModel(String chart_no, String name, String created_date, String clinic) {
        this.chart_no = chart_no;
        this.name = name;
        this.created_date = created_date;
        this.clinic = clinic;
    }

    public String getChart_no() {
        return chart_no;
    }

    public void setChart_no(String chart_no) {
        this.chart_no = chart_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }
}
