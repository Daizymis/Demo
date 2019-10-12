SET JAVA_HOME=C:\Program Files (x86)\Java\jdk1.8.0_60
SET PATH=%JAVA_HOME%\bin;%PATH%;
SET CLASSPATH=.;%JAVA_HOME%\lib\tools.jar;%JAVA_HOME%\lib\dt.jar;

call gradlew clean build -x test
pause