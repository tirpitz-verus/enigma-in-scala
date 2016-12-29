package mlesiewski.enigmainscala.engine

trait Keyboard extends EnigmaPart {

  def typeLetter(letter: Char): Engine

  def getLetterTyped: Char
}
