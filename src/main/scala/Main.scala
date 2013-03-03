package org.jamieei.elk

import java.io._
import java.net._

object Main {
  //val msg = "Da11001234003F" // arm away
  val msg = "09sp12300BE" // phrase 123

  def main(args: Array[String]) {
    val ip = sys.env("ELK")
    val port = 2101
    val code = sys.env("ELK_CODE")
    println(s"Connecting to ELK at $ip:$port using code $code")

    val socket = new Socket(ip, port)
    val out = new PrintWriter(socket.getOutputStream, true)
    //val isr = new InputStreamReader(socket.getInputStream)
    //val in = new BufferedReader(isr)

    try {
      out.print(msg + "\r\n")
      // send command
      // wait for response
      // print response
    } finally {
      out.close
      socket.close
    }
  }
}

