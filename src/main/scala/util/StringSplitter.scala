package org.jamieei.elk.util

object StringSplitter {
  object PartialResult {
    def apply(str: String): PartialResult = new PartialResult(List[String](), str)
    def apply(res: List[String]): PartialResult = new PartialResult(res, "")
  }

  case class PartialResult(val result: List[String], val remainder: String) {
    override def toString: String = s"""($result, "$remainder")"""
    def reverse: PartialResult = new PartialResult(result.reverse, remainder)
  }
}

class StringSplitter(leftLengths: List[Int], rightLengths: List[Int]) {
  import StringSplitter.PartialResult

  def split(str: String): List[String] = {
    val left = splitLeft(str)
    val right = splitRight(left.remainder)
    left.result ++ prependUnlessEmpty(right.remainder, right.result)
  }

  def splitLeft(str: String): PartialResult = {
    val acc = PartialResult(str)
    leftLengths.foldLeft(acc)((acc, len) => {
      val (part, rem) = acc.remainder.splitAt(len)
      val res = prependUnlessEmpty(part, acc.result)
      new PartialResult(res, rem)
    }).reverse
  }

  def splitRight(str: String): PartialResult = {
    val acc = PartialResult(str)
    rightLengths.foldRight(acc)((len, acc) => {
      val (rem, part) = acc.remainder.splitAt(acc.remainder.length - len)
      val res = prependUnlessEmpty(part, acc.result)
      new PartialResult(res, rem)
    })
  }

  private def calcResult(acc: PartialResult, len: Int): PartialResult = {
    val (part, rem) = acc.remainder.splitAt(len)
    val res = prependUnlessEmpty(part, acc.result)
    new PartialResult(res, rem)
  }

  private def prependUnlessEmpty(x: String, xs: List[String]) = if (x.isEmpty) xs else x :: xs
}

