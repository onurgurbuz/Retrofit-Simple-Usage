package com.retrofitdeneme;

import com.google.gson.annotations.SerializedName;

public class LoginPOJO {

    @SerializedName("error")
    public Boolean error;
    @SerializedName("ID")
    public String id;
    @SerializedName("Ad")
    public String ad;
    @SerializedName("Soyad")
    public String soyad;
    @SerializedName("Kod")
    public String kod;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }
}