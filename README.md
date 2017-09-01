# ztouhoudecoration
A MineCraft Mod. Fire extinguishers, all kind of signs
Current Version: 1.11.2

# Dependencies
[LibRikka](https://github.com/rikka0w0/librikka)

# Compiling and Testing
1. Ensure that `Java` (found [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)), `Git` (found [here](http://git-scm.com/)) are installed correctly on your system.
1. Create a base directory for the repo
1. (On Windows) open Git CMD and navigate to the directory just created
1. `git clone https://github.com/rikka0w0/ztouhoudecoration`
1. `git submodule init` and 'git submodule update` to get LibRikka
1. `gradlew build` to build jars
1. `gradlew setupDecompWorkspace` to setup a complete development environment.
* On Windows: use `gradlew.bat` instead of `gradlew`

# Notes
1. If you are using Intellij Idea, the IDE will configure LibRikka automatically, so you don't need to worry about this
2. Obfuscated and deobfuscated jars needs LibRikka to work properly
3. Navigate to librikka directory and use `gradlew build' to build LibRikka
4. In standalone MineCraft, you need to have both `ztouhoudecoration.jar` and `librikka.jar` in `mods` folder
