wget -c https://dlcdn.apache.org/spark/spark-3.1.2/spark-3.1.2-bin-hadoop3.2.tgz -O - | tar -xz

pushd spark-3.1.2-bin-hadoop3.2/conf

# Configure spark-env.sh to include 3 instances with ports that do not conlfict with OverOps
cp spark-env.sh.template spark-env.sh
echo "SPARK_WORKER_INSTANCES=3" >> spark-env.sh
echo "SPARK_MASTER_WEBUI_PORT=8090" >> spark-env.sh
echo "SPARK_WORKER_PORT=8091" >> spark-env.sh

cp spark-defaults.conf.template spark-defaults.conf
echo "spark.master                       spark://$(hostname):7077" >> spark-defaults.conf

popd