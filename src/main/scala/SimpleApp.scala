/* SimpleApp.scala */

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD

object SimpleApp {

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Simple Application").setMaster("spark://192.168.1.108:7077")
    val sc = new SparkContext(conf)
    val data = sc.textFile("hdfs://192.168.1.108:50090/user/plain/ts-adapter-wa*")
    println(data.take(1)(0))
  }

}
