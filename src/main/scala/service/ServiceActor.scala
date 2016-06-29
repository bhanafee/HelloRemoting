package service

import akka.actor.Actor
import java.util.{Currency, Locale}
import messages._

class ServiceActor extends Actor {
  def describe(locale: Locale) =
    s"In ${locale.getDisplayCountry}, ${locale.getDisplayLanguage} is spoken and the currency is the ${Currency.getInstance(locale).getDisplayName}"

  override def receive = {
    case Start =>
      println("Server is active")
    case Tour(code) =>
      Locale.getAvailableLocales.filter(_.getCountry == code).foreach { locale =>
        sender ! Guide(code, describe(locale))
      }
  }
}
