import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Transaction {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private double amount;
    private LocalDate date;
    private String comment;
    private TransactionType type;
    private UUID id;

    public Transaction(double amount, String comment, LocalDate date, TransactionType type) {
        this(amount, comment, date, type, UUID.randomUUID());
    }

    public Transaction(double amount, String comment, LocalDate date, TransactionType type, UUID id) {
        validateAmount(amount);
        validateDate(date);
        validateComment(comment);

        this.amount = amount;
        this.comment = comment;
        this.date = date;
        this.type = type;
        this.id = id;
    }

    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Beloppet kan inte vara negativt");
        }
    }

    private void validateDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Datumet kan inte ha ett null-värde");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Datumet kan inte vara i framtiden");
        }
    }

    private void validateComment(String comment) {
        if (comment == null || comment.trim().isEmpty()) {
            throw new IllegalArgumentException("Vänligen ange en kommentar");
        }
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    public TransactionType getType() {
        return type;
    }

    public UUID getId() {
        return id;
    }

    public void setAmount(double amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public void setDate(LocalDate date) {
        validateDate(date);
        this.date = date;
    }

    public void setComment(String comment) {
        validateComment(comment);
        this.comment = comment;
    }

    public void setType(TransactionType type) {
        if (type == null) {
            throw new IllegalArgumentException("Typ kan inte vara null");
        }
        this.type = type;
    }

    public double getSignedAmount() {
        return type == TransactionType.INCOME ? amount : -amount;
    }

    @Override
    public String toString() {
        return type + " | " +
                "Summa: " + amount + " kr | " +
                "Kommentar: " + comment + " | " +
                "Datum: " + date.format(DATE_FORMAT) + " | " +
                "ID: " + id;
    }
}