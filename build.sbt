

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
  "org.hibernate" % "hibernate-core" % "5.4.9.Final",
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "org.scalactic" %% "scalactic" % "3.2.2",
  "com.typesafe.slick" %% "slick" % "3.3.2",
  "com.typesafe.slick" %% "slick-testkit" % "3.3.3" % "test",
  "com.typesafe.play" %% "play-json" % "2.8.0-M5",
  "com.typesafe.slick" %% "slick" % "3.2.1",
  "com.typesafe.play" %% "play-slick" % "4.0.2",
  "org.scalactic" %% "scalactic" % "3.2.0",
  "com.typesafe.slick" %% "slick-codegen" % "3.3.2",
  "com.github.tminglei" %% "slick-pg" % "0.19.3",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.2",


  //Adding JWT TOkens
  "com.nimbusds" % "nimbus-jose-jwt" % "9.30.2",

  // Adding akka actors
  "com.typesafe.akka" %% "akka-actor" % "2.8.0",

  "org.seleniumhq.selenium" % "selenium-java"            % "4.8.1",
  "commons-io"              % "commons-io"               % "20030203.000550",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "2.2.0",
 "org.typelevel" %%"cats-core"%"2.9.0",
  "org.aspectj" % "aspectjweaver" % "1.9.19",
  "org.aspectj" % "aspectjrt" % "1.9.19",

//  testing
   "org.scalatest" %% "scalatest" % "3.2.15" % "test",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % "test",
  "org.scalatestplus" %% "mockito-4-6" % "3.2.15.0" % "test",
)

enablePlugins(DockerPlugin)

dockerExposedPorts ++= Seq(9000, 9001)
dockerExposedUdpPorts += 4444
