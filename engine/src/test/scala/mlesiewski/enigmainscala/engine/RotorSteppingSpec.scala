package mlesiewski.enigmainscala.engine

import org.scalatest.Matchers._
import org.scalatest._

class RotorSteppingSpec extends FunSpec {

  // based on https://en.wikipedia.org/wiki/Enigma_rotor_details#Turnover_notch_positions
  describe ("rotors can step") {

    val reflectorName = "A"
    val leftWheelKey = new WheelKey ("I", 0, 'A')
    val rightWheelKey = new WheelKey ("III", 0, 'U')

    it ("in the so-called normal sequence") {
      val middleWheelKey = new WheelKey ("II", 0, 'A')
      val key = new DailyKey (reflectorName, Option.empty, leftWheelKey, middleWheelKey, rightWheelKey, Seq.empty)
      var engine = new Engine (key)

      // normal step of right rotor
      engine.leftWheel.rotorOffset should be (leftWheelKey.rotorOffset)
      engine.middleWheel.rotorOffset should be (middleWheelKey.rotorOffset)
      engine.rightWheel.rotorOffset should be (rightWheelKey.rotorOffset)

      // right rotor (III) goes in V—notch position
      engine = engine.stepRotors ()
      engine.leftWheel.rotorOffset should be (leftWheelKey.rotorOffset)
      engine.middleWheel.rotorOffset should be (middleWheelKey.rotorOffset)
      engine.rightWheel.rotorOffset should be ('V')

      // right rotor takes middle rotor one step further
      engine = engine.stepRotors ()
      engine.leftWheel.rotorOffset should be (leftWheelKey.rotorOffset)
      engine.middleWheel.rotorOffset should be ('B')
      engine.rightWheel.rotorOffset should be ('W')

      // normal step of right rotor
      engine = engine.stepRotors ()
      engine.leftWheel.rotorOffset should be (leftWheelKey.rotorOffset)
      engine.middleWheel.rotorOffset should be ('B')
      engine.rightWheel.rotorOffset should be ('X')
    }

    it ("in the so-called double step sequence") {
      val middleWheelKey = new WheelKey ("II", 0, 'D')
      val key = new DailyKey (reflectorName, Option.empty, leftWheelKey, middleWheelKey, rightWheelKey, Seq.empty)
      var engine = new Engine (key)

      // normal step of right rotor
      engine.leftWheel.rotorOffset should be (leftWheelKey.rotorOffset)
      engine.middleWheel.rotorOffset should be (middleWheelKey.rotorOffset)
      engine.rightWheel.rotorOffset should be (rightWheelKey.rotorOffset)

      // right rotor (III) goes in V—notch position
      engine = engine.stepRotors ()
      engine.leftWheel.rotorOffset should be (leftWheelKey.rotorOffset)
      engine.middleWheel.rotorOffset should be (middleWheelKey.rotorOffset)
      engine.rightWheel.rotorOffset should be ('V')

      // right rotor steps, takes middle rotor (II) one step further, which is now in its own E—notch position
      engine = engine.stepRotors ()
      engine.leftWheel.rotorOffset should be (leftWheelKey.rotorOffset)
      engine.middleWheel.rotorOffset should be ('E')
      engine.rightWheel.rotorOffset should be ('W')

      // normal step of right rotor, double step of middle rotor, normal step of left rotor
      engine = engine.stepRotors ()
      engine.leftWheel.rotorOffset should be ('B')
      engine.middleWheel.rotorOffset should be ('F')
      engine.rightWheel.rotorOffset should be ('X')

      // normal step of right rotor
      engine = engine.stepRotors ()
      engine.leftWheel.rotorOffset should be ('B')
      engine.middleWheel.rotorOffset should be ('F')
      engine.rightWheel.rotorOffset should be ('Y')
    }
  }
}
