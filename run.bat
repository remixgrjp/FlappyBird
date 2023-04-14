javac src/*.java -d out
jar cvfm out/FlappyBird.jar manifest.txt lib -C out .
java -jar out/FlappyBird.jar
RMDIR /S /Q out
