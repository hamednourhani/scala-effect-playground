package cats.runners

import cats.effect.{ExitCode, IO, IOApp}

class IORunner extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    IO.pure(ExitCode.Success)

}
