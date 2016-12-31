package mlesiewski.enigmainscala.engine

trait Keyboard extends EnigmaPart {

  val typedLetter: Char

  def typeLetter(letter: Char): Engine
}
