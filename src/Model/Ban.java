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
public class Ban {
    private int maban;
    private String tenban, trangthai;

    public Ban() {
    }

    public Ban(int maban, String tenban, String trangthai) {
        this.maban = maban;
        this.tenban = tenban;
        this.trangthai = trangthai;
    }

    public int getMaban() {
        return maban;
    }

    public void setMaban(int maban) {
        this.maban = maban;
    }

    public String getTenban() {
        return tenban;
    }

    public void setTenban(String tenban) {
        this.tenban = tenban;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
