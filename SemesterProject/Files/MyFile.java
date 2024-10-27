package Files;

import java.io.Serializable;
import java.util.Date;

/**
 * Base file Class, it is used to store basic file data:
 * file size, file name, file directory, creation time, modification time,
 * execution program
 */
public class MyFile extends EditableFile implements Cloneable, Serializable {
    /**
     * fileIcon- An Icon class object which saves info about specific file type icon
     * creationTime saves time when MyFile object was created
     * exProgram saves execution program in string
     */
    public Icon fileIcon;
    private final Date creationTime;

    private final String iconPath = "../Icons/file.png";
    protected int iconWidth = 65;
    protected int iconHeight = 65;

    /**
     * Constructor MyFile()
     * <p>
     * Creates empty MyFile object
     * adds file icon
     */
    public MyFile() {
        this(0, "", "");
        fileIcon = new Icon(iconPath, iconWidth, iconHeight);
    }

    /**
     * Constructor MyFile(String fileName)
     * <p>
     * Creates New empty MyFile with given file name
     * assigns file name value to fileName variable
     * assigns fileIcon new icon(txt file icon)
     * file name- should not include fullPath to the file)
     */
    public MyFile(String fileName) {
        this(0, "", "");
        fileIcon = new Icon(iconPath, iconWidth, iconHeight);
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
    public MyFile(int fileSize, String fileDir, String fileName) {
        this.fileSize = fileSize;
        this.fileDir = fileDir;
        this.fileName = fileName;
        creationTime = new Date();
        modificationTime = new Date();
        fileIcon = new Icon(iconPath, iconWidth, iconHeight);
    }

    /**
     * Method Date getCreationtime()
     * <p>
     * returns creation date
     *
     * @return creation time(Date)
     */
    public final Date getCreationTime() {
        return creationTime;
    }

    /**
     * Method String getExProgram()
     * <p>
     * returns execution program
     *
     * @return exProgram(String)
     */
    public String getExProgram() {
        return exProgram;
    }

    /**
     * Method String getFiledir()
     * <p>
     * returns file directory
     *
     * @return fileDir(String)
     */
    public final String getFileDir() {
        return fileDir;
    }

    /**
     * Method String getFileName()
     * <p>
     * returns file name
     *
     * @return fileName(String)
     */
    public final String getFileName() {
        return fileName;
    }

    /**
     * Method String getFilesize()
     *
     * @return fileSize(long)
     */
    public final long getFileSize() {
        return fileSize;
    }

    /**
     * Method String getModificationTime()
     *
     * @return modificationTime(Date)
     */
    public final Date getModificationTime() {
        return modificationTime;
    }

    /**
     * Method String setFileDir(String fileDir)
     * <p>
     * sets new File directory
     *
     * @param fileDir - file directory
     */
    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    /**
     * Method String setFileName(String fileName)
     * <p>
     * sets new fileName
     *
     * @param fileName - file Name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Method String setFileSize()
     * <p>
     * sets file Size
     *
     * @param fileSize - file size
     */
    public final void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
    
    /**
     * Method toString()
     * returns a string consisting of all MyFile
     * variables description
     *
     * @return String of variables
     */
    @Override
    public String toString() {
        return "CreationTime:" + getCreationTime() + "\n"
                + "FileSize: " + getFileSize() + "\n"
                + "ModificationDate: " + getModificationTime() + "\n"
                + "Filedir: " + getFileDir() + "\n"
                + "Filename: " + getFileName() + "\n"
                + "exProgram: " + exProgram + "\n";
    }

    /**
     * Method clone()
     *
     * @throws CloneNotSupportedException returns Object type object(Clone)
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getFileNameExtension(String fileName) {
        int j = fileName.lastIndexOf('.');
        if (j >= 0)
            return fileName.substring(j + 1);

        return "";
    }
}
