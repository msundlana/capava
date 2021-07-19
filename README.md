**ING Team CAPAVA Test**

* This a simple spring boot application. 
  * To run it under development, execute the main method CapavaApplication.java.
  * To run the application under test mode, run CapavaApplicationTests.java.
  * To run the application on production, do a maven clean install to generate a war file 
    * deploy it on a server 
    * java -jar capava-0.0.1-SNAPSHOT.war
    
Once application is up, statical data application executes daily at 4:00 AM and the output.csv file gets updated.
The output.csv will be found under /Download directory on your machine.
  * API access URI http://localhost:8080/capava/download to manually execute and download.
  