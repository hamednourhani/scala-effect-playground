import sbt._

object Dependencies {


  object Cats {
    val org: String = "org.typelevel"
    val catsVersion: String = "1.5.0"
    val effectVersion: String = "1.1.0"

    val core: ModuleID = org %% "cats-core" % catsVersion
    val effect: ModuleID = org %% "cats-effect" % effectVersion
    val machinist: ModuleID = org %% "machinist" % "0.6.6"
    val macros: ModuleID = org %% "cats-macros" % catsVersion
  }

  object Scalaz {
    val org:     String = "org.scalaz"
    val version: String = "7.2.27"

    val core: ModuleID = org %% "scalaz-core" % version
  }

  object ScalazZio {
    val org:        String = Scalaz.org
    val zioVersion: String = "1.0-RC4"
    val zioInteropVersion : String = "0.5.0"

    val core:    ModuleID = org %% "scalaz-zio"         % zioVersion
    val interop: ModuleID = org %% "scalaz-zio-interop" % zioInteropVersion
  }

}