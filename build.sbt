name := "Suzie"

version := "1.0"

lazy val `suzie` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  cache , ws, evolutions, specs2 % Test,
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "com.typesafe.play" %% "play-slick" % "2.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0",
  "org.postgresql" % "postgresql" % "9.4.1208.jre7",
//  Testing
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % "test"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

resolvers ++= Seq("scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
)

javaOptions in Test += "-Dconfig.file=conf/application.test.conf"