import sbt.url

name := "jouter"

version := "0.1"

scalaVersion := "2.13.7"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.1.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.1" % "test"

ThisBuild / organization := "io.github.marchliu"
ThisBuild / organizationName := "Mars Liu"
ThisBuild / organizationHomepage := Some(url("https://marchliu.github.io/"))

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/MarchLiu/jouter"),
    "scm:git@github.com:MarchLiu/jouter.git"
  )
)
ThisBuild / developers := List(
  Developer(
    id    = "Mars Liu",
    name  = "Liu Xin",
    email = "mars.liu@outlook.com",
    url   = url("https://marchliu.github.io/")
  )
)

ThisBuild / description := "Command line tasks router, written by scala"
ThisBuild / licenses := List("MIT" -> new URL("https://github.com/MarchLiu/jouter/blob/master/LICENSE"))
ThisBuild / homepage := Some(url("https://github.com/MarchLiu/jouter"))
