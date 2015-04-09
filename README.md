# spark-simple

1. Edit `/src/main/scala/SimpleApp.scala`
2. run `sbt package

# Use spark-submit to run your application
$ /opt/spark-1.3.0/bin/spark-submit \
  --class "SimpleApp" \
  --master local[4] \
  target/scala-2.10/simple-project_2.10-1.0.jar
