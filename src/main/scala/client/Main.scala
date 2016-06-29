package client

import akka.actor.{ActorSystem, Props}

object Main extends App {
  val system = ActorSystem("ClientSystem")
  val client = system.actorOf(Props[ClientActor], name="client")
  client ! messages.Start
}
