cd ..

rmdir bin
mkdir bin
del sources.txt

dir /s /B *.java > sources.txt
javac @sources.txt -d bin
del sources.txt

dir /s /B *.class > sources.txt
::jar -cf falling-blocks.jar nh/fb/game/Game.class @sources.txt
::build-res/META-INF/MANIFEST.MF
jar cvfm build-res/falling-blocks.jar build-res/META-INF/MANIFEST.MF -C bin .

del sources.txt
