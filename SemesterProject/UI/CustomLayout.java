package UI;

import java.util.List;
import javax.swing.JFrame;
import java.util.function.IntFunction;
import java.util.stream.Stream;

public class CustomLayout {
    public static void revalidate(JFrame frame, CustomPanel leftMenu, CustomPanel filePanel, List<CustomJLabel> fileList, List<CustomJLabel> folderList) {
        List<CustomJLabel> combinedJLabelList = Stream.concat(folderList.stream(), fileList.stream()).toList();

        int frameWidth = frame.getBounds().width;
        filePanel.setWidth(frameWidth - leftMenu.getWidth());

        int spacing = 20;
        int verticalSpacing = 25;
        int padding = 25;

        // we can assume that iconWidth and height is the same for all icons.
        int iconWidth = fileList.get(0).file.iconWidth;
        int iconHeight = fileList.get(0).file.iconHeight;

        // filePanel = 2*padding + n * cellWidth + (n - 1) * spacing =>
        // n = (filePanel - leftMenu.width -2*padding + spacing)/(cellWidth + spacing)
        IntFunction<Integer> calculateCellsInOneLine = s -> (filePanel.panel.getWidth() - 2 * padding + s) / (iconWidth + s);

        int possibleCellsInOneLine = calculateCellsInOneLine.apply(spacing);

        // change spacing only if the files and folders does not fit in one line
        // if it does not fit, reduce spacing by one until you can squeeze in another icon
        if (possibleCellsInOneLine < combinedJLabelList.size())
            for(; spacing >=0; spacing-- )
                if (calculateCellsInOneLine.apply(spacing) == possibleCellsInOneLine + 1)
                    break;

        filePanel.panel.setLayout(null);

        possibleCellsInOneLine = calculateCellsInOneLine.apply(spacing);
        int index = 0;
        for (CustomJLabel customJLabel : combinedJLabelList) {
            int cellXPosition = padding + (index % possibleCellsInOneLine) * (iconWidth + spacing);
            int cellYPosition = padding + (index / possibleCellsInOneLine) * (iconHeight + verticalSpacing);
            customJLabel.setBounds(cellXPosition, cellYPosition, iconWidth + spacing, iconHeight + verticalSpacing);
            index++;
        }

        filePanel.panel.revalidate();
        filePanel.panel.repaint();
    }
}
