import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class CustomPanel {
    private int x, y;
    JScrollPane scrollbar;
    private int xSize, ySize;
    JPanel panel;
    JScrollPane Scrollbar;
    static String directory;

    public CustomPanel() {
        panel = new JPanel();
        x = 0;
        y = 0;
        xSize = 0;
        ySize = 0;
    }

    public CustomPanel(int x, int y) {
        panel = new JPanel();
        this.x = x;
        this.y = y;
        xSize = 0;
        ySize = 0;
    }

    public CustomPanel(int x, int y, int xSize, int ySize) {// topPanel and filePanel
        panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(xSize, ySize));
        panel.setLocation(x, y);
        this.x = x;
        this.y = y;
        setSize(xSize, ySize);
    }

    public CustomPanel(int x, int y, int xSize, int ySize, JTree tree) {// left panel
        panel = new JPanel();
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
    // public CustomPanel(int x,int y,Dimension pref){//topPanel
    // panel=new JPanel();
    // panel.setLayout(null);
    // panel.setPreferredSize(pref);
    // panel.setLocation(x,y);
    // setSize(xSize,ySize);
    // // panel.setBackground(Color.gray);
    // // scrollbar=new JScrollPane(panel);
    // //
    // scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    // //
    // scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    // // panel.add(scrollbar);// fix to add with buttons
    // }
}
