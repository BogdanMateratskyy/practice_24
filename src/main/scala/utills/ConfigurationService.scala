package utills

import java.io.File
import java.sql.{Connection, DriverManager}
import java.util.Properties

import org.apache.commons.configuration2.Configuration
import org.apache.commons.configuration2.builder.fluent.Configurations
import org.apache.kafka.clients.producer.KafkaProducer

object ConfigurationService {
  private var bootstrapServers: String = _
  private var schemaServers: String = _
  private var topicName: String = _

  private var mySqlServers: String = _
  private var mySqlUser: String = _
  private var mySqlPasswordRequired: Boolean = _
  private var mySqlPassword: String = _
  private var mySqlDatabase: String = _
  private var mySqlPort: String = _

  var mySqlTable: String = _
  var producerSleepTime: Long = _


  def configure(propertiesFileLocation: String): Unit = {
    val configs = new Configurations
    val propertiesFile: File = new File(propertiesFileLocation)
    val config: Configuration =configs.properties(propertiesFile)

    bootstrapServers = config.getString("bootstrap.servers")
    schemaServers = config.getString("schema.registry.url")
    topicName = config.getString("topic.name")

    mySqlServers  = config.getString("mysql.servers")
    mySqlUser = config.getString("mysql.user")
    mySqlDatabase = config.getString("mysql.database")
    mySqlPort = config.getString("mysql.port")
    mySqlPasswordRequired = config.getBoolean("mysql.password.required")
    mySqlTable = config.getString("mysql.table")

    if(mySqlPasswordRequired)
      mySqlPassword = config.getString("mysql.password")

    producerSleepTime = config.getLong("producer.sleep.ms")
  }

  def getTopicName: String = {
    topicName
  }

  def kafkaProducer(): KafkaProducer[String, AnyRef] = {
    val props = new Properties()
    props.put("bootstrap.servers", bootstrapServers)
    props.put("client.id", "ScalaProducerExample")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer")
    props.put("schema.registry.url", schemaServers)


   new KafkaProducer[String, AnyRef](props)
  }

  def mySqlConn(): Connection ={
    val mySqlProps: Properties = new Properties()

    mySqlProps.put("user", mySqlUser)
    if(mySqlPasswordRequired)
      mySqlProps.put("password", mySqlPassword)
    mySqlProps.put("useSSL", "false")

    DriverManager.getConnection(s"jdbc:mysql://$mySqlServers:$mySqlPort/$mySqlDatabase", mySqlProps)
  }
}
