package cats.runners

import java.util.concurrent.ScheduledExecutorService

import cats.effect.{ExitCode, IO, IOApp, SyncIO}
import scala.concurrent.duration._
import cats.syntax.flatMap._
import scala.concurrent.duration.FiniteDuration
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

object IOSamples extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    for{
      _ <- sample1()
      _ <- sample2()
      _ <- sample3()
    } yield{
      ExitCode.Success
    }


  }

  def sample1(): IO[Unit] ={
    val io = IO.pure(println("one"))
    val io2 = IO(println("two"))

    var counter = 0

    val io3 = IO({
      counter += 1
      println(counter)
    })

    val program = for{
      _ <- io
      _ <- io2
      _ <- io3
      _ <- io3
    }yield {
      ()
    }

    program
  }

  def fib(n:Int, a:Long = 0 ,b:Long = 1) : IO[Long] =
    IO(a + b).flatMap{ b2 =>
      if(n > 0)
        fib(n - 1,b,b2)
      else
        IO.pure(b2)
    }

  def sample2(): IO[Long] = {
    fib(10)
  }

  def putStringLine(value:String) = IO(println(value))
  val readLine = IO(scala.io.StdIn.readLine())

  def sample3(): IO[Unit] ={
    for{
      v <- readLine
      _ <- putStringLine(v)
    }yield {
      ()
    }
  }

  /**
    * convert Future to IO
    * @param fa
    * @param ec
    * @tparam A
    * @return
    */
  def convert[A](fa: => Future[A])(implicit ec:ExecutionContext):IO[A]=
    IO.async{ cb =>
      fa.onComplete{
        case Success(value) =>
          cb(Right(value))
        case Failure(e) =>
          cb(Left(e))
      }

    }

  /**
    * custom pure scheduler
    * @param d
    * @param ec
    * @return
    */
  def delayTicket(d:FiniteDuration)(implicit ec:ScheduledExecutorService) : IO[Unit] = {
    IO.cancelable{ cb =>
      val r = new Runnable {
        override def run(): Unit = cb(Right(()))
      }
      val f = ec.schedule(r,d.length,d.unit)

      IO(f.cancel(false))

    }
  }

  // delayTicket(1.minutes)(???).flatMap(_ => IO.pure(true))

  /**
    * using IO suspend to implement fb
    */
  def fb2(i:Int, a :Long,b:Long ) :IO[Unit] = {
    IO.suspend{
      if(i > 0)
        fb2(i - 1 , a, a+ b)
      else
        IO.pure(a)
    }
  }


  def sleep(d:FiniteDuration)(implicit sc:ScheduledExecutorService): IO[Boolean] ={
    IO.cancelable{ cb =>
      val r = new Runnable {
        override def run(): Unit = cb(Right(true))
      }
      val f = sc.schedule(r,d.length,d.unit)

      IO(f.cancel(false))

    }
  }


//  val launchMissiles: IO[Nothing] = IO.raiseError(new Exception("ec"))
//  val runToBunker = IO(println("hi"))
//
//  for{
//    fiber <- launchMissiles.start
//    _ <- runToBunker.handleErrorWith{ error =>
//     fiber.cancel *> IO.raiseError(error)
//    }
//    aftermath <- fiber.join
//
//  }yield {
//    aftermath
//  }

}

