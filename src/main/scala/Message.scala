package org.jamieei.elk

import org.jamieei.elk.util._

object Message {
  def apply(packetString: String): Message = new ParsedMessage(packetString)
  def apply(code: String, data: String): Message = {
    val cmd = Command(code)
    new SyntheticMessage(cmd, data)
  }

  def checksum(msg: String): Int = {
    val sumOfChars = msg.foldLeft(0)(_ + _.toInt)
    (255 - sumOfChars % 256) + 1
  }
}

// Message format = NNMSD..00CC (CR-LF)
//   NN - Packet length [0-9A-F]: Length excluding NN and CR-LF
//   M - Message type [a-zA-Z]
//   S - Sub-Message type [0-9a-zA-Z]
//   D - Data [\p{Print}]
//   00 - Reserved
//   CC - Checksum: Hex 2's complement of mod-256 sum of message chars excluding checksum and CR-LF
trait Message {
  def packetString: String
  def command: Command
  def data: String
}

class ParsedMessage(val packetString: String) extends Message {
  import Message._

  private val ss = new StringSplitter(List(2, 2), List(2, 2))
  val List(packetLengthString, code, data, _, checksumString) = ss.split(packetString)
  lazy val command = Command(code)
}

class SyntheticMessage(val command: Command, val data: String) extends Message {
  import Message._

  private val packetLength: Int = 6 + data.length
  private val packetStringWithoutChecksum: String = "%02X".format(packetLength) + command.code + data + "00"
  private val checksumString: String = "%02X".format(Message.checksum(packetStringWithoutChecksum))
  val packetString: String = packetStringWithoutChecksum + checksumString
}

