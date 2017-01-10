package mlesiewski.enigmainscala.engine

/** A set of starting settings for the parts of the Enigma. */
class DailyKey(
  val reflectorName: Char,
  val greekWheel: Option[WheelKey],
  val leftWheel: WheelKey,
  val middleWheel: WheelKey,
  val rightWheel: WheelKey,
  val pluggedPairs: Seq[(Char, Char)]
)

/** A starting setting for a wheel. */
class WheelKey (
  val name: Char,
  val setting: Char,
  val position: Char
)
