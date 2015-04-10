import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.storage.StorageLevel

import scala.util.Random

/**
 * Counts words in UTF8 encoded, '\n' delimited text received from the network every second.
 *
 * Usage: NetworkWordCount <hostname> <port>
 * <hostname> and <port> describe the TCP server that Spark Streaming would connect to receive data.
 *
 * To run this on your local machine, you need to first run a Netcat server
 * `$ nc -lk 9999`
 * and then run the example
 * `$ bin/run-example org.apache.spark.examples.streaming.NetworkWordCount localhost 9999`
 */
object StreamingBingo {

  sealed trait StreamState
  final case class Missing(words:Set[String]) extends StreamState
  case object Done extends StreamState

  val buzzwords = Set("cake")

  def main(args: Array[String]) {

    // Create the context with a 10 second batch size
    val sparkConf = new SparkConf().setAppName("StreamingBingo")
    val ssc = new StreamingContext(sparkConf, Seconds(10))

    import org.apache.spark.streaming.kafka._

    val kafkaStream = KafkaUtils.createStream(ssc,
      "192.168.1.105:2181", ""+Random.nextInt(), Map("test" -> 7))

    val guesses = kafkaStream.map(_._2).map{ message =>
      val nameAndBuzzword = message.split(" ").toList
      nameAndBuzzword match {
        case (name :: Nil) => (name, "")
        case (name :: guess :: _) => (name, guess)
      }
    }

    val stateStream = guesses.updateStateByKey[StreamState]{ (in, state) => (in,state ) match {
      case (in, None) => Some(Missing(buzzwords -- in))
      case (in, Some(Missing(words))) if words -- in == Set.empty[String] => Some(Done)
      case (in, Some(Missing(words))) => Some(Missing(words -- in))
      case (in, Some(Done)) => Some(Missing(buzzwords -- in))
    }}

    stateStream.filter(_ == Done).map(name => s"$name screams BINGO!").print()

    kafkaStream.print()
    ssc.start()
    ssc.awaitTermination()
  }
}