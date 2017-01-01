package mlesiewski.enigmainscala.engine

/** Will allow an user interface to create descriptions for the user. */
trait EnigmaPart {

  val partName: String
  val shortDescription: String
  val longDescription: Option[String]
}
