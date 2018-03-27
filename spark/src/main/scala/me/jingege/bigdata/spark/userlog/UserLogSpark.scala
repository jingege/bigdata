package me.jingege.bigdata.spark.userlog

import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  *
  * @author bill 2018-03-22 23:51
  */
object UserLogSpark {

  def main(args: Array[String]): Unit = {

    val (master) = (args(0))

    val topics = Set("userlog")

    val conf = new SparkConf().setAppName("UserLogTagFound").setMaster(master)
    val ssc = new StreamingContext(conf, Seconds(1))

    val brokers = "localhost:9092"

    val kafkaParam = Map[String, String](
      "bootstrap.servers" -> brokers,
      "group.id" -> "spark-consumer",
      "key.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "key.deserializer.class" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer.class" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> "false"
    )

    var stream = KafkaUtils.createDirectStream[String, String](ssc, PreferConsistent, Subscribe[String, String](topics, kafkaParam))
    stream.map(s => (s.key(), s.value())).print()

    ssc.start()
    ssc.awaitTermination()
  }

}