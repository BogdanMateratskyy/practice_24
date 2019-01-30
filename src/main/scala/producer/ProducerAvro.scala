package producer

import entities.CreditCards
import com.sksamuel.avro4s.RecordFormat
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import utills.ConfigurationService

object ProducerAvro {
  implicit private val valueFormatter: RecordFormat[CreditCards] = RecordFormat[CreditCards]

  private val producer: KafkaProducer[String, AnyRef] = ConfigurationService.kafkaProducer()
  private var commandRecord: ProducerRecord[String, AnyRef] = _
  private var value: GenericRecord = _
  private var key: String = _

  private def sendRecIntoTopic (keyRecord: String, valueRecord: AnyRef): Unit = {
    commandRecord = new ProducerRecord [String, AnyRef](ConfigurationService.getTopicName, keyRecord, valueRecord)
    producer.send(commandRecord)
  }

  def sendCreditCardData (creditCard: CreditCards): Unit = {
    value = valueFormatter.to(creditCard)
    key = creditCard.CreditCardID.toString
    sendRecIntoTopic(key, value)
    System.out.println("Record was send to topic")
    Thread.sleep(ConfigurationService.producerSleepTime)
  }

}
