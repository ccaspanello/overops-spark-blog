# It works on my machine!  <br> . . . the Spark app you thought would scale.

This project is to support an OverOps blog post on Apache Spark.  It consists of 3 components:  OverOps, Spark, and an example application.

## Quickstart
In order to run this example the OverOps server & collector must be running as well as a local Spark cluster (standalone mode).  OverOps and Spark can be started in any order.  

**Start OverOps:**
* `cd overops`
* `./download-agent-mac.sh`
* `./docker-compose up -d`

**Start Spark:**
* `cd spark`
* `./setup.sh`
* `./start-spark.sh`

# Compile and Run Spark Application
* `mvn clean install`
* `submit.sh`