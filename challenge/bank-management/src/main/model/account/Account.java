package src.main.model.account;

import java.text.DecimalFormat;

public abstract class Account {
     
    private String id;
    private String name;
    private double balance;

    public Account(String id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;

    }
    public Account(Account source) {
        this.id = source.id;
        this.name = source.name;
        this.balance = source.balance;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public abstract void deposit(double amount);
    public abstract boolean withdraw(double amount);
    
    protected double round(double amount) {
        DecimalFormat formatter = new DecimalFormat("#.##");
        return Double.parseDouble(formatter.format(amount));
    }

    public abstract Account clone();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "    " +
            "\t" + getId() + "" +
            "\t" + getName() + "" +
            "\t$" + getBalance() + "";
            
    }



}
