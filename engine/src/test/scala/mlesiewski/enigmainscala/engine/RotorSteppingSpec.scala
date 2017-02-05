package mlesiewski.enigmainscala.engine

import org.scalatest.Matchers._
import org.scalatest._

class RotorSteppingSpec extends FunSpec {

  // based on https://en.wikipedia.org/wiki/Enigma_rotor_details#Turnover_notch_positions
  describe ("rotors can step") {

    val reflectorName = "Reflector A"
    val leftWheelKey = new WheelKey ("I", 'A', 0)
    val rightWheelKey = new WheelKey ("III", 'U', 0)

    it ("in the so-called normal sequence") {
      val middleWheelKey = new WheelKey ("II", 'A', 0)
      val key = new DailyKey (reflectorName, Option.empty, leftWheelKey, middleWheelKey, rightWheelKey, Seq.empty)
      var engine = new Engine (key)

      // normal step of right rotor
      engine.leftWheel.position should be (leftWheelKey.position)
      engine.middleWheel.position should be (middleWheelKey.position)
      engine.rightWheel.position should be (rightWheelKey.position)

      // right rotor (III) goes in V—notch position
      engine = engine.stepRotors ()
      engine.leftWheel.position should be (leftWheelKey.position)
      engine.middleWheel.position should be (middleWheelKey.position)
      engine.rightWheel.position should be ('V')

      // right rotor takes middle rotor one step further
      engine = engine.stepRotors ()
      engine.leftWheel.position should be (leftWheelKey.position)
      engine.middleWheel.position should be ('B')
      engine.rightWheel.position should be ('W')

      // normal step of right rotor
      engine = engine.stepRotors ()
      engine.leftWheel.position should be (leftWheelKey.position)
      engine.middleWheel.position should be ('B')
      engine.rightWheel.position should be ('X')
    }

    it ("in the so-called double step sequence") {
      val middleWheelKey = new WheelKey ("II", 'D', 0)
      val key = new DailyKey (reflectorName, Option.empty, leftWheelKey, middleWheelKey, rightWheelKey, Seq.empty)
      var engine = new Engine (key)

      // normal step of right rotor
      engine.leftWheel.position should be (leftWheelKey.position)
      engine.middleWheel.position should be (middleWheelKey.position)
      engine.rightWheel.position should be (rightWheelKey.position)

      // right rotor (III) goes in V—notch position
      engine = engine.stepRotors ()
      engine.leftWheel.position should be (leftWheelKey.position)
      engine.middleWheel.position should be (middleWheelKey.position)
      engine.rightWheel.position should be ('V')

      // right rotor steps, takes middle rotor (II) one step further, which is now in its own E—notch position
      engine = engine.stepRotors ()
      engine.leftWheel.position should be (leftWheelKey.position)
      engine.middleWheel.position should be ('E')
      engine.rightWheel.position should be ('W')

      // normal step of right rotor, double step of middle rotor, normal step of left rotor
      engine = engine.stepRotors ()
      engine.leftWheel.position should be ('B')
      engine.middleWheel.position should be ('F')
      engine.rightWheel.position should be ('X')

      // normal step of right rotor
      engine = engine.stepRotors ()
      engine.leftWheel.position should be ('B')
      engine.middleWheel.position should be ('F')
      engine.rightWheel.position should be ('Y')
    }
  }
}
