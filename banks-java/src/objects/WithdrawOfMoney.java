package objects;

import services.*;
import tools.*;

import java.util.UUID;

public class WithdrawOfMoney implements ITransaction {
    private Double sum;
    private IAccount account;
    private UUID id;
    private boolean isValid;

    public WithdrawOfMoney(IAccount account, Double sum) throws BanksException {
        Guard.isNotNull(account, "account");
        if (sum < 0)
        {
            throw new BanksException("Invalid value of sum");
        }
        this.account = account;
        this.sum = sum;
        id = UUID.randomUUID();
        isValid = true;
    }

    public boolean isValid(){
        return isValid;
    }

    public IAccount getAccountFrom() {
        return account;
    }

    public String getId() {
        return id.toString();
    }

    public Double getSum() { return sum; }

    public void cancelTransaction() throws BanksException {
        isValid = false;
        account.increaseMoney(sum);
    }
}
