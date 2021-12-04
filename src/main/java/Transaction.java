import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class Transaction {

    private String typeOfOperation;
    private final String date;
    private final int amount;

    public Transaction(String typeOfOperation, String date, int amount) {
        this.typeOfOperation = typeOfOperation;
        this.date = date;
        this.amount = amount;
    }

    public Transaction(String date, int amount) {
        this.date = date;
        this.amount = amount;
    }

}