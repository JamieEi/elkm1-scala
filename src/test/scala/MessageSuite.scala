package org.jamieei.elk

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class MessageSuite extends FunSuite with ShouldMatchers {
  val SAY_PHRASE_123 = "09sp12300BE"
  val ARM_AWAY_1234 = "Da11001234003F"

  test("parse message") {
    val msg = Message(SAY_PHRASE_123)
    msg.packetString should equal (SAY_PHRASE_123)
    //msg.command should equal (Command("sp"))
    msg.data should equal ("123")
  }

  test("synthesize message") {
    val msg = Message("sp", "123")
    msg.packetString should equal (SAY_PHRASE_123)
    //msg.command should equal (Command("sp"))
    msg.data should equal ("123")
  }

  test("checksum") {
    val checksum = Message.checksum(SAY_PHRASE_123.dropRight(2))
    val exp = Integer.valueOf(SAY_PHRASE_123.takeRight(2), 16)
    checksum should equal (exp)
  }
}

