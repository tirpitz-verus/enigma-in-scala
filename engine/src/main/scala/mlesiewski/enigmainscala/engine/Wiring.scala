package mlesiewski.enigmainscala.engine

/** represents the wiring of the rotor - maps one letter to another - keep in mind that the offset is 1 based
  * ie. offset of 1 means 'no change') */
private[engine] class Wiring (
                               /** letters that would be returned for a mapping of the rotor if in offset 1 */
                               val baseSetting: String,

                               /** offset of the rotor - default value is 1 (no change to the baseSetting) */
                               val offset: Int = 1
                             ) {

  /** letter - 'A' (baseSetting is 0-based) + offset -1 (offset is 1-based)
    *
    * @return encoded character
    */
  def encode (letter: Char): Char = baseSetting.charAt ((letter.toInt - 66 + offset) % 26)
}
