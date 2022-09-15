import sbt._

object Dependencies {

  val compile: Seq[ModuleID] = Seq(
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "org.seleniumhq.selenium"     % "selenium-java" % "4.2.2",
    "org.slf4j"                   % "slf4j-simple"  % "2.0.0"
  )

  val test: Seq[ModuleID] = Seq(
    "com.vladsch.flexmark" % "flexmark-all" % "0.62.2" % Test,
    "org.scalatest"       %% "scalatest"    % "3.2.13" % Test
  )

}
