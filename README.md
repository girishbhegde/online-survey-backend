# online-survey-backend
Provides backend API for online survey

**Online Survey Backend API**

The project is made up of two modules. survey-consumer and survey
manager. Survey-consumer is the microservice which serves survey
questions and option to submit answers. Survey-manager is for survey
creators who can create survey, add/edit/delete/get questions. Also it
provides option to read all responses to a particular question and
analyze the percentage distribution of responses among various options.

Additionally, survey-manager has two extra APIs for admin. To create new
User and get user details of those users who can create survey and
manage their surveys.

The application uses Postgres as backend DB.

The two services run in separate docker containers with postgres also
running in docker.

For simplicity, User authentication and Authorization has been not
provided. As name suggests, it’s not a UI but backend.

You can connect to your own containerized/non-containerized DB by making
the changes in src/main/resources/application.properties file.

**Prerequisites:**

-   Install JDK 8

-   Install maven 3 (settings.xml is provided in case you face issue connecting to publc repo)

-   Docker

**How to build:**

cd to path where survey-api is downloaded/cloned.

-   cd survey-api/survey-consumer/

-   mvn clean install

-   mvn docker:build //This might take some time to build for first time

-   cd ../survey-manager/

-   mvn clean install

-   mvn docker:build

**Run postgres in docker:**

-   docker pull postgres

-   docker run --name dev-postgres -e POSTGRES\_PASSWORD=dbuser -d
    postgres

-   docker run -it --rm --link dev-postgres:postgres postgres psql -h
    postgres -U postgres

    The above step opens a psql terminal. When it asks for password
    enter: dbuser. Hit enter

<!-- -->

-   Create required tables by running contents from
 *[survey\_tables\_ddl.sql](survey-api/survey\_tables\_ddl.sql)*

-   Create sample data by running contents from survey\_sample\_dml.sql
*[survey\_sample\_dml.sql](survey-api/survey\_sample\_dml.sql)*

**Start survey-consumer container:**

docker run -it --link dev-postgres:postgres -p 8081:8080
girish/survey-consumer

**Start survey-manager container:**

docker run -it --link dev-postgres:postgres -p 8082:8080
girish/survey-manager

**Swagger Docs:**

You can access swagger docs by accessing below URL:

<http://localhost:8081/index.html> .

-   In search box enter : <http://localhost:8081/api-docs> . (Change
    port if you are running application on different port)

-   Click on app-controller to see available API. You can even use the
    UI for calling the APIs

You can access survey manager similarly by accessing same link with port
on which survey-manager is running(8082)

API docs are also available in file api-docs.txt

**scope for improvement**
-   Introduce a distributed cache layer (probably redis) to cache the questions for most commonly accessed surveys.
-   Junits completion for survey-manager module
-   Validation of survey response to make sure all questions are answered (Could be handled in UI)