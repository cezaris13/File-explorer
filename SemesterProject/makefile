JC = javac
FILES = *.java
FLAGS = -cp
JAR = flatlaf-0.31.jar
JV = java
CLASS = FileApp

build:
	$(JC) -Xlint:unchecked -Xlint:deprecation  $(FILES)
	$(JV) $(FLAGS) .:$(JAR) $(CLASS)

clean:
	find . -name "*.class" -type f -delete
