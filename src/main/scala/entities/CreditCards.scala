package entities

case class CreditCards (creditCardID: Int,
                        cardType: String,
                        cardNumber: String,
                        expMonth: Byte,
                        expYear: Short,
                        modifiedDate: String)
