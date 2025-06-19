package lum.core.model;

import java.util.Optional;

public interface Member extends Accessible {
    Optional<ClassModel> owner();
    String name();
}
