package mlesiewski.enigmainscala.engine

/** A rotor that can step - the position of the rotor can be modified so that
  * the wiring would encode letters differently
  */
private[engine] abstract class SteppingRotor (
                                               /** position of the rotor - the one displayed by the Enigma panel above the lampboard */
                                               val position: Char,

                                               /** wiring of the rotor that maps letter to one another */
                                               override val wiring: Wiring,

                                               /** notches of the rotor - positions in which a notch would be engaged */
                                               val notches: Seq[Char]

                                             ) extends Rotor (wiring) {

  /** returns the next position of the rotor
    *
    * @return next position of the rotor
    */
  protected def nextPosition: Char = if (position == 'Z') 'A' else (position.toInt + 1).toChar

  /** steps the rotor to the next position
    *
    * @return a stepped SteppingRotor
    */
  private[engine] def step: SteppingRotor

  /** Checks if the notch is engaged - should the next rotor be stepped.
    *
    * @return true if the notch is engaged
    */
  private[engine] def notchEngaged: Boolean = notches contains position
}

/** a companion object for obtaining SteppingRotor instances */
private[engine] object SteppingRotor {

  /**
    * @param wheelKey a key for the wheel
    * @return an instance of a SteppingRotor
    */
  def get (wheelKey: Option[WheelKey]): Option[SteppingRotor] = wheelKey.map (wheelKey => get (wheelKey.rotorName, wheelKey.position, wheelKey.offset))

  /**
    * @param wheelKey a key for the wheel
    * @return an instance of a SteppingRotor
    */
  def get (wheelKey: WheelKey): SteppingRotor = get (wheelKey.rotorName, wheelKey.position, wheelKey.offset)


  /** returns an instance of SteppingRotor
    *
    * @param rotorName name of the stepping rotor to produce
    * @param position  starting position of the rotor
    * @param offset    offset of the rotor
    * @return an instance of a SteppingRotor
    */
  def get (rotorName: String, position: Char, offset: Int): SteppingRotor = rotorName match {
    case "I" => new Rotor_I (position, offset)
    case "II" => new Rotor_II (position, offset)
    case "III" => new Rotor_III (position, offset)
    case "IV" => new Rotor_IV (position, offset)
    case "V" => new Rotor_V (position, offset)
    case "VI" => new Rotor_VI (position, offset)
    case "VII" => new Rotor_VII (position, offset)
    case "VIII" => new Rotor_VIII (position, offset)
    case _ => throw new IllegalArgumentException ("unknown rotorName")
  }
}


private[engine] class Rotor_I (
                                position: Char,
                                offset: Int
                              ) extends SteppingRotor (position, new Wiring ("EKMFLGDQVZNTOWYHXUSPAIBRCJ", offset), Seq ('Q')) {

  val rotorName: String = "I"
  val partName: String = "Rotor I"
  val description: String = "It encrypts one letter (substitution cypher). Rotor I was introduced in the Enigma I in 1930."

  override def step: SteppingRotor = new Rotor_I (nextPosition, offset)
}

private[engine] class Rotor_II (
                                 position: Char,
                                 offset: Int
                               ) extends SteppingRotor (position, new Wiring ("AJDKSIRUXBLHWTMCQGZNPYFVOE", offset), Seq ('E')) {

  val rotorName: String = "II"
  val partName: String = "Rotor II"
  val description: String = "It encrypts one letter (substitution cypher). Rotor II was introduced in the Enigma I in 1930."

  override def step: SteppingRotor = new Rotor_II (nextPosition, offset)
}

private[engine] class Rotor_III (
                                  position: Char,
                                  offset: Int
                                ) extends SteppingRotor (position, new Wiring ("BDFHJLCPRTXVZNYEIWGAKMUSQO", offset), Seq ('V')) {

  val rotorName: String = "III"
  val partName: String = "Rotor III"
  val description: String = "It encrypts one letter (substitution cypher). Rotor III was introduced in the Enigma I in 1930."

  override def step: SteppingRotor = new Rotor_III (nextPosition, offset)
}

private[engine] class Rotor_IV (
                                 position: Char,
                                 offset: Int
                               ) extends SteppingRotor (position, new Wiring ("ESOVPZJAYQUIRHXLNFTGKDCMWB", offset), Seq ('J')) {

  val rotorName: String = "IV"
  val partName: String = "Rotor IV"
  val description: String = "It encrypts one letter (substitution cypher). Rotor IV was introduced in the M3 'Army' Enigma on December 1938."

  override def step: SteppingRotor = new Rotor_IV (nextPosition, offset)
}

private[engine] class Rotor_V (
                                position: Char,
                                offset: Int
                              ) extends SteppingRotor (position, new Wiring ("VZBRGITYUPSDNHLXAWMJQOFECK", offset), Seq ('Z')) {

  val rotorName: String = "V"
  val partName: String = "Rotor V"
  val description: String = "It encrypts one letter (substitution cypher). Rotor V was introduced in the M3 'Army' Enigma on December 1938."

  override def step: SteppingRotor = new Rotor_V (nextPosition, offset)
}

private[engine] class Rotor_VI (
                                 position: Char,
                                 offset: Int
                               ) extends SteppingRotor (position, new Wiring ("JPGVOUMFYQBENHZRDKASXLICTW", offset), Seq ('Z', 'M')) {

  val rotorName: String = "VI"
  val partName: String = "Rotor VI"
  val description: String = "It encrypts one letter (substitution cypher)." +
    " Rotor VI was introduced in the M3 'Army' Enigma in 1939 and in the M4 'Navy' Enigma on February 1942."

  override def step: SteppingRotor = new Rotor_VI (nextPosition, offset)
}

private[engine] class Rotor_VII (
                                  position: Char,
                                  offset: Int
                                ) extends SteppingRotor (position, new Wiring ("NZJHGRCXMYSWBOUFAIVLPEKQDT", offset), Seq ('Z', 'M')) {

  val rotorName: String = "VII"
  val partName: String = "Rotor VII"
  val description: String = "It encrypts one letter (substitution cypher)." +
    " Rotor VII was introduced in the M3 'Army' Enigma in 1939 and in the M4 'Navy' Enigma on February 1942."

  override def step: SteppingRotor = new Rotor_VII (nextPosition, offset)
}

private[engine] class Rotor_VIII (
                                   position: Char,
                                   offset: Int
                                 ) extends SteppingRotor (position, new Wiring ("FKQHTLXOCBJSPDZRAMEWNIUYGV", offset), Seq ('Z', 'M')) {

  val rotorName: String = "VIII"
  val partName: String = "Rotor VIII"
  val description: String = "It encrypts one letter (substitution cypher)." +
    " Rotor VIII was introduced in the M3 'Army' Enigma in 1939 and in the M4 'Navy' Enigma on February 1942."

  override def step: SteppingRotor = new Rotor_VIII (nextPosition, offset)
}
