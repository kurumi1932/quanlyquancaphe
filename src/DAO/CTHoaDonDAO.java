/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connect.MyConnection;
import Model.CTHoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NHT_Kurumi
 */
public class CTHoaDonDAO {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    public List<CTHoaDon> getListCTHDbyMaHD(String mahd){
        List<CTHoaDon> CTHDlist = new ArrayList<CTHoaDon>();
        
        conn = MyConnection.getMyConnection();
        String sql = "SELECT * FROM cthoadon WHERE MaHD = ?";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1,mahd);
            rs = ps.executeQuery();
            
            while(rs.next()){
                CTHoaDon cthd = new CTHoaDon();             
                cthd.setMahd(rs.getString("MaHD"));
                cthd.setMamon(rs.getInt("MaMon"));
                cthd.setTenmon(rs.getString("TenMon"));
                cthd.setDvtinh(rs.getString("DVTinh"));
                cthd.setSoluong(rs.getInt("SoLuong"));
                cthd.setDongia(rs.getFloat("DonGia"));
                cthd.setThanhtien(rs.getFloat("ThanhTien"));
                
                CTHDlist.add(cthd);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return CTHDlist;
    }
    
    public CTHoaDon getCTHDbyMaHDAndMaMon(String mahd, int mamon){
        
        conn = MyConnection.getMyConnection();
        String sql = "SELECT * FROM cthoadon WHERE MaHD = ? and MaMon = ?";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1,mahd);
            ps.setInt(2, mamon);
            rs = ps.executeQuery();
            
            while(rs.next()){
                CTHoaDon cthd = new CTHoaDon();             
                cthd.setMahd(rs.getString("MaHD"));
                cthd.setMamon(rs.getInt("MaMon"));
                cthd.setTenmon(rs.getString("TenMon"));
                cthd.setDvtinh(rs.getString("DVTinh"));
                cthd.setSoluong(rs.getInt("SoLuong"));
                cthd.setDongia(rs.getFloat("DonGia"));
                cthd.setThanhtien(rs.getFloat("ThanhTien"));
                
                return cthd;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;// nếu không có dữ liệu
    }
    
    public void addCTHoaDon(CTHoaDon cthd){
        
        conn = MyConnection.getMyConnection();
        
        String sql = "INSERT INTO cthoadon(mahd, mamon, tenmon, dvtinh, soluong, dongia, thanhtien) VALUE(?,?,?,?,?,?,?)";
        
        try{
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, cthd.getMahd());
            ps.setInt(2, cthd.getMamon());
            ps.setString(3, cthd.getTenmon());
            ps.setString(4, cthd.getDvtinh());
            ps.setInt(5, cthd.getSoluong());
            ps.setFloat(6, cthd.getDongia());
            ps.setFloat(7, cthd.getThanhtien());
            
            int rs = ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateCTHoaDon(CTHoaDon cthd){
        
        conn = MyConnection.getMyConnection();
        String sql = "update cthoadon set soluong = ?, thanhtien = ? WHERE mahd = ? and mamon = ?";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(3, cthd.getMahd());
            ps.setInt(4, cthd.getMamon());
            ps.setInt(1, cthd.getSoluong());
            ps.setFloat(2, cthd.getThanhtien());
            
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteCTHoaDon(String mahd, int mamon){
        
        conn = MyConnection.getMyConnection();
        String sql = "DELETE FROM cthoadon WHERE MaHD = ? and MaMon =?";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, mahd);
            ps.setInt(2, mamon);
            ps.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
