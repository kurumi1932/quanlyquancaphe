/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connect.MyConnection;
import Model.Ban;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author NHT_Kurumi
 */
public class BanDAO{

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public List<Ban> getListBan(int maban) {
        List<Ban> listBan = new ArrayList<Ban>();

        conn = MyConnection.getMyConnection();

        String sql;
        if (maban == 0) {
            sql = "Select * From ban where trangthai2=1";
        } else {
            sql = "Select * From ban Where MaBan = '"+maban+"'";
        }

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Ban ban = new Ban();

                ban.setMaban(rs.getInt("MaBan"));
                ban.setTenban(rs.getString("TenBan"));
                ban.setTrangthai(rs.getString("TrangThai"));

                listBan.add(ban);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi !");
        } 
        return listBan;
    }
    
    public Ban getBanByMaBan(int maban){
        
        conn = MyConnection.getMyConnection();
        String sql = "SELECT * FROM ban WHERE MaBan = ?";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1,maban);
            rs = ps.executeQuery();
            
            if(rs.next()){
                Ban ban = new Ban();               
                ban.setMaban(rs.getInt("MaBan"));
                ban.setTenban(rs.getString("TenBan"));
                ban.setTrangthai(rs.getString("TrangThai"));
                
                return ban;
            }
       
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;// nếu không có dữ liệu
    }
    
    public List<Ban> getListBanByTenBan(String tenban){
        List<Ban> listBan = new ArrayList<Ban>();
        
        conn = MyConnection.getMyConnection();
        String sql = " SELECT * FROM ban WHERE TenBan LIKE ? and trangthai2=1";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, tenban);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Ban ban = new Ban();
                
                ban.setMaban(rs.getInt("MaBan"));
                ban.setTenban(rs.getString("TenBan"));
                ban.setTrangthai(rs.getString("TrangThai"));
                
                listBan.add(ban);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return listBan;
    }
    
    public void addBan(Ban ban){
        
        conn = MyConnection.getMyConnection();
        String sql = "INSERT INTO ban(TenBan) VALUE(?)";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, ban.getTenban());
            
            int rs = ps.executeUpdate();
            
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateBan(Ban ban){
        
        conn = MyConnection.getMyConnection();
        String sql = "update ban set TenBan = ?, TrangThai = ? WHERE MaBan = ?";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(3, ban.getMaban());
            ps.setString(1, ban.getTenban());
            ps.setString(2, ban.getTrangthai());
            
            int rs = ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteBan(int maban){
        
        conn = MyConnection.getMyConnection();
        String sql = "update ban set TrangThai2 = 0 WHERE MaBan = ?";
        
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, maban);            
            int rs = ps.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}