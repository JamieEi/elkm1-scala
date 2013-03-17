package org.jamieei.elk

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class CommandSuite extends FunSuite with ShouldMatchers {
  test("parse commands line") {
    val (code, desc) = Command.parse("ab - Some command")
    code should equal ("ab")
    desc should equal ("Some command")
  }

  test("parse with number") {
    val (code, desc) = Command.parse("a0 – Disarm")
    code should equal ("a0")
    desc should equal ("Disarm")
  }

  test("loadCommands") {
    val commands = Command.loadCommands
    commands.size should equal (108)

    val desc = commands("IR")
    desc should equal ("M1XSP Insteon Read")
  }

  test("apply") {
    val cmd = Command("a0") 
    cmd.code should equal ("a0")
    cmd.description should equal ("Disarm")
  }

  test("apply w/ bad code") {
    val cmd = Command("xyz") 
    cmd.code should equal ("xyz")
    cmd.description should equal (UnknownCommand.Description)
  }
}

