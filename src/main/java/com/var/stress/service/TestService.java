package com.var.stress.service;

import com.var.stress.components.RedisComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TestService {

    @Autowired
    private RedisComponent redisComponent;

    public boolean incr(){
        Lock lock = new ReentrantLock(false);
        String str = String.valueOf(System.currentTimeMillis() / 1000);
        lock.lock();
        boolean flag = redisComponent.increment(str);
        lock.unlock();
        return flag;
    }

}
