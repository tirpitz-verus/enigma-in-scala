package mlesiewski.enigmainscala.engine

/** A set of starting settings for the parts of the Enigma. */
class DailyKey(
  reflectorName: Char,
  greekWheel: Option[WheelKey],
  leftWheel: WheelKey,
  middleWheel: WheelKey,
  rightWheel: WheelKey,
  pluggedPairs: Seq[(Char, Char)]
)

/** A starting setting for a wheel. */
class WheelKey (
  name: Char,
  setting: Char,
  position: Char
)
