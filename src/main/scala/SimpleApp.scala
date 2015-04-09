/* SimpleApp.scala */

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD

object SimpleApp {
  def main(args: Array[String]) {

    val data = List(
      "ot_Vid=8dfeca34-8cfae2fffa&sd_BBS=ok",
      "ot_Vid=8dfeca34-8cfae2fffa&order_WarenkorbArtikelHinzufuegen=145243"
    )
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
    val rdd = sc.parallelize(data)
    val (numAs, numBs) = myApp(rdd)
    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  }


  def myApp(in:RDD[String]) = {
    val numAs = in.filter(line => line.contains("a")).count()
    val numBs = in.filter(line => line.contains("b")).count()
    (numAs, numBs)
  }

}
