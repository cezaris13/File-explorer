JC = javac
FILES= *.java
FLAGS= -cp
JAR=flatlaf-0.31.jar
JV=java
CLASS=fileApp

build:
	$(JC) -Xlint:unchecked  $(FILES)
	$(JV) $(FLAGS) .:$(JAR) $(CLASS)
