/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.HoaDon;

import DAO.*;
import Model.*;
import View.BanHang.jd_SoLuong;
import View.BanHang.jp_GoiMon;
import View.Run;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 *
 * @author NHT_Kurumi
 */
public class jf_editHD extends javax.swing.JFrame {

    public static jf_editHD edit;
    HoaDonDAO hoadonDAO;
    CTHoaDonDAO cthoadonDAO;
    ThucDonDAO thucdonDAO = new ThucDonDAO();
    BanDAO banDAO = new BanDAO();
    private DefaultTableModel ModelTD;
    private DefaultTableModel ModelCTHD;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    DefaultListModel listModel = new DefaultListModel();
    
    public static String SoLuong;
    String MaHD = String.valueOf(jp_HoaDon.tableHD.getValueAt(jp_HoaDon.tableHD.getSelectedRow(), 0));
    int MaBan;
    public static float tiencu;
    /**
     * Creates new form EditHoaDonJFrame
     */
    public jf_editHD() {
        initComponents();
        edit = this;
        setTitle("Cập nhật hóa đơn");
        setResizable(false);//Vô hiệu hóa nút phóng to
        getContentPane().setBackground(Color.WHITE);//Backgrounf đổi màu
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);//Đóng Jframe khi click(x)
        TableCTHD();
        LamMoi();
        HoaDon hd= hoadonDAO.getHoaDonByMaHD(MaHD);
        lblTongTien.setText(String.format("%,.0f",hd.getTongtien()));
        jlNhomMon();
        
    }
    
    public void TableCTHD(){
        cthoadonDAO = new CTHoaDonDAO();
        ModelCTHD = new DefaultTableModel(){
            @Override //Không cho người dùng edit table
            public boolean isCellEditable(int row, int column) {
                return false;
            }   
        };
        tableCTHD.setModel(ModelCTHD); 
        ModelCTHD.addColumn("Mã món");
        ModelCTHD.addColumn("STT");
        ModelCTHD.addColumn("Tên món");
        ModelCTHD.addColumn("Đơn vị");
        ModelCTHD.addColumn("Số lượng");
        ModelCTHD.addColumn("Đơn giá");
        ModelCTHD.addColumn("Thành tiền");
        
        //Giảm đọ dài cột đầu tiên của tableCTHD về 0
        tableCTHD.getColumnModel().getColumn(0).setMinWidth(0);
        tableCTHD.getColumnModel().getColumn(0).setMaxWidth(0);
        tableCTHD.getColumnModel().getColumn(1).setMinWidth(50);
        tableCTHD.getColumnModel().getColumn(1).setMaxWidth(50);
        tableCTHD.getColumnModel().getColumn(2).setMinWidth(200);
        tableCTHD.getColumnModel().getColumn(2).setMaxWidth(200);
        tableCTHD.getColumnModel().getColumn(3).setMinWidth(135);
        tableCTHD.getColumnModel().getColumn(3).setMaxWidth(135);
        tableCTHD.getColumnModel().getColumn(4).setMinWidth(70);
        tableCTHD.getColumnModel().getColumn(4).setMaxWidth(70);
        tableCTHD.getColumnModel().getColumn(5).setMinWidth(130);
        tableCTHD.getColumnModel().getColumn(5).setMaxWidth(130);
        tableCTHD.getColumnModel().getColumn(6).setMinWidth(130);
        tableCTHD.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        setDataCTHDtable(cthoadonDAO.getListCTHDbyMaHD(MaHD));
    }
    
    public void setDataCTHDtable(List<CTHoaDon> CTHDlist) {
        for(CTHoaDon cthd : CTHDlist){
        ModelCTHD.addRow(new Object[]{
            cthd.getMamon(), 
            ModelCTHD.getRowCount() + 1, 
            cthd.getTenmon(), cthd.getDvtinh(), 
            cthd.getSoluong(),
            String.format("%,.0f",cthd.getDongia()),
            String.format("%,.0f",cthd.getThanhtien())
            });
        }
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
                MaBan = Integer.parseInt(lblMaBan.getText().toString());
                Ban ban = banDAO.getBanByMaBan(MaBan);
                
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
                                jd_SoLuongHD sl = new jd_SoLuongHD(Run.tc, true, e.getComponent().getName(), ban.getTenban(), MaBan, dongia, MaHD);
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
    
    public void LamMoi(){
        hoadonDAO = new HoaDonDAO();
        HoaDon hd = hoadonDAO.getHoaDonByMaHD(MaHD);
        lblMaHD.setText(hd.getMahd());
        lblGioLap.setText(df.format(hd.getGioden()));
        lblMaBan.setText(String.valueOf(hd.getMaban()));
        lblGioCapNhat.setText(df.format(hd.getGiocapnhat()));
        lblTenNV.setText(hd.getTennv());
        lblTongTienMoi.setText("Tổng tiền mới: ...");
        lblTienTraKhach.setText("Tiền trả khách: ...");
        
        TableCTHD();
        tiencu = hd.getTongtien();
    }

    public void ThanhToan(){
        MaBan = Integer.parseInt(lblMaBan.getText().toString());
        HoaDon hd = hoadonDAO.GetHoaDonByMaBan(MaBan,MaHD);
        lblTongTienMoi.setText("Tổng tiền mới: "+String.format("%,.0f", hd.getTongtien()));
        float tientk = tiencu - hd.getTongtien();
        lblTienTraKhach.setText("Tiền trả khách: "+String.format("%,.0f", tientk));
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
        mniDeleteTD = new javax.swing.JMenuItem();
        mniEditSP = new javax.swing.JMenuItem();
        panelHoaDon = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCTHD = new javax.swing.JTable();
        lblTongTienMoi = new javax.swing.JLabel();
        lblTienTraKhach = new javax.swing.JLabel();
        jbtClose = new javax.swing.JButton();
        lblTongTien = new javax.swing.JLabel();
        lblGioCapNhat = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblTenNV = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblGioLap = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        lblMaBan = new javax.swing.JLabel();
        panelSanPham = new javax.swing.JPanel();
        jpThucDon = new javax.swing.JPanel();
        jc = new javax.swing.JScrollPane();
        jlNhomMon = new javax.swing.JList<>();

        mniDeleteTD.setText("Xóa sản phẩm");
        mniDeleteTD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDeleteTDActionPerformed(evt);
            }
        });
        pmnCTHD.add(mniDeleteTD);

        mniEditSP.setText("Sửa sản phẩm");
        mniEditSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniEditSPActionPerformed(evt);
            }
        });
        pmnCTHD.add(mniEditSP);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        panelHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        panelHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        tableCTHD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tableCTHD.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tableCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên món", "Đon vị", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        tableCTHD.setComponentPopupMenu(pmnCTHD);
        tableCTHD.setGridColor(new java.awt.Color(255, 255, 255));
        tableCTHD.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tableCTHDComponentShown(evt);
            }
        });
        jScrollPane2.setViewportView(tableCTHD);

        lblTongTienMoi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTongTienMoi.setForeground(java.awt.Color.red);
        lblTongTienMoi.setText("Tổng tiền mới: ...");

        lblTienTraKhach.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTienTraKhach.setForeground(java.awt.Color.red);
        lblTienTraKhach.setText("Tiền trả khách: ...");

        jbtClose.setBackground(new java.awt.Color(204, 0, 0));
        jbtClose.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jbtClose.setForeground(new java.awt.Color(255, 255, 255));
        jbtClose.setText("Đóng");
        jbtClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCloseActionPerformed(evt);
            }
        });

        lblTongTien.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTongTien.setForeground(java.awt.Color.red);

        lblGioCapNhat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblGioCapNhat.setForeground(java.awt.Color.red);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel10.setText("Nhân viên lập");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel9.setText("Mã HD");

        lblTenNV.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTenNV.setForeground(java.awt.Color.red);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel11.setText("Giờ đến");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel12.setText("Tổng tiền");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel13.setText("Giờ cập nhật");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel14.setText("Mã bàn");

        lblGioLap.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblGioLap.setForeground(java.awt.Color.red);

        lblMaHD.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMaHD.setForeground(java.awt.Color.red);

        lblMaBan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMaBan.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout panelHoaDonLayout = new javax.swing.GroupLayout(panelHoaDon);
        panelHoaDon.setLayout(panelHoaDonLayout);
        panelHoaDonLayout.setHorizontalGroup(
            panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHoaDonLayout.createSequentialGroup()
                        .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                            .addComponent(lblMaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblGioCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGioLap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelHoaDonLayout.createSequentialGroup()
                            .addComponent(lblTongTienMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblTienTraKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtClose, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelHoaDonLayout.setVerticalGroup(
            panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHoaDonLayout.createSequentialGroup()
                        .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaBan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))
                    .addGroup(panelHoaDonLayout.createSequentialGroup()
                        .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGioLap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGioCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtClose, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTongTienMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienTraKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelSanPham.setBackground(new java.awt.Color(255, 255, 255));
        panelSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thực đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jpThucDon.setBackground(new java.awt.Color(255, 255, 255));
        jpThucDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jc.setBackground(new java.awt.Color(255, 255, 255));
        jc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jlNhomMon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jc.setViewportView(jlNhomMon);

        javax.swing.GroupLayout panelSanPhamLayout = new javax.swing.GroupLayout(panelSanPham);
        panelSanPham.setLayout(panelSanPhamLayout);
        panelSanPhamLayout.setHorizontalGroup(
            panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jc, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpThucDon, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelSanPhamLayout.setVerticalGroup(
            panelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSanPhamLayout.createSequentialGroup()
                .addComponent(jc, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelSanPhamLayout.createSequentialGroup()
                .addComponent(jpThucDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableCTHDComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tableCTHDComponentShown

    }//GEN-LAST:event_tableCTHDComponentShown

    private void jbtCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_jbtCloseActionPerformed

    private void mniDeleteTDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDeleteTDActionPerformed
        int row = tableCTHD.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm!", "Error Warning", JOptionPane.ERROR_MESSAGE);
        } else {
            int mamon=Integer.parseInt(tableCTHD.getValueAt(row, 0).toString());
            String mahd = lblMaHD.getText();
            cthoadonDAO.deleteCTHoaDon(mahd, mamon);
            TableCTHD();
            ThanhToan();
        }
    }//GEN-LAST:event_mniDeleteTDActionPerformed

    private void mniEditSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniEditSPActionPerformed
        int row = tableCTHD.getSelectedRow();
        String mahd = lblMaHD.getText();
        String mamon=tableCTHD.getValueAt(row, 0).toString();
        MaBan = Integer.parseInt(lblMaBan.getText().toString());
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm!", "Error Warning", JOptionPane.ERROR_MESSAGE);
        } else {
            ThucDon td = thucdonDAO.getTDByMaMon(Integer.parseInt(mamon));
            Ban ban = banDAO.getBanByMaBan(MaBan);
            jd_SoLuongHD sl = new jd_SoLuongHD(Run.tc, true, mamon, ban.getTenban(), MaBan, td.getDongia(),mahd);
            sl.setLocationRelativeTo(null);
            sl.setVisible(true);
        }
    }//GEN-LAST:event_mniEditSPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtClose;
    private javax.swing.JScrollPane jc;
    private javax.swing.JList<String> jlNhomMon;
    private javax.swing.JPanel jpThucDon;
    private javax.swing.JLabel lblGioCapNhat;
    private javax.swing.JLabel lblGioLap;
    private javax.swing.JLabel lblMaBan;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblTenNV;
    public static javax.swing.JLabel lblTienTraKhach;
    private javax.swing.JLabel lblTongTien;
    public static javax.swing.JLabel lblTongTienMoi;
    private javax.swing.JMenuItem mniDeleteTD;
    private javax.swing.JMenuItem mniEditSP;
    private javax.swing.JPanel panelHoaDon;
    private javax.swing.JPanel panelSanPham;
    private javax.swing.JPopupMenu pmnCTHD;
    public static javax.swing.JTable tableCTHD;
    // End of variables declaration//GEN-END:variables
}
