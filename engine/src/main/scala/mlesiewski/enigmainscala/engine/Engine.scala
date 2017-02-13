package mlesiewski.enigmainscala.engine

/** Describes a group of parts of the 4-wheel (M4 or Navy) Enigma machine.
  * Effectively this is the state before the next keystroke.
  */
class Engine private[engine] (
                               val keyboard: Keyboard,
                               val lampboard: Lampboard,
                               val plugboard: Plugboard,
                               val reflector: Reflector,
                               val greekWheel: Option[Rotor],
                               val leftWheel: Rotor,
                               val middleWheel: Rotor,
                               val rightWheel: Rotor
                             ) {

  private[engine] def this (dailyKey: DailyKey) = {
    this (
      null,
      null,
      null, // dailyKey.pluggedPairs
      null, // dailyKey.reflectorName
      Rotor.get (dailyKey.greekWheel),
      Rotor.get (dailyKey.leftWheel),
      Rotor.get (dailyKey.middleWheel),
      Rotor.get (dailyKey.rightWheel)
    )
  }

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
    new Engine (keyboard, lampboard.highlight (letter), plugboard, reflector, steppedEngine.greekWheel, steppedEngine.leftWheel, steppedEngine.middleWheel, steppedEngine.rightWheel)
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
