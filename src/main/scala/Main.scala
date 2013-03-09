package org.jamieei.elk

import java.io._
import java.net.Socket

object Main {
  def main(args: Array[String]) {
    val con = Connection()
    try {
      //val msg = Message("sp", "123") // say phrase 123
      //val msg = "09ld00100D6" // request system log data
      //val msg = Message("a1", "100" + code).packetString // arm away
      //val msg = "0Da11001234003F"
      //con.send(msg)

      println("Hit any key to break...")
      do {
        val response = con.receive
        if (!response.isEmpty) {
          val msg = response.get
          msg.command.code match {
            case "XK" => println("heartbeat")
            case _ => println(msg)
          }
        }
      } while (!scala.Console.in.ready)

      println("Done!")
    } finally {
      con.close
    }
  }
}

