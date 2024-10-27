package Files;

/**
 * Base file Class, it is used to store basic file data:
 * file size, file name, file directory, creation time, modification time,
 * execution program
 */
public class File extends Directory {
    /**
     * Constructor MyFile(int fileSize, String fileDir, String fileName)
     * <p>
     * Creates New MyFile with given file size,directory, name
     * assigns file name value to fileName variable(Same with fileDir,fileSize)
     * assigns fileIcon new icon(txt file icon)
     * file name-should not include fullPath to the file)
     * creates Modification date and creation date
     */
    public File(String fileDir, String fileName) {
        this.fileDir = fileDir;
        this.name = fileName;
        String iconPath = "../Icons/file.png";
        icon = new Icon(iconPath, iconWidth, iconHeight);
        exProgram = "kate";
    }

    public String getFileNameExtension(String fileName) {
        int j = fileName.lastIndexOf('.');
        if (j >= 0)
            return fileName.substring(j + 1);

        return "";
    }

    @Override
    public File clone() throws CloneNotSupportedException {
        return (File) super.clone();
    }
}
