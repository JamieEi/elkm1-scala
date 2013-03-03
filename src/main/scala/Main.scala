package org.jamieei.elk

import java.io._
import java.net._

object Main {
  def main(args: Array[String]) {
    val ip = sys.env("ELK")
    val port = 2101
    val code = sys.env("ELK_CODE")
    println(s"Connecting to ELK at $ip:$port using code $code")

    //val msg = s"Da1100${code}003F" // arm away
    //val msg = "09sp12300BE" // phrase 123
    val msg = "09ld00100D6" // request system log data

    val socket = new Socket(ip, port)
    try {
      val out = new PrintWriter(socket.getOutputStream, true)
      val isr = new InputStreamReader(socket.getInputStream)
      val in = new BufferedReader(isr)

      println("SEND: " + msg)
      out.print(msg + "\r\n")

      Thread.sleep(1000)

      val response = in.readLine
      println("RESPONSE: " + response)

      socket.shutdownOutput
      out.close
      in.close
      println("Done!")
    } finally {
      socket.close
    }
  }
}

