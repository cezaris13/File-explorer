package Files;

import javax.swing.*;

public class CustomJLabel extends JLabel {
    public MyFile file=new MyFile();
    public CustomJLabel(String name,ImageIcon icon,int horizontalAlignment,MyFile file){
        super(name,icon,horizontalAlignment);
        this.file=file;
    }
    public CustomJLabel(){
        super();
        this.file=new MyFile();
    }
}
