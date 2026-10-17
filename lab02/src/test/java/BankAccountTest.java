import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankAccountTest {
    @Test
    void withdrawDecreasesBalance() {
        BankAccount account = new BankAccount(100);

        account.withdraw(40);

        assertEquals(60, account.getBalance());
    }

    @Test
    void withdrawRejectsAmountGreaterThanBalance() {
        BankAccount account = new BankAccount(100);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(101));
    }

    @Test
    void constructorRejectsNegativeBalance() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(-1));
    }

    @Test
    void depositIncreasesBalance() {
        BankAccount account = new BankAccount(100);

        account.deposit(25);

        assertEquals(125, account.getBalance());
    }

    @Test
    void depositRejectsNonPositiveAmount() {
        BankAccount account = new BankAccount(100);

        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-5));
    }

    @Test
    void withdrawRejectsNonPositiveAmount() {
        BankAccount account = new BankAccount(100);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-5));
    }
}
