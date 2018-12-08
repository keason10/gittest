package com.hk.autoAck;

import com.hk.BaseRabbitMq;
import com.rabbitmq.client.Channel;

/**
 * @ClassName:
 * @Description:
 * @auther: yant09
 * @date: 2018/11/5 15:53
 */
public class Send {
    private final static String QUEUE_NAME = "hello";
    private static void send(String message) throws Exception{
        Channel channel = new BaseRabbitMq().getChannelInstance();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 50; i++) {
            Thread.sleep(100);
            send("hello keason10,   " + i);
        }
    }
}
