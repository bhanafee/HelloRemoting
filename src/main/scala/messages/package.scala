package object messages {

  case class Inquiry(code: String)

  case class Guidance(code: String, description: String)

  case class Start(codes: Seq[String])
}
