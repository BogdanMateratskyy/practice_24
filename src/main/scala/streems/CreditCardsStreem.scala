package streems

import java.util.Properties

// from avro to case class--> import com.sksamuel.avro4s.RecordFormat
import io.confluent.kafka.streams.serdes.avro.GenericAvroSerde
import org.apache.kafka.streams._
import org.apache.kafka.streams.kstream.{KStream, KStreamBuilder}
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.processor.WallclockTimestampExtractor

//import scala.collection.JavaConverters.asJavaIterableConverter

object CreditCardsStreem {

  //from avro to case class--> private val userFormatter = RecordFormat[User]

  def main(args: Array[String]): Unit = {

    val config: Properties = {
      val props = new Properties()

      props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
      props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass.getName)
      props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, classOf[GenericAvroSerde])

      props.put(StreamsConfig.APPLICATION_ID_CONFIG, "credit-cards-streem")
      props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, classOf[WallclockTimestampExtractor])
      props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, "5000")
      props.put(StreamsConfig.METRICS_SAMPLE_WINDOW_MS_CONFIG, "5000")
      props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081")

      props
    }

    val builder: KStreamBuilder = new KStreamBuilder()
    val creditCard: KStream[String, AnyRef] = builder.stream("raw-data")
    val creditCardsDistinguish = creditCard.filter((k, v) => k == "Distinguish")

    //from avro to case class--> userFormatter.from(v)

    creditCardsDistinguish.to("credit-cards-distinguish")

    val streams: KafkaStreams = new KafkaStreams(builder, config)
    streams.start()
  }

}