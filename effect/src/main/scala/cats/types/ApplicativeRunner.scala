package cats.types


import cats.effect.{ExitCode, IO, IOApp}

object ApplicativeRunner extends IOApp {


  override def run(args: List[String]): IO[ExitCode] = {

    val f: (Int, Char) => Double = (i, c) => (i + c).toDouble
    val int = Some(5)
    val char = Some('a')

    int.map(i => (c: Char) => f(i, c))

    IO.pure(ExitCode.Success)
  }

}
