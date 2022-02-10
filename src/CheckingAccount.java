
public class CheckingAccount extends Account{

    /**
     * used to store the overdraft limit in decimal form, should be a positive number representing
     * amount less than 0 the account can withdrawal
     */
    private double overdraftLimit;

    /**
     * stores the number of checking accounts created so far
     */
    private static int numOfCheckingAccounts = 0;

    /**
     * constructor for checking account
     * @param balance  initial balance
     * @param accountNumber
     * @param accountHolder  name of the account holder
     * @param overdraftLimit decimal of amount less than 0 the account can withdrawal
     * @param accountType should be either "individual" or "company"
     */
    public CheckingAccount(double balance, int accountNumber, String accountHolder, double overdraftLimit, String accountType){
        super(balance,accountNumber,accountHolder, accountType);
        if (overdraftLimit < 0){ // the overdraft limit should always be positive because it is the amount below 0 the account can go
            throw new IllegalArgumentException("Overdraft Limit should be positive representing amount from 0 the account can go over");
        }
        this.overdraftLimit = overdraftLimit;
        numOfCheckingAccounts++;
    }

    /**
     * getter for the overdraft limit
     * @return overdraft limit
     */
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    /**
     * setter for the overdraft limit
     * @param overdraftLimit decimal of amount less than 0 the account can withdrawal
     */
    public void setOverdraftLimit(double overdraftLimit) {
        if (overdraftLimit < 0){ // the overdraft limit should always be positive because it is the amount below 0 the account can go
            throw new IllegalArgumentException("Overdraft Limit should be positive representing amount from 0 the account can go over");
        }
        this.overdraftLimit = overdraftLimit;
    }

    /**
     * getter for the number of checking accounts created so far
     * @return number of checking accounts created so far
     */
    public static int getNumOfCheckingAccounts(){
        return numOfCheckingAccounts;
    }

    /**
     * withdrawal method for checking account throws an error
     * if the amount would result in going over the overdraft limit
     * @param amount the amount of money you want to withdrawal
     */
    @Override
    public void withdrawal(double amount){
        if(getBalance() - amount < overdraftLimit * -1){ // checks if withdrawing that amount would cause an overdraft of the account, errors out if it would
            throw new IllegalArgumentException("Transaction results in going over the overdraft limit, translation canceled.");
        }
        setBalance(getBalance()-amount);
    }
}
