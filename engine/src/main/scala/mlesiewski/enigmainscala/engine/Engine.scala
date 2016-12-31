package mlesiewski.enigmainscala.engine

trait Engine {

  val keyboard: Keyboard
  val lampboard: Lampboard
  val plugboard: Plugboard
  val reflector: Reflector
  val wheels: List[Wheel]
}
