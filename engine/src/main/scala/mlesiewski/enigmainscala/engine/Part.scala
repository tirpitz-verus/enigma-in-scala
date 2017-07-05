package mlesiewski.enigmainscala.engine

/** Will allow an user interface to create descriptions for the user. */
trait Part {

  /** a name of the part */
  val partName: String

  /** describes what the part does */
  val description: String
}
