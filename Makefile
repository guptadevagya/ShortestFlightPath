runFDTests:
	javac -cp ../junit5.jar: Frontend.java
	javac Frontend.java FrontendInterface.java TextUITester.java BackendInterface.java ShortestPathResultInterface.java
	javac -cp ../junit5.jar: FrontendDeveloperTests.java
	java -jar ../junit5.jar -cp . -c FrontendDeveloperTests
runBDTests:
	javac -cp ../junit5.jar *.java
	java -jar ../junit5.jar --class-path . --include-classname=.* --select-class=BackendDeveloperTests
clean:
	rm -f *.class
