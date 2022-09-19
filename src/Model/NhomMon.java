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
public class NhomMon {
    private int manhom;
    private String TenNhom;

    public NhomMon() {
    }

    public NhomMon(int manhom, String TenNhom) {
        this.manhom = manhom;
        this.TenNhom = TenNhom;
    }

    public int getManhom() {
        return manhom;
    }

    public void setManhom(int manhom) {
        this.manhom = manhom;
    }

    public String getTenNhom() {
        return TenNhom;
    }

    public void setTenNhom(String TenNhom) {
        this.TenNhom = TenNhom;
    }
    
    
}
