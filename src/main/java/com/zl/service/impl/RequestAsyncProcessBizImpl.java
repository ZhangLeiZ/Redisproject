package com.zl.service.impl;

import com.zl.common.RequestQueue;
import com.zl.service.IRequestAsyncProcessBiz;
import com.zl.service.UserService;

import java.util.concurrent.ArrayBlockingQueue;

public class RequestAsyncProcessBizImpl implements IRequestAsyncProcessBiz {
    @Override
    public void process(UserService request) {
        ArrayBlockingQueue arrayBlockingQueue = getQueueByProductId(request.getProductId());
        try {
            arrayBlockingQueue.put(request);
        } catch (InterruptedException e) {
            System.out.println("ID加入队列失败");
            e.printStackTrace();
        }
    }

    public static ArrayBlockingQueue<UserService> getQueueByProductId(Integer productId) {
        RequestQueue requestQueue = RequestQueue.getInstance();
        String key = String.valueOf(productId);
        int hashcode;
        int hash = (key == null) ? 0 : (hashcode = key.hashCode())^(hashcode>>>16);
        int index = (requestQueue.sizeQueue() -1 ) & hash;
        return requestQueue.getByindexQueue(index);
    }
}
