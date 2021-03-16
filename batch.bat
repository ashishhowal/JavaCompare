echo running batch
set CLASSPATH=.
set CLASSPATH=%CLASSPATH%;%~dp0/complexityBatch.jar;%~dp0/commons-io-2.2.jar

%JAVA_HOME%\bin\java -Xms128m -Xmx384m -Xnoclassgc Howal.FileCompare