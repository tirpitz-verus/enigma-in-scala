package mlesiewski.enigmainscala.engine

/** Describes a group of parts of the 4-wheel (M4 or Navy) Enigma machine. Effectively this is the state before the next keystroke. */
class Engine(
  val keyboard: Keyboard,
  val lampboard: Lampboard,
  val plugboard: Plugboard,
  val reflector: Reflector,
  val greekWheel: Option[Rotor],
  val leftWheel: Rotor,
  val middleWheel: Rotor,
  val rightWheel: Rotor
)
