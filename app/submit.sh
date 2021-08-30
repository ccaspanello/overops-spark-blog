./mvnw clean package -DskipTests

rm -rf ../data/output

pushd $(pwd)
cd ..
PROJECT_DIR=$(pwd)
popd

DATA_DIR=$PROJECT_DIR/data
#AGENT_DIR=$PROJECT_DIR/overops/Takipi
AGENT_DIR=/Users/Shared/Takipi

spark-submit \
  --class com.overops.blog.spark.Main \
  --master spark://Chriss-MacBook-Pro.local:7077 \
  --deploy-mode cluster \
  --conf "spark.driver.extraJavaOptions=-agentpath:${AGENT_DIR}/lib/libTakipiAgent.dylib -Dtakipi.application.name=OverOpsBlog -Dtakipi.deployment.name=driver -Dtakipi.collector.host=192.168.0.115 -Dtakipi.collector.port=6060 -Dtakipi.log.to.console" \
  --conf "spark.executor.extraJavaOptions=-agentpath:${AGENT_DIR}/lib/libTakipiAgent.dylib -Dtakipi.application.name=OverOpsBlog -Dtakipi.deployment.name=executor -Dtakipi.collector.host=192.168.0.115 -Dtakipi.collector.port=6060 -Dtakipi.log.to.console" \
  target/app-1.0.0-SNAPSHOT.jar \
  file:/${DATA_DIR}/input file:/${DATA_DIR}/output

