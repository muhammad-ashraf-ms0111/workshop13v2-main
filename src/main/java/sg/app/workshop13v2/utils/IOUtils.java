package sg.app.workshop13v2.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtils {
  private static final Logger logger = LoggerFactory.getLogger(IOUtils.class);

    public static void createDir(String path) {
        File dir = new File(path);
        boolean isDirCreated = dir.mkdirs();
        logger.info("dir created > " + isDirCreated);
        if (isDirCreated) {
            String osName = System.getProperty("os.name");
            if (!osName.contains("Windows")) {
                try {
                    String perm = "rwxrwx---";
                    Set<PosixFilePermission> permissions = PosixFilePermissions.fromString(perm);
                    Files.setPosixFilePermissions(dir.toPath(), permissions);
                } catch (IOException e) {
                    logger.error("Error ", e);
                }
            }
        }
    }
}

