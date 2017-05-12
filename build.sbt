name := "Guidebook"

version := "1.0"

scalaVersion := "2.12.2"

resolvers += "Lightbend Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.0",
  "io.opentracing"    %  "opentracing-api"  % "0.21.0",
  "io.opentracing"    %  "opentracing-mock" % "0.21.0"
)
