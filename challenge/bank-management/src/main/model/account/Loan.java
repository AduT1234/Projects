package src.main.model.account;

public class Loan extends Account {
       private static final double WITHDRAW_INTEREST = .02;
       private static final double BALANCE_LIMIT = 10000;

       public Loan(String id, String name, double balance) {
           super(id,name,balance);
       }
       public Loan(Loan source) {
           super(source);
       }
    @Override
    public void deposit(double amount) {
        super.setBalance(super.round(super.getBalance() - amount));
        
    }
    @Override
    public boolean withdraw(double amount) {
         double newBalance = (super.round(super.getBalance() + amount + (amount * WITHDRAW_INTEREST)));

         if(newBalance > BALANCE_LIMIT) {
             return false;
         }


        super.setBalance(newBalance);

        return true;
    }
    @Override
    public Account clone() {
        
        return new Loan(this);
    }

}
