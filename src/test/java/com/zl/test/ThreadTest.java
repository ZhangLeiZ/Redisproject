package com.zl.test;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ThreadTest {

    /*开启100个线程.每个线程向List加100个数据,
     那么当所有线程执行完成之后应该是10000条,然后就对比一下结果,看看是否为10000条.
     ----------------------------------------------------------------------
    运行结果为9979,而且每次运行结果还不一定是这个数.当然,我们可以通过学过的知识,在执行list.add是给它加锁,
    比如将list.add(Thread.currentThread().getName() + ":" + j);改为synchronized
    (list){list.add(Thread.currentThread().getName() + ":" + j);}这样就能保证线程同步了.*/
    @Test
    public void Threadtest() {
        List<String> list = new ArrayList<>();
        Thread[] threads = new Thread[100];
        for(int i=0;i<threads.length;i++) {
            threads[i] = new Thread(()->{
                for(int j=0;j<100;j++) {
                    synchronized (this){
                        list.add(Thread.currentThread().getName()+":"+j);
                    }
                }
            });
            threads[i].start();
        }
        for (Thread thread:threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(list.size());
    }

    @Test
    public void Test() {
        System.out.println(12 & 15);
        System.out.println(15>>>3);
        try{
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("")));
        }catch (Exception e){

        }
    }
}
