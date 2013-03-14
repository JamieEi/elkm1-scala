package org.jamieei.elk

import scala.io.Source

object Command {
  private val LINE = """\s*(\p{Alpha}\p{Alnum})\s+\W+\s*(\S[\p{Graph}\s]+)""".r
  private val commands = loadCommands

  def apply(code: String): Command = {
    val cmd = commands.get(code)
    if (cmd.isEmpty) throw new IllegalArgumentException(s"Command not found: $code")
    cmd.get
  }

  def unapply(cmd: Command): String = cmd.code

  def parse(line: String): Command = {
    val LINE(code, desc) = line
    new Command(code, desc.trim)
  }

  def loadCommands = {
    val res = getClass.getResource("/commands")
    val source = Source.fromURL(res)
    source.getLines.map(parse(_)).map((c) => (c.code, c)).toMap
  }
}

class Command(val code: String, val description: String) {
}

// 2nd attempt

sealed abstract class Command2(code: String) extends Command2.Value {
  def description: String = "foo" //description(code)
}

object Command2 extends Enum[Command2] {
  case object SPEAK_PHRASE extends Command2("xx")
}

