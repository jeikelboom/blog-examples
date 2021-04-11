import Dependencies._
import sbt.Keys.libraryDependencies

ThisBuild / scalaVersion     := "2.12.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.github.jeikelboom"
ThisBuild / organizationName := "VXCompany"

lazy val root = (project in file("."))
  .settings(
    name := "temporal",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += cats,
    libraryDependencies += cats_collections,
    libraryDependencies += json
  )

