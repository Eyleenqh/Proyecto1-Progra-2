package bibliotechquirosvargassemestreianno2018;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MenuIcon implements Border {

    private BufferedImage menuIcon;

    public MenuIcon(BufferedImage menuIcon) {
        this.menuIcon = menuIcon;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        int x0 = x + (width - menuIcon.getWidth()) / 2;
        int y0 = y + (height - menuIcon.getHeight()) / 2;
        g.drawImage(menuIcon, x0, y0, null);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }

}
