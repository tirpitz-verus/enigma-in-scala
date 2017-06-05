package mlesiewski.enigmainscala.engine

/** represents the wiring of the rotor - maps one letter to another - keep in mind that the ring setting is 1 based
  * ie. ring setting of 1 means 'no change') */
private[engine] class Wiring (
                               /** letters that would be returned for a mapping of the rotor if in ring setting 1 */
                               val baseSetting: String,

                               /** ring setting (Ringstellung) - default value is 1 (no change to the baseSetting) */
                               val ringSetting: Int = 1
                             ) {

  /** letter - 'A' (baseSetting is 0-based) + offset -1 (ring setting is 1-based)
    *
    * @return encoded character
    */
  def encode (letter: Char): Char = baseSetting.charAt ((letter.toInt - 66 + ringSetting) % 26)
}
