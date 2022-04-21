package services;

import tools.BanksException;

public interface ITransaction
{
    IAccount getAccountFrom();

    String getId();

    Double getSum();

    void cancelTransaction() throws BanksException;
}
