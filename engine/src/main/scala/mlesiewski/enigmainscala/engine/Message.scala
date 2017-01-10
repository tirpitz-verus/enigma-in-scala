package mlesiewski.enigmainscala.engine

/** represents a message to be encrypted with an Enigma machine */
class Message (
                /** a setting for this message */
                val setting: (Char, Char, Char),

                /** a "pretty text" message - with space characters and numbers */
                val prettyText: String,

                /** a text in Enigma format - without spaces and numbers */
                val inEnigmaFormat: String
              )

object Message {

  /** creates a message from a pretty text
    *
    * @param prettyText a pretty text string
    */
  def fromPrettyText (setting: (Char, Char, Char), prettyText: String): Message =
    new Message (setting, prettyText, toEnigmaFormat (prettyText))

  private[engine] def toEnigmaFormat (prettyText: String): String = {
    return null
  }

  /** creates a message from a text in an Enigma format
    *
    * @param inEnigmaFormat text in an Enigma format
    */
  def fromEnigmaFormat (setting: (Char, Char, Char), inEnigmaFormat: String): Message =
    new Message (setting, toPrettyText (inEnigmaFormat), inEnigmaFormat)

  private[engine] def toPrettyText (inEnigmaFormat: String): String = {
    return null
  }
}
