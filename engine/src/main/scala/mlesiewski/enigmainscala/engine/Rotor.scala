package mlesiewski.enigmainscala.engine

/** Describes an Enigma wheel (wheel, drum or scrambler - Waltzen in German).
  *
  * @constructor used to set common fields
  * @param position position of the rotor
  * @param wiring   wiring of the rotor
  */
abstract class Rotor private[engine] (
                                       /** position of the rotor - the one displayed by the Enigma panel above the lampboard */
                                       val position: Char,

                                       /** wiring of the rotor that maps letter to one another */
                                       val wiring: Wiring,

                                       /** notch or notches of the rotor  */
                                       val notches: Seq[Char]
                                     ) extends EnigmaPart {

  /** encodes a single letter
    *
    * @param letter a letter to encode
    * @return an encoded letter
    * */
  def encode (letter: Char): Char = wiring encode letter

  /** steps the Rotor to the next position
    *
    * @return a tuple containing a stepped Rotor and a Boolean indicating if the next Rotor should be stepped (in an original Enigma machine that would mean that the notch engaged)
    */
  def step: (Rotor, Boolean)

  private[engine] def notchEngaged: Boolean = notches contains position

  private[engine] def nextPosition: Char = if (position == 'Z') 'A' else position
}

/** a companion object for obtaining Rotor instances */
object Rotor {

  /**
    * @param wheelKey a key for the wheel
    * @return an instance of a Rotor
    */
  def get (wheelKey: Option[WheelKey]): Option[Rotor] = wheelKey.map (wheelKey => get (wheelKey.rotorName, wheelKey.position, wheelKey.offset))

  /**
    * @param wheelKey a key for the wheel
    * @return an instance of a Rotor
    */
  def get (wheelKey: WheelKey): Rotor = get (wheelKey.rotorName, wheelKey.position, wheelKey.offset)


  /** returns an instance of Rotor
    *
    * @param rotorName name of the rotor to produce
    * @param position  starting position of the rotor
    * @param offset    offset of the rotor
    * @return an instance of a Rotor
    */
  def get (rotorName: String, position: Char, offset: Int): Rotor = rotorName match {
    case "I" => new Rotor_I (position, offset)
    case "II" => new Rotor_II (position, offset)
    case "III" => new Rotor_III (position, offset)
    case _ => throw new IllegalArgumentException ("unknown rotorName")
  }
}

/** represents the wiring of the rotor - maps one letter to another */
private[engine] class Wiring (
                               /** offset of the rotor */
                               val offset: Int
                             ) {

  def encode (letter: Char): Char = ???

  /**
    * @param baseSetting letters that would be returned for a mapping of the rotor if in offset 1
    * @param offset      offset of the rotor
    */
  def this (baseSetting: String, offset: Int) = this (offset)
}

private[engine] class Rotor_I (
                                position: Char,
                                offset: Int
                              ) extends Rotor (position, new Wiring ("EKMFLGDQVZNTOWYHXUSPAIBRCJ", offset), Seq ('Q')) {

  val rotorName: String = "I"
  val partName: String = "Rotor I"
  val description: String = "It encrypts one letter (substitution cypher). Rotor I was introduced in the Enigma I in 1930."

  override def step: (Rotor, Boolean) = (new Rotor_I (nextPosition, offset), notchEngaged)
}

private[engine] class Rotor_II (
                                 position: Char,
                                 offset: Int
                               ) extends Rotor (position, new Wiring ("AJDKSIRUXBLHWTMCQGZNPYFVOE", offset), Seq ('E')) {

  val rotorName: String = "II"
  val partName: String = "Rotor II"
  val description: String = "It encrypts one letter (substitution cypher). Rotor II was introduced in the Enigma I in 1930."

  override def step: (Rotor, Boolean) = (new Rotor_II (nextPosition, offset), notchEngaged)
}

private[engine] class Rotor_III (
                                  position: Char,
                                  offset: Int
                                ) extends Rotor (position, new Wiring ("BDFHJLCPRTXVZNYEIWGAKMUSQO", offset), Seq ('V')) {

  val rotorName: String = "III"
  val partName: String = "Rotor III"
  val description: String = "It encrypts one letter (substitution cypher). Rotor III was introduced in the Enigma I in 1930."

  override def step: (Rotor, Boolean) = (new Rotor_III (nextPosition, offset), notchEngaged)
}