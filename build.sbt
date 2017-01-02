lazy val scalatest = "org.scalatest" %% "scalatest" % "3.0.1" % "test"

lazy val commonSettings = Seq(
  organization := "mlesiewski",
  version := "0.1.0",
  scalaVersion := "2.12.1",
  name := "enigma-in-scala",
  libraryDependencies += scalatest
)

lazy val root = (project in file(".")).
  aggregate(engine, cli)

lazy val cli = project.
  enablePlugins(JvmPlugin).
  settings(commonSettings: _*).
  settings(
    name := name + "-cli",
    mainClass in Compile := Some("mlesiewski.enigmainscala.Main")
  ).dependsOn(engine)

lazy val engine = project.
  settings(commonSettings: _*).
  settings(
    name := name + "-engine"
  )