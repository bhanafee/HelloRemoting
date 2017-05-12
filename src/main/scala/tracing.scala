trait Spanned {
  import io.opentracing.{Span, Tracer}
  import io.opentracing.propagation.TextMap
  import io.opentracing.propagation.Format.Builtin.TEXT_MAP

  val tracer: Tracer

  var span: Span = _

  def trace(): Map[String, String] = {
    var kvs: List[(String, String)] = List.empty
    tracer.inject(span.context(), TEXT_MAP, new TextMap() {
      override def put(key: String, value: String): Unit =
        kvs = (key, value) :: kvs

      override def iterator() =
        throw new UnsupportedOperationException()
    })
    Map(kvs: _*)
  }
}

trait Traceable {
  val trace: Map[String, String]
}

import akka.actor.Actor.Receive

class TracingReceive(r: Receive, state: Spanned) extends Receive {
  import io.opentracing.{SpanContext, Tracer}
  import io.opentracing.propagation.Format.Builtin.TEXT_MAP
  import io.opentracing.References.FOLLOWS_FROM
  import scala.util.{Failure, Success, Try}

  override def isDefinedAt(x: Any): Boolean = r.isDefinedAt(x)

  override def apply(v1: Any): Unit = {
    val operation: String = v1.getClass.getName
    val builder: Tracer.SpanBuilder = state.tracer.buildSpan(operation)
    state.span = extract(v1) match {
      case Success(ctx) =>
        builder.addReference(FOLLOWS_FROM, ctx).start()
      case Failure(ex) =>
        builder.start()
    }
    r(v1)
    state.span.finish()
  }

  import io.opentracing.propagation.TextMap
  import java.util.{Iterator => JIterator, Map => JMap}
  import scala.collection.JavaConverters._

  def extract(v1: Any): Try[SpanContext] = v1 match {
    case m: Traceable =>
      if (m.trace.isEmpty) Failure(new NoSuchElementException("Empty trace"))
      else Try(state.tracer.extract(TEXT_MAP, new TextMap() {
        override def put(key: String, value: String): Unit =
          throw new UnsupportedOperationException()

        override def iterator(): JIterator[JMap.Entry[String, String]] =
          m.trace.asJava.entrySet().iterator()
      })) match {
        case Success(null) =>
          Failure(new NullPointerException("Tracer.extract returned null"))
        case x => x
      }

    case _ => Failure(new UnsupportedOperationException("Untraceable message received"))
  }
}

object TracingReceive {
  def apply(state: Spanned)(r: Receive): Receive =
    new TracingReceive(r, state)
}
