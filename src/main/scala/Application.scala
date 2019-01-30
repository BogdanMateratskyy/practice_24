import entities.CreditCards
import org.apache.commons.cli.{DefaultParser, Option, Options}
import producer.ProducerAvro
import utills.ConfigurationService

object Application extends App {
  val options = new Options
  val configFileOption = Option.builder("p").hasArg.required(true).longOpt("properties").build
  options.addOption(configFileOption)
  val parser = new DefaultParser
  val cmd = parser.parse(options, args)
  val propertiesFileLocation: String = cmd.getOptionValue("p")

  ConfigurationService.configure(propertiesFileLocation)

  ProducerAvro.sendCreditCardData(CreditCards(1, "SuperiorCard", "33332664695310", 11, 2006,	"2013-07-29 00:00:00.000"))
  ProducerAvro.sendCreditCardData(CreditCards(2, "Distinguish",	"55552127249722",	8, 2005,	"2013-12-05 00:00:00.000"))
  ProducerAvro.sendCreditCardData(CreditCards(3, "ColonialVoice", "77778344838353", 7, 2005, "2014-01-14 00:00:00.000"))
  ProducerAvro.sendCreditCardData(CreditCards(4, "ColonialVoice",	"77774915718248",	7, 2006,	"2013-05-20 00:00:00.000"))
  ProducerAvro.sendCreditCardData(CreditCards(5, "Distinguish",	"55557132036181",	9, 2006, "2014-04-10 00:00:00.000"))

}
