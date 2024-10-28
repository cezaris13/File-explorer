package UI;

import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Stream;

public class CustomLayout {
    public static void revalidate(CustomPanel filePanel, List<CustomJLabel> fileList, List<CustomJLabel> folderList, int frameWidth, int leftMenuWidth) {
        List<CustomJLabel> combinedJLabelList = Stream.concat(folderList.stream(), fileList.stream()).toList();

        filePanel.setWidth(frameWidth - leftMenuWidth);

        int horizontalSpacing = 20;
        int verticalSpacing = 25;
        int padding = 25;

        // we can assume that iconWidth and height is the same for all icons.
        int iconWidth = fileList.get(0).file.iconWidth;
        int iconHeight = fileList.get(0).file.iconHeight;

        // filePanel = 2*padding + n * cellWidth + (n - 1) * spacing =>
        // n = (filePanel - leftMenu.width -2*padding + spacing)/(cellWidth + spacing)
        IntFunction<Integer> calculateCellsInOneLine = s -> (filePanel.panel.getWidth() - 2 * padding + s) / (iconWidth + s);

        int possibleCellsInOneLine = calculateCellsInOneLine.apply(horizontalSpacing);

        // change spacing only if the files and folders does not fit in one line
        // if it does not fit, reduce spacing by one until you can squeeze in another icon
        if (possibleCellsInOneLine < combinedJLabelList.size())
            for(; horizontalSpacing >=0; horizontalSpacing-- )
                if (calculateCellsInOneLine.apply(horizontalSpacing) == possibleCellsInOneLine + 1)
                    break;

        filePanel.panel.setLayout(null);

        possibleCellsInOneLine = calculateCellsInOneLine.apply(horizontalSpacing);
        int index = 0;
        for (CustomJLabel customJLabel : combinedJLabelList) {
            int cellXPosition = padding + (index % possibleCellsInOneLine) * (iconWidth + horizontalSpacing);
            int cellYPosition = padding + (index / possibleCellsInOneLine) * (iconHeight + verticalSpacing);
            customJLabel.setBounds(cellXPosition, cellYPosition, iconWidth + horizontalSpacing, iconHeight + verticalSpacing);
            index++;
        }

        filePanel.panel.revalidate();
        filePanel.panel.repaint();
    }
}
