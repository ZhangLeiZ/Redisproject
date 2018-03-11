package com.zl.common;

import com.zl.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class RequestQueue {
    /*ArrayBlockingQueue是一个阻塞式的队列，继承自AbstractBlockingQueue,
    间接的实现了Queue接口和Collection接口。底层以数组的形式保存数据(实际上可看作一个循环数组)。
    常用的操作包括 add ,offer,put，remove,poll,take,peek*/
    private List<ArrayBlockingQueue<UserService>> arrayBlockingQueues = new ArrayList<>();
    private Map<Integer,Boolean> booleanMap = new ConcurrentHashMap<>();
    private RequestQueue() {}

    private static class Singleton {
        private static RequestQueue requestQueue;
        static{
            requestQueue = new RequestQueue();
        }
        public static RequestQueue getInstance() {
            return requestQueue;
        }
    }

    public static RequestQueue getInstance() {
        return Singleton.getInstance();
    }

    public void addQueue(ArrayBlockingQueue<UserService> requests) {
        arrayBlockingQueues.add(requests);
    }

    public int sizeQueue() {
        return arrayBlockingQueues.size();
    }

    public ArrayBlockingQueue<UserService> getByindexQueue(int index) {
        return arrayBlockingQueues.get(index);
    }

    public Map<Integer,Boolean> getBooleanMap() {
        return this.booleanMap;
    }
}
