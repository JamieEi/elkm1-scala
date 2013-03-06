package org.jamieei.elk

import java.io._
import java.net.Socket

object Main {
  def main(args: Array[String]) {
    val ip = sys.env("ELK")
    val port = 2101
    val code = sys.env("ELK_CODE")
    println(s"Connecting to ELK at $ip:$port using code $code")

    //val msg = "09sp12300BE" // phrase 123
    //val msg = "09ld00100D6" // request system log data
    val msg = Message("sp", "123").packetString // say phrase 123
    //val msg = Message("a1", "100" + code).packetString // arm away
    //val msg = "0Da11001234003F"
    val socket = new Socket(ip, port)
    try {
      socket.setSoTimeout(500);
      val out = new PrintWriter(socket.getOutputStream, true)
      println("SEND: " + msg)
      out.print(msg + "\r\n")
      out.flush

      val isr = new InputStreamReader(socket.getInputStream)
      val in = new BufferedReader(isr)
      println("Reading response. Hit any key to break...")
      do {
        try {
          val response = in.readLine
          val msg = Message(response)
          msg.command.code match {
            case "XK" => // ethernet test
            case _ => println(s"$msg [$response]")
          }
        } catch {
          case ex: IOException => // expect "Read timed out"
          case ex: Exception => {
            println(s"(EE) $ex")
            ex.printStackTrace
          }
        }
      } while (!scala.Console.in.ready)

      socket.shutdownOutput
      out.close
      in.close
      println("Done!")
    } finally {
      socket.close
    }
  }
}

