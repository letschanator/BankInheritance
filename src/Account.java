
public class Account {

    /**
     * used to represent the total balance in the account
     */
    private double balance;

    /**
     * used to store the account number for records
     */
    private int accountNumber;

    /**
     * used to store the name of the account holder
     */
    private String accountHolder;

    /**
     * stores the number of accounts of any type that have been created so far
     */
    private static int numOfAccounts = 0;

    /**
     * stores the account type should be either "individual" or "company"
     */
    private String accountType;

    /**
     * constructor for account
     * @param balance  initial balance
     * @param accountNumber
     * @param accountHolder  name of the account holder
     * @param accountType should be either "individual" or "company"
     */
    public Account(double balance, int accountNumber, String accountHolder, String accountType){
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        numOfAccounts++;
        if(!accountType.equals("individual") && !accountType.equals("company")){  // account type should oly be individual or company
            throw new IllegalArgumentException("The account type should either be an individual or a company type.");
        }
        this.accountType = accountType;
    }

    /**
     * getter for balance
     * @return current balance in the account
     */
    public double getBalance() {
        return balance;
    }

    /**
     * setter for the balance
     * @param balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * getter for the account number
     * @return the account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * setter for the account number
     * @param accountNumber
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * getter for the name of the account holder
     * @return name of the account holder
     */
    public String getAccountHolder() {
        return accountHolder;
    }

    /**
     * setter for the name of the account holder
     * @param accountHolder name of the new account holder
     */
    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    /**
     * getter for the number of accounts of any type that have been created so far
     * @return number of accounts of any type that have been created so far
     */
    public static int getNumOfAccounts(){
        return numOfAccounts;
    }

    /**
     * getter for the type of account either individual or company
     * @return either "individual" or "company"
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * setter for the type of account either individual or company
     * @param accountType should be either "individual" or "company"
     */
    public void setAccountType(String accountType) {
        if(!accountType.equals("individual") && !accountType.equals("company")){ // account type should oly be individual or company
            throw new IllegalArgumentException("The account type should either be an individual or a company type.");
        }
        this.accountType = accountType;
    }

    /**
     * used to deposit money into the account
     * @param amount the amount of money you want to deposit
     */
    public void deposit(double amount){
        balance += amount;
    }

    /**
     * used to withdrawal money from the account
     * @param amount the amount of money you want to withdrawal
     */
    public  void withdrawal(double amount){
        balance -= amount;
    }
}
