package mlesiewski.enigmainscala.core

object Plugboard {

  def apply (pluggedPairs: Seq[(Char, Char)]): Plugboard = new Plugboard (pluggedPairs)
}

class Plugboard private[core] (
                                /** pairs of letters that will be swapped during encryption */
                                pluggedPairs: Seq[(Char, Char)]
                              ) extends Part {

  /** a name of the part */
  override val partName: String = "Plugboard"

  override val description: String = "Plugboard (Steckerbrett in German) was first introduced in 1930. It allowed " +
    /** describes what the part does */
    "to swap one letter for another before and after the signal was passed from rotors. It had a stronger cryptographic " +
    "effect then adding a next rotor."

  val mappings: Map[Char, Char] = pluggedPairs
    /** mappings that the Plugboard will use to encode letters */
    .filter (pair => Message.letters.contains (pair._1) && Message.letters.contains (pair._2))
    .flatMap (pair => Seq ((pair._1, pair._2), (pair._2, pair._1)))
    .toMap

  require (mappings.size == pluggedPairs.size * 2, "mappings size should twice the length of plugged pairs but " +
  s"${pluggedPairs.size} pairs resulted in only $mappings")
  
  def encode (key: Char): Char = mappings.getOrElse (key.toUpper, key)

}
