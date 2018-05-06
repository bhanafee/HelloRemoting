name := "Guidebook"

version := "1.0"

scalaVersion := "2.12.6"

resolvers += "Lightbend Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.12",
  "com.typesafe.akka" %% "akka-remote" % "2.5.12",
  "io.opentracing"    %  "opentracing-api"  % "0.31.0",
  "io.opentracing"    %  "opentracing-mock" % "0.31.0"
)
