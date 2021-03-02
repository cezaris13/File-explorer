import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class Panel{
    int x,y;
    JScrollPane scrollbar;
    int xSize,ySize;
    JPanel panel;
    JScrollPane Scrollbar;
    public Panel(){
        panel=new JPanel();
    }
    public Panel(int a,int b){
        panel=new JPanel();
        x=a;
        y=b;
    }
    public Panel(int x,int y,int xSize,int ySize){//topPanel
        panel=new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(xSize,ySize));
        panel.setLocation(x,y);
    }
    public void setSize(int xSize,int ySize){
        this.xSize=xSize;
        this.ySize=ySize;
        panel.setSize(xSize,ySize);
    }
    public Panel(int x,int y,int xSize,int ySize,JTree tree){//left panel
        panel=new JPanel();
        panel.setBounds(x,y,xSize,ySize);
        panel.setLayout(new BorderLayout());
        scrollbar=new JScrollPane(tree);
        scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollbar);
    }

    // public Panel(){
    //     panel=newJPanel();
    // }
}
