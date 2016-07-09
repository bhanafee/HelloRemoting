import akka.actor.{ActorRef, ActorSystem, Props}
import guide.GuidebookActor

object GuidebookMain extends App {
  val system: ActorSystem = ActorSystem("BookSystem")

  val guideProps: Props =
    Props(classOf[GuidebookActor], "Wikipedia")
  val guidebook: ActorRef =
    system.actorOf(guideProps, "guidebook")
}
