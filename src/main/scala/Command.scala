package org.jamieei.elk

import scala.io.Source

object Command {
  val LINE = """\s*(\p{Alpha}\p{Alnum})\s+\W+\s*(\S[\p{Graph}\s]+)""".r
  val commands = loadCommands

  def apply(code: String): Command = commands(code)

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
