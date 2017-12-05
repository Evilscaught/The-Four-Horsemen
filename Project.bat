@echo off
cd CS Account Viewer\src\application
javac *.java controller\*.java -cp "..\..\Libraries\algs4.jar;stdlib.jar"; 
cd..
java -cp "..\Libraries\algs4.jar;stdlib.jar;" application.Main
pause
