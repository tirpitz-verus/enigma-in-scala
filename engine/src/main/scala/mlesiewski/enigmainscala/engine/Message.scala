package mlesiewski.enigmainscala.engine

class Message (
  val setting: (Char, Char, Char),
  val prettyText: String,
  val textForEncoding: String
  ) {}

object Message {

  def fromPrettyText(setting: (Char, Char, Char), prettyText: String): Message =
    new Message(setting, prettyText, toTextForEncoding(prettyText))

  def fromTextForEncoding(setting: (Char, Char, Char), textForEncoding: String): Message =
    new Message(setting, toPrettyText(textForEncoding), textForEncoding)

  private def toPrettyText(textForEncoding: String): String = {
    
  }

  private def toTextForEncoding(prettyText: String): String = {

  }
}
