package lum.core.model;

import java.nio.file.Path;

/**
 * @param pathToDir
 * @param fileName
 * @param className
 */
public record ClassPath(
        Path pathToDir,
        String fileName,
        String className
) {
    public ClassModel load() {
        return null;
    }
}
