pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories  {
        google()
        mavenCentral()
        // JitPack 远程仓库：https://jitpack.io
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://s01.oss.sonatype.org/content/groups/public") }
    }
}

rootProject.name = "Android Learned"
include(":app")
