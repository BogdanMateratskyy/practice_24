val confluentVersion = "5.1.0"
val kafkaVersion = "0.11.0.0"

resolvers += "Confluent Maven Repo" at "http://packages.confluent.io/maven"

val dependencies = Seq (
  "org.apache.kafka" % "kafka-streams" % kafkaVersion,
  "org.apache.kafka" % "kafka-clients" % kafkaVersion,
  "io.confluent" % "kafka-streams-avro-serde" % confluentVersion,
  "io.confluent" % "kafka-avro-serializer" % "3.2.0",
  "org.apache.avro" % "avro" % "1.8.2",
  "org.apache.avro" % "avro-maven-plugin" % "1.8.2",
  "mysql" % "mysql-connector-java" % "5.1.44",
  "com.sksamuel.avro4s" %% "avro4s-core" % "2.0.4",
  "commons-beanutils" % "commons-beanutils" % "1.9.3",
  "com.typesafe.play" %% "play-json" % "2.6.8",
  "commons-cli" % "commons-cli" % "1.4",
  "org.apache.commons" % "commons-configuration2" % "2.1.1",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.4"
).map(_.exclude("org.slf4j.slf4j-log4j12", "*"))

lazy val root = (project in file(".")).settings(
  name := "kafkaExampleScala",
  version := "0.2",
  scalaVersion := "2.12.8",
  libraryDependencies ++= dependencies
)