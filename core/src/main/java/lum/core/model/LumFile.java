package lum.core.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record LumFile(
        Path path,
        Imports imports,
        List<ClassModel> classes,
        List<MethodModel> functions,
        List<FieldModel> variables
) {
    public static LumFile from(Path path) {
        Imports imports = Imports.from(path);
        List<ClassModel> classes = new ArrayList<>();
        List<MethodModel> functions = new ArrayList<>();
        List<FieldModel> variables = new ArrayList<>();

        var file = new LumFile(path, imports, classes, functions, variables);

        var paths = file.getClassPaths();
        classes.addAll(paths.stream().map(ClassModelPool::get).toList());

        return file;
    }

    public Set<ClassPath> getClassPaths() {
        Set<ClassPath> paths =
                classes()
                .stream()
                .map(classModel -> new ClassPath(path(), classModel.name(), classModel.name(), Extension.LUM))
                .collect(Collectors.toSet());

        var filename = path().getFileName().toString().replace(".", "");
        paths.add(new ClassPath(path(), filename, filename, Extension.LUM));

        return paths;
    }
}
