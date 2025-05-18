package com.example.buszjegy;

public class JegyModelClass {
    Integer id;
    String viszonylat;
    String ar;

    public JegyModelClass(String viszonylat, String ar) {
        this.viszonylat = viszonylat;
        this.ar = ar;
    }

    public JegyModelClass(Integer id, String viszonylat, String ar) {
        this.id = id;
        this.viszonylat = viszonylat;
        this.ar = ar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getViszonylat() {
        return viszonylat;
    }

    public void setViszonylat(String viszonylat) {
        this.viszonylat = viszonylat;
    }

    public String getAr() {
        return ar;
    }

    public void setAr(String ar) {
        this.ar = ar;
    }
}
