package mlesiewski.enigmainscala.core

import mlesiewski.enigmainscala.core.keyboard.{DummyKeyboard, Keyboard, KeyboardListener}
import mlesiewski.enigmainscala.core.lampboard.{DummyLampboard, Lampboard}
import mlesiewski.enigmainscala.core.rotor.{Reflector, SteppingRotor}

private[core] object Engine {

  def apply (dailyKey: DailyKey): Engine = apply (dailyKey, new DummyLampboard (), new DummyKeyboard)

  def apply (dailyKey: DailyKey, lampboard: Lampboard, keyboard: Keyboard): Engine = {
    val plugboard: Plugboard = Plugboard (dailyKey.pluggedPairs)
    val greekWheel: Option[SteppingRotor] = dailyKey.greekWheel.map (key => SteppingRotor (key))
    val reflector: Reflector = greekWheel match {
      case Some (_) => Reflector.asThin (Reflector (dailyKey.reflectorName))
      case None => Reflector (dailyKey.reflectorName)
    }
    val leftWheel: SteppingRotor = SteppingRotor (dailyKey.leftWheel)
    val middleWheel: SteppingRotor = SteppingRotor (dailyKey.middleWheel)
    val rightWheel: SteppingRotor = SteppingRotor (dailyKey.rightWheel)
    new Engine (
      keyboard,
      lampboard,
      plugboard,
      reflector,
      greekWheel,
      leftWheel,
      middleWheel,
      rightWheel
    )
  }
}

/** Describes a group of parts of the 4-wheel (M4 or Navy) Enigma machine.
  * Effectively this is the state before the next keystroke.
  */
private[core] class Engine private (
                                     val keyboard: Keyboard,
                                     val lampboard: Lampboard,
                                     val plugboard: Plugboard,
                                     val reflector: Reflector,
                                     val greekWheel: Option[SteppingRotor],
                                     var leftWheel: SteppingRotor,
                                     var middleWheel: SteppingRotor,
                                     var rightWheel: SteppingRotor
                                   ) extends KeyboardListener {

  keyboard.setListener (this)

  override private[core] def onLetterTyped (letter: Char): Unit = {
    stepRotors ()
    val encoded = encode (letter)
    lampboard.highlight (encoded)
  }

  /** steps all the rotors
    *
    * @return an Engine with its rotors stepped
    */
  private[core] def stepRotors () = {
    val newRightWheel = rightWheel.step
    var newMiddleWheel = if (rightWheel.notchEngaged) middleWheel.step else middleWheel
    val newLeftWheel = if (middleWheel.notchEngaged) leftWheel.step else leftWheel
    // the left wheel takes middle wheel with it (a.k.a. double stepping)
    // so if the left wheel stepped so will the middle wheel
    newMiddleWheel = if (middleWheel.notchEngaged) newMiddleWheel.step else newMiddleWheel
    // stepping takes into account the starting positions of rotors
    // so now we need to assign them new values
    rightWheel = newRightWheel
    middleWheel = newMiddleWheel
    leftWheel = newLeftWheel
  }

  /** Encodes a single letter going through the lapmboard, rotors, reflector and rotors in reverse order
    *
    * @param letter character to encode
    * @return encoded character
    */
  private def encode (letter: Char): Char = {
    var encoded = plugboard.encode (letter)
    encoded = rightWheel.encode (encoded)
    encoded = middleWheel.encode (encoded)
    encoded = leftWheel.encode (encoded)
    encoded = greekWheel.map (r => r.encode (encoded)).getOrElse (encoded)
    encoded = reflector.encode (encoded)
    encoded = greekWheel.map (r => r.encode (encoded)).getOrElse (encoded)
    encoded = leftWheel.encode (encoded)
    encoded = middleWheel.encode (encoded)
    rightWheel.encode (encoded)
  }
}
