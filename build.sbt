name := "scala-effect-playground"

version := "0.1"

scalaVersion := "2.12.8"

import Dependencies._

resolvers in ThisBuild ++= Seq(
  Resolver.bintrayRepo("rbmhtechnology", "maven")
)


lazy val effect: Project = (project in file("effect"))
  .settings(
    name                       := "effect",
    libraryDependencies ++= Seq(
      Cats.core,
      Cats.effect,
      Scalaz.core,
      ScalazZio.core,
      ScalazZio.interop
    )
  )