package mlesiewski.enigmainscala.core.rotor

import mlesiewski.enigmainscala.core.Part

/** Describes an Enigma wheel (wheel, drum or scrambler - Waltzen in German).
  *
  * @constructor used to set common fields
  * @param wiring wiring of the rotor
  */
abstract class Rotor private[core] (
                                       /** wiring of the rotor that maps letter to one another */
                                       val wiring: Wiring
                                     ) extends Part {

  /** encodes a single letter
    *
    * @param letter a letter to encode
    * @return an encoded letter
    */
  private[core] def encode (letter: Char): Char = wiring encode letter
}
