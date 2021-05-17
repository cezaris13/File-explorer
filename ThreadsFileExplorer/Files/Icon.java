package Files;

import java.io.Serializable;

import javax.swing.ImageIcon;
/**
 * Class Icon is used to store information about specific icon
 *  */
public class Icon implements Serializable{
    private int width;
    private int height;
    private ImageIcon icon;
     /**
       * Constructor Icon()
       *
       * Creates empty Icon object
       * changes picture width and height to 0
       * creates empty ImageIcon object
       * */
    public Icon(){
        width = 0;
        height = 0;
        icon = new ImageIcon();
    }
    /**
       * Constructor Icon(String dir, int width,int height)
       *
       * Creates Icon object
       * adds specific icon to icon variable and scales it using given height and width
       * changes height and width to image height and width
       * changes picture width and height to 0
       * creates empty ImageIcon object
       * */
    public Icon(String dir,int width,int height){
        icon = new ImageIcon(dir);
        icon = new ImageIcon(icon.getImage().getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH));
        this.width   = icon.getImage().getWidth(null);
        this.height  = icon.getImage().getHeight(null);
    }
    /**
     * Method int getWidth()
     *
     * returns image width
     *
     * @return width(int)
     *
     *  */
    public int getWidth(){
        return width;
    }
    /**
     * Method int getHeight()
     *
     * returns image height
     *
     * @return height(int)
     *
     *  */
    public int getHeight(){
        return height;
    }
    /**
     * Method ImageIcon getIcon()
     *
     * returns image icon
     *
     * @return icon(ImageIcon)
     *
     *  */
    public ImageIcon getIcon(){
        return icon;
    }
}
