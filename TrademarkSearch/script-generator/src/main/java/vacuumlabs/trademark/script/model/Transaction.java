package vacuumlabs.trademark.script.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "Transaction", namespace = "http://euipo.europa.eu/trademark/data")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction implements Serializable {

    @XmlElement
    TradeMarkTransactionBody TradeMarkTransactionBody;

    public TradeMarkXml getTradeMark() {
        if (this.TradeMarkTransactionBody != null &&
                this.TradeMarkTransactionBody.TransactionContentDetails != null &&
                this.TradeMarkTransactionBody.TransactionContentDetails.TransactionData != null &&
                this.TradeMarkTransactionBody.TransactionContentDetails.TransactionData.TradeMarkDetails != null &&
                this.TradeMarkTransactionBody.TransactionContentDetails.TransactionData.TradeMarkDetails.TradeMarkXml != null) {
            return this.TradeMarkTransactionBody.TransactionContentDetails.TransactionData.TradeMarkDetails.TradeMarkXml;
        }
        return null;
    }
}