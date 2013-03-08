package org.jamieei.elk.util

/**
 * Splits a string into multiple fixed-length parts and a single variable-length remainder.
 * @constructor Creates a new StringSplitter instance.
 * @param leftLengths Length of parts to the left of the remainder.
 * @param rightLengths Length of parts to the right of the remainder.
 * @example new StringSplitter(List(5, 2), List(3)).split("Luckyisadog") => List("Lucky", "is", "a", "dog")
 */
class StringSplitter(leftLengths: List[Int], rightLengths: List[Int]) {
  private val specLength = leftLengths.sum + rightLengths.sum

  def split(str: String): List[String] = {
    // Merge the left and right lengths with the implicit middle length
    val midLength = str.length - specLength
    val lengths = leftLengths ++ (midLength :: rightLengths)

    // Split the string
    val acc = (List[String](), str)
    lengths.foldLeft(acc)((acc, len) => {
      val (part, rem) = acc._2.splitAt(len)
      val res = prependUnlessEmpty(part, acc._1)
      (res, rem)
    })._1.reverse
  }

  private def prependUnlessEmpty(x: String, xs: List[String]) = if (x.isEmpty) xs else x :: xs
}

