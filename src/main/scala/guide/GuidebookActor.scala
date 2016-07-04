package guide

import java.util.{Currency, Locale}

import akka.actor.Actor
import messages._

class GuidebookActor(val guide: String) extends Actor {
  def describe(locale: Locale) =
    s"${guide} says that in ${locale.getDisplayCountry}, ${locale.getDisplayLanguage} is spoken and the currency is the ${Currency.getInstance(locale).getDisplayName}"

  override def receive = {
    case Inquiry(code) =>
      Locale.getAvailableLocales.filter(_.getCountry == code).foreach { locale =>
        sender ! Guidance(code, describe(locale))
      }
  }
}
