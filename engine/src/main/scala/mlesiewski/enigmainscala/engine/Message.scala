package mlesiewski.enigmainscala.engine

import scala.collection.mutable
import scala.collection.mutable.StringBuilder

/** represents a message to be encrypted with an Enigma machine */
class Message (
                /** a setting for this message */
                val setting: (Char, Char, Char),

                /** a "pretty text" message - with space characters and numbers */
                val prettyText: String,

                /** a text in Enigma format - without spaces and numbers */
                val inEnigmaFormat: String
              )

object Message {

  private val letters = "QWERTYUIOPASDFGHJKLZXCVBNM"
  private val numbers = "1234567890"
  private val lettersEncodingNumbers = "PQWERTZUIO"

  /** creates a message from a pretty text
    *
    * @param setting    a setting for this message
    * @param prettyText a pretty text string
    */
  def fromPrettyText (setting: (Char, Char, Char), prettyText: String): Message =
    new Message (setting, prettyText, toEnigmaFormat (prettyText))

  private[engine] def toEnigmaFormat (prettyText: String): String = {
    implicit val allowed = letters + numbers
    val result = valid (prettyText.toUpperCase)
    "([0-9]+)".r.replaceAllIn (result, m => number2letter (m.matched))
  }

  // 48 is 0 in ASCII
  private def number2letter (number: String): String = {
    number.foldLeft (new mutable.StringBuilder ("Y")) { (acc: mutable.StringBuilder, c: Char) =>
      acc += lettersEncodingNumbers.charAt (c.toInt - 48)
    }.toString
  }

  /** creates a message from a text in an Enigma format
    *
    * @param setting        a setting for this message
    * @param inEnigmaFormat text in an Enigma format
    */
  def fromEnigmaFormat (setting: (Char, Char, Char), inEnigmaFormat: String): Message =
    new Message (setting, toPrettyText (inEnigmaFormat), inEnigmaFormat)

  private[engine] def toPrettyText (inEnigmaFormat: String): String = {
    implicit val allowed = letters
    val result = valid (inEnigmaFormat.toUpperCase)
    s"Y[$lettersEncodingNumbers]+".r.replaceAllIn(result, m => letter2number(m.matched))
  }

  // 48 is 0 in ASCII
  private def letter2number (letters: String): String = {
    letters.substring(1).foldLeft (new mutable.StringBuilder ()) { (acc: mutable.StringBuilder, c: Char) =>
      acc += (lettersEncodingNumbers.indexOf(c) + 48).toChar
    }.toString
  }

  private def valid (input: String)(implicit allowed: String): String = input.map (c => if (!allowed.contains (c)) throw new IllegalArgumentException (s"illegal character found '$c'") else c).toString
}
