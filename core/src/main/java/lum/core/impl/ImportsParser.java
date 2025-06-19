package lum.core.impl;

import lum.antlr4.LumParser;
import lum.core.model.*;
import lum.core.util.Utils;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

final class ImportsParser {
    private ImportsParser() {}

    public static void parseImports(ImportsModel imports, List<LumParser.ImportsContext> ctxs) {
        for (var ctx : ctxs) {
            switch (ctx) {
                case LumParser.SingleImportContext single -> {
                    addSingleImport(single, imports);
                }
                case LumParser.AsImportContext importAs -> {
                    addImportAs(importAs.importAs(), imports);
                }
                case LumParser.FromImportContext importFrom -> {
                    addImportFrom(importFrom.importFrom(), imports);
                }
                case LumParser.MultiImportContext importMulti -> {
                    addImportMulti(importMulti.importMulti(), imports);
                }
                default -> throw new IllegalStateException("Unexpected value: " + ctx);
            }
        }
    }

    private static void addImportMulti(LumParser.ImportMultiContext importMulti, ImportsModel imports) {
        importMulti.importAny().stream()
                .filter(ctx -> ctx instanceof LumParser.AsContext)
                .map(ctx -> ((LumParser.AsContext) ctx).importAs())
                .forEach(importAs -> {
                    var identifiers = importAs.importSingle().IDENTIFIER() ;
                    final String alias = importAs.IDENTIFIER().getText();
                    for (var member : parseImport(identifiers).orElse(Utils.EMPTY_MEMBERS)) {
                        switch (member) {
                            case ClassModel model -> imports.classes().put(alias, model);
                            case MethodModel method -> imports.methods().put(alias, method);
                            case FieldModel field -> imports.fields().put(alias, field);
                            default -> throw new IllegalStateException("Unexpected value: " + member);
                        }
                    }
                });

        importMulti.importAny().stream()
                .filter(ctx -> ctx instanceof LumParser.SingleContext)
                .map(ctx -> ((LumParser.SingleContext) ctx).importSingle())
                .forEach(importSingle -> {
                    var identifiers = importSingle.IDENTIFIER();
                    final String alias = importSingle.IDENTIFIER().getLast().getText();
                    for (var member : parseImport(identifiers).orElse(Utils.EMPTY_MEMBERS)) {
                        switch (member) {
                            case ClassModel model -> imports.classes().put(alias, model);
                            case MethodModel method -> imports.methods().put(alias, method);
                            case FieldModel field -> imports.fields().put(alias, field);
                            default -> throw new IllegalStateException("Unexpected value: " + member);
                        }
                    }
                });
    }

    private static void addImportFrom(LumParser.ImportFromContext importFrom, ImportsModel imports) {
        importFrom.importAny().stream()
                .filter(ctx -> ctx instanceof LumParser.AsContext)
                .map(ctx -> ((LumParser.AsContext) ctx).importAs())
                .forEach(importAs -> {
            var identifiers = importFrom.from().IDENTIFIER();
            identifiers.addAll(importAs.importSingle().IDENTIFIER());
            final String alias = importAs.IDENTIFIER().getText();
            for (var member : parseImport(identifiers).orElse(Utils.EMPTY_MEMBERS)) {
                switch (member) {
                    case ClassModel model -> imports.classes().put(alias, model);
                    case MethodModel method -> imports.methods().put(alias, method);
                    case FieldModel field -> imports.fields().put(alias, field);
                    default -> throw new IllegalStateException("Unexpected value: " + member);
                }
            }
        });

        importFrom.importAny().stream()
                .filter(ctx -> ctx instanceof LumParser.SingleContext)
                .map(ctx -> ((LumParser.SingleContext) ctx).importSingle())
                .forEach(importSingle -> {
                    var identifiers = importFrom.from().IDENTIFIER();
                    identifiers.addAll(importSingle.IDENTIFIER());
                    final String alias = importSingle.IDENTIFIER().getLast().getText();
                    for (var member : parseImport(identifiers).orElse(Utils.EMPTY_MEMBERS)) {
                        switch (member) {
                            case ClassModel model -> imports.classes().put(alias, model);
                            case MethodModel method -> imports.methods().put(alias, method);
                            case FieldModel field -> imports.fields().put(alias, field);
                            default -> throw new IllegalStateException("Unexpected value: " + member);
                        }
                    }
                });
    }

    private static void addImportAs(LumParser.ImportAsContext importAs, ImportsModel imports) {
        final String alias = importAs.IDENTIFIER().getText();
        for (var member : parseSingleImport(importAs.importSingle()).orElse(Utils.EMPTY_MEMBERS)) {
            switch (member) {
                case ClassModel model -> imports.classes().put(alias, model);
                case MethodModel method -> imports.methods().put(alias, method);
                case FieldModel field -> imports.fields().put(alias, field);
                default -> throw new IllegalStateException("Unexpected value: " + member);
            }
        }
    }

