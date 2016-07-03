package service

import akka.actor.{ActorSystem, Props}

object Main extends App {
  val guide = args.headOption.getOrElse("Someone")
  val system = ActorSystem("ServiceSystem")
  val service = system.actorOf(Props(classOf[ServiceActor],guide), name="service")
  service ! messages.Start(Seq.empty)
}
