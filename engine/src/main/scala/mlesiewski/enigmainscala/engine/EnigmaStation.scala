package mlesiewski.enigmainscala.engine

trait EnigmaStation {

  def getEngine: Engine

  def setDailyKey(dailyKey: DailyKey): EnigmaStation

  def getDailyKey: DailyKey

  def encode(message: Message): String

  def decode(encoded: String): Message
}
