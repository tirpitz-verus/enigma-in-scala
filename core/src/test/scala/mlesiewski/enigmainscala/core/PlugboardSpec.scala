package mlesiewski.enigmainscala.core

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class PlugboardSpec extends FunSpec {

  describe ("while constructing a Plugboard") {

    it ("each letter can be paired only once") {
      val letter = 'E'
      val pair = (letter, letter)
      val sequence = Seq(pair)
      an [IllegalArgumentException] should be thrownBy {
        Plugboard(sequence)
      }
    }

    it ("only letters are allowed") {
      an [IllegalArgumentException] should be thrownBy {
        Plugboard(Seq((';', '@')))
      }
    }
  }

  describe ("encoding") {

    val pair :(Char, Char) = ('A', 'Z')
    val plugboard :Plugboard = Plugboard(Seq(pair))

    it ("swaps letters in pairs") {
      plugboard encode pair._1 should be (pair._2)
      plugboard encode pair._2 should be (pair._1)
    }

    it ("does not swap letters not in pairs") {
      val letter :Char = (pair._1.toInt + 1).toChar
      plugboard encode letter should be (letter)
    }

    it ("case does not matter") {
      plugboard encode pair._1.toLower should be (pair._2)
    }
  }
}
