package mlesiewski.enigmainscala.core

/** An output for the Enigma machine. */
trait Lampboard extends Part {

  /** a name of the part */
  override val partName: String = "Lampboard"

  /** describes what the part does */
  override val description: String = "Lampboard was a panel with lamps for each letter. When a letter was encrypted " +
    "the output letter was highlighted on the panel. The person operating the Enigma machine had to note down the " +
    "letter as it would change on the next key pressed."

  def highlightedLetter: Option[Char]

  private[core] def highlight (letter: Char): Unit
}

private[core] class BasicLampboard extends Lampboard {

  private var letter :Option[Char] = Option.empty

  override def highlightedLetter: Option[Char] = letter

  override private[core] def highlight (letter: Char): Unit = {
    this.letter = Option.apply(letter)
  }
}
