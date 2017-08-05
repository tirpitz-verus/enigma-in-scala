package mlesiewski.enigmainscala.core.rotor

import mlesiewski.enigmainscala.core.{DailyKey, Engine, WheelKey}
import org.scalatest.Matchers._
import org.scalatest._

class RotorSteppingSpec extends FunSpec {

  it ("rotor offset affects letter encoding in the same way as ring setting") {
    val letter = 'C'

    val rotor_2_A = SteppingRotor(new WheelKey ("I", 2, 'A'))
    val rotor_1_B = SteppingRotor(new WheelKey ("I", 1, 'B'))

    rotor_1_B.encode(letter) should be (rotor_2_A.encode(letter))
  }

  // based on https://en.wikipedia.org/wiki/Enigma_rotor_details#Turnover_notch_positions
  describe ("rotors can step") {

    val reflectorName = "A"
    val leftWheelKey = new WheelKey ("I", 1, 'A')
    val rightWheelKey = new WheelKey ("III", 1, 'U')

    it ("in the so-called normal sequence") {
      val middleWheelKey = new WheelKey ("II", 1, 'A')
      val key = new DailyKey (reflectorName, Option.empty, leftWheelKey, middleWheelKey, rightWheelKey, Seq.empty)
      val engine = Engine (key)

      // normal step of right rotor
      engine.leftWheel.rotorOffset should be (leftWheelKey.rotorOffset)
      engine.middleWheel.rotorOffset should be (middleWheelKey.rotorOffset)
      engine.rightWheel.rotorOffset should be (rightWheelKey.rotorOffset)

      // right rotor (III) goes in V—notch position
      engine.stepRotors ()
      engine.leftWheel.rotorOffset should be (leftWheelKey.rotorOffset)
      engine.middleWheel.rotorOffset should be (middleWheelKey.rotorOffset)
      engine.rightWheel.rotorOffset should be ('V')

      // right rotor takes middle rotor one step further
      engine.stepRotors ()
      engine.leftWheel.rotorOffset should be (leftWheelKey.rotorOffset)
      engine.middleWheel.rotorOffset should be ('B')
      engine.rightWheel.rotorOffset should be ('W')

      // normal step of right rotor
      engine.stepRotors ()
      engine.leftWheel.rotorOffset should be (leftWheelKey.rotorOffset)
      engine.middleWheel.rotorOffset should be ('B')
      engine.rightWheel.rotorOffset should be ('X')
    }

    it ("in the so-called double step sequence") {
      val middleWheelKey = new WheelKey ("II", 1, 'D')
      val key = new DailyKey (reflectorName, Option.empty, leftWheelKey, middleWheelKey, rightWheelKey, Seq.empty)
      val engine = Engine (key)

      // normal step of right rotor
      engine.leftWheel.rotorOffset should be (leftWheelKey.rotorOffset)
      engine.middleWheel.rotorOffset should be (middleWheelKey.rotorOffset)
      engine.rightWheel.rotorOffset should be (rightWheelKey.rotorOffset)

      // right rotor (III) goes in V—notch position
      engine.stepRotors ()
      engine.leftWheel.rotorOffset should be (leftWheelKey.rotorOffset)
      engine.middleWheel.rotorOffset should be (middleWheelKey.rotorOffset)
      engine.rightWheel.rotorOffset should be ('V')

      // right rotor steps, takes middle rotor (II) one step further, which is now in its own E—notch position
      engine.stepRotors ()
      engine.leftWheel.rotorOffset should be (leftWheelKey.rotorOffset)
      engine.middleWheel.rotorOffset should be ('E')
      engine.rightWheel.rotorOffset should be ('W')

      // normal step of right rotor, double step of middle rotor, normal step of left rotor
      engine.stepRotors ()
      engine.leftWheel.rotorOffset should be ('B')
      engine.middleWheel.rotorOffset should be ('F')
      engine.rightWheel.rotorOffset should be ('X')

      // normal step of right rotor
      engine.stepRotors ()
      engine.leftWheel.rotorOffset should be ('B')
      engine.middleWheel.rotorOffset should be ('F')
      engine.rightWheel.rotorOffset should be ('Y')
    }
  }
}
