package com.kedi.common.utils

object StringUtils {
   implicit class StringExtensions(val s: String) {
    import scala.util.control.Exception._
    def toIntOption = catching(classOf[NumberFormatException]) opt s.trim.toInt
    def toLongOption = catching(classOf[NumberFormatException]) opt s.trim.toLong
    def toDoubleOption = catching(classOf[NumberFormatException]) opt s.trim.toDouble
  }
}