package Files;

import Exceptions.FileIsMissingException;
import Exceptions.IncorrectFileNameException;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.FileSystems;

/**
 * Base file Class, it is used to store basic file data:
 * file size, file name, file directory, creation time, modification time,
 * execution program
 */
public class File implements Cloneable, Serializable {
    public String fileDir;
    public String name = "";
    public Icon icon;
    public FileType fileType;
    public int iconWidth = 65;
    public int iconHeight = 65;

    /**
     * Constructor MyFile(int fileSize, String fileDir, String fileName)
     * <p>
     * Creates New MyFile with given file size,directory, name
     * assigns file name value to fileName variable(Same with fileDir,fileSize)
     * assigns fileIcon new icon(txt file icon)
     * file name-should not include fullPath to the file)
     * creates Modification date and creation date
     */
    public File(String fileDir, String fileName, java.io.File file) {
        this.fileDir = fileDir;
        this.name = fileName;
        String iconPath = "";

        if (file.isDirectory()) {
            fileType = FileType.Directory;
        } else {
            fileType = FileType.getFileType(file.getName());
        }

        switch (fileType) {
            case Image -> iconPath = "../Icons/image.png";
            case Document -> iconPath = "../Icons/document.png";
            case Media -> iconPath = "../Icons/media.png";
            case Directory -> iconPath = "../Icons/folder.png";
            case Default -> iconPath = "../Icons/file.png";
        }

        icon = new Icon(iconPath, iconWidth, iconHeight);
    }

    @Override
    public File clone() throws CloneNotSupportedException {
        return (File) super.clone();
    }

    /**
     * Method String getFileName()
     * <p>
     * returns file name
     *
     * @return fileName(String)
     */
    public final String getName() {
        return name;
    }

    /**
     * Method openFile(String exProgram, String fileName)
     * <p>
     * <p>
     * this method tries to open file using given execution program and its fileName
     * checks if correct file name and if file exists
     * if yes-tries to open
     * else throws exception
     */
    public void openFile() throws FileIsMissingException {
        String separator = FileSystems.getDefault().getSeparator();
        String absoluteFileDirectory = fileDir + separator + name;
        System.out.println(name);
        java.io.File tmpFile = new java.io.File(absoluteFileDirectory);
        if (isCorrectFileName(name))
            throw new IncorrectFileNameException("Incorrect filename : " + name);

        if (!tmpFile.exists())
            throw new FileIsMissingException("File not Found", name);

        editFile(tmpFile);
    }

    /**
     * Method isCorrectFileName(String fileName)
     * <p>
     * this method checks if given file name is valid
     * Invalid characters: @$%\\/:*?\"'>|~`#^+={}[];!
     * if yes-returns true
     * else returns false
     *
     * @param fileName - file name
     * @return Boolean
     */
    private boolean isCorrectFileName(String fileName) {
        String illegalCharacters = "@$%&\\/:*?\"'<>|~`#^+={}[];!";
        for (int i = 0; i < fileName.length(); i++)
            for (int j = 0; j < illegalCharacters.length(); j++)
                if (fileName.charAt(i) == illegalCharacters.charAt(j))
                    return true;
        return false;
    }

    private void editFile(final java.io.File file) {
        if (!Desktop.isDesktopSupported())
            return;

        Desktop desktop = Desktop.getDesktop();
        if (!desktop.isSupported(Desktop.Action.EDIT))
            return;

        try {
            desktop.edit(file);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
