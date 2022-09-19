/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connect.MyConnection;
import Model.ThucDon;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author NHT_Kurumi
 */
public class ThucDonDAO {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public List<ThucDon> getListThucDon() {
        List<ThucDon> listThucDon = new ArrayList<ThucDon>();

        conn = MyConnection.getMyConnection();
        String sql = "Select * From thucdon where Trangthai='1'";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ThucDon thucdon = new ThucDon();
                thucdon.setMamon(rs.getInt("MaMon"));
                thucdon.setTenmon(rs.getString("TenMon"));
                thucdon.setManhom(rs.getInt("MaNhom"));
                thucdon.setDvtinh(rs.getString("DVTinh"));
                thucdon.setDongia(rs.getFloat("DonGia"));
               
                listThucDon.add(thucdon);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi !");
        } 
        return listThucDon;
    }
    
    public ThucDon getTDByMaMon(int mamon){
        
        conn = MyConnection.getMyConnection();
        String sql = "SELECT * FROM thucdon WHERE MaMon = ?";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1,mamon);
            rs = ps.executeQuery();
            
            if(rs.next()){
                ThucDon thucdon = new ThucDon();               
                thucdon.setMamon(rs.getInt("MaMon"));
                thucdon.setTenmon(rs.getString("TenMon"));
                thucdon.setManhom(rs.getInt("MaNhom"));
                thucdon.setDvtinh(rs.getString("DVTinh"));
                thucdon.setDongia(rs.getFloat("DonGia"));
               
                return thucdon;
            }
       
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;// nếu không có dữ liệu
    }
    
    public List<ThucDon> getListTDByMaMon(int mamon){
        List<ThucDon> listThucDon = new ArrayList<ThucDon>();
        
        conn = MyConnection.getMyConnection();
        String sql = " SELECT * FROM thucdon WHERE Trangthai='1' and mamon like ?";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, mamon);
            rs = ps.executeQuery();
            
            while(rs.next()){
               ThucDon thucdon = new ThucDon();               
                thucdon.setMamon(rs.getInt("MaMon"));
                thucdon.setManhom(rs.getInt("MaNhom"));
                thucdon.setTenmon(rs.getString("TenMon"));
                thucdon.setDvtinh(rs.getString("DVTinh"));
                thucdon.setDongia(rs.getFloat("DonGia"));
               
                listThucDon.add(thucdon);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return listThucDon;
    }
    
    public List<ThucDon> getListTDByTenMon(String tenmon){
        List<ThucDon> listThucDon = new ArrayList<ThucDon>();
        
        conn = MyConnection.getMyConnection();
        String sql = " SELECT * FROM thucdon WHERE Trangthai='1' and TenMon LIKE ?";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, tenmon);
            rs = ps.executeQuery();
            
            while(rs.next()){
               ThucDon thucdon = new ThucDon();               
                thucdon.setMamon(rs.getInt("MaMon"));
                thucdon.setManhom(rs.getInt("MaNhom"));
                thucdon.setTenmon(rs.getString("TenMon"));
                thucdon.setDvtinh(rs.getString("DVTinh"));
                thucdon.setDongia(rs.getFloat("DonGia"));
               
                listThucDon.add(thucdon);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return listThucDon;
    }
    
    public List<ThucDon> getListTDByMaNhom(int manhom){
        List<ThucDon> listThucDon = new ArrayList<ThucDon>();
        
        conn = MyConnection.getMyConnection();
        String sql = " SELECT * FROM thucdon WHERE Trangthai='1' and manhom = ?";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, manhom);
            rs = ps.executeQuery();
            
            while(rs.next()){
               ThucDon thucdon = new ThucDon();               
                thucdon.setMamon(rs.getInt("MaMon"));
                thucdon.setManhom(rs.getInt("MaNhom"));
                thucdon.setTenmon(rs.getString("TenMon"));
                thucdon.setDvtinh(rs.getString("DVTinh"));
                thucdon.setDongia(rs.getFloat("DonGia"));
               
                listThucDon.add(thucdon);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return listThucDon;
    }
    
    public void addThucDon(ThucDon thucdon){
        
        conn = MyConnection.getMyConnection();
        String sql = "INSERT INTO thucdon(TenMon,MaNhom,DVTinh,DonGia) VALUE(?,?,?,?)";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, thucdon.getTenmon());
            ps.setInt(2, thucdon.getManhom());
            ps.setString(3, thucdon.getDvtinh());
            ps.setFloat(4, thucdon.getDongia());
            
            int rs = ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateThucDon(ThucDon thucdon){
        
        conn = MyConnection.getMyConnection();
        String sql = "update thucdon set  TenMon = ?, MaNhom = ?, DVTinh = ? ,DonGia = ? WHERE MaMon = ?";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(5, thucdon.getMamon());
            ps.setString(1, thucdon.getTenmon());
            ps.setInt(2 , thucdon.getManhom());
            ps.setString(3, thucdon.getDvtinh());
            ps.setFloat(4, thucdon.getDongia());
            
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteThucDon(ThucDon thucdon){
        
        conn = MyConnection.getMyConnection();
        String sql = "update thucdon set TrangThai = 0 WHERE MaMon = ?";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, thucdon.getMamon());
            
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}