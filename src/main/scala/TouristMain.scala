import java.util.Locale

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.util.Timeout
import tourist.TouristActor

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object TouristMain extends App {
  implicit val timeout: Timeout = 5 seconds

  val system: ActorSystem = ActorSystem("TouristSystem")

  val path =
    "akka.tcp://BookSystem@127.0.0.1:2552/user/guidebook"

  system.actorSelection(path).resolveOne().onComplete {
    case Success(guidebook) =>
      val tourProps: Props =
        Props(classOf[TouristActor], guidebook)
      val tourist: ActorRef = system.actorOf(tourProps)

      tourist ! messages.Start(Locale.getISOCountries)

    case Failure(e) => println(e)
  }
}