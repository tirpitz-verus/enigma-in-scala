package mlesiewski.enigmainscala.core.keyboard

import mlesiewski.enigmainscala.core.Part

/** A 26-letter QWERTZ keyboard used in Enigma machines. */
trait Keyboard extends Part {

  /** a name of the part */
  override val partName: String = "Keyboard"

  /** describes what the part does */
  override val description: String = "Enigma machine had a keyboard with 26 letters in QWERTZ layout."

  /** registered KeyboardListener */
  private var _listener: Option[KeyboardListener] = Option.empty

  /** Sets a listener that will be informed about a letter typed
    *
    * @param listener this listener will be informed about a letter typed on the keyboard
    */
  def setListener (listener: KeyboardListener): Unit = _listener = Option.apply (listener)

  /** notifies about typed letter KeyboardListener
    *
    * @param letter letter typed on the keyboard that will be forwarded to the KeyboardListener
    */
  protected def fireKeyboardEvent (letter: Char): Unit = _listener match {
    case Some(listener) => listener.onLetterTyped(letter)
    case None => throw new IllegalArgumentException ("no listener to listen for typed letters")
  }
}
