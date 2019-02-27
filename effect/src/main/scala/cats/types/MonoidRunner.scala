package cats.types

import cats.effect.{ExitCode, IO, IOApp}
import cats._
import cats.implicits._

object MonoidRunner extends IOApp{
  override def run(args: List[String]): IO[ExitCode] = {

    Monoid[String].empty
    Monoid[String].combineAll(List("1","2","3"))

    Monoid[Map[String,Int]].combineAll(List(Map("a" -> 1, "b" -> 2),Map("a" -> 7, "b" -> 5)))

    implicit def monoidTuple[A :Monoid,B:Monoid]: Monoid[(A,B)] =
      new Monoid[(A,B)]{
        override def empty: (A, B) = (Monoid[A].empty,Monoid.empty[B])

        override def combine(x: (A, B), y: (A, B)): (A, B) = {
          val (xa,xb) = x
          val (ya,yb) = y
          (Monoid[A].combine(xa,ya),Monoid[B].combine(xb,yb))
        }
      }

    IO.pure(ExitCode.Success)
  }
}
