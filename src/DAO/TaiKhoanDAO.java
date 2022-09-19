/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connect.MyConnection;
import Model.TaiKhoan;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NHT_Kurumi
 */
public class TaiKhoanDAO {

    public static List<TaiKhoan> findAll() {
        List<TaiKhoan> taikhoanList = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "SELECT * FROM taikhoan";
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(rs.getString("UserName"), rs.getString("Password"), rs.getInt("Level"));
                taikhoanList.add(tk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return taikhoanList;
    }

    public static void insert(TaiKhoan tk) {
        Connection conn = null;

        PreparedStatement prestatement = null;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "INSERT INTO taikhoan(UserName,Password,Level) values(?,?,?)";
            prestatement = conn.prepareCall(sql);
            prestatement.setString(1, tk.getUsername());
            prestatement.setString(2, tk.getPassword());
            prestatement.setInt(3, tk.getLevel());

            prestatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (prestatement != null) {
                try {
                    prestatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void update(TaiKhoan tk) {
        Connection conn = null;

        PreparedStatement prestatement = null;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "UPDATE taikhoan SET  Password=?, Level = ? WHERE UserName= ?";
            prestatement = conn.prepareCall(sql);
            prestatement.setString(3, tk.getUsername());
            prestatement.setString(1, tk.getPassword());
            prestatement.setInt(2, tk.getLevel());
            prestatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (prestatement != null) {
                try {
                    prestatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void delete(String UserName) {
        Connection conn = null;

        PreparedStatement prestatement = null;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "DELETE FROM taikhoan WHERE UserName= ?";
            prestatement = conn.prepareCall(sql);
            prestatement.setString(1, UserName);

            prestatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (prestatement != null) {
                try {
                    prestatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static List<TaiKhoan> findbyUserName(String UserName) {
        List<TaiKhoan> taikhoanList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement prestatement = null;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "SELECT * FROM taikhoan WHERE UserName like ?";
            prestatement = conn.prepareStatement(sql);
            prestatement.setString(1, "%" + UserName + "%");
            ResultSet rs = prestatement.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setUsername(rs.getString("UserName"));
                tk.setPassword(rs.getString("Password"));
                tk.setLevel(rs.getInt("Level"));
                taikhoanList.add(tk);

            }
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (prestatement != null) {
                try {
                    prestatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return taikhoanList;
    }

    public TaiKhoan getTaiKhoanbyUsername(String UserName) {

        Connection conn = null;
        PreparedStatement prestatement = null;

        try {
            conn = MyConnection.getMyConnection();
            String sql = "SELECT * FROM taikhoan WHERE UserName = ?";
            prestatement = conn.prepareStatement(sql);
            prestatement.setString(1, UserName);
            ResultSet rs = prestatement.executeQuery();

            if (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setUsername(rs.getString("UserName"));
                tk.setPassword(rs.getString("Password"));
                tk.setLevel(rs.getInt("Level"));
                return tk;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;// nếu không có dữ liệu
    }
}
