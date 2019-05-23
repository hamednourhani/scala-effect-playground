package cats.types

import cats.implicits._
import cats.effect.{ExitCode, IO, IOApp}
import cats.kernel.Semigroup
import cats.syntax.eq._

object SemigroupRunner extends IOApp{

  override def run(args: List[String]): IO[ExitCode] = {

    //problem : combine two nested Map
    Map("foo" -> Map("bar" -> 5)).combine(Map("foo" -> Map("bar" -> 6),"baz" -> Map()))

    //solution
    val aMap = Map("foo" -> Map("bar" -> 5))
    val bMap = Map("foo" -> Map("bar" -> 6))

    val combinedMap = Semigroup[Map[String,Map[String,Int]]].combine(aMap,bMap)

//    combinedMap.get("foo") === Option(Map("bar" -> 11))


    val one: Option[Int] = Option(1)
    val two: Option[Int] = Option(2)

//    val n : Option[Int] = None

//    (one |+| two) === Option(3)
//    (two |+| n) === None

//    (n |+| n) === None

//    (two |+| n) === None

    IO.pure(ExitCode.Success)
  }
}
