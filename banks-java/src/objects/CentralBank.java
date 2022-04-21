package objects;

import services.*;
import tools.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CentralBank
{
    private final String name;
    private final List<Bank> banks;

    public CentralBank(String name) throws BanksException {
        this.name = name;
        banks = new ArrayList<Bank>();
    }

    public String getName(){ return name; }

    public List<Bank> getBanks(){ return banks.stream().toList();}

    public Bank registerNewBank(String name, Double creditCommission, Double debitPercent, Double verifyLimit, Map<Integer, Double> depositPercents) throws BanksException {
        if (name == null || name.isEmpty())
        {
            throw new BanksException("Invalid value of name");
        }

        if (creditCommission < 0)
            throw new BanksException("Invalid value of credit commission");

        if (debitPercent < 0 || debitPercent > 100)
        {
            throw new BanksException("Invalid value of debit percent");
        }

        if (verifyLimit < 0)
        {
            throw new BanksException("Invalid value of verify limit");
        }

        var tempBank = new Bank(this, name, creditCommission, debitPercent, verifyLimit, depositPercents);
        banks.add(tempBank);
        return tempBank;
    }

    public void bankDayNotification()
    {
        for (Bank b : banks)
            for (Client c : b.getClients())
                for (IAccount a : c.getAccounts()) {
                    a.dailyBankOperations();
                }
    }

    public void transactionBetweenBanks(IAccount account, Bank secondBank, String secondAccountId, Double sum) throws BanksException {
        Guard.isNotNull(account, "account");
        Guard.isNotNull(secondBank, "second bank");
        Guard.isNotNull(secondAccountId, "id of the second account");
        if (sum < 0)
        {
            throw new BanksException("Invalid value of sum");
        }

        if (account.getClient().getBank() == secondBank)
            throw new BanksException("You should use inwardly bank transfer method");
        IAccount secondAccount = secondBank.findAccountById(secondAccountId);
        if (secondAccount == null)
            throw new BanksException("You should check that you've entered details of account to transfer correctly");

        var transaction = new TransferOfMoney(account, secondAccount, sum);
        if (account.getClient().getBank().permissionForReduceMoneyTransaction(transaction))
        {
            account.reduceMoney(sum);
            secondAccount.increaseMoney(sum);
        }
    }

    public Bank findBankByName(String name) throws BanksException {
        if (name == null || name.isEmpty())
        {
            throw new BanksException("Invalid value of name");
        }

        return banks.stream().filter(b -> b.getName().equals(name)).findFirst().get();
    }
}