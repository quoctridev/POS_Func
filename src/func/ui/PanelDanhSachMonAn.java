/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package func.ui;

import func.entity.OrderDetailEntity;
import func.utils.Database;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PanelDanhSachMonAn extends javax.swing.JDialog {

    private JTable table;
    private DefaultTableModel tableModel;
    private int maThamChieu;

    /**
     * Creates new form DanhSachMonAn
     */
    public PanelDanhSachMonAn(java.awt.Frame parent, boolean modal, int maThamChieu) {
        super(parent, modal);
        this.maThamChieu = maThamChieu; // Lưu mã tham chiếu
        initComponents();
        load(maThamChieu); // Gọi load() với mã tham chiếu
    }
    private Map<Integer, Integer> productMap = new HashMap<>();

    private PanelDanhSachMonAn(JFrame jFrame, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setMaThamChieuTest(int maThamChieu) {
        this.maThamChieu = maThamChieu;
        // Nếu cần, cập nhật giao diện hoặc tải dữ liệu tương ứng
        loadData(maThamChieu);
    }

    private void loadData(int maThamChieu) {
        System.out.println("Đã nhận maThamChieu: " + maThamChieu);
        // Gọi API hoặc truy vấn DB theo mã tham chiếu
    }

    public void load(int orderId) {
        // Lấy dữ liệu OrderDetails từ database
        List<OrderDetailEntity> orderDetails = getOrderDetailsById(orderId);

        // Xóa dữ liệu cũ trong bảng
        DefaultTableModel model = (DefaultTableModel) tabSanPham.getModel();
        model.setRowCount(0);

        // Xóa dữ liệu cũ trong Map (Lưu trữ order_detail_id và product_id)
        productMap.clear();
        System.out.println(maThamChieu);
        // Duyệt qua danh sách OrderDetails và đưa vào bảng
        for (OrderDetailEntity orderDetail : orderDetails) {
            // Lưu order_detail_id vào Map để tiện xoá
            productMap.put(orderDetail.getOrderDetailId(), orderDetail.getProductId());

            // Lấy tên sản phẩm từ database
            String productName = getProductNameById(orderDetail.getProductId());

            // Thêm dữ liệu vào bảng
            model.addRow(new Object[]{
                productName, // Hiển thị tên sản phẩm thay vì ID
                orderDetail.getPrice() + " VND",
                orderDetail.getQuantity(),
                orderDetail.getStatus(), // Hiển thị trạng thái
                orderDetail.getNote(), // Hiển thị ghi chú
                "Xóa" // Chỉ hiển thị chữ "Xóa"
            });
        }

        // Thêm sự kiện click vào bảng
        tabSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tabSanPham.getSelectedRow(); // Lấy hàng được chọn
                if (selectedRow != -1) {
                    // Lấy Product Name từ bảng (cột đầu tiên)
                    String productName = (String) model.getValueAt(selectedRow, 0);

                    // Tìm Product ID từ Map
                    int productId = -1;
                    int orderDetailId = -1;
                    for (Map.Entry<Integer, Integer> entry : productMap.entrySet()) {
                        if (entry.getValue().equals(getProductIdByName(productName))) {
                            productId = entry.getValue();
                            orderDetailId = entry.getKey();
                            break;
                        }
                    }

                    // Hiển thị thông tin hoặc xử lý theo yêu cầu
                    System.out.println("Product Name: " + productName);
                    System.out.println("Product ID: " + productId);
                    System.out.println("Order Detail ID: " + orderDetailId);
                }
            }
        });
    }

    private String getProductNameById(int productId) {
        String sql = "SELECT product_name FROM Products WHERE product_id = ?";
        try {
            ResultSet rs = Database.query(sql, productId);
            if (rs.next()) {
                return rs.getString("product_name");
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Không tìm thấy sản phẩm";
    }

    private int getProductIdByName(String productName) {
        String sql = "SELECT product_id FROM Products WHERE product_name = ?";
        try {
            ResultSet rs = Database.query(sql, productName);
            if (rs.next()) {
                return rs.getInt("product_id");
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Trả về -1 nếu không tìm thấy
    }

    public static List<OrderDetailEntity> getOrderDetailsById(int orderId) {
        List<OrderDetailEntity> orderDetails = new ArrayList<>();

        String sql = "SELECT order_detail_id, order_id, product_id, quantity, price, note, status, created_at "
                + "FROM OrderDetails WHERE order_id = ?";

        ResultSet rs = null;
        try {
            rs = Database.query(sql, orderId);
            while (rs.next()) {
                OrderDetailEntity orderDetail = new OrderDetailEntity();
                orderDetail.setOrderDetailId(rs.getInt("order_detail_id"));
                orderDetail.setOrderId(rs.getInt("order_id"));
                orderDetail.setProductId(rs.getInt("product_id"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setPrice(rs.getBigDecimal("price"));
                orderDetail.setNote(rs.getString("note"));
                orderDetail.setStatus(rs.getString("status"));
                orderDetail.setCreatedAt(rs.getTimestamp("created_at"));

                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lấy dữ liệu OrderDetails", e);
        }

        return orderDetails;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabSanPham = new javax.swing.JTable();
        btnSua = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(153, 102, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Sản Phẩm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(346, 346, 346)
                .addComponent(jLabel1)
                .addContainerGap(347, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Ảnh Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Trạng Thái", "Note"
            }
        ));
        tabSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabSanPham);

        btnSua.setText("Sửa ");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jLabel2.setText("Số Lượng");

        jLabel3.setText("Trạng Thái ");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pending", "completed", "cooking", "cancel" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSoLuong)
                            .addComponent(jComboBox1, 0, 99, Short.MAX_VALUE))
                        .addGap(52, 52, 52)
                        .addComponent(btnSua)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSua)))
                .addGap(47, 47, 47))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // Lấy hàng được chọn
    int selectedRow = tabSanPham.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một món ăn để cập nhật!");
        return;
    }

    // Lấy giá trị từ ô nhập liệu
    int newQuantity;
    try {
        newQuantity = Integer.parseInt(txtSoLuong.getText().trim());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!");
        return;
    }
    
    String newStatus = (String) jComboBox1.getSelectedItem();

    // Lấy orderDetailId từ Map bằng cách tìm theo productId
    String productName = (String) tabSanPham.getValueAt(selectedRow, 0);
    int productId = getProductIdByName(productName);
    int orderDetailId = -1;

    for (Map.Entry<Integer, Integer> entry : productMap.entrySet()) {
        if (entry.getValue() == productId) {
            orderDetailId = entry.getKey();
            break;
        }
    }

    if (orderDetailId == -1) {
        JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu đơn hàng!");
        return;
    }

    // Cập nhật dữ liệu vào database
    String sql = "UPDATE OrderDetails SET quantity = ?, status = ? WHERE order_detail_id = ?";
    Database.update(sql, newQuantity, newStatus, orderDetailId);

    // Hiển thị thông báo thành công
    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");

    // Load lại dữ liệu sau khi cập nhật
    load(maThamChieu);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tabSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabSanPhamMouseClicked
        int selectedRow = tabSanPham.getSelectedRow();
        if (selectedRow != -1) {
            // Lấy dữ liệu từ bảng
            String productName = (String) tabSanPham.getValueAt(selectedRow, 0);
            String quantity = tabSanPham.getValueAt(selectedRow, 2).toString();
            String status = tabSanPham.getValueAt(selectedRow, 3).toString();

            // Điền vào ô nhập liệu
            txtSoLuong.setText(quantity);
            jComboBox1.setSelectedItem(status);
        }
    }//GEN-LAST:event_tabSanPhamMouseClicked

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
            java.util.logging.Logger.getLogger(PanelDanhSachMonAn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelDanhSachMonAn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelDanhSachMonAn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelDanhSachMonAn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PanelDanhSachMonAn dialog = new PanelDanhSachMonAn(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnSua;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabSanPham;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
