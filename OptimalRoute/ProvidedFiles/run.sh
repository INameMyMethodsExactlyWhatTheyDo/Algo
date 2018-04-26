#!/bin/sh

javac *.java
java -classpath . Driver -d example_input/3
java -classpath . Driver -d example_input/5
java -classpath . Driver -d example_input/10
java -classpath . Driver -d example_input/500
java -classpath . Driver -w example_input/3
java -classpath . Driver -w example_input/5
java -classpath . Driver -w example_input/10
java -classpath . Driver -w example_input/500

rm *.class

