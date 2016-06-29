package client

import java.util.Locale

import akka.actor.Actor
import messages._

class ClientActor extends Actor {
  val service = context.actorSelection("akka.tcp://ServiceSystem@127.0.0.1:8880/user/service")

  override def receive = {
    case Start =>
      Locale.getISOCountries.foreach(service ! Tour(_))
    case Guide(code, description) =>
      println(s"$code: $description")
  }
}
