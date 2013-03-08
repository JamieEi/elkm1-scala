package org.jamieei.elk

import org.jamieei.elk.util._

object Message {
  def apply(packetString: String): Message = new ParsedMessage(packetString)
  def apply(code: String, data: String): Message = {
    val cmd = Command(code)
    new SyntheticMessage(cmd, data)
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
  def isValid: Boolean

  /*
  def packetLength: Int
  def messageType: Character
  def subMessageType: Character
  def reserved: String
  def checksum: String

  def messageSum: Int = packetStringWithoutChecksum.foldLeft(0)(_ + _.toInt)
  def checksum: Int = (255 - messageSum % 256) + 1
  */
}

object MessageParts {
  def apply(packetString: String): MessageParts = {
    val ss = new StringSplitter(List(2, 2), List(2, 2))
    val List(packetLength, code, data, _, checksum) = ss.split(packetString)
    new MessageParts(packetLength, code, data, checksum)
  }

  def apply(code: String, data: String): MessageParts = {
    val packetLength = 6 + data.length
    val packetLengthString = "%02X".format(packetLength)
    val packetStringWithoutChecksum = packetLengthString + code + data + "00"
    val messageSum = packetStringWithoutChecksum.foldLeft(0)(_ + _.toInt)
    val checksum = (255 - messageSum % 256) + 1
    val checksumString = "%02X".format(checksum)
    new MessageParts(packetLengthString, code, data, checksumString)
  }
}

sealed case class MessageParts(val packetLength: String, val code: String, val data: String, val checksum: String) {
}

class ParsedMessage(val packetString: String) extends Message {
  val List(packetLength, messageType, subMessageType, data, reserved, checksum) = new StringSplitter(List(2, 1, 1), List(2, 2)).split(packetString)
  private val commandString = messageType + subMessageType
  def command = Command(commandString)
  def isValid = true 
}

class SyntheticMessage(val command: Command, val data: String) extends Message {
  private val packetLength: Int = 6 + data.length
  private val packetStringWithoutChecksum: String = "%02X".format(packetLength) + command.code + data + "00"
  private val messageSum: Int = packetStringWithoutChecksum.foldLeft(0)(_ + _.toInt)
  private val checksum: Int = (255 - messageSum % 256) + 1
  private val checksumString: String = "%02X".format(checksum)
  val packetString: String = packetStringWithoutChecksum + checksumString
  def isValid = true
}

