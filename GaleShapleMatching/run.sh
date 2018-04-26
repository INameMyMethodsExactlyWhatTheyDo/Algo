#!/bin/sh
echo "Compile"
javac *.java
echo "Running"
java Driver -g 4.in
java Driver -g 6.in
java Driver -g 8.in
java Driver -g 10.in
java Driver -g 160.in
java Driver -g 320.in
java Driver -g 640.in
java Driver -g 1280.in
echo "End Run"
rm *.class

