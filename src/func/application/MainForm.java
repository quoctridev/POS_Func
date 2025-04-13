package func.application;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import func.ui.DialogDangNhap;
import func.ui.PanelChonBan;
import func.ui.PanelDatBan;
import func.ui.PanelDonHang;
import func.ui.PanelHoaDon;
import func.ui.PanelQuanLyBan;
import func.ui.PanelQuanLyDanhMuc;
import func.ui.PanelQuanLyGiamGia;
import func.ui.PanelQuanLyKhachHang;
import func.ui.PanelQuanLySanPham;
import func.ui.PanelThongKe;
import func.ui.PanelQuanLyNhanVien;
import func.utils.Auth;
import func.utils.IconWithHorizontalMargin;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author quoca
 */
public class MainForm extends javax.swing.JFrame {

    int role;

    private static MainForm app;

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        init();

    }

    void init() {
        new DialogDangNhap(this, true).setVisible(true);
        role = Auth.isRole();
        addHoverEffect(jLabel5);
        addHoverEffect(jLabel6);
        addHoverEffect(jLabel7);
        addHoverEffect(jLabel9);
        addHoverEffect(jLabel10);
        addHoverEffect(jLabel11);
        addHoverEffect(jLabel12);
        switch (role) {
            case 1:
                jLabel5.setText("Quản lý nhân viên");
                clickPanel(jLabel5, PanelQuanLyNhanVien.class);
                FlatSVGIcon users = new FlatSVGIcon("func/image/users.svg").derive(30, 30);
                Icon user = new IconWithHorizontalMargin(users, 10);
                jLabel5.setIcon(user);

                jLabel6.setText("Quản lý danh mục");
                clickPanel(jLabel6, PanelQuanLyDanhMuc.class);
                FlatSVGIcon danhMuc = new FlatSVGIcon("func/image/list.svg").derive(30, 30);
                jLabel6.setIcon(new IconWithHorizontalMargin(danhMuc, 10));

                jLabel7.setText("Quản lý sản phẩm");
                clickPanel(jLabel7, PanelQuanLySanPham.class);
                FlatSVGIcon sanPham = new FlatSVGIcon("func/image/cart.svg").derive(30, 30);
                jLabel7.setIcon(new IconWithHorizontalMargin(sanPham, 10));

                jLabel9.setText("Quản lý giảm giá");
                clickPanel(jLabel9, PanelQuanLyGiamGia.class);
                FlatSVGIcon giamGia = new FlatSVGIcon("func/image/percent.svg").derive(25, 30);
                jLabel9.setIcon(new IconWithHorizontalMargin(giamGia, 10));

                jLabel10.setText("Thống kê");
                clickPanel(jLabel10, PanelThongKe.class);
                FlatSVGIcon thongKe = new FlatSVGIcon("func/image/chart.svg").derive(30, 30);
                jLabel10.setIcon(new IconWithHorizontalMargin(thongKe, 10));

                jLabel11.setText("Quản lí bàn");
                clickPanel(jLabel11, PanelQuanLyBan.class);
                FlatSVGIcon ban = new FlatSVGIcon("func/image/tablets.svg").derive(30, 30);
                jLabel11.setIcon(new IconWithHorizontalMargin(ban, 10));

                jLabel12.setText("Quản lí khách hàng");
                clickPanel(jLabel12, PanelQuanLyKhachHang.class);
                jLabel12.setIcon(user); // đã có margin
                break;

            case 2:
                jLabel5.setText("Quản lý hóa đơn");
                clickPanel(jLabel5, PanelHoaDon.class);
                FlatSVGIcon hoaDon = new FlatSVGIcon("func/image/invoice.svg").derive(30, 30);
                jLabel5.setIcon(new IconWithHorizontalMargin(hoaDon, 10));

                jLabel6.setText("Quản lý bàn");
                clickPanel(jLabel6, PanelChonBan.class);
                FlatSVGIcon quanLyBan = new FlatSVGIcon("func/image/tablets.svg").derive(30, 30);
                jLabel6.setIcon(new IconWithHorizontalMargin(quanLyBan, 10));

                jLabel7.setText("Quản lý đơn hàng");
                clickPanel(jLabel7, PanelDonHang.class);
                FlatSVGIcon donHang = new FlatSVGIcon("func/image/receipt.svg").derive(30, 30);
                jLabel7.setIcon(new IconWithHorizontalMargin(donHang, 10));

                jLabel9.setText("Đặt bàn");
                clickPanel(jLabel9, PanelDatBan.class);
                FlatSVGIcon datBan = new FlatSVGIcon("func/image/user-plus.svg").derive(30, 30);
                jLabel9.setIcon(new IconWithHorizontalMargin(datBan, 10));
                break;

            case 3:
                jLabel5.setText("Quản lí đơn hàng");
                clickPanel(jLabel5, PanelDonHang.class);
                FlatSVGIcon quanLiDon = new FlatSVGIcon("func/image/receipt.svg").derive(30, 30);
                jLabel7.setIcon(new IconWithHorizontalMargin(quanLiDon, 10));
                break;

        }
        FlatSVGIcon danxuat = new FlatSVGIcon("func/image/logout.svg").derive(25, 30);
        dangxuat.setIcon(new IconWithHorizontalMargin(danxuat, 10));

    }

    private void addHoverEffect(JLabel label) {
        Color defaultColor = label.getBackground();  // màu nền gốc
        Color hoverColor = new Color(220, 220, 220);  // màu khi hover
        Color clickColor = new Color(200, 200, 200);  // màu khi nhấn

        label.setOpaque(true); // cần thiết để setBackground có hiệu lực

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setBackground(hoverColor);
                label.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setBackground(defaultColor);
                label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                label.setBackground(clickColor);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                label.setBackground(hoverColor);
            }
        });
    }

    private void clickPanel(javax.swing.JLabel label, Class<? extends JPanel> panelClass) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    JPanel panel = panelClass.getDeclaredConstructor().newInstance(); // Tạo mới panel
                    showPanel(panel);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void showPanel(JPanel panel) {
        pnlMain.removeAll();
        pnlMain.setLayout(null); // Không sử dụng layout manager

        // Đặt kích thước và vị trí cho panel
        panel.setBounds(0, 0, pnlMain.getWidth(), pnlMain.getHeight());

        pnlMain.add(panel);
        pnlMain.revalidate(); // Cập nhật giao diện
        pnlMain.repaint(); // Vẽ lại giao diện
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
        logo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        dangxuat = new javax.swing.JLabel();
        pnlMain = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/func/image/logo.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel5.setText("");

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel6.setText("");

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel7.setText("");

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel9.setText("");

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel10.setText("");

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel11.setText("");

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel12.setText("");

        dangxuat.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        dangxuat.setText("Đăng xuất");
        dangxuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dangxuatMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dangxuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(dangxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1005, Short.MAX_VALUE)
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dangxuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dangxuatMouseClicked
        // TODO add your handling code here:
        Auth.clear();
        dispose();
        MainForm main = new MainForm();
        main.setVisible(true);
    }//GEN-LAST:event_dangxuatMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FlatLightLaf.setup();
                FlatLaf.registerCustomDefaultsSource("func.theme");
                java.awt.EventQueue.invokeLater(() -> {
                    app = new MainForm();
                    app.setVisible(true);

                });

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dangxuat;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables
}
