/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.NhanVien;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Connect.MyConnection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trung
 */
public class NhanVienDAO {

    public static List<NhanVien> findAll() {
        List<NhanVien> nhanvienList = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "SELECT * FROM nhanvien";
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString("MaNV"), rs.getString("TenNV"), rs.getString("GioiTinh"),
                        rs.getString("Sdt"), rs.getString("DiaChi"), rs.getString("TrangThai"), rs.getDate("NgaySinh"));
                nhanvienList.add(nv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return nhanvienList;
    }

    public static void insert(NhanVien nv) {
        Connection conn = null;

        PreparedStatement prestatement = null;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "INSERT INTO nhanvien(MaNV,TenNV,GioiTinh,NgaySinh,Sdt,DiaChi,TrangThai) values(?,?,?,?,?,?,?)";
            prestatement = conn.prepareCall(sql);
            prestatement.setString(1, nv.getManv());
            prestatement.setString(2, nv.getTennv());
            prestatement.setString(3, nv.getGioitinh());
            prestatement.setDate(4, nv.getNgaysinh());
            prestatement.setString(5, nv.getSdt());
            prestatement.setString(6, nv.getDiachi());
            prestatement.setString(7, nv.getTrangthai());

            prestatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (prestatement != null) {
                try {
                    prestatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void update(NhanVien nv) {
        Connection conn = null;

        PreparedStatement prestatement = null;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "UPDATE nhanvien SET TenNV=?,GioiTinh=?,NgaySinh=?,Sdt=?,DiaChi=?,TrangThai=? WHERE MaNV= ?";
            prestatement = conn.prepareCall(sql);
            prestatement.setString(7, nv.getManv());
            prestatement.setString(1, nv.getTennv());
            prestatement.setString(2, nv.getGioitinh());
            prestatement.setDate(3, nv.getNgaysinh());
            prestatement.setString(4, nv.getSdt());
            prestatement.setString(5, nv.getDiachi());
            prestatement.setString(6, nv.getTrangthai());

            prestatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (prestatement != null) {
                try {
                    prestatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void delete(String MaNV) {
        Connection conn = null;

        PreparedStatement prestatement = null;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "DELETE FROM nhanvien WHERE MaNV= ?";
            prestatement = conn.prepareCall(sql);
            prestatement.setString(1, MaNV);

            prestatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (prestatement != null) {
                try {
                    prestatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static List<NhanVien> findbyTenNV(String TenNV) {
        List<NhanVien> nhanvienList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement prestatement = null;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "SELECT * FROM nhanvien WHERE TenNV like ?";
            prestatement = conn.prepareStatement(sql);
            prestatement.setString(1, "%" + TenNV + "%");
            ResultSet rs = prestatement.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setManv(rs.getString("MaNV"));
                nv.setTennv(rs.getString("TenNV"));
                nv.setGioitinh(rs.getString("GioiTinh"));
                nv.setSdt(rs.getString("Sdt"));
                nv.setDiachi(rs.getString("DiaChi"));
                nv.setTrangthai(rs.getString("TrangThai"));
                nv.setNgaysinh(rs.getDate("NgaySinh"));
                nhanvienList.add(nv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (prestatement != null) {
                try {
                    prestatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return nhanvienList;
    }

    public NhanVien getNhanVienbyMaNV(String MaNV) {

        Connection conn = null;
        PreparedStatement prestatement = null;

        try {
            conn = MyConnection.getMyConnection();
            String sql = "SELECT * FROM nhanvien WHERE MaNV = ?";
            prestatement = conn.prepareStatement(sql);
            prestatement.setString(1, MaNV);
            ResultSet rs = prestatement.executeQuery();

            if (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setManv(rs.getString("MaNV"));
                nv.setTennv(rs.getString("TenNV"));
                nv.setGioitinh(rs.getString("GioiTinh"));
                nv.setSdt(rs.getString("Sdt"));
                nv.setDiachi(rs.getString("DiaChi"));
                nv.setTrangthai(rs.getString("TrangThai"));
                nv.setNgaysinh(rs.getDate("NgaySinh"));
                return nv;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;// nếu không có dữ liệu
    }
}
