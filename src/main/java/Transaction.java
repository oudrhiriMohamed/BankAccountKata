import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class Transaction {

    private String typeOfOperation;
    private final String date;
    private final double amount;

    public Transaction(String typeOfOperation, String date, double amount) {
        this.typeOfOperation = typeOfOperation;
        this.date = date;
        this.amount = amount;
    }

    public Transaction(String date, double amount) {
        this.date = date;
        this.amount = amount;
    }

}