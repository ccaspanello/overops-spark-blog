export SPARK_HOME=$(pwd)/spark-3.1.2-bin-hadoop3.2
export PATH=$SPARK_HOME/bin:$SPARK_HOME/sbin:$PATH

start-master.sh
start-worker.sh spark://$(hostname):7077