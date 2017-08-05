package mlesiewski.enigmainscala.core.keyboard

/**
  * a dummy keyboard that can forward events to its listener
  */
class DummyKeyboard extends Keyboard {

  private var keyboardListener: Option[KeyboardListener] = Option.empty

  /** Types a letter on the keyboard.
    *
    * @return new keyboard with changed last typed letter
    */
  def typeLetter (letter: Char): Unit = keyboardListener match {
    case Some(listener) => listener.onLetterTyped(letter)
    case None => throw new IllegalArgumentException ("no listener to listen for typed letters")
  }

  /** sets a listener that will be notified about events */
  override def setListener (listener: KeyboardListener): Unit = keyboardListener = Option.apply(listener)
}
