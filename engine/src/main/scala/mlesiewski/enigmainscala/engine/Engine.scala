package mlesiewski.enigmainscala.engine

/** Describes a group of parts of the 4-wheel (M4 or Navy) Enigma machine. Effectively this is the state before the next keystroke. */
class Engine(
  keyboard: Keyboard,
  lampboard: Lampboard,
  plugboard: Plugboard,
  reflector: Reflector,
  greekWheel: Option[Wheel],
  leftWheel: Wheel,
  middleWheel: Wheel,
  rightWheel: Wheel
)
