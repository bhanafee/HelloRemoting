package object messages {

  case class Start(codes: Seq[String])

  case class Tour(code: String)

  case class Guide(code: String, description: String)
}
