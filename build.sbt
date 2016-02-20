name := """play-spring-aop-aspectj"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.springframework" % "spring-context" % "4.1.6.RELEASE",
  "org.springframework" % "spring-orm" % "4.1.6.RELEASE",
  "org.springframework" % "spring-aop" % "4.1.6.RELEASE",
  "org.aspectj" % "aspectjrt" % "1.6.11",
  "org.aspectj" % "aspectjweaver" % "1.6.11",
  "javax.inject" % "javax.inject" % "1",
  "mysql" % "mysql-connector-java" % "5.1.31",
  "org.springframework" % "spring-test" % "4.1.6.RELEASE" % "test",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-hibernate4" % "2.5.3",
  "org.hibernate" % "hibernate-entitymanager" % "4.3.8.Final", // replace by your jpa implementation
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api")
)
