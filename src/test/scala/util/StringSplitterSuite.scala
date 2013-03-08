package org.jamieei.elk.util

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class StringSplitterSuite extends FunSuite with ShouldMatchers {
  test("split empty string") {
    val ss = new StringSplitter(List(), List())
    ss.split("") should equal (List())
    ss.split("abc") should equal (List("abc"))
  }

  test("split left only string") {
    val ss = new StringSplitter(List(1, 2), List())
    ss.split("") should equal (List())
    ss.split("a") should equal (List("a"))
    ss.split("abc") should equal (List("a", "bc"))
    ss.split("abcdef") should equal (List("a", "bc", "def"))
  }

  test("split right only string") {
    val ss = new StringSplitter(List(), List(1, 2))
    ss.split("") should equal (List())
    ss.split("a") should equal (List("a"))
    ss.split("abc") should equal (List("a", "bc"))
    ss.split("abcdef") should equal (List("abc", "d", "ef"))
  }

  test("split left and right string") {
    val ss = new StringSplitter(List(1, 2), List(1, 2))
    ss.split("") should equal (List())
    ss.split("abcdef") should equal (List("a", "bc", "d", "ef"))
    ss.split("abcdefghi") should equal (List("a", "bc", "def", "g", "hi"))
  }

  test("scaladoc example(s)") {
    val ss = new StringSplitter(List(5, 2), List(3))
    ss.split("Luckyisadog") should equal (List("Lucky", "is", "a", "dog"))
  }
}

