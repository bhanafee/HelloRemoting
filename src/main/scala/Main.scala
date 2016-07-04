import java.util.Locale

import akka.actor.{ActorRef, ActorSystem, Props}
import guide.GuidebookActor
import tourist.TouristActor

object Main extends App {
  val system: ActorSystem = ActorSystem("GuidebookSystem")

  val guideProps: Props = Props(classOf[GuidebookActor], "Wikipedia")
  val guidebook: ActorRef = system.actorOf(guideProps)

  val tourProps: Props = Props(classOf[TouristActor], guidebook)
  val tourist: ActorRef = system.actorOf(tourProps)

  tourist ! messages.Start(Locale.getISOCountries)
}
