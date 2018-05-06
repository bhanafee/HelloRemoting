name := "Guidebook"

version := "1.0"

scalaVersion := "2.12.6"

resolvers += "Lightbend Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.12",
  "com.typesafe.akka" %% "akka-slf4j" % "2.5.12",
  "ch.qos.logback" %  "logback-classic" % "1.1.3"
)
