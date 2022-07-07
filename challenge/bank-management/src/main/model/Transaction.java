package src.main.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Transaction implements Comparable<Transaction> {
   
    public enum Type {
       WITHDRAW, DEPOSIT
   }
   private Type type;
   private long timestamp;
   private String id;
   private double amount;


   public Transaction(Type type, long timestamp, String id, double amount) {
       if(id == null || id.isBlank() || amount <0 ) {
         throw new IllegalArgumentException("INVALID PARAMS");
       }
    
       this.type = type;
       this.timestamp = timestamp;
       this.id = id;
       this.amount = amount;



   }

   public Transaction(Transaction source) {
    this.type = source.type;
    this.timestamp = source.timestamp;
    this.id = source.id;
    this.amount = source.amount;
   }

   public Type getType() {
       return type;
   }

   public long getTimestamp() {
       return timestamp;
   }

   public String getId() {
       return id;
   }

   public double getAmount() {
       return amount;
   }

   public void setType(Type type) {
       this.type = type;
   }

   public void setTimestamp(long timestamp) {
       this.timestamp = timestamp;
   }

   public void setId(String id) {
       if(id == null || id.isBlank()) {
           throw new IllegalArgumentException("id cannot be null or blank");
       }
       this.id = id;
   }

   public void setAmount(double amount) {
 
    if(amount <0 ) {
        throw new IllegalArgumentException("amount cannot be less than 0");
    }
       this.amount = amount;
   }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Transaction)) {
            return false;
        }
        Transaction transaction = (Transaction) o;
       
        return transaction.type == this.type && transaction.timestamp == this.timestamp && transaction.id.equals(this.id) && transaction.amount == this.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, timestamp, id, amount);
    }


   public String getDate() {
    long milliseconds = TimeUnit.SECONDS.toMillis(getTimestamp());
    Date date = new Date(milliseconds);
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    return dateFormat.format(date);
   }




@Override
public String toString() {
    return (type) + "    " +
        "\t" + getDate() + "" +
        "\t" + id + "" +
        "\t$" + amount + "";
}

@Override
public int compareTo(Transaction o) {
    return Double.compare(this.timestamp, o.getTimestamp());
}
  
  

}
