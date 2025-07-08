package lum.core.impl.model;

import lum.antlr4.LumLexer;
import lum.antlr4.LumParser;
import lum.core.model.ImportsModel;
import lum.core.model.Member;
import lum.core.util.Utils;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class FileParser {
    private FileParser() {}

    private static final Map<Path, ImportsModel> importsCache = new HashMap<>();
    private static final Map<Path, Optional<Member[]>> membersCache = new HashMap<>();
    private static final Map<Path, LumParser.FileContext> contextsCache = new HashMap<>();

    public static Optional<Member[]> parseFile(Path workDir, Path path) {
        if (membersCache.containsKey(path))
            return membersCache.get(path);

        LumParser.FileContext fileCtx = getFileCtx(path);

        ImportsModel imports = getImports(path);
        importsCache.put(path, imports);
        ImportsParser.parseImports(workDir, imports, fileCtx.imports());

        String pkg = "";
        if (workDir != null)
            pkg = Utils.subtractPaths(path.getParent(), workDir).toString()
                    .replaceAll("\\%s".formatted(File.separator), ".");

        var members = ClassModelParser.buildMemberModels(pkg, imports, fileCtx);
        membersCache.put(path, members);
        return members;
    }

    public static ImportsModel getImports(Path path) {
        return importsCache.containsKey(path) ? importsCache.get(path) : new ImportsModelImpl();
    }

    public static LumParser.@NotNull FileContext getFileCtx(Path path) {
        return contextsCache.computeIfAbsent(path, k -> {
            try {
                return new LumParser(new CommonTokenStream(new LumLexer(CharStreams.fromPath(k)))).file();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
