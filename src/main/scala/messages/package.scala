package object messages {

  case class Start(codes: Seq[String])

  case class Inquiry(code: String)

  case class Guidance(code: String, description: String)
}
