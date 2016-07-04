import akka.actor.{ActorRef, ActorSystem, Props}
import guide.GuidebookActor

object GuidebookMain extends App {
  val guide: String = args.headOption.getOrElse("Wikipedia")
  val system: ActorSystem = ActorSystem("GuidebookSystem")
  val guidebook: ActorRef = system.actorOf(Props(classOf[GuidebookActor], guide), name = "guidebook")
}
