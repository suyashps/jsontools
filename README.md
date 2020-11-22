# Jsontools
## Json Tools library
Currently implements only flattening of Json file.

### Prerequisites

* Java 1.8 https://www.java.com/en/download/
* Apache Maven https://maven.apache.org/install.html

### How To Install

From command line do
```
mvn clean install
```
This will create `target` directory with necessary jar files.

### Run Unit Tests
To run provided unit tests run following command
```
mvn test
```

### How To Run
Run the following command to see output for `test.json`. You can pass any file to test further.
```
java -jar target/JsonTools-1.0-SNAPSHOT-jar-with-dependencies.jar test.json
```
