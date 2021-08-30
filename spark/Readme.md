# Apache Spark 3.1.2

## Configuration
To keep this demo simple and enable everyone to try it without infrastructure overhead we will use Apache Spark 3.1.2 in standalone mode.  To emulate multiple nodes we will spin up 3 worker proceses along side the master.  Applications will be deployed to the master node and distributed across the other 3 workers.

## Installation
Run `setups.sh` this will do the following:
* Download & extract Apache Spark 3.1.2
* Create `spark-env.sh` with the following properties:
  * SPARK_WORKER_INSTANCES=3
  * SPARK_MASTER_WEBUI_PORT=8090
  * SPARK_WORKER_PORT=8091

## Starting Local Spark Cluster
Simply run `start-spark.sh`

## Stopping Local Spark Cluster
Simply run `stop-spark.sh`