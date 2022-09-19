/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.HoaDon;

import View.*;
import DAO.*;
import Model.*;
import java.sql.Timestamp;
import java.text.*;
import java.time.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NHT_Kurumi
 */
public class jp_HoaDon extends javax.swing.JPanel {

    HoaDonDAO hoadonDAO;
    CTHoaDonDAO cthoadonDAO;
    private DefaultTableModel ModelHD;
    private DefaultTableModel ModelCTHD;
    NumberFormat tienfm = new DecimalFormat("#,###,###");
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public jp_HoaDon() {
        initComponents();
        LamMoi();

        txtSearchMaHD.setToolTipText("Nhập mã hóa đơn!");
        inputDay1.setToolTipText("dd/mm/yyyy");
        inputDay2.setToolTipText("dd/mm/yyyy");

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        inputDay1.setDate(date);
        inputDay2.setDate(date);
        SearchHDbyDay();
    }

    private void TableHD() {
        hoadonDAO = new HoaDonDAO();

        ModelHD = new DefaultTableModel() {
            @Override //Không cho người dùng edit table
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableHD.setModel(ModelHD);
        ModelHD.addColumn("Mã HD");
        ModelHD.addColumn("Mã bàn");
        ModelHD.addColumn("Giờ đến");
        ModelHD.addColumn("Giờ cập nhật");
        ModelHD.addColumn("Tổng tiền");
        ModelHD.addColumn("Trạng thái");

        tableHD.getColumnModel().getColumn(0).setMinWidth(155);
        tableHD.getColumnModel().getColumn(0).setMaxWidth(155);
        tableHD.getColumnModel().getColumn(1).setMinWidth(50);
        tableHD.getColumnModel().getColumn(1).setMaxWidth(50);
        tableHD.getColumnModel().getColumn(2).setMinWidth(150);
        tableHD.getColumnModel().getColumn(2).setMaxWidth(150);
        tableHD.getColumnModel().getColumn(3).setMinWidth(150);
        tableHD.getColumnModel().getColumn(3).setMaxWidth(150);
        tableHD.getColumnModel().getColumn(4).setMinWidth(130);
        tableHD.getColumnModel().getColumn(4).setMaxWidth(130);
        tableHD.getColumnModel().getColumn(5).setMinWidth(100);
        tableHD.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        setDataHDtable(hoadonDAO.getListHD());
    }

    private void setDataHDtable(List<HoaDon> HDlist) {
        for (HoaDon hd : HDlist) {
            ModelHD.addRow(new Object[]{
                hd.getMahd(), hd.getMaban(), df.format(hd.getGioden()), df.format(hd.getGiocapnhat()),
                tienfm.format(hd.getTongtien()), hd.getTrangthai()
            });
        }
    }

    private void TableCTHD() {
        cthoadonDAO = new CTHoaDonDAO();
        ModelCTHD = new DefaultTableModel() {
            @Override //Không cho người dùng edit table
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableCTHD.setModel(ModelCTHD);
        ModelCTHD.addColumn("Mã HD");
        ModelCTHD.addColumn("Mã SP");
        ModelCTHD.addColumn("STT");
        ModelCTHD.addColumn("Tên món");
        ModelCTHD.addColumn("Đơn vị");
        ModelCTHD.addColumn("Số lượng");
        ModelCTHD.addColumn("Đơn giá");
        ModelCTHD.addColumn("Thành tiền");

        //Giảm đọ dài cột đầu tiên của tableCTHD về 0
        tableCTHD.getColumnModel().getColumn(0).setMinWidth(0);
        tableCTHD.getColumnModel().getColumn(0).setMaxWidth(0);
        tableCTHD.getColumnModel().getColumn(1).setMinWidth(0);
        tableCTHD.getColumnModel().getColumn(1).setMaxWidth(0);
        tableCTHD.getColumnModel().getColumn(2).setMinWidth(50);
        tableCTHD.getColumnModel().getColumn(2).setMaxWidth(50);
        tableCTHD.getColumnModel().getColumn(3).setMinWidth(285);
        tableCTHD.getColumnModel().getColumn(3).setMaxWidth(285);
        tableCTHD.getColumnModel().getColumn(4).setMinWidth(135);
        tableCTHD.getColumnModel().getColumn(4).setMaxWidth(135);
        tableCTHD.getColumnModel().getColumn(5).setMinWidth(70);
        tableCTHD.getColumnModel().getColumn(5).setMaxWidth(70);
        tableCTHD.getColumnModel().getColumn(6).setMinWidth(170);
        tableCTHD.getColumnModel().getColumn(6).setMaxWidth(170);
        tableCTHD.getColumnModel().getColumn(7).setMinWidth(170);
        tableCTHD.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
    }

    private void setDataCTHDtable(List<CTHoaDon> CTHDlist) {
        for (CTHoaDon cthd : CTHDlist) {
            ModelCTHD.addRow(new Object[]{
                cthd.getMahd(), cthd.getMamon(),
                ModelCTHD.getRowCount() + 1,
                cthd.getTenmon(), cthd.getDvtinh(),
                cthd.getSoluong(),
                tienfm.format(cthd.getDongia()),
                tienfm.format(cthd.getThanhtien())
            });
        }
    }

    private void SearchHDbyDay() {
        SimpleDateFormat dayfm1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat dayfm2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        String day1 = dayfm1.format(inputDay1.getDate());
        String day2 = dayfm2.format(inputDay2.getDate());
//        System.out.println("day1:"+ day1);
//        System.out.println("day2:"+ day2);
        ModelHD.setRowCount(0);
        setDataHDtable(hoadonDAO.getListHDbyInputDay(day1, day2));
    }

    private void LamMoi() {
        TableHD();
        TableCTHD();

        txtSearchMaHD.setText("");
        lblMaHD.setText("");
        lblMaBan.setText("");
        lblGioLap.setText("");
        lblGioCapNhat.setText("");
        lblTongTien.setText("");
        lblTenNV.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHD = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        txtSearchMaHD = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btReset = new javax.swing.JButton();
        btEditHD = new javax.swing.JButton();
        btDeleteHD = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btSearch = new javax.swing.JButton();
        inputDay1 = new com.toedter.calendar.JDateChooser();
        inputDay2 = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCTHD = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblGioLap = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        lblMaBan = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblGioCapNhat = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblTenNV = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        tableHD.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tableHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HD", "Mã bàn", "Giờ đến", "Giờ cập nhật", "Tổng tiền", "Trạng thái"
            }
        ));
        tableHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableHD);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        txtSearchMaHD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtSearchMaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchMaHDActionPerformed(evt);
            }
        });
        txtSearchMaHD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchMaHDKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Mã HD");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Từ ngày");

        btReset.setBackground(new java.awt.Color(0, 204, 0));
        btReset.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btReset.setForeground(new java.awt.Color(255, 255, 255));
        btReset.setText("Làm mới");
        btReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResetActionPerformed(evt);
            }
        });

        btEditHD.setBackground(new java.awt.Color(0, 204, 0));
        btEditHD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btEditHD.setForeground(new java.awt.Color(255, 255, 255));
        btEditHD.setText("Sửa");
        btEditHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditHDActionPerformed(evt);
            }
        });

        btDeleteHD.setBackground(new java.awt.Color(255, 51, 0));
        btDeleteHD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btDeleteHD.setForeground(new java.awt.Color(255, 255, 255));
        btDeleteHD.setText("Xóa");
        btDeleteHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteHDActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Đến ngày");

        btSearch.setBackground(new java.awt.Color(0, 153, 255));
        btSearch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btSearch.setForeground(new java.awt.Color(255, 255, 255));
        btSearch.setText("Tìm kiếm");
        btSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSearchActionPerformed(evt);
            }
        });

        inputDay1.setBackground(new java.awt.Color(255, 255, 255));
        inputDay1.setDateFormatString("dd/MM/yyyy");
        inputDay1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        inputDay2.setBackground(new java.awt.Color(255, 255, 255));
        inputDay2.setDateFormatString("dd/MM/yyyy");
        inputDay2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSearchMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(inputDay1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(inputDay2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btReset, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(192, 192, 192)
                        .addComponent(btEditHD, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btDeleteHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearchMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputDay2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(inputDay1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btReset, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEditHD, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btDeleteHD, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        tableCTHD.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tableCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên món", "Đơn vị", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane2.setViewportView(tableCTHD);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setText("Mã HD");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel6.setText("Giờ đến");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setText("Tổng tiền");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel8.setText("Giờ cập nhật");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel9.setText("Mã bàn");

        lblGioLap.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblGioLap.setForeground(java.awt.Color.red);

        lblMaHD.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMaHD.setForeground(java.awt.Color.red);

        lblMaBan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMaBan.setForeground(java.awt.Color.red);

        lblTongTien.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTongTien.setForeground(java.awt.Color.red);

        lblGioCapNhat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblGioCapNhat.setForeground(java.awt.Color.red);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel10.setText("Nhân viên lập");

        lblTenNV.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTenNV.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                            .addComponent(lblMaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(85, 85, 85)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 4, Short.MAX_VALUE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblGioCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblGioLap, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 158, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaBan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGioLap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGioCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHDMouseClicked
        try {
            int row = tableHD.getSelectedRow();
            String MaHD = String.valueOf(tableHD.getValueAt(row, 0));
            HoaDon hd = hoadonDAO.getHoaDonByMaHD(MaHD);

            lblMaHD.setText(hd.getMahd());
            lblMaBan.setText(String.valueOf(hd.getMaban()));
            lblGioLap.setText(df.format(hd.getGioden()));
            lblGioCapNhat.setText(df.format(hd.getGiocapnhat()));
            lblTongTien.setText(tienfm.format(hd.getTongtien()));
            lblTenNV.setText(hd.getTennv());

            //Load tableCTHD
            ModelCTHD.setRowCount(0);
            cthoadonDAO = new CTHoaDonDAO();
            setDataCTHDtable(cthoadonDAO.getListCTHDbyMaHD(MaHD));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_tableHDMouseClicked

    private void btResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResetActionPerformed
        LamMoi();
    }//GEN-LAST:event_btResetActionPerformed

    private void btEditHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditHDActionPerformed
        int row = tableHD.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn cần sửa!", "Error Warning", JOptionPane.ERROR_MESSAGE);
        } else {
            jf_editHD editHD = new jf_editHD();
            editHD.setLocationRelativeTo(null);//Hiện giữa màn hình
            editHD.setVisible(true);
        }
    }//GEN-LAST:event_btEditHDActionPerformed

    private void btDeleteHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteHDActionPerformed
        int row = tableHD.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn xóa!", "Error Warning", JOptionPane.ERROR_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa không?", "Thông báo", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                String MaHD = String.valueOf(tableHD.getValueAt(row, 0));
                hoadonDAO.deleteHoaDon(MaHD);

                ModelHD.setRowCount(0);
                setDataHDtable(hoadonDAO.getListHD());

                LamMoi();
            }
        }
    }//GEN-LAST:event_btDeleteHDActionPerformed

    private void btSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSearchActionPerformed
        SearchHDbyDay();
    }//GEN-LAST:event_btSearchActionPerformed

    private void txtSearchMaHDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchMaHDKeyReleased
        String TuKhoa = "%" + txtSearchMaHD.getText() + "%";

        ModelHD.setRowCount(0);
        setDataHDtable(hoadonDAO.getListHDbyMaHD(TuKhoa));
    }//GEN-LAST:event_txtSearchMaHDKeyReleased

    private void txtSearchMaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchMaHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchMaHDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btDeleteHD;
    public static javax.swing.JButton btEditHD;
    private javax.swing.JButton btReset;
    private javax.swing.JButton btSearch;
    private com.toedter.calendar.JDateChooser inputDay1;
    private com.toedter.calendar.JDateChooser inputDay2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblGioCapNhat;
    private javax.swing.JLabel lblGioLap;
    private javax.swing.JLabel lblMaBan;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JTable tableCTHD;
    public static javax.swing.JTable tableHD;
    private javax.swing.JTextField txtSearchMaHD;
    // End of variables declaration//GEN-END:variables
}
