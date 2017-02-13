package mlesiewski.enigmainscala.engine

trait Reflector extends EnigmaPart {

  val name: Char

  def encode (letter: Char): Char
}
