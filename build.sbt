name := "shopping-cart"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= {
  val akkaV = "2.3.12"
  val sprayV = "1.3.3"
  val sprayJsonV = "1.3.0"
  val scalatestV = "2.2.4"
  val specsV = "2.4.1"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "io.spray" %% "spray-can" % sprayV,
    "io.spray" %% "spray-client" % sprayV,
    "io.spray" %% "spray-routing" % sprayV,
    "io.spray" %% "spray-json" % sprayJsonV,
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-testkit" % akkaV % "test",
    "io.spray" %% "spray-testkit" % sprayV % "test",
    "org.specs2" %% "specs2" % specsV % "test",
    "org.scalatest" %% "scalatest" % scalatestV % "test"
  )
}
