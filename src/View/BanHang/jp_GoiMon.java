/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.BanHang;

import DAO.*;
import Model.*;
import View.Run;
import View.jf_TrangChu;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NHT_Kurumi
 */
public class jp_GoiMon extends javax.swing.JPanel {

    public static jp_GoiMon gm;
    String TenBan, MaHD;
    int MaBan;
    BanDAO banDAO = new BanDAO();
    HoaDonDAO hoadonDAO = new HoaDonDAO();
    ThucDonDAO thucdonDAO = new ThucDonDAO();
    CTHoaDonDAO cthdDAO = new CTHoaDonDAO();
    NhomMonDAO nmDAO = new NhomMonDAO();
    public DefaultTableModel ModelCTHD;
    HoaDon hd;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    DefaultListModel listModel = new DefaultListModel();

    /**
     * Creates new form jp_GoiMon
     *
     * @param trangthai
     * @param tenban
     * @param maban
     */
    public jp_GoiMon(String tenban, int maban) {
        initComponents();
        gm = this;
        TableCTHD();
        jlNhomMon();

        MaBan = maban;
        TenBan = tenban;
        lblBan.setText(tenban);
        Ban ban = banDAO.getBanByMaBan(maban);
        lblTrangThai.setText("Trạng thái: " + ban.getTrangthai());

        hd = hoadonDAO.getHoaDonByMaBan(maban);
        if (lblTrangThai.getText().equals("Trạng thái: Trống")) {
            btDatBan.setText("Đặt bàn");
            btThanhToan.setVisible(false);
            return;
        } else if (lblTrangThai.getText().equals("Trạng thái: Đã đặt trước")) {
            btDatBan.setText("Hủy đặt");
            btThanhToan.setVisible(false);
            return;
        } else {
            setDataCTHDtable(cthdDAO.getListCTHDbyMaHD(hd.getMahd()));
            lblGioDen.setText("Giờ đến: " + df.format(hd.getGioden()));
            lblNV.setText("Người nhập: " + hd.getTennv());
            lblMaHD.setText(hd.getMahd());
            lblTongTien.setText("Tông tiền: " + String.format("%,.0f", hd.getTongtien()));
            btDatBan.setVisible(false);
        }
    }

