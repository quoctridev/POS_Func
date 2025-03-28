/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.cell;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.DefaultCellEditor;
import javax.swing.JPanel;

/**
 *
 * @author ASUS
 */
public class ChinhSuaBang extends DefaultCellEditor {

    private SuKienHanhDong event;
    private boolean useRUD;

    public ChinhSuaBang(boolean useRUD, SuKienHanhDong event) {
        super(new JCheckBox());
        this.useRUD = useRUD;
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean isSelected, int row, int column) {
        JPanel action = useRUD ? new PanelRUD() : new PanelUD();

        if (useRUD) {
            ((PanelRUD) action).initEvent(event, row);
        } else {
            ((PanelUD) action).initEvent(event, row);
        }

        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
}
    