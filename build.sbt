name := "kafkaExampleScala"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies += 
  "org.apache.kafka" %% "kafka" % "2.1.0" excludeAll(
    ExclusionRule(organization = "com.sun.jdmk"),
    ExclusionRule(organization = "com.sun.jmx"),
    ExclusionRule(organization = "javax.jms")
  )
