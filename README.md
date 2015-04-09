# spark-simple

1. Edit `/src/main/scala/SimpleApp.scala`
2. run `sbt package

# Use spark-submit to run your application
$ /opt/spark-1.3.0/bin/spark-submit \
  --class "SimpleApp" \
  --master spark://192.168.1.108:7077 \
  target/scala-2.10/simple-project_2.10-1.0.jar
