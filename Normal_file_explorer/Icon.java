import javax.swing.ImageIcon;
public class Icon{
    int width;
    int height;
    ImageIcon icon;
    public Icon(String dir,int width,int height){
        icon = new ImageIcon(dir);
        icon = new ImageIcon(icon.getImage().getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH));
        this.width   = icon.getImage().getWidth(null);
        this.height  = icon.getImage().getHeight(null);
    }
}
