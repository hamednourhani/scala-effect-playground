package cats.types

import cats.Apply
import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._

object ApplyRunner extends IOApp{
  override def run(args: List[String]): IO[ExitCode] = {

    val intToString : Int => String = _.toString
    val double : Int => Int = _ * 2
    val addTwo : Int => Int = _ + 2

    Apply[Option].map(Some(1)(intToString))
    Apply[Option].map(Some(1)(double))
    Apply[Option].map(Some(1)(addTwo))

    Apply[Option].ap(Some(intToString))(Some(1))
    Apply[Option].ap(Some(double))(Some(1))

    val add = (a:Int,b:Int) => a + b

    Apply[Option].ap2(Some(add))(Some(1),Some(2))

    val add3 = (a:Int,b:Int,c:Int) => a + b + c

    Apply[Option].ap3(Some(add3))(Some(1),Some(2),Some(3))


    Apply[Option].map2(Some(1),Some(2))(add)
    Apply[Option].map3(Some(1),Some(2),Some(3))(add3)

    Apply[Option].tuple2(Some(1),Some(2))
    Apply[Option].tuple3(Some(1),Some(2),Some(3))




    IO.pure(ExitCode.Success)
  }
}
