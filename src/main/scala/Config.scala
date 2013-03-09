package org.jamieei.elk

/**
 * ELK configuration.
 */
object Config {
  val ip = sys.env("ELK")
  val port = 2101
  val code = sys.env("ELK_CODE")
}
