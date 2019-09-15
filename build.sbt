organization := "io.github.marad"
name := "scalartemis"
version := "1.0.0"

scalaVersion := "2.13.0"

resolvers += Resolver.jcenterRepo

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  "org.scalacheck" %% "scalacheck" % "1.14.0" % "test",
  "org.mockito" % "mockito-core" % "3.0.0" % "test"
)

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
