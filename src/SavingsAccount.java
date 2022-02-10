
public class SavingsAccount extends Account{

    /**
     * used to store the interest rate in decimal form (so a 5% interest rate would be .05)
     */
    private double interest;

    /**
     * store the number of savings accounts that have been created so far
     */
    private static int numOfSavingsAccounts = 0;

    /**
     * constructor for savings account, the account type should always be individual
     * @param balance initial balance
     * @param accountNumber
     * @param accountHolder name of the account holder
     * @param interest interest rate in decimal form (so a 5% interest rate would be .05)
     */
    public SavingsAccount(double balance, int accountNumber, String accountHolder, double interest) {
        super(balance, accountNumber, accountHolder, "individual");
        if (interest < 0){
            throw new IllegalArgumentException("Can not create a savings account with a negative interest");
        }
        this.interest = interest;
        numOfSavingsAccounts++;
    }

    /**
     * getter for interest rate
     * @return interest rate in decimal form (so a 5% interest rate would be .05)
     */
    public double getInterestRate() {
        return interest;
    }

    /**
     * setter for interest rate
     * @param interest interest rate in decimal form (so a 5% interest rate would be .05)
     */
    public void setInterestRate(double interest) {
        this.interest = interest;
    }

    /**
     * getter for the number of savings accounts created
     * @return the number of savings accounts created
     */
    public static int getNumOfSavingsAccounts(){
        return numOfSavingsAccounts;
    }

    /**
     * applies the interest to the balance
     */
    public void applyInterest(){
        setBalance(getBalance()*(1+interest)); // needs to multiply by 1 + interest to add back principle amount
    }
}
