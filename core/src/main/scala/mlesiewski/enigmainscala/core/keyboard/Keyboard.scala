package mlesiewski.enigmainscala.core.keyboard

import mlesiewski.enigmainscala.core.Part

/** A 26-letter QWERTZ keyboard used in Enigma machines. */
trait Keyboard extends Part {

  /** a name of the part */
  override val partName: String = "Keyboard"

  /** describes what the part does */
  override val description: String = "Enigma machine had a keyboard with 26 letters in QWERTZ layout."

  /** Sets a listener that will be informed about a letter typed
    *
    * @param listener this listener will be informed about a letter typed on the keyboard
    */
  def setListener(listener: KeyboardListener): Unit
}
