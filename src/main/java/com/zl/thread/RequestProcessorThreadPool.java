package com.zl.thread;

import com.zl.common.RequestQueue;
import com.zl.service.UserService;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RequestProcessorThreadPool {

    private static final int blockingQueueNum = 30;
    private static final int queueDataNum = 100;
    //创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
    private ExecutorService executorService = Executors.newFixedThreadPool(blockingQueueNum);


    private RequestProcessorThreadPool() {
        for(int i=0;i<blockingQueueNum;i++) {//初始化队列
            ArrayBlockingQueue<UserService> Queue = new ArrayBlockingQueue<>(queueDataNum);
            RequestQueue.getInstance().addQueue(Queue);
            //submit(Callable)接收的是一个Callable的实现，Callable接口中的call()方法有一个返回值，可以返回任务的执行结果
            executorService.submit(new RequestProcessorThread(Queue));
        }
    }

    private static class Sintaonl{
        private static RequestProcessorThreadPool processorThreadPool;
        static{
            processorThreadPool = new RequestProcessorThreadPool();
        }
        private static RequestProcessorThreadPool getInstance() {
            return processorThreadPool;
        }
    }

    public static RequestProcessorThreadPool getInstance() {
        return Sintaonl.getInstance();
    }

    public static void Init() {
        getInstance();
    }

}
