@echo off
set DBSERVER=localhost
set DBPORT=33060
set MEMBERAPIHOST=localhost
set ISSUEAPIHOST=localhost
set BOOKAPIHOST=localhost
set ISSUEAPIPORT=3003
set MEMBERAPIPORT=3002
set BOOKAPIPORT=3001
set DOCPORT=9000
set DOCHOST=localhost

set GOAL=%1
if "%GOAL%" == "build" ( 
start "Building Books-API" cmd /k mvn  -f books-api/pom.xml clean package -DskipTests
start "Building Members-API" cmd /k mvn  -f issues-api/pom.xml clean package -DskipTests
start "Building Issues-API" cmd /k mvn  -f members-api/pom.xml clean package -DskipTests
start "Building Gateway" cmd /k mvn  -f library-gateway/pom.xml clean package -DskipTests
start "Building Docs" cmd /k mvn  -f library-docs/pom.xml clean package -DskipTests
) 
IF "%GOAL%" == "docker-build" (
	start "Building Container Image for Database" cmd /k docker build -t mahendrshinde/ms-demo-library:db database
	start "Building Container Image for Books-API" cmd /k docker build -t mahendrshinde/ms-demo-library:books-api books-api
	start "Building Container Image for Members-API" cmd /k docker build -t mahendrshinde/ms-demo-library:members-api members-api
	start "Building Container Image for Issue-API" cmd /k docker build -t mahendrshinde/ms-demo-library:issues-api issues-api
	start "Building Container Image for Gateway" cmd /k docker build -t mahendrshinde/ms-demo-library:gateway library-gateway
	start "Building Container Image for API-Docs" cmd /k docker build -t mahendrshinde/ms-demo-library:docs library-docs
)
IF "%GOAL%" == "" ( 
start "Launching Books-API" cmd /k java -jar books-api\target\books-api-1.0.jar --PORT=%BOOKAPIPORT%
timeout /T 15 /NOBREAK
start "Launching Members-API" cmd /k java -jar members-api\target\members-api-1.0.jar --PORT=%MEMBERAPIPORT%
timeout /T 15 /NOBREAK
start "Launching Issues-API" cmd /k java  -jar issues-api\target\issues-api-1.0.jar --PORT=%ISSUEAPIPORT%
timeout /T 15 /NOBREAK
start "Launching Docs" cmd /k java -jar library-docs\target\library-docs-1.0.jar --PORT=%DOCPORT% 
start "Launching Gateway" cmd /k java -jar library-gateway\target\library-gateway-1.0.jar --PORT=8080 
)

