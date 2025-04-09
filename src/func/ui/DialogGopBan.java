/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package func.ui;

import func.application.MainForm;
import func.dao.MergeTableDAO;
import func.dao.OrderDAO;
import func.dao.TableDAO;
import func.entity.MergeTableEntity;
import func.entity.OrderEntity;
import func.entity.TableEntity;
import func.utils.Auth;
import func.utils.Message;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author quoctris.dev
 */
public class DialogGopBan extends javax.swing.JDialog {

    int trangThai;
    private List<String> selectedTables = new ArrayList<>();
    private List<String> selectedTargetTables = new ArrayList<>();
    private int selectedSeats = 0;
    private int requiredSeats = 0;
    private String selectedZone = null;

    /**
     * Creates new form JDialogGopBan
     */
    public DialogGopBan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
        init();
    }

    void init() {
        if (trangThai == 1) {
            gopBan();
        } else {
            chuyenBan();
        }

    }

    public void chuyenBan() {
        jLabel1.setText("Chuyển bàn");
        TableDAO tableDAO = new TableDAO();
        List<String> zones = tableDAO.selectZone();

        for (String z : zones) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
            jTabbedPane1.addTab(z, panel);

            List<TableEntity> tables = tableDAO.selectByZone(z);
            for (TableEntity tb : tables) {
                JButton btn = new JButton("Bàn " + tb.getTableNumber() + " (" + tb.getCapacity() + " chỗ)");
                btn.setPreferredSize(new Dimension(120, 80));
                btn.setBackground(getStatusColor(tb.getStatus()));
                btn.putClientProperty("tableId", tb.getTableId());
                btn.putClientProperty("zone", z);

                btn.addActionListener(e -> handleChuyenBan(tableDAO, btn, tb, panel));
                panel.add(btn);
            }
        }
    }

    private void handleChuyenBan(TableDAO tableDAO, JButton btn, TableEntity tb, JPanel panel) {
        int tableId = (int) btn.getClientProperty("tableId");
        String tableZone = (String) btn.getClientProperty("zone");

        if (selectedTables.isEmpty()) {
            if (!tb.getStatus().equals("occupied")) {
                Message.warning(null, "Chỉ có thể chọn bàn đang có khách làm bàn nguồn");
                return;
            }

            List<String> mergedTables = tableDAO.getMergedTables(tableId);
            int seatsUsed = mergedTables.stream()
                    .filter(t -> t.matches("\\d+")) // Tránh lỗi số không hợp lệ
                    .mapToInt(t -> tableDAO.getSeatsUsed(Integer.parseInt(t))) // Gọi phương thức sửa lỗi
                    .sum(); 
            if (seatsUsed == 0) {
                Message.warning(null, "Không tìm thấy hóa đơn của bàn này!");
                return;
            }

            requiredSeats = seatsUsed;
            selectedTables.addAll(mergedTables);
            selectedZone = tableZone;
            btn.setBackground(Color.ORANGE);
        } else {
            if (!tb.getStatus().equals("available")) {
                Message.warning(null, "Chỉ có thể chọn bàn trống làm bàn đích");
                return;
            }
            if (!tableZone.equals(selectedZone)) {
                Message.warning(null, "Bạn chỉ có thể chuyển bàn trong cùng khu vực " + selectedZone);
                return;
            }

            selectedTargetTables.add(String.valueOf(tableId));
            selectedSeats += tb.getCapacity();
            btn.setBackground(Color.GREEN);

            if (selectedSeats >= requiredSeats) {
                boolean confirm = Message.confirm(null, "Bạn có muốn chuyển bàn ngay không?");
                if (confirm) {
                    boolean success = tableDAO.chuyenBan(selectedTables, selectedTargetTables);
                    if (success) {
                        Message.info(null, "Chuyển bàn thành công!");
                        updateTableColors(panel, tableDAO);
                        resetSelection();
                    }
                }
            }
        }
    }

    private void updateTableColors(JPanel panel, TableDAO tableDAO) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JButton) {
                JButton tableBtn = (JButton) comp;
                int id = (int) tableBtn.getClientProperty("tableId");
                tableBtn.setBackground(getStatusColor(tableDAO.getTableStatus(id)));
            }
        }
    }

    private void resetSelection() {
        selectedTables.clear();
        selectedTargetTables.clear();
        selectedSeats = 0;
        selectedZone = null;
    }

    private Color getStatusColor(String status) {
        switch (status) {
            case "available":
                return Color.LIGHT_GRAY;
            case "reserved":
                return new Color(52, 152, 219);
            case "occupied":
                return Color.YELLOW;
            case "waiting_payment":
                return Color.ORANGE;
            case "cleaning":
                return Color.CYAN;
            case "maintenance":
                return Color.RED;
            default:
                return Color.LIGHT_GRAY;
        }
    }

    void gopBan() {
        jLabel1.setText("Gộp bàn");
        List<String> zones = new TableDAO().selectZone();
        for (String z : zones) {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
            jTabbedPane1.addTab(z, panel);

            List<TableEntity> tables = new TableDAO().selectByZone(z);
            for (TableEntity tb : tables) {
                JButton btn = new JButton("Bàn " + tb.getTableNumber() + " (" + tb.getCapacity() + " chỗ)");
                btn.setPreferredSize(new Dimension(120, 80));
                Color defaultColor = getStatusColor(tb.getStatus());
                btn.setBackground(defaultColor);

                btn.addActionListener(e -> {
                    String tableNumber = String.valueOf(tb.getTableId()); // Lấy id bàn
                    String tableZone = z; // Zone của bàn hiện tại

                    if (!tb.getStatus().equals("available")) {
                        Message.warning(null, "Bạn không thể chọn bàn này do đang có người sử dụng hoặc đặt trước");
                        return;
                    }

                    // Nếu chưa có zone nào được chọn, đặt zone hiện tại
                    if (selectedZone == null) {
                        selectedZone = tableZone;
                    }

                    // Kiểm tra nếu bàn cùng zone thì mới cho chọn
                    if (!tableZone.equals(selectedZone)) {
                        Message.warning(null, "Bạn chỉ có thể chọn bàn trong khu vực " + selectedZone);
                        return;
                    }

                    if (selectedTables.contains(tableNumber)) {
                        // Nếu đã chọn, bỏ chọn
                        selectedTables.remove(tableNumber);
                        btn.setBackground(defaultColor);

                        // Nếu bỏ hết bàn, reset lại zone
                        if (selectedTables.isEmpty()) {
                            selectedZone = null;
                        }
                    } else {
                        // Nếu chưa chọn, thêm vào danh sách chọn
                        selectedTables.add(tableNumber);
                        btn.setBackground(Color.GREEN);
                    }

                    System.out.println("Bàn đã chọn: " + selectedTables);
                });

                panel.add(btn);
            }
        }
    }

