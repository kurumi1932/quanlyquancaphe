/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.Date;
/**
 *
 * @author Admin
 */
public class ThongKe extends ThucDon {
    private Date ngay;
    private int tongsoluong;
    private float tongthanhtien;
    private float tongtien;

    public ThongKe() {
    }

    public ThongKe(Date ngay, int tongsoluong, float tongthanhtien, float tongtien, int mamon, int manhom, String tenmon, String dvtinh, float dongia, String trangthai) {
        super(mamon, manhom, tenmon, dvtinh, dongia, trangthai);
        this.ngay = ngay;
        this.tongsoluong = tongsoluong;
        this.tongthanhtien = tongthanhtien;
        this.tongtien = tongtien;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int getTongsoluong() {
        return tongsoluong;
    }

    public void setTongsoluong(int tongsoluong) {
        this.tongsoluong = tongsoluong;
    }

    public float getTongthanhtien() {
        return tongthanhtien;
    }

    public void setTongthanhtien(float tongthanhtien) {
        this.tongthanhtien = tongthanhtien;
    }

    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }

    
}
