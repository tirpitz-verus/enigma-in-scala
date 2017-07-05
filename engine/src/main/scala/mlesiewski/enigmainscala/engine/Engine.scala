package mlesiewski.enigmainscala.engine

import mlesiewski.enigmainscala.engine.rotor.{Reflector, SteppingRotor}

private[engine] object Engine {

  def apply (dailyKey: DailyKey): Engine = {
    val keyboard: Keyboard = null
    val lampboard: Lampboard = null
    val plugboard: Plugboard = null
    val greekWheel: Option[SteppingRotor] = dailyKey.greekWheel.map(key => SteppingRotor(key))
    val reflector: Reflector = greekWheel match {
      case Some (_) => Reflector.asThin(Reflector(dailyKey.reflectorName))
      case None => Reflector(dailyKey.reflectorName)
    }
    val leftWheel: SteppingRotor = SteppingRotor(dailyKey.leftWheel)
    val middleWheel: SteppingRotor = SteppingRotor(dailyKey.middleWheel)
    val rightWheel: SteppingRotor = SteppingRotor(dailyKey.rightWheel)
    new Engine(
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
private[engine] class Engine private (
                                       val keyboard: Keyboard,
                                       val lampboard: Lampboard,
                                       val plugboard: Plugboard,
                                       val reflector: Reflector,
                                       val greekWheel: Option[SteppingRotor],
                                       val leftWheel: SteppingRotor,
                                       val middleWheel: SteppingRotor,
                                       val rightWheel: SteppingRotor
                                     ) {

  /** Simulates when a key is pressed on the Enigma machine.
    * First rotors are stepped.
    * Then the character is feed through the rotors.
    * The result is switched on the lampbord.
    *
    * @param key a character that was entered into the enigma machine
    * @return a new state of the engine - with rotors stepped, and lampboard lit
    */
  private[engine] def pressKey (key: Char): Engine = {
    val steppedEngine = stepRotors ()
    val letter = steppedEngine.encode (key)
    val newLampboard = lampboard.highlight (letter)
    new Engine (
      keyboard,
      newLampboard,
      plugboard,
      reflector,
      steppedEngine.greekWheel,
      steppedEngine.leftWheel,
      steppedEngine.middleWheel,
      steppedEngine.rightWheel
    )
  }

  /** steps all the rotors
    *
    * @return an Engine with its rotors stepped
    */
  private[engine] def stepRotors () = {
    val newRightWheel = rightWheel.step
    var newMiddleWheel = if (rightWheel.notchEngaged) middleWheel.step else middleWheel
    val newLeftWheel = if (middleWheel.notchEngaged) leftWheel.step else leftWheel
    // the left wheel takes middle wheel with it (a.k.a. double stepping)
    newMiddleWheel = if (middleWheel.notchEngaged) newMiddleWheel.step else newMiddleWheel
    new Engine (keyboard, lampboard, plugboard, reflector, greekWheel, newLeftWheel, newMiddleWheel, newRightWheel)
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
