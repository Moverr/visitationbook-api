

name := """visitation"""
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
  "org.hibernate" % "hibernate-core" % "5.5.6",
  "org.postgresql" % "postgresql" % "42.5.4",
  "org.scalactic" %% "scalactic" % "3.2.15",
  "com.typesafe.slick" %% "slick" % "3.4.1",
  "com.typesafe.slick" %% "slick-testkit" % "3.4.1" % "test",
  "com.typesafe.play" %% "play-json" % "2.9.4",
  "com.typesafe.slick" %% "slick" % "3.4.1",
  "com.typesafe.play" %% "play-slick" % "5.1.0",
  "org.scalactic" %% "scalactic" % "3.2.15",
  "com.typesafe.slick" %% "slick-codegen" % "3.4.1",
  "com.github.tminglei" %% "slick-pg" % "0.21.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1",
//  "ch.qos.logback" % "logback-classic" % "1.4.5",

  "org.scalatest" %% "scalatest-flatspec" % "3.2.15" % "test",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % "test",
  "org.scalatestplus" %% "mockito-3-4" % "3.2.10.0" % "test",

  //Adding JWT TOkens
  "com.nimbusds" % "nimbus-jose-jwt" % "9.30.2",

  // Adding akka actors
  "com.typesafe.akka" %% "akka-actor" % "2.7.0",

  "org.seleniumhq.selenium" % "selenium-java"            % "4.8.1",
  "commons-io"              % "commons-io"               % "20030203.000550",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "2.2.0",
//  "org.scalatest"          %% "scalatest"                % "3.2.8" % "Test",
//  "org.scalamock"          %% "scalamsock"                % "5.1.0" % "Test"
 "org.typelevel" %%"cats-core"%"2.9.0",
  "org.scalatest" %% "scalatest" % "3.2.15" % "test",
  "org.aspectj" % "aspectjweaver" % "1.9.19",
  "org.aspectj" % "aspectjrt" % "1.9.19",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
  "ch.qos.logback" % "logback-classic" % "1.4.5",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
  "ch.qos.logback" % "logback-classic" % "1.4.5",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
  "ch.qos.logback" % "logback-classic" % "1.4.5"
)

scalacOptions ++=Seq(
  "-Xfatal-warnings"
)

enablePlugins(DockerPlugin)
dockerExposedPorts ++= Seq(9000, 9001)
dockerExposedUdpPorts += 4444
//CQRS/Event Sourcing architecture
//swaggerDomainNameSpaces := Seq("models")

//libraryDependencies += "org.webjars" % "swagger-ui" % "4.11.1"

