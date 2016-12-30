package mlesiewski.enigmainscala.engine

import org.scalatest._
import Matchers._
import Inspectors._

class MessageSpec extends FunSpec {

  describe("converting text") {

    describe("from 'for encoding' to 'pretty'") {

      it("should change X to space characters"){
        Message.toPrettyText("X") should be (" ")
        Message.toPrettyText("XX") should be ("  ")
        Message.toPrettyText("ASDFGXHJKL") should be ("ASDFG HJKL")
      }

      it("should change Y and following number letters (QWERTZUIOP) to a number"){
        Message.toPrettyText("Y") should be ("Y")
        Message.toPrettyText("YC") should be ("YC")
        Message.toPrettyText("YQC") should be ("1C")
        Message.toPrettyText("YQWERTZUIOP") should be ("1234567890")
        Message.toPrettyText("YYQWERTZUIOP") should be ("Y1234567890")
      }

      it("should throw IllegalArgumentException on illegal characters"){
        forAll (nonLetters :+ " " :+ "1234567890") { character =>
          an [IllegalArgumentException] should be thrownBy {
            Message.toPrettyText(character)
          }
        }
      }

      it("should leave normal letters intact"){
        Message.toPrettyText("") should be ("")
        Message.toPrettyText("QAZWSXEDC") should be ("QAZWSXEDC")
      }

      it("should output text in upper case") {
        Message.toPrettyText("qWeRt") should be ("QWERT")
      }
    }

    describe("from 'pretty' to 'for encoding'") {

      it("should change space characters to X"){
        Message.toTextForEncoding(" ") should be ("X")
        Message.toTextForEncoding("  ") should be ("XX")
        Message.toTextForEncoding("ASDFG HJKL") should be ("ASDFGXHJKL")
      }

      it("should change numbers to an Y number format"){
        Message.toTextForEncoding("1234567890") should be ("YQWERTZUIOP")
        Message.toTextForEncoding("B1WAZ") should be ("BYQWAZ")
      }

      it("should throw IllegalArgumentException on non letters"){
        forAll (nonLetters) { character =>
          an [IllegalArgumentException] should be thrownBy {
            Message.toTextForEncoding(character)
          }
        }
      }

      it("should leave normal letters intact"){
        Message.toTextForEncoding("") should be ("")
        Message.toTextForEncoding("QAZWSXEDC") should be ("QAZWSXEDC")
      }

      it("should output text in upper case") {
        Message.toTextForEncoding("qWeRt") should be ("QWERT")
      }

    }

  }

  private def nonLetters = {
    (1 to 255).
      filter (i => i < 'A'.toInt && i > 'Z'.toInt).
      filter (i => i < 'a'.toInt && i > 'z'.toInt).
      map (i => i.toChar).
      map (i => i.toString)
  }
}