    private static void addSingleImport(LumParser.SingleImportContext single, ImportsModel imports) {
        for (var member : parseSingleImport(single.importSingle()).orElse(Utils.EMPTY_MEMBERS)) {
            switch (member) {
                case ClassModel model -> imports.classes().put(model.className(), model);
                case MethodModel method -> imports.methods().put(method.name(), method);
                case FieldModel field -> imports.fields().put(field.name(), field);
                default -> throw new IllegalStateException("Unexpected value: " + member);
            }
        }
    }

    private static Optional<Member[]> parseSingleImport(LumParser.ImportSingleContext ctx) {
        return parseImport(ctx.IDENTIFIER());
    }

    private static Optional<Member[]> parseImport(List<TerminalNode> identifiers) {
        return convertToMembers(importToPath(identifiers.stream().map(TerminalNode::getText).toList()));
    }

    private static Optional<Member[]> convertToMembers(PathToClass path) {
        Optional<Member[]> members;
        if (path.isFile())
            members = FileParser.parseFile(path.pathToFile());
        else {
            try {
                members = ClassModelFactory.of(Class.forName(path.javaClassName()))
                        .map(m -> new Member[]{m});
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        if (members.isEmpty())
            throw new IllegalStateException("Provided file does not contain any member to import");

        var membersArray = members.get();
        for (Iterator<String> iterator = path.memberRefs().iterator(); iterator.hasNext();) {
            var ref = iterator.next();
            var foundMembersArray = Arrays.stream(membersArray).filter(m -> m.name().equals(ref)).toArray(Member[]::new);
            if (iterator.hasNext())
                membersArray = Arrays.stream(foundMembersArray)
                        .filter(m -> m instanceof ClassModel)
                        .map(m -> ((ClassModel) m).members())
                        .reduce(new ArrayList<Member>(),
                                (l, m) -> {
                                        l.addAll(List.of(m));
                                        return l;
                                    },
                                (a, b) -> {
                                        a.addAll(b);
                                        return a;
                                    }
                        )
                        .stream()
                        .filter(Accessible::isStatic)
                        .toArray(Member[]::new);
        }

        if (membersArray.length == 0) return Optional.empty();
        return Optional.of(membersArray);
    }
    // org example pack Class1 -> org/example/pack/Class1.lum   done
    // org example file ClassInFile -> org/example/file.lum::ClassInFile
    // static: org example NoPackClass2.staticFunc1 -> org/example/NoPackClass2.lum::staticFunc1
    // static: org example file.staticFuncInFile -> org/example/file.lum::staticFuncInFile
    // static: org example file.SomeClass.staticFuncInClass -> org/example/file.lum::SomeClass::staticFuncInFile

    private static PathToClass importToPath(List<String> parts) {
        var forNameParts = new ArrayList<>(parts);
        var classPtr = new Class<?>[1];
        while (!forNameParts.isEmpty() && !tryClassForName(forNameParts, classPtr)) {
            forNameParts.removeLast();
        }
        if (!forNameParts.isEmpty())
            return new PathToClass(String.join(".", forNameParts), parts.subList(forNameParts.size(), parts.size()));

        Path fullPath = Path.of("/", parts.toArray(String[]::new)).getParent().resolve(parts.getLast()+".lum");
        if (Files.exists(fullPath))
            return new PathToClass(fullPath, parts.getLast());

        Path possibleFilePath = fullPath.getParent();
        if (Files.exists(possibleFilePath)) {
            return new PathToClass(possibleFilePath, parts.getLast());
        }
        int c = 1;
        do {
            possibleFilePath = Path.of(possibleFilePath.getParent().toString()+".lum");
            c++;
        }
        while (!Files.exists(possibleFilePath));

        return new PathToClass(possibleFilePath, parts.subList(0, parts.size()-c));
    }

    private static boolean tryClassForName(List<String> parts, Class<?>[] out) {
        try {
            out[0] = Class.forName(String.join(".", parts));
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private record PathToClass(
            Path pathToFile,
            String javaClassName,
            List<String> memberRefs,
            boolean isFile
    ) {
        PathToClass(String javaClassName,
                    String memberRef) {
            this(null, javaClassName, Collections.singletonList(memberRef), false);
        }

        PathToClass(String javaClassName,
                    List<String> memberRef) {
            this(null, javaClassName, memberRef, false);
        }

        PathToClass(Path pathToFile,
                           String memberRef) {
            this(pathToFile, null, Collections.singletonList(memberRef), true);
        }

        PathToClass(Path pathToFile,
                           List<String> memberRef) {
            this(pathToFile, null, memberRef, true);
        }
    }
}
