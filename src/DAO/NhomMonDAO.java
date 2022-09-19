/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connect.MyConnection;
import Model.NhomMon;
import java.sql.*;
import java.util.*;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author NHT_Kurumi
 */
public class NhomMonDAO {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public List<NhomMon> getListNhomMon() {
        List<NhomMon> listNhomMon = new ArrayList<NhomMon>();

        conn = MyConnection.getMyConnection();
        String sql = "Select * From nhommon where trangthai=1";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                NhomMon nhommon = new NhomMon();
                nhommon.setManhom(rs.getInt("MaNhom"));
                nhommon.setTenNhom(rs.getString("TenNhom"));

                listNhomMon.add(nhommon);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi !");
        }
        return listNhomMon;
    }

    public NhomMon getNMByMaNhom(int manhom) {

        conn = MyConnection.getMyConnection();
        String sql = "SELECT * FROM nhommon WHERE manhom = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, manhom);
            rs = ps.executeQuery();

            if (rs.next()) {
                NhomMon nhommon = new NhomMon();
                nhommon.setManhom(rs.getInt("MaNhom"));
                nhommon.setTenNhom(rs.getString("TenNhom"));

                return nhommon;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;// nếu không có dữ liệu
    }

    public NhomMon getNMByTenNhom(String tennhom) {

        conn = MyConnection.getMyConnection();
        String sql = "SELECT * FROM nhommon WHERE tennhom = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, tennhom);
            rs = ps.executeQuery();

            if (rs.next()) {
                NhomMon nhommon = new NhomMon();
                nhommon.setManhom(rs.getInt("MaNhom"));
                nhommon.setTenNhom(rs.getString("TenNhom"));

                return nhommon;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;// nếu không có dữ liệu
    }

    public void addNhomMon(NhomMon nhommon) {

        conn = MyConnection.getMyConnection();
        String sql = "INSERT INTO nhommon(TenNhom) VALUE(?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, nhommon.getTenNhom());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNhomMon(NhomMon nhommon) {

        conn = MyConnection.getMyConnection();
        String sql = "update nhommon set TenNhom = ?  WHERE MaNhom = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(2, nhommon.getManhom());
            ps.setString(1, nhommon.getTenNhom());

            int rs = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteNhomMon(int manhom) {

        conn = MyConnection.getMyConnection();
        String sql = "update nhommon set TrangThai = 0 WHERE MaNhom = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, manhom);
            int rs = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
