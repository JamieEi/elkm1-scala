package org.jamieei.elk

/**
 * Basic enum implementation. Incomplete.
 * @see http://stackoverflow.com/questions/1898932/case-classes-vs-enumerations-in-scala
 */
trait Enum[A] {
  trait Value { self: A => 
    _values :+= this
  }

  private var _values = List.empty[A]
  def values = _values
}

