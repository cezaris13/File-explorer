import UI.*;

import java.util.List;
import javax.swing.JFrame;
import java.awt.Rectangle;

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

        for (CustomJLabel customJLabel : folderList) {
            if (countX + 4 > space) {
                countY++;
                countX = 0;
            }
            customJLabel.setBounds(countX * cellWidth + initSpace, countY * initialY + initSpace,
                    customJLabel.file.icon.getWidth() + space, customJLabel.file.icon.getHeight() + space);
            countX++;
        }
        for (CustomJLabel customJLabel : fileList) {
            if (countX + 4 > space) {
                countY++;
                countX = 0;
            }
            customJLabel.setBounds(countX * cellWidth + initSpace, countY * initialY + initSpace,
                    customJLabel.file.icon.getWidth() + space, customJLabel.file.icon.getHeight() + space);
            countX++;
        }

        // countY = positionLabelsInGrid(folderList, cellWidth, initialY, initSpace, space,  countX, countY);
        // // Position files in the grid
        // positionLabelsInGrid(fileList, cellWidth, initialY, initSpace, space, countX, countY);

        filePanel.panel.setSize(frameWidth - leftMenu.getXSize() - space, frameHeight);
        filePanel.panel.revalidate();
        filePanel.panel.repaint();
    }

    // private static int positionLabelsInGrid(List<CustomJLabel> labels, int cellWidth, int initialY,
    //                                         int initSpace, int space, int countX, int countY) {
    //     for (CustomJLabel customJLabel : labels) {
    //         if (countX + 4 > space) {
    //             countY++;
    //             countX = 0;
    //         }
    //         customJLabel.setBounds(countX * cellWidth + initSpace, countY * initialY + initSpace,
    //                 customJLabel.file.fileIcon.getWidth() + space, customJLabel.file.fileIcon.getHeight() + space);
    //         countX++;
    //     }
    //     return countY; // Return the current Y count for possible further use
    // }
}
