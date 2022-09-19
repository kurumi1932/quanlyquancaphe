/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author NHT_Kurumi
 */
public class HoaDon {
    private String mahd, trangthai, tennv;
    private int maban;
    private Timestamp gioden, giocapnhat;
    private float tongtien;

    public HoaDon() {
    }

    public HoaDon(String mahd, String trangthai, String tennv, int maban, Timestamp gioden, Timestamp giocapnhat, float tongtien) {
        this.mahd = mahd;
        this.trangthai = trangthai;
        this.tennv = tennv;
        this.maban = maban;
        this.gioden = gioden;
        this.giocapnhat = giocapnhat;
        this.tongtien = tongtien;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public int getMaban() {
        return maban;
    }

    public void setMaban(int maban) {
        this.maban = maban;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public Timestamp getGioden() {
        return gioden;
    }

    public void setGioden(Timestamp gioden) {
        this.gioden = gioden;
    }

    public Timestamp getGiocapnhat() {
        return giocapnhat;
    }

    public void setGiocapnhat(Timestamp giocapnhat) {
        this.giocapnhat = giocapnhat;
    }

    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }

    
    
}
