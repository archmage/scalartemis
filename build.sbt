organization := "io.github.marad"
name := "scalartemis"
version := "1.0.0"

scalaVersion := "2.13.0"

resolvers += Resolver.jcenterRepo

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.8",
  "org.scalacheck" %% "scalacheck" % "1.14.0",
  "org.mockito" % "mockito-core" % "3.0.0"
)

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
