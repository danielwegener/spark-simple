import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{Matchers, BeforeAndAfter, WordSpec}


class SimpleAppTest extends WordSpec with Matchers {


  "A SimpleApp" should {

    val conf = new SparkConf().setMaster("local").setAppName("SimpleAppTest")
    val sc = new SparkContext(conf)

    "have a working myApp function" in {
    }

  }

}