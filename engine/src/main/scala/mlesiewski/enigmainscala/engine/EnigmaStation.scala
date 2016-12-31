package mlesiewski.enigmainscala.engine

class EnigmaStation (
  dailyKey: DailyKey,
  engine: Engine
) {

  def encode(message: Message): String

  def decode(encoded: String): Message
}

object EnigmaStation {

  def get()
}
