object Tourist {

  case class Guidance(code: String, description: String)

  case class Start(codes: Seq[String])

}

import akka.actor.{Actor, ActorRef}
import Guidebook.Inquiry
import Tourist.{Guidance, Start}
import akka.event.LoggingReceive

class Tourist(guidebook: ActorRef) extends Actor {

  override def receive = LoggingReceive {
      case Start(codes) =>
        codes.foreach(guidebook ! Inquiry(_))
      case Guidance(code, description) =>
        println(s"$code: $description")
  }
}
