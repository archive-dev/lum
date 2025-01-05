package lum.core.model;

public enum Extension {
    LUM(".lum"), JAVA(".java"), CLASS(".class");

    public final String extension;

    Extension(String extension) {
        this.extension = extension;
    }

    public static Extension getExtension(String fileName) {
        for (var v : Extension.values()) {
            if (!fileName.contains(v.extension)) continue;

            if (fileName.replace(v.extension, "").equals(fileName.substring(0, fileName.length() - v.extension.length())))
                return v;
        }

        return null;
    }
}
