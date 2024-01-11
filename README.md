# TrainingProjects-hsbc
This project is use to reverse the String data residing in file "inputstring/testinputstr.txt" and writing it back to "outputstring/testoutputstr.txt".
To open the project in eclipse clone the repository "git clone https://github.com/mayanksingh4042/TrainingProjects-hsbc.git"
In eclipse open the project by going to dir "TrainingProjects-hsbc" as existing java project.
Make sure JDK 8  and higher is installed
Make sure .classpath should have entry like below:
                      <?xml version="1.0" encoding="UTF-8"?>
                      <classpath>
                      	<classpathentry kind="con" path="org.eclipse.jdt.junit.JUNIT_CONTAINER/4"/>
                      	<classpathentry kind="src" path="src"/>
                      	<classpathentry exported="true" kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.8"/>
                      	<classpathentry kind="lib" path="byte-buddy-1.10.19.jar"/>
                      	<classpathentry kind="lib" path="byte-buddy-agent-1.10.19.jar"/>
                      	<classpathentry kind="lib" path="mockito-core-3.7.0.jar"/>
                      	<classpathentry kind="lib" path="objenesis-3.1.jar"/>
                      	<classpathentry kind="output" path="bin"/>
                      </classpath>
============================================================================
To Run the executable jar which will read string from file "inputstring/testinputstr.txt" and writing the reverse string it back to "outputstring/testoutputstr.txt".
**GOTO dir TrainingProjects-hsbc/project_executable_jars and open command prompt CLI**
Then execute command **java -jar hsbcstringreversaldemo.jar**
This will print the reverse string kept in "inputstring/testinputstr.txt" and write the reverse string to "outputstring/testoutputstr.txt (open and check both .txt files)
=============================================================================

To run the Junit test cases 
**GOTO dir TrainingProjects-hsbc/project_executable_jars and open command prompt CLI**
Then execute command  **java -jar junit-platform-console-standalone.jar --class-path hsbcstringreversaldemo.jar --scan-classpath --include-package=test.com.java.exam.hsbc.junit**
on the output you shoud see 15 tests successful.

