package mlesiewski.enigmainscala.engine

trait Engine {

  def getKeyboard: Keyboard

  def getLampboard: Lampboard

  def getPlugboard: Plugboard

  def getReflector: Reflector

  def getWheels: List[Wheel]
}
