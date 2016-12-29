package mlesiewski.enigmainscala.engine

trait Plugboard extends EnigmaPart {

  def getPluggedPairs: Seq[(Char, Char)]

  def plugPair(pair: (Char, Char)): Engine

  def unplugPair(pair: (Char, Char)): Engine
}
