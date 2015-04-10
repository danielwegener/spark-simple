name := "spark-simple"

version := "1.0"

scalaVersion := "2.10.5"

net.virtualvoid.sbt.graph.Plugin.graphSettings

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.3.0" % "provided"

libraryDependencies += "org.apache.spark" %% "spark-streaming" % "1.3.0" % "provided"

libraryDependencies += "org.apache.spark" %% "spark-streaming-kafka" % "1.3.0" exclude("org.spark-project.spark","unused")

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.1" % "test"