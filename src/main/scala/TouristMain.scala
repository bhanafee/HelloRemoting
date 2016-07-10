import java.util.Locale

import akka.actor.{ActorRef, ActorSystem, Props}
import tourist.TouristActor

import scala.concurrent.Await
import scala.concurrent.duration._

object TouristMain extends App {
  val system: ActorSystem = ActorSystem("TouristSystem")

  val path =
    "akka.tcp://BookSystem@127.0.0.1:2552/user/guidebook"

  val guidebook: ActorRef = Await.result(system.
    actorSelection(path).
    resolveOne(5 seconds), 6 seconds)

  val tourProps: Props =
    Props(classOf[TouristActor], guidebook)
  val tourist: ActorRef = system.actorOf(tourProps)

  tourist ! messages.Start(Locale.getISOCountries)
}