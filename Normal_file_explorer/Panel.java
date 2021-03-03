import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
public class Panel{
    int x,y;
    JScrollPane scrollbar;
    int xSize,ySize;
    JPanel panel;
    JScrollPane Scrollbar;
    static String dirpath;
    public void setSize(int xSize,int ySize){
        this.xSize=xSize;
        this.ySize=ySize;
        panel.setSize(xSize,ySize);
    }
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
        setSize(xSize,ySize);
    }
    public Panel(int x,int y,Dimension pref){//topPanel
        panel=new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(pref);
        panel.setLocation(x,y);
        setSize(xSize,ySize);
        // panel.setBackground(Color.gray);
        scrollbar=new JScrollPane(panel);
        scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // panel.add(scrollbar);// fix to add with buttons
    }

    //get/set dalyka padaryti
    public Panel(int x,int y,int xSize,int ySize,JTree tree){//left panel
        panel=new JPanel();
        panel.setBounds(x,y,xSize,ySize);
        setSize(xSize,ySize);
        panel.setLayout(new BorderLayout());
        scrollbar=new JScrollPane(tree);
        scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollbar);
    }
}
