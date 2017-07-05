package mlesiewski.enigmainscala.engine.rotor

import mlesiewski.enigmainscala.engine._

/** A rotor that can step - the offset of the rotor can be modified so that
  * the wiring would encode letters differently
  *
  * @param rotorOffset offset of this rotor that changes with stepping
  * @param wiring      wiring of the rotor
  * @param notches     positions in which a notch would be engaged
  */
abstract class SteppingRotor (
                          /** rotor offset - the one displayed by the Enigma panel above the lampboard and changing with rotor stepping */
                          val rotorOffset: Char,

                          /** wiring of the rotor that maps letter to one another */
                          override val wiring: Wiring,

                          /** notches of the rotor - positions in which a notch would be engaged */
                          val notches: Seq[Char]

                        ) extends Rotor (wiring) {

  /** returns the next offset of the rotor
    *
    * @return next offset of the rotor
    */
  protected def nextOffset: Char = if (rotorOffset == 'Z') 'A' else (rotorOffset.toInt + 1).toChar

  /** steps the rotor to the next position
    *
    * @return a stepped SteppingRotor
    */
  private[engine] def step: SteppingRotor

  /** Checks if the notch is engaged - should the next rotor be stepped.
    *
    * @return true if the notch is engaged
    */
  private[engine] def notchEngaged: Boolean = notches contains rotorOffset
}

object SteppingRotor {

  /**
    * @param wheelKey a key for the wheel
    * @return an instance of a SteppingRotor
    */
  def apply (wheelKey: WheelKey): SteppingRotor = SteppingRotor.apply (wheelKey.rotorName, wheelKey.rotorOffset, wheelKey.ringSetting)


  /** returns an instance of SteppingRotor
    *
    * @param rotorName   name of the stepping rotor to produce
    * @param rotorOffset starting offset of the rotor
    * @param ringSetting ring setting
    * @return an instance of a SteppingRotor
    */
  def apply (rotorName: String, rotorOffset: Char, ringSetting: Int): SteppingRotor = rotorName match {
    case "I" => new Rotor_I (rotorOffset, ringSetting)
    case "II" => new Rotor_II (rotorOffset, ringSetting)
    case "III" => new Rotor_III (rotorOffset, ringSetting)
    case "IV" => new Rotor_IV (rotorOffset, ringSetting)
    case "V" => new Rotor_V (rotorOffset, ringSetting)
    case "VI" => new Rotor_VI (rotorOffset, ringSetting)
    case "VII" => new Rotor_VII (rotorOffset, ringSetting)
    case "VIII" => new Rotor_VIII (rotorOffset, ringSetting)
    case _ => throw new IllegalArgumentException (s"unknown rotor name $rotorName")
  }
}

class Rotor_I (
                rotorOffset: Char,
                ringSetting: Int
              ) extends SteppingRotor (rotorOffset, new Wiring ("EKMFLGDQVZNTOWYHXUSPAIBRCJ", ringSetting + rotorOffset), Seq ('Q')) {

  val rotorName: String = "I"
  val partName: String = "Rotor I"
  val description: String = "It encrypts one letter (substitution cypher). Rotor I was introduced in the Enigma I in 1930."

  override def step: SteppingRotor = new Rotor_I (nextOffset, ringSetting)
}

class Rotor_II (
                 rotorOffset: Char,
                 ringSetting: Int
               ) extends SteppingRotor (rotorOffset, new Wiring ("AJDKSIRUXBLHWTMCQGZNPYFVOE", ringSetting + rotorOffset), Seq ('E')) {

  val rotorName: String = "II"
  val partName: String = "Rotor II"
  val description: String = "It encrypts one letter (substitution cypher). Rotor II was introduced in the Enigma I in 1930."

  override def step: SteppingRotor = new Rotor_II (nextOffset, ringSetting)
}

class Rotor_III (
                  rotorOffset: Char,
                  ringSetting: Int
                ) extends SteppingRotor (rotorOffset, new Wiring ("BDFHJLCPRTXVZNYEIWGAKMUSQO", ringSetting + rotorOffset), Seq ('V')) {

  val rotorName: String = "III"
  val partName: String = "Rotor III"
  val description: String = "It encrypts one letter (substitution cypher). Rotor III was introduced in the Enigma I in 1930."

  override def step: SteppingRotor = new Rotor_III (nextOffset, ringSetting)
}

class Rotor_IV (
                 rotorOffset: Char,
                 ringSetting: Int
               ) extends SteppingRotor (rotorOffset, new Wiring ("ESOVPZJAYQUIRHXLNFTGKDCMWB", ringSetting + rotorOffset), Seq ('J')) {

  val rotorName: String = "IV"
  val partName: String = "Rotor IV"
  val description: String = "It encrypts one letter (substitution cypher). Rotor IV was introduced in the M3 'Army' Enigma on December 1938."

  override def step: SteppingRotor = new Rotor_IV (nextOffset, ringSetting)
}

class Rotor_V (
                rotorOffset: Char,
                ringSetting: Int
              ) extends SteppingRotor (rotorOffset, new Wiring ("VZBRGITYUPSDNHLXAWMJQOFECK", ringSetting + rotorOffset), Seq ('Z')) {

  val rotorName: String = "V"
  val partName: String = "Rotor V"
  val description: String = "It encrypts one letter (substitution cypher). Rotor V was introduced in the M3 'Army' Enigma on December 1938."

  override def step: SteppingRotor = new Rotor_V (nextOffset, ringSetting)
}

class Rotor_VI (
                 rotorOffset: Char,
                 ringSetting: Int
               ) extends SteppingRotor (rotorOffset, new Wiring ("JPGVOUMFYQBENHZRDKASXLICTW", ringSetting + rotorOffset), Seq ('Z', 'M')) {

  val rotorName: String = "VI"
  val partName: String = "Rotor VI"
  val description: String = "It encrypts one letter (substitution cypher)." +
    " Rotor VI was introduced in the M3 'Army' Enigma in 1939 and in the M4 'Navy' Enigma on February 1942."

  override def step: SteppingRotor = new Rotor_VI (nextOffset, ringSetting)
}

class Rotor_VII (
                  rotorOffset: Char,
                  ringSetting: Int
                ) extends SteppingRotor (rotorOffset, new Wiring ("NZJHGRCXMYSWBOUFAIVLPEKQDT", ringSetting + rotorOffset), Seq ('Z', 'M')) {

  val rotorName: String = "VII"
  val partName: String = "Rotor VII"
  val description: String = "It encrypts one letter (substitution cypher)." +
    " Rotor VII was introduced in the M3 'Army' Enigma in 1939 and in the M4 'Navy' Enigma on February 1942."

  override def step: SteppingRotor = new Rotor_VII (nextOffset, ringSetting)
}

class Rotor_VIII (
                   rotorOffset: Char,
                   ringSetting: Int
                 ) extends SteppingRotor (rotorOffset, new Wiring ("FKQHTLXOCBJSPDZRAMEWNIUYGV", ringSetting + rotorOffset), Seq ('Z', 'M')) {

  val rotorName: String = "VIII"
  val partName: String = "Rotor VIII"
  val description: String = "It encrypts one letter (substitution cypher)." +
    " Rotor VIII was introduced in the M3 'Army' Enigma in 1939 and in the M4 'Navy' Enigma on February 1942."

  override def step: SteppingRotor = new Rotor_VIII (nextOffset, ringSetting)
}
