package Files;

import Exceptions.FileIsMissingException;
import Exceptions.IncorrectFileNameException;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.FileSystems;

/**
 * Base file Class, it is used to store basic file data:
 * file size, file name, file directory, creation time, modification time,
 * execution program
 */
public class Directory implements Cloneable, Serializable {
    /**
     * fileIcon- An Icon class object which saves info about specific file type icon
     * creationTime saves time when MyFile object was created
     * exProgram saves execution program in string
     */
    public String fileDir;
    public String name = "";
    public String exProgram;
    public Icon icon;
    public int iconWidth = 65;
    public int iconHeight = 65;

    private final String iconPath = "../Icons/folder.png";

    /**
     * Constructor MyFile()
     * <p>
     * Creates empty MyFile object
     * adds file icon
     */
    public Directory() {
        this("", "");
        icon = new Icon(iconPath, iconWidth, iconHeight);
    }

    /**
     * Constructor MyFile(int fileSize, String fileDir, String fileName)
     * <p>
     * Creates New MyFile with given file size,directory, name
     * assigns file name value to fileName variable(Same with fileDir,fileSize)
     * assigns fileIcon new icon(txt file icon)
     * file name-should not include fullPath to the file)
     * creates Modification date and creation date
     */
    public Directory(String fileDir, String fileName) {
        this.fileDir = fileDir;
        this.name = fileName;
        icon = new Icon(iconPath, iconWidth, iconHeight);
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
        try {
            String absoluteFileDirectory = fileDir + separator + name;
            System.out.println(name);
            java.io.File tmpFile = new File(absoluteFileDirectory);
            if (isCorrectFileName(name))
                throw new IncorrectFileNameException("Incorrect filename : " + name);

            if (!tmpFile.exists())
                throw new FileIsMissingException("File not Found", name);

            ProcessBuilder processBuilder = new ProcessBuilder(exProgram, absoluteFileDirectory);
            processBuilder.start();
        } catch (IOException ignored) {
        }
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

    @Override
    public Directory clone() throws CloneNotSupportedException {
        return (Directory) super.clone();
    }
}
