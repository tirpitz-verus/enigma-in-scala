package mlesiewski.enigmainscala.core.rotor

import scala.language.implicitConversions

/** Just marks a Rotor as a Reflector.
  * The reflector is also known as the reversing drum or, from the German, the Umkehrwalze or UKW. (cited from Wiki)
  */
trait Reflector extends Rotor

object Reflector {

  /**
    * @param reflectorName name of the reflector
    * @return an instance of a Reflector
    */
  def apply (reflectorName: String): Reflector = reflectorName match {
    case "Beta" => new Reflector_Beta
    case "Gamma" => new Reflector_Gamma
    case "A" => new Reflector_A
    case "B" => new Reflector_B
    case "C" => new Reflector_C
    case "B Thin" => new Reflector_B_Thin
    case "C Thin" => new Reflector_C_Thin
    case _ => throw new IllegalArgumentException (s"unknown reflector $reflectorName")
  }

  implicit def asThin (reflector: Reflector): Reflector with Thin = reflector match {
    case Reflector_B_Thin () => reflector.asInstanceOf [Reflector with Thin]
    case Reflector_C_Thin () => reflector.asInstanceOf [Reflector with Thin]
    case _ => throw new Exception (s"reflector ${reflector.getClass.getSimpleName} is not Thin")
  }
}

class Reflector_Beta extends Rotor (new Wiring ("LEYJVCNIXWPBQMDRTAKZGFUHOS")) with Reflector {

  val rotorName: String = "Beta"
  val partName: String = "Reflector Beta"
  val description: String = "It encrypts one letter (substitution cypher). " +
    "Reflector Beta was introduced in 1941 in the Navy four rotor Enigma."
}

class Reflector_Gamma extends Rotor (new Wiring ("FSOKANUERHMBTIYCWLQPZXVGJD")) with Reflector {

  val rotorName: String = "Gamma"
  val partName: String = "Reflector Gamma"
  val description: String = "It encrypts one letter (substitution cypher). " +
    "Reflector Gamma was introduced in 1942 in the Navy four rotor Enigma."
}

class Reflector_A extends Rotor (new Wiring ("EJMZALYXVBWFCRQUONTSPIKHGD")) with Reflector {

  val rotorName: String = "A"
  val partName: String = "Reflector A"
  val description: String = "It encrypts one letter (substitution cypher)."
}

class Reflector_B extends Rotor (new Wiring ("YRUHQSLDPXNGOKMIEBFZCWVJAT")) with Reflector {

  val rotorName: String = "B"
  val partName: String = "Reflector B"
  val description: String = "It encrypts one letter (substitution cypher)."
}

class Reflector_C extends Rotor (new Wiring ("FVPJIAOYEDRZXWGCTKUQSBNMHL")) with Reflector {

  val rotorName: String = "C"
  val partName: String = "Reflector C"
  val description: String = "It encrypts one letter (substitution cypher)."
}

case class Reflector_B_Thin () extends Rotor (new Wiring ("ENKQAUYWJICOPBLMDXZVFTHRGS")) with Reflector with Thin {

  val rotorName: String = "B Thin"
  val partName: String = "Reflector B Thin"
  val description: String = "It encrypts one letter (substitution cypher). " +
    "Reflector B Thin was introduced in 1940 for the Navy Enigma to accompany Thin Rotors."
}

case class Reflector_C_Thin () extends Rotor (new Wiring ("RDOBJNTKVEHMLFCWZAXGYIPSUQ")) with Reflector with Thin  {

  val rotorName: String = "C Thin"
  val partName: String = "Reflector C Thin"
  val description: String = "It encrypts one letter (substitution cypher). " +
    "Reflector C Thin was introduced in 1940 for the Navy Enigma to accompany Thin Rotors."
}
