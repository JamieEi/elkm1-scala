package org.jamieei.elk

import java.io._
import java.net.Socket

object Connection {
  def apply(): Connection = {
    println(s"Connecting to ELK at ${Config.ip}:${Config.port}")
    new Connection(Config.ip, Config.port)
  }
}

class Connection(val ip: String, val port: Int) {
  private val socket = new Socket(ip, port)
  socket.setSoTimeout(500);

  private val out = new PrintWriter(socket.getOutputStream, true)
  private val isr = new InputStreamReader(socket.getInputStream)
  private val in = new BufferedReader(isr)

  def close {
    socket.shutdownOutput
    out.close
    in.close
  }

  def send(msg: Message) {
    println("SEND: " + msg)
    out.print(msg.packetString + "\r\n")
    out.flush
  }

  def receive: Option[Message] = {
    try {
      val response = in.readLine
      val msg = Message(response)
      println("RECEIVE: " + msg)
      Some(msg)
    } catch {
      case ex: IOException => None
      case ex: RuntimeException => println("ERROR: " + ex); None
    }
  }
}

