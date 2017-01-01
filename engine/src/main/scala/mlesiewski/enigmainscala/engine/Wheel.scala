package mlesiewski.enigmainscala.engine

/** Describes an Enigma wheel (rotor or scrabler). */
trait Wheel extends EnigmaPart {

  /** starting position of the wheel - the one displayed by the Enigma panel above the lamboard */
  val position: Char
  /** setting of the wheel */
  val setting: Char
  /** name of the wheel */
  val name: Char
}
