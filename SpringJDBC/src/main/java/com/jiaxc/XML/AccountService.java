package com.jiaxc.XML;

public interface AccountService {

    // 转账
    public void transfer(String outUser, String inUser, int money);

}
