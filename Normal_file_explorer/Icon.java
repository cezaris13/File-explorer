import javax.swing.ImageIcon;
public class Icon{
    private int width;
    private int height;
    private ImageIcon icon;
    //modifikavimo laikas
    //sukurimo laikas final
    //dydis
    public Icon(){
        width = 0;
        height = 0;
        icon = new ImageIcon();
    }
    public Icon(String dir,int width,int height){
        icon = new ImageIcon(dir);
        icon = new ImageIcon(icon.getImage().getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH));
        this.width   = icon.getImage().getWidth(null);
        this.height  = icon.getImage().getHeight(null);
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public ImageIcon getIcon(){
        return icon;
    }
    public void setWidth(int width){
        this.width=width;
    }
    public void setHeight(int height){
        this.height=height;
    }
    public void setIcon(ImageIcon icon){
        this.icon=icon;
    }
    public void println(){
        System.out.println("width: "+width);
        System.out.println("height: "+height);
        System.out.println("icon: "+icon.toString());
    }
    // public static void main(String[] args){
    //     Icon tmp = new Icon();
    //     tmp.println();
    // }
}
