package mlesiewski.enigmainscala.engine

trait Plugboard extends EnigmaPart {

  val pluggedPairs: Seq[(Char, Char)]

  def plugPair(pair: (Char, Char)): Engine

  def unplugPair(pair: (Char, Char)): Engine

  def encode (key: Char): Char
}
