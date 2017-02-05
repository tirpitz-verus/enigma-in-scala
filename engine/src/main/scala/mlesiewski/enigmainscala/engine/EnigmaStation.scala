package mlesiewski.enigmainscala.engine

/**
  * Main class of the package. Here one can encode and decode messages.
  *
  * @param dailyKey daily key to initialize this station
  * @param engine   an Engine with all the parts
  */
class EnigmaStation private[engine] (
                      val dailyKey: DailyKey,
                      val engine: Engine
                    ) {

  /**
    * @param message a message do encode
    * @return a String representing an encoded message
    */
  def encode (message: Message): String = null

  /**
    * @param encoded a String represented an encoded message
    * @return a decoded message
    */
  def decode (encoded: String): Message = null
}

/**
  * A companion object initialize the station.
  */
object EnigmaStation {

  /**
    * @param dailyKey a daily key to initialize a station with
    * @return a station ready for work
    */
  def get (dailyKey: DailyKey): EnigmaStation = new EnigmaStation(dailyKey, new Engine(dailyKey))
}
