package mlesiewski.enigmainscala.engine

class Message (
  setting: (Char, Char, Char),
  prettyText: String,
  textForEncoding: String
)

object Message {

  def fromPrettyText(setting: (Char, Char, Char), prettyText: String): Message =
    new Message(setting, prettyText, toTextForEncoding(prettyText))

  def fromTextForEncoding(setting: (Char, Char, Char), textForEncoding: String): Message =
    new Message(setting, toPrettyText(textForEncoding), textForEncoding)

  private[engine] def toPrettyText(textForEncoding: String): String = {
    return null
  }

  private[engine] def toTextForEncoding(prettyText: String): String = {
    return null
  }
}
