import UI.*;

import java.util.List;
import javax.swing.JFrame;
import java.awt.Rectangle;

public class CustomLayout {

    public static void revalidate(JFrame frame, CustomPanel leftMenu, CustomPanel filePanel, List<CustomJLabel> fileList, List<CustomJLabelFolders> folderList) {
        int space = 20;
        Rectangle r = frame.getBounds();
        int x = r.width / space;
        int xx = r.width;
        int yy = r.height;
        int y = 85;
        int countx = 0;
        int county = 0;
        int initSpace = 25;
        while (3 * x < 4 * 65 && space > 0) {// if space size + icon size is 1.33 times bigger than Files.Icon size
            space--;
            x = r.width / space;
        }
        filePanel.panel.setLayout(null);
        for (CustomJLabelFolders customJLabelFolders : folderList) {
            if (countx + 4 > space) {
                county++;
                countx = 0;
            }
            customJLabelFolders.setBounds(countx * x + initSpace, county * y + initSpace,
                    customJLabelFolders.folderIcon.getWidth() + 20, customJLabelFolders.folderIcon.getHeight() + 20);
            countx++;
        }
        for (CustomJLabel customJLabel : fileList) {
            if (countx + 4 > space) {
                county++;
                countx = 0;
            }
            customJLabel.setBounds(countx * x + initSpace, county * y + initSpace,
                    customJLabel.file.fileIcon.getWidth() + 20, customJLabel.file.fileIcon.getHeight() + 20);
            countx++;
        }
        filePanel.panel.setSize(xx - leftMenu.getXSize() - 20, yy);
        filePanel.panel.revalidate();
        filePanel.panel.repaint();
    }
}
