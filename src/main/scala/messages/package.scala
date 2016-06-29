package object messages {

  case object Start

  case class Tour(code: String)

  case class Guide(code: String, description: String)
}
