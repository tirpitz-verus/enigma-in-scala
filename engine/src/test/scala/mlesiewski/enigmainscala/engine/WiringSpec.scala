package mlesiewski.enigmainscala.engine

import org.scalatest.Inspectors._
import org.scalatest.Matchers._
import org.scalatest._

class WiringSpec extends FunSpec {

  // Rotor I
  val testBaseSetting = "EKMFLGDQVZNTOWYHXUSPAIBRCJ"

  describe ("with base setting of '" + testBaseSetting + "'") {

    forAll (1 to 26) { offset =>

      describe("and offset of '" + offset + "'") {

        val wiring = new Wiring(testBaseSetting, offset)

        forAll(0 to 25) { charCode =>

          val letter = (65 + charCode).toChar
          val expected = testBaseSetting.charAt((charCode + offset - 1) % 26)

          it("should encode '" + letter + "' into  '" + expected + "'") {
            wiring.encode(letter) should be (expected)
          }
        }
      }
    }
  }
}
