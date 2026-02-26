plugins {
    id("com.android.application") version "8.6.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false
}

extra.apply {
    set("compileSdk", 34)
    set("minSdk", 23)
    set("targetSdk", 34)
    set("composeCompiler", "1.5.14")
}
