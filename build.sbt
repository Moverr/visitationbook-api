

name := """visitation-book-api"""
organization := "com.kodeinc"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
//lazy val root = (project in file(".")).enablePlugins(PlayScala, SwaggerPlugin) //enable plugin
scalaVersion := "2.13.3"
maintainer := "moverr@gmail.com"

lazy val postgresversion = "9.4-1201-jdbc41"

libraryDependencies ++= Seq(
  jdbc,
  guice,
  evolutions,
  javaJpa,
  ws,
  caffeine,
  filters,
  "org.hibernate" % "hibernate-core" % "5.4.9.Final",
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "org.scalactic" %% "scalactic" % "3.2.2",
  "com.typesafe.slick" %% "slick" % "3.3.2",
  "com.typesafe.slick" %% "slick-testkit" % "3.3.3" % "test",
  "com.typesafe.play" %% "play-json" % "2.8.0-M5",
  "com.typesafe.slick" %% "slick" % "3.2.1",
  "com.typesafe.play" %% "play-slick" % "4.0.2",

  "com.typesafe.slick" %% "slick-codegen" % "3.3.2",
  "com.github.tminglei" %% "slick-pg" % "0.19.3",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.2",


  //Adding JWT TOkens
  "com.nimbusds" % "nimbus-jose-jwt" % "9.30.2",

  // Adding akka actors
  "com.typesafe.akka" %% "akka-actor" % "2.8.0",
  "com.typesafe.akka" %% "akka-actor-typed" % "2.8.0",
  "com.typesafe.akka" %% "akka-slf4j" % "2.8.0",
  "com.typesafe.akka" %% "akka-protobuf-v3" % "2.8.0",
  "com.typesafe.akka" %% "akka-stream" % "2.8.0",
  "com.typesafe.akka" %% "akka-serialization-jackson" % "2.8.0",
  //akka-actor-typed, akka-slf4j, akka-protobuf-v3, akka-stream, akka-serialization-jackson]

  "commons-io"              % "commons-io"               % "20030203.000550",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "2.2.0",
 "org.typelevel" %%"cats-core"%"2.9.0",
  "org.aspectj" % "aspectjweaver" % "1.9.19",
  "org.aspectj" % "aspectjrt" % "1.9.19",
  "com.typesafe.play" %% "play-ws" % "2.8.18",
//  testing
  "org.scalactic" %% "scalactic" % "3.2.15",
   "org.scalatest" %% "scalatest" % "3.2.15" % "test",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % "test",
  "org.scalatestplus" %% "mockito-4-6" % "3.2.15.0" % "test",
  "org.scalatestplus" %% "mockito-4-6" % "3.2.15.0" % "test"

)



dockerExposedPorts ++= Seq(9000, 9001)
dockerExposedUdpPorts += 4444
