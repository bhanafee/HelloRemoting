package tourist

import akka.actor.{Actor, ActorRef}
import messages._

class TouristActor(guidebook: ActorRef) extends Actor {

  override def receive = {
    case Start(codes) =>
      codes.foreach(guidebook ! Inquiry(_))
    case Guidance(code, description) =>
      println(s"$code: $description")
  }
}
