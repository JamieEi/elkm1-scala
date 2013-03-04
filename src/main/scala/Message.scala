package org.jamieei.elk

object Message {
  def apply(messageString: String): Message = {
    val fmt = """([\dA-F]{2})(\p{Alpha}\p{Alnum})(\p{Print}*)00([\dA-F]{2})""".r
    val fmt(packetLength, code, data, checksum) = messageString
    val msg = apply(code, data)

    if (checksum != msg.checksumString) {
      val err = s"Checksum $checksum does not match calculated value ${msg.checksumString}"
      throw new IllegalArgumentException(err)
    }

    msg
  }

  def apply(code: String, data: String): Message = {
    val cmd = Command(code)
    new Message(cmd, data)
  }
}

// Message format = NNMSD..00CC (CR-LF)
//   NN - Packet length [0-9A-F]: Length excluding NN and CR-LF
//   M - Message type [a-zA-Z]
//   S - Sub-Message type [0-9a-zA-Z]
//   D - Data [\p{Print}]
//   00 - Reserved
//   CC - Checksum: Hex 2's complement of mod-256 sum of message chars excluding checksum
//     and CR-LF
class Message(val command: Command, val data: String) {
  def packetLength: Int = 6 + data.length
  def packetString: String = packetStringWithoutChecksum + checksumString
  def packetStringWithoutChecksum: String =
    "%02X".format(packetLength) + command.code + data + "00"
  def messageSum: Int = packetStringWithoutChecksum.foldLeft(0)(_ + _.toInt)
  def checksum: Int = (255 - messageSum % 256) + 1
  def checksumString: String = "%02X".format(checksum)
}

