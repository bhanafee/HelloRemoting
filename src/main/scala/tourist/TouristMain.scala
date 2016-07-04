package tourist

import java.util.Locale

import akka.actor.{ActorRef, ActorSystem, Props}

object TouristMain extends App {
  val system: ActorSystem = ActorSystem("TouristSystem")
  val tourist: ActorRef = system.actorOf(Props[TouristActor], name = "tourist")
  tourist ! messages.Start(Locale.getISOCountries)
}
