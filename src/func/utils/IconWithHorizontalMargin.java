/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.utils;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author quoctris.dev
 */
public class IconWithHorizontalMargin implements Icon {

    private final Icon icon;
    private final int horizontalMargin;

    public IconWithHorizontalMargin(Icon icon, int horizontalMargin) {
        this.icon = icon;
        this.horizontalMargin = horizontalMargin;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        icon.paintIcon(c, g, x + horizontalMargin, y);
    }

    @Override
    public int getIconWidth() {
        return icon.getIconWidth() + horizontalMargin * 2;
    }

    @Override
    public int getIconHeight() {
        return icon.getIconHeight();
    }
}
