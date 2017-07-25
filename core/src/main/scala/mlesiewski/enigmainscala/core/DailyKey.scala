package mlesiewski.enigmainscala.core

/** A set of starting settings for the parts of the Enigma. */
class DailyKey (
                 val reflectorName: String,
                 val greekWheel: Option[WheelKey],
                 val leftWheel: WheelKey,
                 val middleWheel: WheelKey,
                 val rightWheel: WheelKey,
                 val pluggedPairs: Seq[(Char, Char)]
               )

/** A starting setting for a wheel. */
class WheelKey (
                 val rotorName: String,
                 val ringSetting: Int,
                 val rotorOffset: Char
               )
