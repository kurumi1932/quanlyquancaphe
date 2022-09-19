/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connect.MyConnection;
import Model.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author Admin
 */
public class ThongKeDAO {

    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public List<ThongKe> getListTKbyTD1() {
        List<ThongKe> TKlist = new ArrayList<ThongKe>();
        connection = MyConnection.getMyConnection();
        String sql = "call sp_tkhoadon1()";

        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ThongKe tk = new ThongKe();
                tk.setMamon(rs.getInt("MaMon"));
                tk.setTenmon(rs.getString("TenMon"));
                tk.setTongsoluong(rs.getInt("TongSoLuong"));
                tk.setDongia(rs.getFloat("DonGia"));
                tk.setTongthanhtien(rs.getFloat("TongThanhTien"));

                TKlist.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return TKlist;
    }

    public List<ThongKe> getListTKbyTD2(String gioden1, String gioden2) {
        List<ThongKe> TKlist = new ArrayList<ThongKe>();
        connection = MyConnection.getMyConnection();
        String sql = "call sp_tkhoadon2(?,?) ";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, gioden1);
            ps.setString(2, gioden2);
            rs = ps.executeQuery();

            while (rs.next()) {
                ThongKe tk = new ThongKe();
                tk.setMamon(rs.getInt("MaMon"));
                tk.setTenmon(rs.getString("TenMon"));
                tk.setTongsoluong(rs.getInt("TongSoLuong"));
                tk.setDongia(rs.getFloat("DonGia"));
                tk.setTongthanhtien(rs.getFloat("TongThanhTien"));

                TKlist.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return TKlist;
    }

    public ThongKe getTongTien1() {
        connection = MyConnection.getMyConnection();
        String sql = " call sp_tongtienHD1() ";

        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                ThongKe tk = new ThongKe();
                tk.setTongtien(rs.getFloat("Tongtieuthu"));
                return tk;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ThongKe getTongtien2(String gioden1, String gioden2) {
        connection = MyConnection.getMyConnection();
        String sql = "call sp_tongtienHD2(?,?) ";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, gioden1);
            ps.setString(2, gioden2);
            rs = ps.executeQuery();

            if (rs.next()) {
                ThongKe tk = new ThongKe();
                tk.setTongtien(rs.getFloat("Tongtieuthu"));
                return tk;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
