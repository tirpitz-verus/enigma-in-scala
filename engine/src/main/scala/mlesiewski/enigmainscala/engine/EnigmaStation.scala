package mlesiewski.enigmainscala.engine

/**
  * Main class of the package. Here one can encode and decode messages.
  *
  * @param dailyKey daily key to initialize this station
  * @param engine   an Engine with all the parts
  */
class EnigmaStation private (
                              val dailyKey: DailyKey,
                              val engine: Engine
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
}

object EnigmaStation {

  /**
    * @param dailyKey a daily key to initialize a station with
    * @return a station ready for work
    */
  def apply (dailyKey: DailyKey): EnigmaStation = new EnigmaStation (dailyKey, Engine (dailyKey))
}
