package Files;
import java.util.Date;
public interface B extends A {
    abstract Date getCreationTime();
    abstract Date getModificationTime();
}
