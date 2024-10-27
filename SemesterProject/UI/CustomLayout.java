package UI;

import java.util.List;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.util.stream.Stream;

public class CustomLayout {

    public static void revalidate(JFrame frame, CustomPanel leftMenu, CustomPanel filePanel, List<CustomJLabel> fileList, List<CustomJLabel> folderList) {
        int space = 20;
        Rectangle frameBounds = frame.getBounds();
        int cellWidth = frameBounds.width / space;
        int frameWidth = frameBounds.width;
        int frameHeight = frameBounds.height;
        int initialY = 85;
        int countX = 0;
        int countY = 0;
        final int initSpace = 25;

        int iconWidth = fileList.get(0).file.iconWidth;
        // Adjust space to ensure that cell width is not too large
        while (3 * cellWidth < 4 * iconWidth && space > 0) {
            space--;
            cellWidth = frameBounds.width / space;
        }
        filePanel.panel.setLayout(null);
        List<CustomJLabel> combiledJLabelList = Stream.concat(folderList.stream(), fileList.stream()).toList();

        for (CustomJLabel customJLabel : combiledJLabelList) {
            if (countX + 4 > space) {
                countY++;
                countX = 0;
            }
            customJLabel.setBounds(countX * cellWidth + initSpace, countY * initialY + initSpace,
                    customJLabel.file.icon.getWidth() + space, customJLabel.file.icon.getHeight() + space);
            countX++;
        }
        
        filePanel.panel.setSize(frameWidth - leftMenu.getXSize() - space, frameHeight);
        filePanel.panel.revalidate();
        filePanel.panel.repaint();
    }
}
