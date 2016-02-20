organization  := "com.kedi"

version       := "0.1"

scalaVersion  := "2.11.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.4.0"
  val sprayV = "1.3.3"
  Seq(
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-testkit" % sprayV  % "test",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "org.specs2"          %%  "specs2-core"   % "2.3.11" % "test",
    "org.apache.commons" % "commons-lang3" % "3.4" withSources(),
    "com.typesafe" % "config" % "1.3.0" withSources(),
    "com.typesafe.akka" % "akka-slf4j_2.11" % "2.4.0" withSources(),
    "io.spray" % "spray-json_2.11" % "1.3.2" withSources(),
    "io.spray" % "spray-can_2.11" % "1.3.3" withSources(),
    "io.spray" % "spray-routing_2.11" % "1.3.3" withSources(),
    "joda-time" % "joda-time" % "2.8.2" withSources(),
    "org.slf4j" % "slf4j-api" % "1.7.12" withSources(),
    "javax.mail" % "mail" % "1.4.7" withSources(),
    "org.codehaus.jackson" % "jackson-mapper-asl" % "1.9.13" withSources(),
    "ch.qos.logback" % "logback-core" % "1.1.3" withSources(),
    "ch.qos.logback" % "logback-classic" % "1.1.3" withSources(),
    "com.eaio.uuid" % "uuid" % "3.2" withSources(),
    "org.apache.httpcomponents" % "httpclient" % "4.5.1" withSources(),
    "org.scalatest" % "scalatest_2.11" % "3.0.0-M7" withSources()
  )
}

Revolver.settings
