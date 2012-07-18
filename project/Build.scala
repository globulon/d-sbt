import sbt._
import Keys._

object Properties {
  lazy val scalaVer = "2.9.2"
}

object Resolvers {
  lazy val typesafeReleases = "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
  lazy val scalaToolsRepo   = "sonatype-oss-public" at "https://oss.sonatype.org/content/groups/public/"
}

object BuildSettings {
  import Properties._
  lazy val buildSettings = Defaults.defaultSettings ++ Seq (
    organization        := "com.promindis",
    sbtPlugin           := true,
    version             := "0.1-SNAPSHOT",
    scalaVersion        := scalaVer,
    scalacOptions       := Seq("-unchecked", "-deprecation"),
    //doesn't work
    ivyValidate := false
  )
}


object ApplicationBuild extends Build {
  import Resolvers._
  import BuildSettings._

  lazy val d_sbt = Project(
    "d-sbt",
    file("."),
    settings =  buildSettings ++
      Seq(resolvers ++= Seq(typesafeReleases, scalaToolsRepo)) //++ Seq(libraryDependencies ++= (Seq() ++ testDependencies))
  )



}