// Lấy danh sách bàn đã chọn
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 3, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        jButton1.setBackground(new java.awt.Color(153, 153, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Xác nhận");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        // Nếu chưa chọn bàn nguồn và bàn đích, thì kiểm tra xem có đang gộp bàn không
        if (selectedTables.isEmpty() && selectedTargetTables.isEmpty()) {
            if (!Message.confirm(null, "Bạn có chắc không muốn gộp bàn hoặc chuyển bàn không?")) {
                return;
            }
        }

        // Trường hợp CHUYỂN BÀN
        if (!selectedTables.isEmpty() && !selectedTargetTables.isEmpty()) {
            System.out.println(selectedSeats);
            System.out.println(requiredSeats);
            if (selectedSeats < requiredSeats) {
                Message.warning(null, "Bạn chưa chọn đủ số chỗ cần thiết để chuyển!");
                return;
            }

            boolean confirm = Message.confirm(null, "Bạn có muốn thực hiện chuyển bàn ngay không?");

            if (confirm) {
                boolean success = new TableDAO().chuyenBan(selectedTables, selectedTargetTables);
                if (success) {
                    Message.info(null, "Chuyển bàn thành công!");
                    selectedTables.clear();
                    selectedTargetTables.clear();
                    selectedSeats = 0;
                    selectedZone = null;
                    dispose();
                } else {
                    Message.error(null, "Chuyển bàn thất bại!");
                }
            }
            return;
        }

        // Trường hợp GỘP BÀN
        if (selectedTables.size() <= 1) {
            Message.warning(this, "Bạn phải chọn ít nhất 2 bàn để gộp!");
            return;
        }

        boolean saveCustomerInfo = Message.confirm(this, "Khách hàng muốn lưu thông tin không?");
        String customerName = null, phoneNumber = null;

        if (saveCustomerInfo) {
            DialogThongTinKhachHang customerInfoDialog = new DialogThongTinKhachHang(
                    (Frame) SwingUtilities.getWindowAncestor(this), true);
            customerInfoDialog.setVisible(true);
            customerName = customerInfoDialog.getTenKhach();
            phoneNumber = customerInfoDialog.getSoDienThoai();
        }

        // Tạo hóa đơn mới
        OrderEntity order = new OrderEntity();
        order.setCashierId(Auth.user.getUserId());
        order.setStatus("new");
        order.setCustomerName(customerName);
        order.setCustomerPhone(phoneNumber);

        int newOrderId = new OrderDAO().createOrder(order);

        if (newOrderId > 0) {
            // Gộp tất cả bàn vào hóa đơn mới
            for (String tableIdStr : selectedTables) {
                int tableId = Integer.parseInt(tableIdStr);

                // Thêm vào bảng MergeTable (liên kết bàn với hóa đơn)
                MergeTableEntity mtb = new MergeTableEntity();
                mtb.setOrderId(newOrderId);
                mtb.setTableId(tableId);
                new MergeTableDAO().insertMergeTable(mtb);

                // Cập nhật trạng thái bàn thành "occupied"
                TableEntity tb = new TableEntity();
                tb.setTableId(tableId);
                tb.setStatus("occupied");
                new TableDAO().updateTableStatus(tb);
            }

            // Hiển thị giao diện tạo đơn với hóa đơn vừa tạo
            MainForm mainForm = (MainForm) SwingUtilities.getWindowAncestor(this);
            PanelTaoDon taodon = new PanelTaoDon();
            taodon.setTable(String.join(", ", selectedTables)); // Hiển thị các bàn đã gộp
            taodon.setOrder(String.valueOf(newOrderId)); // Gán ID hóa đơn mới
            mainForm.showPanel(taodon);
        } else {
            Message.error(this, "Lỗi khi tạo Order!");
        }

        // Đóng dialog sau khi hoàn thành
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(DialogGopBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogGopBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogGopBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogGopBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogGopBan dialog = new DialogGopBan(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
