import java.util.Locale

import akka.actor.{ActorRef, ActorSystem, Props}
import Tourist.Start

object Main extends App {
  val system: ActorSystem = ActorSystem("GuideSystem")

  val guideProps: Props = Props[Guidebook]
  val guidebook: ActorRef = system.actorOf(guideProps)

  val tourProps: Props =
    Props(classOf[Tourist], guidebook)
  val tourist: ActorRef = system.actorOf(tourProps)

  tourist ! Start(Locale.getISOCountries)
}
