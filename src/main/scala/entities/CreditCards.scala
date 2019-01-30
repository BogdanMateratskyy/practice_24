package entities

case class CreditCards (CreditCardID: Int,
                        CardType: String,
                        CardNumber: String,
                        ExpMonth: Byte,
                        ExpYear: Short,
                        ModifiedDate: String)
