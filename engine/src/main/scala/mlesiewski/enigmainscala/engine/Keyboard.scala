package mlesiewski.enigmainscala.engine

/** A 26-letter QWERTZ keyboard used in Enigma machines. */
trait Keyboard extends EnigmaPart {

  /** last letter that was typed */
  val typedLetter: Option[Char]

  /** Types a letter on the keyboard.
    *
    * @return new keyboard with changed last typed letter
    */
  def typeLetter(letter: Char): Keyboard
}
