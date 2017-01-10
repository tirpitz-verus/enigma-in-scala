package mlesiewski.enigmainscala.engine

import org.scalatest.Inspectors._
import org.scalatest.Matchers._
import org.scalatest._

class WiringSpec extends FunSpec {

  val testBaseSetting = "EKMFLGDQVZNTOWYHXUSPAIBRCJ"

  describe ("with base setting of '" + testBaseSetting + "'") {

    forAll (for (n <- 1 to 26) yield n) { offset =>

      describe ("and offset of '" + offset + "'") {

        val inputOutputPairsGenerator: Seq[(Char, Char)] = for (input <- 'A' to 'Z'; o <- testBaseSetting) yield {
          val output = if (o > 'Z') {
            o - 26
          } else o
          (input, output.toChar)
        }

        forAll (inputOutputPairsGenerator) { pair =>

          it ("should encode '" + pair._1 + "' into '" + pair._2 + "'") {
            val encoded = new Wiring (testBaseSetting, 0).encode (pair._1)
            encoded should be (pair._2)
          }

        }

      }

    }

  }

}
