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
  case object {{ControlOutputChangeUpdate}} extends KnownCommand("CC") // Control output change update
  case object {{IncomingAudioEquip}} extends KnownCommand("cd") // Incoming Audio Equip Command
  case object {{OutgoingAudioEquip}} extends KnownCommand("CD") // Outgoing Audio Equip Command
  case object {{ControlOutputOff}} extends KnownCommand("cf") // Control output OFF
  case object {{ControlOutputOn}} extends KnownCommand("cn") // Control output ON
  case object {{RequestAllCustomValues}} extends KnownCommand("cp") // Request ALL custom values
  case object {{RequestCustomValue}} extends KnownCommand("cr") // Request custom value
  case object {{CustomValueReportData}} extends KnownCommand("CR") // Custom value report data
  case object {{ControlOutputStatusRequest}} extends KnownCommand("cs") // Control output status request
  case object {{ControlOutputStatusReportData}} extends KnownCommand("CS") // Control output status report data
  case object {{ControlOutputToggle}} extends KnownCommand("ct") // Control output TOGGLE
  case object {{ChangeUserCodeRequest}} extends KnownCommand("cu") // Change user code request
  case object {{ChangeUserCodeReply}} extends KnownCommand("CU") // Change user code reply
  case object {{Write custom value data}} extends KnownCommand("cw") // Write custom value data
  case object {{Request Counter value}} extends KnownCommand("cv") // Request Counter value
  case object {{Counter Value Data}} extends KnownCommand("CV") // Counter Value Data
  case object {{Write counter value}} extends KnownCommand("cx") // Write counter value
  case object {{Display message}} extends KnownCommand("dm") // Display message
  case object {{Lighting Poll Request}} extends KnownCommand("ds") // Lighting Poll Request
  case object {{Lighting Poll Response}} extends KnownCommand("DS") // Lighting Poll Response
  case object {{Display KP LCD Data, not used}} extends KnownCommand("DK") // Display KP LCD Data, not used
  case object {{Entry/Exit Time Data}} extends KnownCommand("EE") // Entry/Exit Time Data
  case object {{Email Trigger to M1XEP}} extends KnownCommand("EM") // Email Trigger to M1XEP
  case object {{Send invalid user code digits}} extends KnownCommand("IC") // Send invalid user code digits
  case object {{Installer program exited}} extends KnownCommand("IE") // Installer program exited
  case object {{M1XSP Insteon Program}} extends KnownCommand("IP") // M1XSP Insteon Program
  case object {{M1XSP Insteon Program}} extends KnownCommand("ip") // M1XSP Insteon Program
  case object {{M1XSP Insteon Read}} extends KnownCommand("IR") // M1XSP Insteon Read
  case object {{M1XSP Insteon Read}} extends KnownCommand("ir") // M1XSP Insteon Read
  case object {{Request keypad areas}} extends KnownCommand("ka") // Request keypad areas
  case object {{Keypad areas report data}} extends KnownCommand("KA") // Keypad areas report data
  case object {{Request F Key illumination status}} extends KnownCommand("kc") // Request F Key illumination status
  case object {{Keypad key change update}} extends KnownCommand("KC") // Keypad key change update
  case object {{Request simulated function key press}} extends KnownCommand("kf") // Request simulated function key press
  case object {{Function key pressed data}} extends KnownCommand("KF") // Function key pressed data
  case object {{Log data with index}} extends KnownCommand("LD") // Log data with index
  case object {{Request log data, with index}} extends KnownCommand("ld") // Request log data, with index
  case object {{Write Log Data Entry}} extends KnownCommand("le") // Write Log Data Entry
  case object {{Request temperature data}} extends KnownCommand("lw") // Request temperature data
  case object {{Reply temperature data}} extends KnownCommand("LW") // Reply temperature data
  case object {{Reply Source Name}} extends KnownCommand("NS") // Reply Source Name
  case object {{Reply Zone Name}} extends KnownCommand("NZ") // Reply Zone Name
  case object {{Control any PLC device}} extends KnownCommand("pc") // Control any PLC device
  case object {{PLC change update}} extends KnownCommand("PC") // PLC change update
  case object {{Turn OFF PLC device}} extends KnownCommand("pf") // Turn OFF PLC device
  case object {{Turn ON PLC device}} extends KnownCommand("pn") // Turn ON PLC device
  case object {{Request PLC status}} extends KnownCommand("ps") // Request PLC status
  case object {{PLC status report data}} extends KnownCommand("PS") // PLC status report data
  case object {{Toggle PLC device}} extends KnownCommand("pt") // Toggle PLC device
  case object {{Reset Ethernet Module}} extends KnownCommand("RE") // Reset Ethernet Module
  case object {{ELKRP connected}} extends KnownCommand("RP") // ELKRP connected
  case object {{Request Real Time Clock Read}} extends KnownCommand("rr") // Request Real Time Clock Read
  case object {{Real Time Clock Data}} extends KnownCommand("RR") // Real Time Clock Data
  case object {{Used by Touchscreen}} extends KnownCommand("rs") // Used by Touchscreen
  case object {{Real Time Clock Write}} extends KnownCommand("rw") // Real Time Clock Write
  case object {{Request text string descriptions}} extends KnownCommand("sd") // Request text string descriptions
  case object {{Text string description report data}} extends KnownCommand("SD") // Text string description report data
  case object {{Speak phrase}} extends KnownCommand("sp") // Speak phrase
  case object {{Request System Trouble Status}} extends KnownCommand("ss") // Request System Trouble Status
  case object {{System Trouble Status data}} extends KnownCommand("SS") // System Trouble Status data
  case object {{Request temperature}} extends KnownCommand("st") // Request temperature
  case object {{Temperature report data}} extends KnownCommand("ST") // Temperature report data
  case object {{Speak word}} extends KnownCommand("sw") // Speak word
  case object {{Request Omnistat 2 data}} extends KnownCommand("t2") // Request Omnistat 2 data
  case object {{Reply Omnistat 2 data}} extends KnownCommand("T2") // Reply Omnistat 2 data
  case object {{Task change update}} extends KnownCommand("TC") // Task change update
  case object {{Task activation}} extends KnownCommand("tn") // Task activation
  case object {{Request thermostat data}} extends KnownCommand("tr") // Request thermostat data
  case object {{Thermostat data report}} extends KnownCommand("TR") // Thermostat data report
  case object {{Set thermostat data}} extends KnownCommand("ts") // Set thermostat data
  case object {{Request user code areas}} extends KnownCommand("ua") // Request user code areas
  case object {{User code areas report data}} extends KnownCommand("UA") // User code areas report data
  case object {{request Version Number of M1}} extends KnownCommand("vn") // request Version Number of M1
  case object {{Reply Version Number of M1}} extends KnownCommand("VN") // Reply Version Number of M1
  case object {{reserved by ELKRP}} extends KnownCommand("XB") // reserved by ELKRP
  case object {{Reply from Ethernet test}} extends KnownCommand("xk") // Reply from Ethernet test
  case object {{Request Ethernet test}} extends KnownCommand("XK") // Request Ethernet test
  case object {{Zone bypass request}} extends KnownCommand("zb") // Zone bypass request
  case object {{Zone bypass report data}} extends KnownCommand("ZB") // Zone bypass report data
  case object {{Zone change update}} extends KnownCommand("ZC") // Zone change update
  case object {{Request zone definition data}} extends KnownCommand("zd") // Request zone definition data
  case object {{Zone definition report data}} extends KnownCommand("ZD") // Zone definition report data
  case object {{Zone partition request}} extends KnownCommand("zp") // Zone partition request
  case object {{Zone partition report data}} extends KnownCommand("ZP") // Zone partition report data
  case object {{Zone status request}} extends KnownCommand("zs") // Zone status request
  case object {{Zone status report data}} extends KnownCommand("ZS") // Zone status report data
  case object {{Request Zone analog voltage}} extends KnownCommand("zv") // Request Zone analog voltage
  case object {{Zone analog voltage data}} extends KnownCommand("ZV") // Zone analog voltage data
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
