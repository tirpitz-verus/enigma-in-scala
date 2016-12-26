lazy val scalatest = "org.scalatest" %% "scalatest" % "3.0.1" % "test"

lazy val commonSettings = Seq(
  organization := "mlesiewski",
  version := "0.1.0",
  scalaVersion := "2.12.1",
  libraryDependencies += scalatest
)

lazy val root = (project in file(".")).
  enablePlugins(JvmPlugin).
  settings(commonSettings: _*).
  settings(
    name := "enigma-in-scala",
    mainClass in Compile := Some("mlesiewski.enigmainscala.Main")
  )
    