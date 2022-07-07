package src.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import src.main.model.Bank;
import src.main.model.Transaction;
import src.main.model.account.Account;
import src.main.model.account.Chequing;
import src.main.model.account.Loan;
import src.main.model.account.Savings;

public class Main {

   static String ACCOUNTS_FILE = "src/main/data/accounts.txt";            
   static String TRANSACTIONS_FILE = "src/main/data/transactions.txt";
   static Bank bank = new Bank();
   static Scanner scan;
    public static void main(String[] args) {
       
        try {
            ArrayList<Account> accounts = returnAccounts();
            loadAccounts(accounts);

            ArrayList<Transaction> transactions = returnTransactions();
            runTransactions(transactions);
            bank.deductTaxes();
            for (Account account : accounts) {
                System.out.println("\n\t\t\t\t\t ACCOUNT\n\n\t"+account+"\n\n");
                transactionHistory(account.getId());
            }
            
         } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }



    public static Account createObject(String [] values) throws Exception {
        
        
           return (Account)Class.forName("src.main.model.account." + values[0])
            .getConstructor(String.class, String.class, double.class)
            .newInstance(values[1], values[2], Double.parseDouble(values[3]));
   
    }


    public static ArrayList<Account> returnAccounts() throws FileNotFoundException {
        FileInputStream file = new FileInputStream(ACCOUNTS_FILE);
        scan = new Scanner(file);

        ArrayList <Account> accounts = new ArrayList<Account>();

        while(scan.hasNextLine()) {
            String s = scan.nextLine();
            String [] values = s.split(",");
            
           try {Account account = createObject(values);
            accounts.add(account);  
        }

        catch (Exception e) {
            e.getMessage();
        }
    }
        scan.close();
        return accounts;
}

    public static ArrayList<Transaction> returnTransactions() throws FileNotFoundException {
        FileInputStream file = new FileInputStream(TRANSACTIONS_FILE);
        scan = new Scanner(file);

        ArrayList <Transaction> transactions = new ArrayList<Transaction>();

        while(scan.hasNextLine()) {
            String s = scan.nextLine();
            String [] values = s.split(",");
            Transaction transaction = new Transaction(Transaction.Type.valueOf(values[1]),Long.parseLong(values[0]),values[2],Double.parseDouble(values[3]));
            transactions.add(transaction);
        }
        scan.close();
       Collections.sort(transactions);
       return transactions;
    }

    public static void loadAccounts(ArrayList<Account> accounts) {
        
        for(Account account: accounts) {
            bank.addAccount(account);
        }


    }

    public static void runTransactions(ArrayList<Transaction> transactions) {
        for(Transaction transaction: transactions) {
            bank.executeTransaction(transaction);
        }
    }

    public static void transactionHistory(String id) {
        System.out.print("\t\t\t\t   TRANSACTION HISTORY\n");
        Transaction [] transactions = bank.getTransactions(id);
        for (int i = 0; i < transactions.length; i++) {
            System.out.print("\t"+transactions[i]+"\n");
            wait(300);
        }
        System.out.print("\n\t\t\t\t\tAFTER TAX\n");
        System.out.print("\t" + bank.getAccount(id) +"\n\n\n\n");

    }

    
    /**
     * Function name: wait
     * @param milliseconds
     * 
     * Inside the function:
     *  1. Makes the code sleep for X milliseconds.
     */

     public static void wait(int milliseconds) {
         try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
         } catch (InterruptedException e) {
             System.out.println(e.getMessage());
         }
     }

}
