package mlesiewski.enigmainscala.core.lampboard

import mlesiewski.enigmainscala.core.Part

/** An output of the Enigma machine. */
trait Lampboard extends Part {

  /** a name of the part */
  override val partName: String = "Lampboard"

  /** describes what the part does */
  override val description: String = "Lampboard was a panel with lamps for each letter. When a letter was encrypted " +
    "the output letter was highlighted on the panel. The person operating the Enigma machine had to note down the " +
    "letter as it would change on the next key pressed."

  /** the letter that was highlighted */
  private var _highlighted: Option[Char] = Option.empty

  /** @return highlighted letter */
  protected def highlighted: Option[Char] = _highlighted

  /** highlights a letter on the lampboard */
  private[core] def highlight (letter: Char): Unit = _highlighted = Option.apply (letter)
}
