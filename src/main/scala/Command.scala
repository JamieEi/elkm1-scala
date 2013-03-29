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

  def  
  def genKnownCommands: List[String] = {
  }

  case object Disarm extends KnownCommand("a0") // Disarm
  case object ArmAway extends KnownCommand("a1") // Arm to away
  case object ArmStay extends KnownCommand("a2") // Arm to stay
  case object ArmStayInstant extends KnownCommand("a3") // Arm to stay instant
  case object ArmNight extends KnownCommand("a4") // Arm to night
  case object ArmNightInstant extends KnownCommand("a5") // Arm to night instant
  case object ArmVacation extends KnownCommand("a6") // Arm to vacation
  case object ArmStepNextAway extends KnownCommand("a7") // Arm step to next Away Mode
  case object ArmStepNextStay extends KnownCommand("a8") // Arm step to next Stay Mode
  case object SendAsciiString extends KnownCommand("AP") // Send ASCII String
  case object AlarmReportingAcknowledge extends KnownCommand("ar") // Alarm Reporting Acknowledge
  case object AlarmReportingToEthernet extends KnownCommand("AR") // Alarm Reporting to Ethernet
  case object RequestArmingStatus extends KnownCommand("as") // Request arming status
  case object ArmingStatusReportData extends KnownCommand("AS") // Arming status report data
  case object EthernetTestAcknowledge extends KnownCommand("at") // Ethernet Test Acknowledge
  case object EthernetTestToIP extends KnownCommand("AT") // Ethernet Test to IP
  case object AlarmByZoneRequest extends KnownCommand("az") // Alarm by zone request
  case object AlarmByZoneReply extends KnownCommand("AZ") // Alarm by zone reply
  case object TouchscreenAudioRequest extends KnownCommand("ca") // Request Touchscreen audio command
  case object TouchscreenAudioReply extends KnownCommand("CA") // Reply Touchscreen audio command
  case object XYZ extends KnownCommand("CC") // Control output change update
  case object XYZ extends KnownCommand("cd") // Incoming Audio Equip Command
  case object XYZ extends KnownCommand("CD") // Outgoing Audio Equip Command
  case object XYZ extends KnownCommand("cf") // Control output OFF
  case object XYZ extends KnownCommand("cn") // Control output ON
  case object XYZ extends KnownCommand("cp") // Request ALL custom values
  case object XYZ extends KnownCommand("cr") // Request custom value
  case object XYZ extends KnownCommand("CR") // Custom value report data
  case object XYZ extends KnownCommand("cs") // Control output status request
  case object XYZ extends KnownCommand("CS") // Control output status report data
  case object XYZ extends KnownCommand("ct") // Control output TOGGLE
  case object XYZ extends KnownCommand("cu") // Change user code request
  case object XYZ extends KnownCommand("CU") // Change user code reply
  case object XYZ extends KnownCommand("cw") // Write custom value data
  case object XYZ extends KnownCommand("cv") // Request Counter value
  case object XYZ extends KnownCommand("CV") // Counter Value Data
  case object XYZ extends KnownCommand("cx") // Write counter value
  case object XYZ extends KnownCommand("dm") // Display message
  case object XYZ extends KnownCommand("ds") // Lighting Poll Request
  case object XYZ extends KnownCommand("DS") // Lighting Poll Response
  case object XYZ extends KnownCommand("DK") // Display KP LCD Data, not used
  case object XYZ extends KnownCommand("EE") // Entry/Exit Time Data
  case object XYZ extends KnownCommand("EM") // Email Trigger to M1XEP
  case object XYZ extends KnownCommand("IC") // Send invalid user code digits
  case object XYZ extends KnownCommand("IE") // Installer program exited
  case object XYZ extends KnownCommand("IP") // M1XSP Insteon Program
  case object XYZ extends KnownCommand("ip") // M1XSP Insteon Program
  case object XYZ extends KnownCommand("IR") // M1XSP Insteon Read
  case object XYZ extends KnownCommand("ir") // M1XSP Insteon Read
  case object XYZ extends KnownCommand("ka") // Request keypad areas
  case object XYZ extends KnownCommand("KA") // Keypad areas report data
  case object XYZ extends KnownCommand("kc") // Request F Key illumination status
  case object XYZ extends KnownCommand("KC") // Keypad key change update
  case object XYZ extends KnownCommand("kf") // Request simulated function key press
  case object XYZ extends KnownCommand("KF") // Function key pressed data
  case object XYZ extends KnownCommand("LD") // Log data with index
  case object XYZ extends KnownCommand("ld") // Request log data, with index
  case object XYZ extends KnownCommand("le") // Write Log Data Entry
  case object XYZ extends KnownCommand("lw") // Request temperature data
  case object XYZ extends KnownCommand("LW") // Reply temperature data
  case object XYZ extends KnownCommand("NS") // Reply Source Name
  case object XYZ extends KnownCommand("NZ") // Reply Zone Name
  case object XYZ extends KnownCommand("pc") // Control any PLC device
  case object XYZ extends KnownCommand("PC") // PLC change update
  case object XYZ extends KnownCommand("pf") // Turn OFF PLC device
  case object XYZ extends KnownCommand("pn") // Turn ON PLC device
  case object XYZ extends KnownCommand("ps") // Request PLC status
  case object XYZ extends KnownCommand("PS") // PLC status report data
  case object XYZ extends KnownCommand("pt") // Toggle PLC device
  case object XYZ extends KnownCommand("RE") // Reset Ethernet Module
  case object XYZ extends KnownCommand("RP") // ELKRP connected
  case object XYZ extends KnownCommand("rr") // Request Real Time Clock Read
  case object XYZ extends KnownCommand("RR") // Real Time Clock Data
  case object XYZ extends KnownCommand("rs") // Used by Touchscreen
  case object XYZ extends KnownCommand("rw") // Real Time Clock Write
  case object XYZ extends KnownCommand("sd") // Request text string descriptions
  case object XYZ extends KnownCommand("SD") // Text string description report data
  case object XYZ extends KnownCommand("sp") // Speak phrase
  case object XYZ extends KnownCommand("ss") // Request System Trouble Status
  case object XYZ extends KnownCommand("SS") // System Trouble Status data
  case object XYZ extends KnownCommand("st") // Request temperature
  case object XYZ extends KnownCommand("ST") // Temperature report data
  case object XYZ extends KnownCommand("sw") // Speak word
  case object XYZ extends KnownCommand("t2") // Request Omnistat 2 data
  case object XYZ extends KnownCommand("T2") // Reply Omnistat 2 data
  case object XYZ extends KnownCommand("TC") // Task change update
  case object XYZ extends KnownCommand("tn") // Task activation
  case object XYZ extends KnownCommand("tr") // Request thermostat data
  case object XYZ extends KnownCommand("TR") // Thermostat data report
  case object XYZ extends KnownCommand("ts") // Set thermostat data
  case object XYZ extends KnownCommand("ua") // Request user code areas
  case object XYZ extends KnownCommand("UA") // User code areas report data
  case object XYZ extends KnownCommand("vn") // request Version Number of M1
  case object XYZ extends KnownCommand("VN") // Reply Version Number of M1
  case object XYZ extends KnownCommand("XB") // reserved by ELKRP
  case object XYZ extends KnownCommand("xk") // Reply from Ethernet test
  case object XYZ extends KnownCommand("XK") // Request Ethernet test
  case object XYZ extends KnownCommand("zb") // Zone bypass request
  case object XYZ extends KnownCommand("ZB") // Zone bypass report data
  case object XYZ extends KnownCommand("ZC") // Zone change update
  case object XYZ extends KnownCommand("zd") // Request zone definition data
  case object XYZ extends KnownCommand("ZD") // Zone definition report data
  case object XYZ extends KnownCommand("zp") // Zone partition request
  case object XYZ extends KnownCommand("ZP") // Zone partition report data
  case object XYZ extends KnownCommand("zs") // Zone status request
  case object XYZ extends KnownCommand("ZS") // Zone status report data
  case object XYZ extends KnownCommand("zv") // Request Zone analog voltage
  case object XYZ extends KnownCommand("ZV") // Zone analog voltage data
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