    public void TableCTHD() {
        cthdDAO = new CTHoaDonDAO();
        ModelCTHD = new DefaultTableModel() {
            @Override //Không cho người dùng edit table
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableCTHD.setModel(ModelCTHD);
        ModelCTHD.addColumn("Mã HD");
        ModelCTHD.addColumn("Mã SP");
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
        tableCTHD.getColumnModel().getColumn(2).setMinWidth(125);
        tableCTHD.getColumnModel().getColumn(2).setMaxWidth(125);
        tableCTHD.getColumnModel().getColumn(3).setMinWidth(55);
        tableCTHD.getColumnModel().getColumn(3).setMaxWidth(55);
        tableCTHD.getColumnModel().getColumn(4).setMinWidth(65);
        tableCTHD.getColumnModel().getColumn(4).setMaxWidth(65);
        tableCTHD.getColumnModel().getColumn(5).setMinWidth(100);
        tableCTHD.getColumnModel().getColumn(5).setMaxWidth(100);
        tableCTHD.getColumnModel().getColumn(6).setMinWidth(120);
        tableCTHD.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
    }

    public void setDataCTHDtable(List<CTHoaDon> CTHDlist) {
        for (CTHoaDon cthd : CTHDlist) {
            ModelCTHD.addRow(new Object[]{
                cthd.getMahd(), cthd.getMamon(),
                cthd.getTenmon(), cthd.getDvtinh(),
                cthd.getSoluong(),
                String.format("%,.0f", cthd.getDongia()),
                String.format("%,.0f", cthd.getThanhtien())
            });
        }
    }

    public void lblTongTien() {
        hd = hoadonDAO.getHoaDonByMaBan(MaBan);
        lblTongTien.setText("Tổng tiền: " + String.format("%,.0f", hd.getTongtien()));
    }

    List<NhomMon> NMlist;
    String gioden;

    private void jlNhomMon() {
        NhomMonDAO nmDAO = new NhomMonDAO();
        NMlist = nmDAO.getListNhomMon();
        jlNhomMon.setModel(listModel);
        jlNhomMon.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        for (int i = 0; i < nmDAO.getListNhomMon().size(); i++) {
            listModel.addElement(NMlist.get(i).getTenNhom());
        }
        jlNhomMon.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String tennhom = jlNhomMon.getSelectedValue();
                System.out.println("tennhom:" + tennhom);
                NhomMon nm = nmDAO.getNMByTenNhom(tennhom);
                System.out.println("manhom:" + nm.getManhom());
                List<ThucDon> listTD = thucdonDAO.getListTDByMaNhom(nm.getManhom());

                //Làm mới mỗi lần tìm kiếm Thực đơn
                jpThucDon.removeAll();
//                jpThucDon.setLayout(new FlowLayout(FlowLayout.CENTER));
                jpThucDon.updateUI();
                if (listTD != null) {
                    JPanel[] pn = new JPanel[listTD.size()];
                    for (int i = 0; i < listTD.size(); i++) {
                        pn[i] = new JPanel();
                        pn[i].setName(String.valueOf(listTD.get(i).getMamon()));
                        pn[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
                        pn[i].setBackground(Color.decode("#dfff80"));
                        pn[i].setBorder(BorderFactory.createLineBorder(Color.decode("#a3a375"), 2));
                        pn[i].setPreferredSize(new Dimension(147, 60));
                        pn[i].add(new JLabel(listTD.get(i).getTenmon())).setFont(new java.awt.Font("Tahoma", 1, 13));
                        pn[i].add(new JLabel(String.format("%,.0f", listTD.get(i).getDongia()) + " VNĐ/ " + listTD.get(i).getDvtinh())).setForeground(Color.decode("#ff0000"));
                        float dongia = Float.parseFloat(String.valueOf(listTD.get(i).getDongia()));
                        pn[i].addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                jd_SoLuong sl = new jd_SoLuong(Run.tc, true, e.getComponent().getName(), TenBan, MaBan, dongia, MaHD);
                                sl.setLocationRelativeTo(null);
                                sl.setVisible(true);
                            }
                        });
                        jpThucDon.add(pn[i]);
                        jpThucDon.updateUI();
                    }
                }

            }

        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pmnCTHD = new javax.swing.JPopupMenu();
        mniDeleteSP = new javax.swing.JMenuItem();
        mniEditSP = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        lblBan = new javax.swing.JLabel();
        lblGioDen = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btDatBan = new javax.swing.JButton();
        btDong = new javax.swing.JButton();
        btThanhToan = new javax.swing.JButton();
        lblTrangThai = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCTHD = new javax.swing.JTable();
        lblNV = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jlNhomMon = new javax.swing.JList<>();
        jpThucDon = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        mniDeleteSP.setText("Xóa sản phẩm");
        mniDeleteSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDeleteSPActionPerformed(evt);
            }
        });
        pmnCTHD.add(mniDeleteSP);

        mniEditSP.setText("Sửa sản phẩm");
        mniEditSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniEditSPActionPerformed(evt);
            }
        });
        pmnCTHD.add(mniEditSP);

        setBackground(new java.awt.Color(255, 255, 255));
        setRequestFocusEnabled(false);
        setVerifyInputWhenFocusTarget(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        lblBan.setBackground(new java.awt.Color(255, 255, 255));
        lblBan.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        lblBan.setForeground(new java.awt.Color(102, 51, 0));
        lblBan.setText("Bàn");

        lblGioDen.setBackground(new java.awt.Color(255, 255, 255));
        lblGioDen.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblGioDen.setText("Giờ đến: ...");

        lblTongTien.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(204, 0, 0));
        lblTongTien.setText("Tổng tiền: ...");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btDatBan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btDatBan.setText("Đặt bàn");
        btDatBan.setPreferredSize(new java.awt.Dimension(140, 60));
        btDatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDatBanActionPerformed(evt);
            }
        });
        jPanel3.add(btDatBan);

        btDong.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btDong.setText("Đóng");
        btDong.setPreferredSize(new java.awt.Dimension(140, 60));
        btDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDongActionPerformed(evt);
            }
        });
        jPanel3.add(btDong);

        btThanhToan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btThanhToan.setText("Thanh toán");
        btThanhToan.setPreferredSize(new java.awt.Dimension(140, 60));
        btThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThanhToanActionPerformed(evt);
            }
        });
        jPanel3.add(btThanhToan);

        lblTrangThai.setBackground(new java.awt.Color(255, 255, 255));
        lblTrangThai.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblTrangThai.setText("Trạng thái: ...");

        tableCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên món", "Đơn vị", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        tableCTHD.setComponentPopupMenu(pmnCTHD);
        tableCTHD.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(tableCTHD);

        lblNV.setBackground(new java.awt.Color(255, 255, 255));
        lblNV.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblNV.setText("Người nhập: ...");

        lblMaHD.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMaHD.setForeground(new java.awt.Color(255, 0, 0));
        lblMaHD.setRequestFocusEnabled(false);
        lblMaHD.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblBan, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGioDen, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNV, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBan, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGioDen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gọi món", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jlNhomMon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jlNhomMon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlNhomMonMouseClicked(evt);
            }
        });
        jlNhomMon.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlNhomMonValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(jlNhomMon);

        jpThucDon.setBackground(new java.awt.Color(255, 255, 255));
        jpThucDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Nhóm món");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Thực đơn");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1)
                        .addGap(110, 110, 110))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jpThucDon, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 261, Short.MAX_VALUE))
                    .addComponent(jpThucDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btDatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDatBanActionPerformed
        if (lblTrangThai.getText().equals("Trạng thái: Đã đặt trước")) {
            lblTrangThai.setText("Trạng thái: Trống");
            btDatBan.setText("Đặt chỗ");
            String TrangThai = "Trống";

            Ban ban = new Ban(MaBan, TenBan, TrangThai);
            banDAO.updateBan(ban);

            jp_BanHang.bh.QuanLyBan();
            jp_BanHang.bh.updateUI();
        } else {
            lblTrangThai.setText("Trạng thái: Đã đặt trước");
            btDatBan.setText("Hủy đặt");
            String TrangThai = "Đã đặt trước";
            Ban b = new Ban(MaBan, TenBan, TrangThai);
            banDAO.updateBan(b);
            jp_BanHang.bh.QuanLyBan();
            jp_BanHang.bh.updateUI();
        }
    }//GEN-LAST:event_btDatBanActionPerformed

    private void btDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDongActionPerformed
        jp_BanHang.bh.jpTaoHoaDon();
    }//GEN-LAST:event_btDongActionPerformed

    private void jlNhomMonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlNhomMonMouseClicked

    }//GEN-LAST:event_jlNhomMonMouseClicked

    private void jlNhomMonValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlNhomMonValueChanged

    }//GEN-LAST:event_jlNhomMonValueChanged

    private void btThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThanhToanActionPerformed
        jd_ThanhToan tt = new jd_ThanhToan(Run.tc, true, hd.getTongtien(), lblBan.getText(), MaBan, MaHD);
        tt.setLocationRelativeTo(null);
        tt.setVisible(true);
    }//GEN-LAST:event_btThanhToanActionPerformed

    private void mniDeleteSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDeleteSPActionPerformed
        try {
            int row = tableCTHD.getSelectedRow();
            int mamon = Integer.parseInt(tableCTHD.getValueAt(row, 1).toString());
            String mahd = tableCTHD.getValueAt(row, 0).toString();
            cthdDAO.deleteCTHoaDon(mahd, mamon);
            TableCTHD();
            setDataCTHDtable(cthdDAO.getListCTHDbyMaHD(mahd));
            lblTongTien();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn món ăn!", "Error Warning", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_mniDeleteSPActionPerformed

    private void mniEditSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniEditSPActionPerformed
        int row = tableCTHD.getSelectedRow();
        String mahd = tableCTHD.getValueAt(row, 0).toString();
        String mamon = tableCTHD.getValueAt(row, 1).toString();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn món ăn!", "Error Warning", JOptionPane.ERROR_MESSAGE);
        } else {
            ThucDon td = thucdonDAO.getTDByMaMon(Integer.parseInt(mamon));
            jd_SoLuong sl = new jd_SoLuong(Run.tc, true, mamon, TenBan, MaBan, td.getDongia(), mahd);
            sl.setLocationRelativeTo(null);
            sl.setVisible(true);
        }
    }//GEN-LAST:event_mniEditSPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btDatBan;
    public static javax.swing.JButton btDong;
    public static javax.swing.JButton btThanhToan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> jlNhomMon;
    private javax.swing.JPanel jpThucDon;
    private javax.swing.JLabel lblBan;
    public static javax.swing.JLabel lblGioDen;
    public static javax.swing.JLabel lblMaHD;
    public static javax.swing.JLabel lblNV;
    private javax.swing.JLabel lblTongTien;
    public static javax.swing.JLabel lblTrangThai;
    private javax.swing.JMenuItem mniDeleteSP;
    private javax.swing.JMenuItem mniEditSP;
    private javax.swing.JPopupMenu pmnCTHD;
    public static javax.swing.JTable tableCTHD;
    // End of variables declaration//GEN-END:variables
}
