package org.jamieei.elk

import java.io._
import java.net.Socket

object Main {
  def main(args: Array[String]) {
    //val msg = "09ld00100D6" // request system log data
    val msg = Message("sp", "123") // say phrase 123
    //val msg = Message("a1", "100" + code).packetString // arm away
    //val msg = "0Da11001234003F"

    val con = Connection()
    try {
      con.send(msg)

      println("Reading response. Hit any key to break...")
      do {
        con.receive
      } while (!scala.Console.in.ready)

      println("Done!")
    } finally {
      con.close
    }
  }
}

