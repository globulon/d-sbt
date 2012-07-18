package com.promindis.sbt.plugins
import sbt._
import sbt.Keys._

object DSbt extends Plugin {

  private def copyDependencies = inputTask {
    (argTask: TaskKey[Seq[String]]) => {
      (argTask, update) map {
        (argTask, updateReport) => {
          val dist = new File("./dist")
          IO.delete(dist)
          IO.createDirectory(dist)
          updateReport.select(Set("compile", "runtime")) foreach {
            srcPath: File => {
              val fileName = srcPath.getName
              IO.copyFile(srcPath, dist / "lib" / fileName)
            }
          }
        }
      }
    }
  }

  private def copyBinaries = inputTask {
    (argTask: TaskKey[Seq[String]]) => {
      (argTask, update) map {
        (argTask, updateReport) => {
          val dist = new File("./dist")
          IO.createDirectory(dist / "bin")
        }
      }
    }
  }

  val buildDist = InputKey[Unit]("build-dist", "")

  val buildDistSettings = Seq( buildDist <<= copyDependencies, buildDist <<= copyBinaries)


}
