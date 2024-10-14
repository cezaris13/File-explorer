package Files;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class Panel {
    private int x = 0;
    private int y = 0;
    JScrollPane scrollbar;
    private int xSize = 0;
    private int ySize = 0;
    JPanel panel = new JPanel();
    JScrollPane Scrollbar;
    static String dirPath;

    public Panel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Panel(int x, int y, int xSize, int ySize) {// topPanel and filePanel
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(xSize, ySize));
        panel.setLocation(x, y);
        this.x = x;
        this.y = y;
        setSize(xSize, ySize);
    }

    public Panel(int x, int y, int xSize, int ySize, JTree tree) {// left panel
        panel.setBounds(x, y, xSize, ySize);
        this.x = x;
        this.y = y;
        setSize(xSize, ySize);
        panel.setLayout(new BorderLayout());
        scrollbar = new JScrollPane(tree);
        scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollbar);
    }

    public void setSize(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        panel.setSize(xSize, ySize);
    }

    public void setSize(Dimension dimensions) {
        xSize = (int) dimensions.getWidth();
        ySize = (int) dimensions.getHeight();
        panel.setSize(dimensions);

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return ySize;
    }

    public void setX(int x) {
        this.x = x;
        panel.setLocation(x, y);
    }

    public void setY(int y) {
        this.y = y;
        panel.setLocation(x, y);
    }

    public void setXSize(int xSize) {
        this.xSize = xSize;
        setSize(xSize, ySize);
    }

    public void setYSize(int ySize) {
        this.ySize = ySize;
        setSize(xSize, ySize);
    }

    public void println() {
        System.out.println("x: " + x);
        System.out.println("y: " + y);
        System.out.println("xSize: " + xSize);
        System.out.println("ySize: " + ySize);
        System.out.println("Panel: " + panel.toString());
        System.out.println("dirpath: " + dirPath);
        System.out.println("");
    }
}
