

name := """visitation"""
organization := "com.khoodi-labs"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
//lazy val root = (project in file(".")).enablePlugins(PlayScala, SwaggerPlugin) //enable plugin
scalaVersion := "2.13.3"
maintainer := "moverr@gmail.com"

lazy val postgresversion = "9.4-1201-jdbc41"

val Versions = new {
  val circe = "0.14.6"
  val config = "1.4.3"
  val munit = "0.7.29"
  val disciplineMunit = "1.0.9"
  val munitCatsEffect = "1.0.7"
}


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

   "com.softwaremill.sttp.client4" %% "core" % "4.0.0-M8",
  "io.circe" %% "circe-core" % "0.15.0-M1" ,
  "io.circe" %% "circe-parser" % "0.15.0-M1",


  "com.nimbusds" % "nimbus-jose-jwt" % "9.30.2",

  "commons-io" % "commons-io" % "20030203.000550",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "2.2.0",
  "org.typelevel" %% "cats-core" % "2.9.0",
  "org.aspectj" % "aspectjweaver" % "1.9.19",
  "org.aspectj" % "aspectjrt" % "1.9.19",

  "org.scalactic" %% "scalactic" % "3.2.15",
  "org.scalatest" %% "scalatest" % "3.2.15" % "test",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % "test",
  "org.scalatestplus" %% "mockito-4-6" % "3.2.15.0" % "test",
  "org.scalatestplus" %% "mockito-4-6" % "3.2.15.0" % "test",

)

val circeVersion = "0.14.3"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)


dockerExposedPorts ++= Seq(9000, 9001)
dockerExposedUdpPorts += 4444
