# ztouhoudecoration
A Minecraft Mod. Fire extinguishers, all kind of signs

Supported Minecraft Version: 1.11.2, 1.12.2, 1.15.2
(Content may vary)

# Dependencies
[LibRikka](https://github.com/rikka0w0/librikka)

# Compiling and Testing
```
git clone https://github.com/rikka0w0/ztouhoudecoration
git submodule update --init
./gradlew runData
./gradlew build

cd librikka
./gradlew build
```

# Notes
1. If you are using Intellij Idea, the IDE will configure LibRikka automatically, so you don't need to worry about this
2. Obfuscated and deobfuscated jars needs LibRikka to work properly
3. Navigate to librikka directory and use `gradlew build' to build LibRikka
4. In standalone MineCraft, you need to have both `ztouhoudecoration.jar` and `librikka.jar` in your `mods` folder
5. `ztouhoudecoration-full.jar` includes LibRikka