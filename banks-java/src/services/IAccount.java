package services;

import objects.*;
import tools.*;

import java.util.*;

public interface IAccount {
    void addTransaction(ITransaction transaction) throws BanksException;
    Double getBalance();
    UUID getId();
    Client getClient();

    List<ITransaction> getHistoryOfTransactions();
    void increaseMoney(Double amount) throws BanksException;
    void reduceMoney(Double amount) throws BanksException;
    void dailyBankOperations();

    String getAccountType();

    IAccount makeCopy() throws BanksException;
}