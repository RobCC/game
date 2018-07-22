rmdir /s /q test
mkdir test
javac -d test/ -cp src src/main/Main.java
java -cp test main.Main
cmd /k
::pause