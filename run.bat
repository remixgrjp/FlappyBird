javac src/*.java -d out -encoding UTF8
REM 
REM jar --create --file out/FlappyBird.jar --manifest manifest.txt lib -C out .
REM jar --create --file out/FlappyBird.jar --manifest manifest.txt -C out . lib
REM jar cvfm out/FlappyBird.jar manifest.txt lib -C out .
REM 
REM ==
REM 
jar cvfm out/FlappyBird.jar manifest.txt -C out . lib
java -jar out/FlappyBird.jar
RMDIR /S /Q out
