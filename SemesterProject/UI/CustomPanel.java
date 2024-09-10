import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;

/**
 * CustomPanel class is used as a group of variables
 * panel x,y sizes
 * Jpanel panel
 * current directory
 * ScrollBar(is not used yet)
 *
 */
public class CustomPanel {
    private int x, y;
    JScrollPane scrollbar;
    private int xSize, ySize;
    JPanel panel;
    JScrollPane Scrollbar;
    static String directory;

    /**
     * Constructor CustomPanel()
     *
     * creates JPanel object and resizes it to 0 0 0 0(x,y,xSize,ySize)
     *
     */
    public CustomPanel() {
        panel = new JPanel();
        x = 0;
        y = 0;
        xSize = 0;
        ySize = 0;
    }

    /**
     * Constructor CustomPanel(int x,int y)
     *
     * creates JPanel object and resizes it to given sizes
     *
     * @param x-      x coordinate of panel
     * @param y(int)- y coordinate of panel
     */
    public CustomPanel(int x, int y) {
        panel = new JPanel();
        this.x = x;
        this.y = y;
        xSize = 0;
        ySize = 0;
    }

    /**
     * Constructor CustomPanel(int x,int y,int xSize,int ySize)
     *
     * creates JPanel object and resizes it to given sizes
     *
     * @param x(int)-     x coordinate of panel,
     * @param y(int)-     y coordinate of panel,
     * @param xSize(int)- x size of panel,
     * @param ySize(int)- y size of panel
     */
    public CustomPanel(int x, int y, int xSize, int ySize) {// topPanel and filePanel
        panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(xSize, ySize));
        panel.setLocation(x, y);
        this.x = x;
        this.y = y;
        setSize(xSize, ySize);
    }

    /**
     * Constructor CustomPanel(int x,int y,int xSize,int ySize, Jtree tree)
     *
     * creates JPanel object and adds to it JTree variable(file list)
     *
     * @param x(int)-      x coordinate of panel
     * @param y(int)-      y coordinate of panel
     * @param xSize(int)-  x size of panel
     * @param ySize(int)-  y size of panel
     * @param tree(JTree)- JTree Object containing fileList
     */
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

    /**
     * Method void setSize(int xSize,int ySize)
     *
     * Changes Panel dimension,xSize and ySize variables
     *
     * @param xSize - x panel size
     * @param ySize - y panel size
     */
    public void setSize(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        panel.setSize(xSize, ySize);
    }

    /**
     * Method void setSize(Dimension dimensions)
     *
     * Changes Panel dimension,xSize and ySize variables
     *
     * @param dimensions - panel dimensions
     */
    public void setSize(Dimension dimensions) {
        xSize = (int) dimensions.getWidth();
        ySize = (int) dimensions.getHeight();
        panel.setSize(dimensions);
    }

    /**
     *
     * @return x(int)
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return y(int)
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @return xSize(int)
     */
    public int getXSize() {
        return xSize;
    }

    /**
     *
     * @return ySize(int)
     */
    public int getYSize() {
        return ySize;
    }

    /**
     *
     * @param x - panel x coordinate
     */
    public void setX(int x) {
        this.x = x;
        panel.setLocation(x, y);
    }

    /**
     *
     * @param y - panel y coordinate
     */
    public void setY(int y) {
        this.y = y;
        panel.setLocation(x, y);
    }

    /**
     * Method void setXSize(int xSize)
     *
     * Changes panel xsize variable
     *
     * @param xSize - x panel size
     */
    public void setXSize(int xSize) {
        this.xSize = xSize;
        setSize(xSize, ySize);
    }

    /**
     * Method void setYSize(Int ySize)
     *
     * Changes panel Ysize variable
     *
     * @param ySize - y panel size
     */
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
