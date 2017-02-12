package mlesiewski.enigmainscala.engine

import org.scalatest.Inspectors._
import org.scalatest.Matchers._
import org.scalatest._

class MessageSpec extends FunSpec {

  describe ("converting text") {

    describe ("from 'for encoding' to 'pretty'") {

      it ("should change Y and following number letters (QWERTZUIOP) to a number") {
        Message.toPrettyText ("Y") should be ("Y")
        Message.toPrettyText ("YC") should be ("YC")
        Message.toPrettyText ("YQC") should be ("1C")
        Message.toPrettyText ("YQWERTZUIOP") should be ("1234567890")
        Message.toPrettyText ("YYQWERTZUIOP") should be ("Y1234567890")
      }

      it ("should throw IllegalArgumentException on illegal characters") {
        forAll (nonLetters :+ " " :+ "1234567890") { character =>
          an [IllegalArgumentException] should be thrownBy {
            Message.toPrettyText (character)
          }
        }
      }

      it ("should leave normal letters intact") {
        Message.toPrettyText ("") should be ("")
        Message.toPrettyText ("QAZWSXEDC") should be ("QAZWSXEDC")
      }

      it ("should output text in upper case") {
        Message.toPrettyText ("qWeRt") should be ("QWERT")
      }
    }

    describe ("from 'pretty' to 'for encoding'") {

      it ("should change numbers to an Y number format") {
        Message.toEnigmaFormat ("1234567890") should be ("YQWERTZUIOP")
        Message.toEnigmaFormat ("B1WAZ") should be ("BYQWAZ")
      }

      it ("should throw IllegalArgumentException on non letters") {
        forAll (nonLetters) { character =>
          an [IllegalArgumentException] should be thrownBy {
            Message.toEnigmaFormat (character)
          }
        }
      }

      it ("should leave normal letters intact") {
        Message.toEnigmaFormat ("") should be ("")
        Message.toEnigmaFormat ("QAZWSXEDC") should be ("QAZWSXEDC")
      }

      it ("should output text in upper case") {
        Message.toEnigmaFormat ("qWeRt") should be ("QWERT")
      }

    }

  }

  private def nonLetters = {
    def byLetterRange (i: Int) = (i < 'A'.toInt && i > 'Z'.toInt) || (i < 'a'.toInt && i > 'z'.toInt)

    def toLetterString (i: Int) = i.toChar.toString

    1 to 255 filter byLetterRange map toLetterString
  }
}
