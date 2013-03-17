package org.jamieei.elk

import scala.io.Source

object Command {
  private val codesToDescriptions: Map[String, String] = loadCommands

  def apply(code: String): Command = {
    new UnknownCommand(code)
  }

  def unapply(cmd: Command): String = cmd.code

  def parse(line: String): (String, String) = {
    val pat = """\s*(\p{Alpha}\p{Alnum})\s+\W+\s*(\S[\p{Graph}\s]+)""".r
    val pat(code, desc) = line
    (code, desc.trim)
  }

  def loadCommands: Map[String, String] = {
    val res = getClass.getResource("/commands")
    val source = Source.fromURL(res)
    source.getLines.map(parse(_)).toMap
  }

  def description(code: String): Option[String] = codesToDescriptions.get(code)

  case object Heartbeat extends KnownCommand("XK")
  case object SayPhrase extends KnownCommand("sp")
}

trait Command {
  def code: String
  def description: String = Command.description(code).getOrElse(UnknownCommand.Description)
}

object KnownCommand extends Enum[KnownCommand]
sealed abstract class KnownCommand(val code: String) extends KnownCommand.Value with Command

object UnknownCommand {
  val Description = "<unknown>"
}

class UnknownCommand(val code: String) extends Command
