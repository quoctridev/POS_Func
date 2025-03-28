/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.cell;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ASUS
 */
public class KetHopBang extends DefaultTableCellRenderer {

    private boolean useRUD; // Xác định dùng PanelRUD hay PanelUD

    public KetHopBang(boolean useRUD) {
        this.useRUD = useRUD;
    }

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSelected, boolean hasFocus, int row, int column) {
        Component com = super.getTableCellRendererComponent(jtable, o, isSelected, hasFocus, row, column);

        JPanel action = useRUD ? new PanelRUD() : new PanelUD();
        action.setBackground(isSelected || row % 2 != 0 ? com.getBackground() : Color.WHITE);

        return action;
    }
}
