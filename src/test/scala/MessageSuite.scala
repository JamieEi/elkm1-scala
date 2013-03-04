package org.jamieei.elk

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class MessageSuite extends FunSuite with ShouldMatchers {
  val SAY_PHRASE_123 = Message("sp", "123") // "09sp12300BE"
  val SAY_PHRASE_123_STR = "09sp12300BE"
  val ARM_AWAY_1234 = "Da11001234003F"

  test("create message from string") {
    Message(SAY_PHRASE_123_STR)
  }

  test("packetLength") {
    SAY_PHRASE_123.packetLength should equal ("09sp12300".length)
  }

  test("packetString") {
    SAY_PHRASE_123.packetString should equal (SAY_PHRASE_123_STR)
  }

  test("packetStringWithoutChecksum") {
    SAY_PHRASE_123.packetStringWithoutChecksum should equal ("09sp12300")
  }

  test("messageSum") {
    SAY_PHRASE_123.messageSum should equal ("09sp12300".map(_.toInt).sum)
  }

  test("checksum") {
    SAY_PHRASE_123.checksum should equal (Integer.parseInt("BE", 16))
  }
}

