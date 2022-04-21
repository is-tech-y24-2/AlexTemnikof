package objects;

import services.*;
import tools.*;

import java.util.UUID;

public class TransferOfMoney implements ITransaction {
    private IAccount accountFrom;
    private IAccount accountTo;
    private Double sum;
    private UUID id;
    private boolean isValid;

    public TransferOfMoney(IAccount accountFrom, IAccount accountTo, Double sum) throws BanksException {
        Guard.isNotNull(accountFrom, "account from");
        Guard.isNotNull(accountTo, "account to");
        if (sum < 0)
        {
            throw new BanksException("Invalid value of sum");
        }

        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.sum = sum;
        id = UUID.randomUUID();
        isValid = true;
    }

    public boolean isValid(){
        return isValid();
    }

    public IAccount getAccountFrom()
    {
        return accountFrom;
    }

    public String getId()
    {
        return id.toString();
    }

    public Double getSum()
    {
        return sum;
    }

    public void cancelTransaction() throws BanksException {
        isValid = false;
        accountFrom.increaseMoney(sum);
        accountTo.reduceMoney(sum);
    }
}