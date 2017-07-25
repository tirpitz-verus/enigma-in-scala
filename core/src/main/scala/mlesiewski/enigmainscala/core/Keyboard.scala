package mlesiewski.enigmainscala.core

/** A 26-letter QWERTZ keyboard used in Enigma machines. */
trait Keyboard extends Part {

  /** last letter that was typed */
  val typedLetter: Option[Char]

  /** Types a letter on the keyboard.
    *
    * @return new keyboard with changed last typed letter
    */
  def typeLetter(letter: Char): Keyboard
}