/* SimpleApp.scala */

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD

object SimpleApp {
  def main(args: Array[String]) {
    val logFile = "YOUR_SPARK_HOME/README.md" // Should be some file on your system
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
    val (numAs, numBs) = myApp(sc.textFile(logFile))
    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  }


  def myApp(in:RDD[String]) = {
    val numAs = in.filter(line => line.contains("a")).count()
    val numBs = in.filter(line => line.contains("b")).count()
    (numAs, numBs)
  }

}
