package cats.types

import cats.effect.{ExitCode, IO, IOApp}
import cats._
import cats.implicits._

object FunctorRunner extends IOApp{
  override def run(args: List[String]): IO[ExitCode] = {

    implicit val optionFunctor:Functor[Option] = new Functor[Option]{
      override def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa.map(f)
    }

    implicit val listFunctor: Functor[List] = new Functor[List]{
      override def map[A, B](fa: List[A])(f: A => B): List[B] = fa.map(f)
    }

    Functor[List].map(List("a","B"))(_ + "plus")

    Functor[Option].map(Some(2))(_ + 2)


    val source = List("cats", "dogs")
    val product = Functor[List].fproduct(List("1","2"))(_.length).toMap

    product.getOrElse("cats", 0)
    val listOpt = Functor[List] compose Functor[Option]

    listOpt.map(List(Some(1),None))(_ + 1) === List(Some(2),None)

    IO.pure(ExitCode.Success)
  }
}
