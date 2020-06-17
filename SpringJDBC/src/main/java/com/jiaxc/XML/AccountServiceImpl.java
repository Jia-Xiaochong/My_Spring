package com.jiaxc.XML;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void transfer(String outUser, String inUser, int money) {
        this.accountDao.out(outUser, money);
        // 模拟断电
        // int i = 1/0;
        this.accountDao.in(inUser, money);
        System.out.println("汇款完成！");
    }

}
