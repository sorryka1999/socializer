package socializer.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.InputStream;

@UtilityClass
public class CustomFileUtils {

    public static File getFileFromResourcesAsFile(String filePath) {
        File file = new File(String.format("./%s", filePath));
        if (!file.exists()) {
            InputStream templateInputStream = CustomFileUtils.class
                    .getClassLoader()
                    .getResourceAsStream(filePath);
            try {
                FileUtils.copyInputStreamToFile(templateInputStream, file);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return file;
    }

}
