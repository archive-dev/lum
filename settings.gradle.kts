plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}
rootProject.name = "lum"
include(":core")
include(":runtime")
include(":compiler")
include(":tools")
include(":gradle-plugin")
