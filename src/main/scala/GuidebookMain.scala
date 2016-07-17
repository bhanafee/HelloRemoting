import akka.actor.{ActorRef, ActorSystem, Props}
import akka.routing.FromConfig

object GuidebookMain extends App {
  val system: ActorSystem = ActorSystem("BookSystem")

  val guideProps: Props = Props[Guidebook]
  val guidebook: ActorRef =
    system.actorOf(FromConfig.props(guideProps), "guidebook")
}
