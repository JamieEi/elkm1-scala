package org.jamieei.elk

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class CommandSuite extends FunSuite with ShouldMatchers {
  test("parse commands line") {
    val cmd = Command.parse("ab - Some command")
    cmd.code should equal ("ab")
    cmd.description should equal ("Some command")
  }

  test("parse with number") {
    val cmd = Command.parse("a0 – Disarm")
    cmd.code should equal ("a0")
    cmd.description should equal ("Disarm")
  }

  test("loadCommands") {
    val commands = Command.loadCommands
    commands.size should equal (108)

    val cmd = commands("IR")
    commands("IR").description should equal ("M1XSP Insteon Read")
  }

  test("apply") {
    val cmd = Command("a0") 
    cmd.code should equal ("a0")
    cmd.description should equal ("Disarm")
  }
}

