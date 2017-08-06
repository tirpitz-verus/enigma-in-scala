package mlesiewski.enigmainscala.core

import mlesiewski.enigmainscala.core.keyboard.{DummyKeyboard, Keyboard}
import mlesiewski.enigmainscala.core.lampboard.{DummyLampboard, Lampboard}

/**
  * Main class of the package that simulates the Enigma machine.
  * Here one can encode and decode messages.
  */
class Enigma private (
                       private[core] val engine: Engine
                     ) {


  /**
    * @param message a message do encode
    * @return a String representing an encoded message
    */
  def encode (message: Message): String = ???

  /**
    * @param encoded a String represented an encoded message
    * @return a decoded message
    */
  def decode (encoded: String): Message = ???

  /** @return a Lampboard to query */
  def lampboard: Lampboard = engine.lampboard

  /** @return a Keyboard to press buttons on */
  def keyboard: Keyboard = engine.keyboard
}

object Enigma {

  /**
    * historically Enigma machines worked only on 26 letters alphabets
    */
  val acceptedLetters = "QWERTYUIOPASDFGHJKLZXCVBNM"

  /** Creates an Enigma instance with a DummyLampboard and a DummyKeyboard
    *
    * @param dailyKey a daily key to initialize a station with
    * @return a station ready for work
    */
  def apply (dailyKey: DailyKey): Enigma = new Enigma (Engine (dailyKey, new DummyLampboard, new DummyKeyboard))

  /**
    * @param dailyKey  a daily key to initialize a station with
    * @param lampboard a Lampboard implementation
    * @param keyboard  a Keyboard implementation
    * @return a station ready for work
    */
  def apply (dailyKey: DailyKey, lampboard: Lampboard, keyboard: Keyboard): Enigma =
    new Enigma (Engine (dailyKey, lampboard, keyboard))
}
