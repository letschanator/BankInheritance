public class LoanAccount extends Account{

    /**
     * used to store interest rate in decimal form (so 5% is .05)
     */
    private double interestRate;

    /**
     * stores the number of loan accounts that have been created so far
     */
    private static int numOfLoanAccounts = 0;

    /**
     * constructor for the loan account class
     * @param balance  initial balance
     * @param accountNumber
     * @param accountHolder  name of the account holder
     * @param interestRate the interest rate in decimal form (so 5% is .05)
     * @param accountType should be either "individual" or "company"
     */
    public LoanAccount(double balance, int accountNumber, String accountHolder, double interestRate, String accountType){
        super(balance, accountNumber, accountHolder, accountType);
        this.interestRate = interestRate;
        numOfLoanAccounts++;
    }


    /**
     * getter for the interest rate
     * @return the interest rate in decimal form (so 5% is .05)
     */
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * setter for interest rate
     * @param interestRate the interest rate in decimal form (so 5% is .05)
     */
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * getter for the number of loan accounts created so far
     * @return number of loan accounts created so far
     */
    public static int getNumOfLoanAccounts(){
        return numOfLoanAccounts;
    }

    /**
     * adds interest to the balance of the loan account
     */
    public void chargeIntrest(){
        setBalance(getBalance()*(1+ interestRate)); // needs to multiply by 1 + interest to add back principle amount
    }

    /**
     * withdrawal now adds to the balance of the loan
     * @param amount the amount of money you want to withdrawal
     */
    @Override
    public void withdrawal(double amount){
        setBalance(getBalance() + amount);
    } //withdrawing from a loan account means putting more money to the loan

    /**
     * deposit now subtracts from the amount owed
     * @param amount the amount of money you want to pay to the loan
     */
    @Override
    public void deposit(double amount){
        setBalance(getBalance() - amount);
    }  //depositing to a loan account means paying back some of the loan
}
