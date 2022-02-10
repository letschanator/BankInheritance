import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class accountTest {

    @Test
    void testAccountConstructor(){
        int accountsBefore = Account.getNumOfAccounts();
        Account acc = new Account(10,234,"bob","individual");
        assertEquals(10,acc.getBalance());
        assertEquals(234,acc.getAccountNumber());
        assertEquals("bob",acc.getAccountHolder());
        assertEquals("individual",acc.getAccountType());
        assertEquals(accountsBefore+1,Account.getNumOfAccounts());
    }
    @Test
    void testCheckingAccountConstructor(){
        int checkingAccountsBefore = CheckingAccount.getNumOfCheckingAccounts();
        int accountsBefore = Account.getNumOfAccounts();
        CheckingAccount acc = new CheckingAccount(10,234,"bob",10,"individual");
        assertEquals(10,acc.getBalance());
        assertEquals(234,acc.getAccountNumber());
        assertEquals("bob",acc.getAccountHolder());
        assertEquals("individual",acc.getAccountType());
        assertEquals(accountsBefore+1,Account.getNumOfAccounts());
        assertEquals(10,acc.getOverdraftLimit());
        assertEquals(checkingAccountsBefore+1,CheckingAccount.getNumOfCheckingAccounts());
    }
    @Test
    void testLoanAccountConstructor(){
        int loanAccountsBefore = LoanAccount.getNumOfLoanAccounts();
        int accountsBefore = Account.getNumOfAccounts();
        LoanAccount acc = new LoanAccount(10,234,"bob",.05,"individual");
        assertEquals(10,acc.getBalance());
        assertEquals(234,acc.getAccountNumber());
        assertEquals("bob",acc.getAccountHolder());
        assertEquals("individual",acc.getAccountType());
        assertEquals(accountsBefore+1,Account.getNumOfAccounts());
        assertEquals(.05,acc.getInterestRate());
        assertEquals(loanAccountsBefore+1,LoanAccount.getNumOfLoanAccounts());
    }
    @Test
    void testSavingsAccountConstructor(){
        int savingsAccountsBefore = SavingsAccount.getNumOfSavingsAccounts();
        int accountsBefore = Account.getNumOfAccounts();
        SavingsAccount acc = new SavingsAccount(10,234,"bob",.05);
        assertEquals(10,acc.getBalance());
        assertEquals(234,acc.getAccountNumber());
        assertEquals("bob",acc.getAccountHolder());
        assertEquals("individual",acc.getAccountType());
        assertEquals(accountsBefore+1,Account.getNumOfAccounts());
        assertEquals(.05,acc.getInterestRate());
        assertEquals(savingsAccountsBefore+1,SavingsAccount.getNumOfSavingsAccounts());
    }

    @Test
    void testIncorrectAccountType(){
       Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Account(1,1,"a","ansdfvuer"));
       assertEquals("The account type should either be an individual or a company type.",exception.getMessage());
    }

    @Test
    void testNegitiveOverdraft(){
        CheckingAccount acc = new CheckingAccount(1,1,"a",1,"company");
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> acc.setOverdraftLimit(-1));
        assertEquals("Overdraft Limit should be positive representing amount from 0 the account can go over",exception.getMessage());
    }

    @Test
    void testSetBalanceValid(){
        Account acc = new Account(1,1,"a", "individual");
        acc.setBalance(10);
        assertEquals(10,acc.getBalance());
    }

    @Test
    void testSetAccountNumberValid(){
        Account acc = new Account(1,1,"a", "individual");
        acc.setAccountNumber(2);
        assertEquals(2,acc.getAccountNumber());
    }

    @Test
    void testSetAccountHolderValid(){
        Account acc = new Account(1,1,"a", "individual");
        acc.setAccountHolder("bob");
        assertEquals("bob",acc.getAccountHolder());
    }

    @Test
    void testSetAccountTypeValid(){
        Account acc = new Account(1,1,"a", "individual");
        acc.setAccountType("company");
        assertEquals("company",acc.getAccountType());
    }

    @Test
    void testSetOverdraftLimitValid(){
        CheckingAccount acc = new CheckingAccount(1,1,"a",1,"company");
        acc.setOverdraftLimit(10);
        assertEquals(10,acc.getOverdraftLimit());
    }

    @Test
    void testSetIntrestRateValidLoan(){
        LoanAccount acc = new LoanAccount(1,1,"a",.05,"company");
        acc.setInterestRate(.01);
        assertEquals(.01,acc.getInterestRate());
    }

    @Test
    void testSetInterestRateValidSavings(){
        SavingsAccount acc = new SavingsAccount(1,1,"a",.05);
        acc.setInterestRate(.01);
        assertEquals(.01,acc.getInterestRate());
    }

    @Test
    void testWithdrawl(){
        Account acc = new Account(11,1,"a", "individual");
        acc.withdrawal(10);
        assertEquals(1,acc.getBalance());
    }

    @Test
    void testDeposit(){
        Account acc = new Account(1,1,"a", "individual");
        acc.deposit(10);
        assertEquals(11,acc.getBalance());
    }

    @Test
    void testCheckingWithdrawal(){
        CheckingAccount acc = new CheckingAccount(10,1,"a",10,"company");
        acc.withdrawal(10);
        assertEquals(0,acc.getBalance());
    }

    @Test
    void TestOverdraft(){
        CheckingAccount acc = new CheckingAccount(10,1,"a",1,"company");
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> acc.withdrawal(12));
        assertEquals("Transaction results in going over the overdraft limit, translation canceled.", exception.getMessage());
    }

    @Test
    void testChargeInterest(){
        LoanAccount acc = new LoanAccount(1,1,"a",.05,"company");
        acc.chargeIntrest();
        assertEquals(1.05,acc.getBalance());
        acc.chargeIntrest();
        assertEquals(1.1025,acc.getBalance());
    }

    @Test
    void testLoanWithdrawl(){
        LoanAccount acc = new LoanAccount(1,1,"a",.05,"company");
        acc.withdrawal(10);
        assertEquals(11,acc.getBalance());
    }

    @Test
    void testLoanDeposit(){
        LoanAccount acc = new LoanAccount(10,1,"a",.05,"company");
        acc.deposit(9);
        assertEquals(1,acc.getBalance());
    }

    @Test
    void testApplyIntrest(){
        SavingsAccount acc = new SavingsAccount(1,1,"a",.05);
        acc.applyInterest();
        assertEquals(1.05,acc.getBalance());
        acc.applyInterest();
        assertEquals(1.1025,acc.getBalance());
    }


}
