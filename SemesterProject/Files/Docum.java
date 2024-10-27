package Files;

import java.io.File;

/**
 * Docum extends MyFile and it is used for document(odt,docx) file types
 */
public class Docum extends MyFile {
    /**
     * Docum class has additional variables:
     * wordCount
     * pages
     * docType - document type(docx, odt)
     */
    private int wordCount;
    private int pages;
    private String docType;

    private String iconPath = "./Icons/document.png";

    /**
     * Constructor Docum()
     * <p>
     * Creates empty Docum object
     * changes word count and pages count to 0
     * changes execution program to empty string
     */
    public Docum() {
        super();
        wordCount = 0;
        pages = 0;
        exProgram = "";
    }

    /**
     * Constructor Docum(String fileName)
     * <p>
     * Creates New empty Docum with given file name
     * assigns file name value to fileName variable
     * assigns fileIcon new icon(image file icon)
     * file name- should not include fullPath to the file)
     *
     * @param fileName(String)
     */
    public Docum(String fileName) {
        this(0, "", "");
        this.fileName = fileName;
        fileIcon = new Icon(iconPath, iconWidth, iconHeight);
    }

    /**
     * Constructor Docum(int fileSize, String fileDir, String fileName)
     * <p>
     * Creates new Docum with given file size,directory, name
     * assigns file name value to fileName variable(Same with fileDir,fileSize)
     * assigns fileIcon new icon(Document file icon)
     * file name-should not include fullPath to the file)
     * changes document type variable
     * changes filesize variable
     *
     * @param fileSize-file size
     * @param fileDir-file  directory
     * @param fileName      - file name
     */
    public Docum(int fileSize, String fileDir, String fileName) {
        super(fileSize, fileDir, fileName);
        fileIcon = new Icon(iconPath, iconWidth, iconHeight);
        setExtensionProgramAndSize(fileName);
    }

    /**
     * Method void setExProgram(String exProgram)
     * <p>
     * Changes execution program
     *
     * @param exProgram - execution program
     */
    public void setExProgram(String exProgram) {
        this.exProgram = exProgram;
    }

    /**
     * Method String setFileName(String fileName)
     * <p>
     * sets new fileName, and changes Docum type variable,
     * ads execution program and changes fileSize variable
     * word count and page count is set to 0
     */
    public void setFileName(String fileName) {
        super.setFileName(fileName);
        setExtensionProgramAndSize(fileName);
    }

    private void setExtensionProgramAndSize(String fileName) {
        docType = getFileNameExtension(fileName);

        if (docType.equals("pdf")) {
            exProgram = "okular";
        } else {
            exProgram = "libreoffice";
        }
        wordCount = 0;
        pages = 0;
        String separator = System.getProperty("file.separator");
        File file = new File(getFileDir() + separator + fileName);
        setFileSize(file.length());
    }

    /**
     * Method toString()
     * returns a string consisting of all Docum
     * variables description
     *
     * @return String of variables
     */
    public String toString() {
        return "CreationTime:" + getCreationTime() + "\n"
                + "FileSize: " + getFileSize() + "\n"
                + "ModificationDate: " + getModificationTime() + "\n"
                + "Filedir: " + getFileDir() + "\n"
                + "Filename: " + getFileName() + "\n"
                + "wordcount:" + wordCount + "\n"
                + "pages:" + pages + "\n"
                + "exProgram: " + exProgram + "\n"
                + "docType: " + docType;
    }
}
