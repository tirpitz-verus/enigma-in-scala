package mlesiewski.enigmainscala.engine

class EnigmaStation (
  dailyKey: DailyKey,
  engine: Engine
) {

  def encode(message: Message): String = null

  def decode(encoded: String): Message = null
}

object EnigmaStation {

  def get(dailyKey: DailyKey): EnigmaStation = null
}
