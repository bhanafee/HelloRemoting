package tourist

import akka.actor.Actor
import messages._

class TouristActor extends Actor {
  val service = context.actorSelection("akka.tcp://GuidebookSystem@127.0.0.1:8880/user/guidebook")

  override def receive = {
    case Start(codes) =>
      codes.foreach(service ! Tour(_))
    case Guide(code, description) =>
      println(s"$code: $description")
  }
}
