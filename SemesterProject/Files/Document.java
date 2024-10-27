package Files;

/**
 * Document extends MyFile, and it is used for document(odt,docx) file types
 */
public class Document extends File {
    /**
     * Constructor Document(int fileSize, String fileDir, String fileName)
     * <p>
     * Creates new Document with given file size,directory, name
     * assigns file name value to fileName variable(Same with fileDir,fileSize)
     * assigns fileIcon new icon(Document file icon)
     * file name-should not include fullPath to the file)
     * changes document type variable
     * changes file size variable
     *
     * @param fileDir-file directory
     * @param fileName     - file name
     */
    public Document(String fileDir, String fileName) {
        super(fileDir, fileName);
        String iconPath = "./Icons/document.png";
        icon = new Icon(iconPath, iconWidth, iconHeight);
        String docType = getFileNameExtension(fileName);

        if (docType.equals("pdf")) {
            exProgram = "okular";
        } else {
            exProgram = "libreoffice";
        }
    }
}
