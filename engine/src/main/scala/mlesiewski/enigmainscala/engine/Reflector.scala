package mlesiewski.enigmainscala.engine

/** Just marks a Rotor as a Reflector.
  * The reflector is also known as the reversing drum or, from the German, the Umkehrwalze or UKW. (cited from Wiki)
  */
private[engine] trait Reflector {
  this: Rotor =>

  /** encodes a single letter
    *
    * @param letter a letter to encode
    * @return an encoded letter
    */
  private[engine] def encode (letter: Char): Char
}

/** A companion object for creating Reflector instances.
  */
private[engine] object Reflector {

  /**
    * @param reflectorName name of the reflector
    * @return an instance of a Reflector
    */
  def get (reflectorName: String): Reflector = reflectorName match {
    case "Beta" => new Reflector_Beta with Reflector
    case "Gamma" => new Reflector_Gamma with Reflector
    case "A" => new Reflector_A with Reflector
    case "B" => new Reflector_B with Reflector
    case "C" => new Reflector_C with Reflector
    case "B Thin" => new Reflector_B_Thin with Reflector
    case "C Thin" => new Reflector_C_Thin with Reflector
    case _ => throw new IllegalArgumentException ("unknown reflectorName")
  }
}

private[engine] class Reflector_Beta extends Rotor (new Wiring ("LEYJVCNIXWPBQMDRTAKZGFUHOS")) {

  val rotorName: String = "Beta"
  val partName: String = "Reflector Beta"
  val description: String = "It encrypts one letter (substitution cypher). " +
    "Reflector Beta was introduced in 1941 in the Navy four rotor Enigma."
}

private[engine] class Reflector_Gamma extends Rotor (new Wiring ("FSOKANUERHMBTIYCWLQPZXVGJD")) {

  val rotorName: String = "Gamma"
  val partName: String = "Reflector Gamma"
  val description: String = "It encrypts one letter (substitution cypher). " +
    "Reflector Gamma was introduced in 1942 in the Navy four rotor Enigma."
}

private[engine] class Reflector_A extends Rotor (new Wiring ("EJMZALYXVBWFCRQUONTSPIKHGD")) {

  val rotorName: String = "A"
  val partName: String = "Reflector A"
  val description: String = "It encrypts one letter (substitution cypher)."
}

private[engine] class Reflector_B extends Rotor (new Wiring ("YRUHQSLDPXNGOKMIEBFZCWVJAT")) {

  val rotorName: String = "B"
  val partName: String = "Reflector B"
  val description: String = "It encrypts one letter (substitution cypher)."
}

private[engine] class Reflector_C extends Rotor (new Wiring ("FVPJIAOYEDRZXWGCTKUQSBNMHL")) {

  val rotorName: String = "C"
  val partName: String = "Reflector C"
  val description: String = "It encrypts one letter (substitution cypher)."
}

private[engine] class Reflector_B_Thin extends Rotor (new Wiring ("ENKQAUYWJICOPBLMDXZVFTHRGS")) {

  val rotorName: String = "B Thin"
  val partName: String = "Reflector B Thin"
  val description: String = "It encrypts one letter (substitution cypher). " +
    "Reflector B Thin was introduced in 1940 for the Navy Enigma to accompany Thin Rotors."
}

private[engine] class Reflector_C_Thin extends Rotor (new Wiring ("RDOBJNTKVEHMLFCWZAXGYIPSUQ")) {

  val rotorName: String = "C Thin"
  val partName: String = "Reflector C Thin"
  val description: String = "It encrypts one letter (substitution cypher). " +
    "Reflector C Thin was introduced in 1940 for the Navy Enigma to accompany Thin Rotors."
}
