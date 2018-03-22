﻿package me.jingege.bigdata.spark.userlog

import org.apache.spark.streaming.StreamingContext
import org.apache.spark.{SparkConf, SparkContext}

object UserLogSpark {

  def main(args:Array[String]):Unit = {
    val topic = "userlog"

    val sparkConf = new SparkConf().setAppName("userlog").setMaster("local[1]")
    val sc = new SparkContext(sparkConf)
//    val ssc = new StreamingContext(sparkConf,10)
  }

}