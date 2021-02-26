// import java.awt.event.*;
// import javax.swing.*;

// public class fileApp{
//         static int currx=500;
//         static int curry=600;
//         public static void main(String[] args) {
//                 JFrame f=new JFrame("Button Example");
//                 final JTextField tf=new JTextField();
//                 tf.setBounds(50,50, 150,20);
//                 JButton b=new JButton("Click Here");
//                 b.setBounds(50,100,95,30);
//                 b.addActionListener(new ActionListener(){
//                                 public void actionPerformed(ActionEvent e){
//                                         tf.setText("Welcome to Javatpoint.");
//                                         b.setBounds(900,100,95,30);
//                                 }
//                         });
//                 b.addComponentListener(new ComponentAdapter() {
//                                 @Override
//                                 public void componentResized(ComponentEvent e) {
//                                         System.out.println("Resized to " + e.getComponent().getSize());
//                                 }
//                                 @Override
//                                 public void componentMoved(ComponentEvent e) {
//                                         System.out.println("Moved to " + e.getComponent().getLocation());
//                                 }
//                         });
//                 f.add(b);f.add(tf);
//                 f.setSize(currx,curry);
//                 f.setLayout(null);
//                 f.setVisible(true);
//         }
// }
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class fileApp {
  public static void main(String[] args) {
    JPanel panel = new JPanel() {
      @Override
      public Dimension getPreferredSize() {
        return new Dimension(200, 200);
      }
    };
    JButton[] bb = new JButton[10];

    
    // JButton b=new JButton(new ImageIcon("/home/pijus/Desktop/Java/Normal_file_explorer/folder.png"));
    // b.setBounds(50,100,95,30);
    // b.addActionListener(new ActionListener(){
    //                 public void actionPerformed(ActionEvent e){
    //                         // b.setBounds(900,100,95,30);
    //                 }
    //         });
   
    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.addTab("test", panel);
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    frame.add(tabbedPane);
    ImageIcon folder = new ImageIcon("/home/pijus/Desktop/Java/Normal_file_explorer/folder.png");
    int width= folder.getImage().getWidth(null);
    int height= folder.getImage().getHeight(null);
    for(int i=0;i<10;i++){
      bb[i]=new JButton(new ImageIcon("/home/pijus/Desktop/Java/Normal_file_explorer/folder.png"));
      frame.add(bb[i]);
    }
    // frame.add(b);
    // frame.pack();
    frame.setSize(500,500);
    frame.setVisible(true);
    frame.addComponentListener(new ComponentAdapter() {
      public void componentResized(ComponentEvent componentEvent) {
          // b.setBounds(componentEvent.getComponent().getSize().width/2,componentEvent.getComponent().getSize().height/2,95,30);
          int x = componentEvent.getComponent().getSize().width/10;
          for(int i=1;i<10;i++){
            bb[i].setBounds(i*x,100,width,height);
          }
      }
  });
  }
}
