package mlesiewski.enigmainscala.engine

/** An output for the Enigma machine. */
trait Lampboard {

  val highlightedLetter: Option[Char]

  def highlight (letter: Char): Lampboard
}
