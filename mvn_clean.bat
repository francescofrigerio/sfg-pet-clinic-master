rem SET JAVA_HOME=C:\Program Files\Java\jdk1.8.0_231
SET JAVA_HOME=C:\Program Files\Java\jdk-12.0.1

rem mvn verify >verify.log 2>&1

rem mvn clean >clean.log 2>&1
mvn clean generate-sources >clean.log 2>&1
