package scalaz.zio

import java.io.IOException

import scalaz.zio.console._

object SampleZIORunner extends App {
  override def run(args: List[String]):
  ZIO[SampleZIORunner.Environment, Nothing, Int] =
    myAppLogic.fold(_ => 1, _ => 0)


  val myAppLogic: ZIO[Console, IOException, Unit] =
    for {
      _ <- putStrLn("how old are you?")
      age <- getStrLn
      _ <- putStrLn("what is your name?")
      name <- getStrLn
      _ <- putStrLn(s"name:$name, age:$age")
    } yield ()
}

object IntegrationExample{
  val runtime: DefaultRuntime = new DefaultRuntime{}

  runtime.unsafeRun(putStrLn("hi!"))
}