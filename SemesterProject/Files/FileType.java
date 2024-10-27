package Files;

public enum FileType {
    Image,
    Document,
    Media,
    Directory,
    Default;

    public static FileType getFileType(String file) {
        String extension = "";
        int j = file.lastIndexOf('.');
        if (j >= 0)
            extension = file.substring(j + 1); // something.txt -> txt

        return switch (extension) {
            case "png", "jpg" -> FileType.Image;
            case "pdf", "tex", "doc" -> FileType.Document;
            case "mp3", "mp4", "mkv" -> FileType.Media;
            default -> FileType.Default;
        };
    }
}
