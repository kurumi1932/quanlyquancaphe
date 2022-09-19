/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author NHT_Kurumi
 */
public class ThucDon {
    private int mamon, manhom;
    private String tenmon, dvtinh, trangthai; 
    private float dongia;

    public ThucDon() {
    }

    public ThucDon(int mamon, int manhom, String tenmon, String dvtinh, float dongia, String trangthai) {
        this.mamon = mamon;
        this.manhom = manhom;
        this.tenmon = tenmon;
        this.dvtinh = dvtinh;
        this.dongia = dongia;
        this.trangthai = trangthai;
    }


    public int getManhom() {
        return manhom;
    }

    public void setManhom(int manhom) {
        this.manhom = manhom;
    }

    public int getMamon() {
        return mamon;
    }

    public void setMamon(int mamon) {
        this.mamon = mamon;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public String getDvtinh() {
        return dvtinh;
    }

    public void setDvtinh(String dvtinh) {
        this.dvtinh = dvtinh;
    }

    public float getDongia() {
        return dongia;
    }

    public void setDongia(float dongia) {
        this.dongia = dongia;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
    
}
