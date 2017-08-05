package mlesiewski.enigmainscala.core.keyboard

/**
  * a listener that will receive events from a Keyboard
  */
trait KeyboardListener {

  /** Gets called when a letter is typed on the keyboard.
    *
    * @param letter a letter that was typed on the keyboard
    */
  private[core] def onLetterTyped(letter: Char): Unit
}
