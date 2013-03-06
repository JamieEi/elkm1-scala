package org.jamieei.elk.util

import org.jamieei.elk.util.StringSplitter.PartialResult
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class StringSplitterSuite extends FunSuite with ShouldMatchers {
  test("splitLeft, empty") {
    val ss = new StringSplitter(List(), List())
    ss.splitLeft("") should equal (PartialResult(List()))
    ss.splitLeft("abc") should equal (PartialResult(List(), "abc"))
  }

  test("splitLeft, one part") {
    val ss = new StringSplitter(List(1), List())
    ss.splitLeft("") should equal (PartialResult(List()))
    ss.splitLeft("abc") should equal (PartialResult(List("a"), "bc"))
  }

  test("splitLeft, two parts") {
    val ss = new StringSplitter(List(1, 2), List())
    ss.splitLeft("") should equal (PartialResult(List()))
    ss.splitLeft("abc") should equal (PartialResult(List("a", "bc"), ""))
    ss.splitLeft("abcd") should equal (PartialResult(List("a", "bc"), "d"))
  }

  test("splitRight, empty") {
    val ss = new StringSplitter(List(), List())
    ss.splitRight("") should equal (PartialResult(List()))
    ss.splitRight("abc") should equal (PartialResult(List(), "abc"))
  }

  test("splitRight, one part") {
    val ss = new StringSplitter(List(), List(1))
    ss.splitRight("") should equal (PartialResult(List()))
    ss.splitRight("abc") should equal (PartialResult(List("c"), "ab"))
  }

  test("splitRight, two parts") {
    val ss = new StringSplitter(List(), List(1, 2))
    ss.splitRight("") should equal (PartialResult(List()))
    ss.splitRight("abc") should equal (PartialResult(List("a", "bc"), ""))
    ss.splitRight("abcd") should equal (PartialResult(List("b", "cd"), "a"))
  }

  test("split, empty") {
    val ss = new StringSplitter(List(), List())
    ss.split("") should equal (List())
    ss.split("abc") should equal (List("abc"))
  }

  test("split, left only") {
    val ss = new StringSplitter(List(1, 2), List())
    ss.split("") should equal (List())
    ss.split("a") should equal (List("a"))
    ss.split("abc") should equal (List("a", "bc"))
    ss.split("abcdef") should equal (List("a", "bc", "def"))
  }

  test("split, right only") {
    val ss = new StringSplitter(List(), List(1, 2))
    ss.split("") should equal (List())
    ss.split("a") should equal (List("a"))
    ss.split("abc") should equal (List("a", "bc"))
    ss.split("abcdef") should equal (List("abc", "d", "ef"))
  }

  test("split, left and right") {
    val ss = new StringSplitter(List(1, 2), List(1, 2))
    ss.split("") should equal (List())
    ss.split("abcdef") should equal (List("a", "bc", "d", "ef"))
    ss.split("abcdefghi") should equal (List("a", "bc", "def", "g", "hi"))
  }
}

