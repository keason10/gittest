package com.hk.publishAndSubscribe;

import com.hk.BaseRabbitMq;
import com.rabbitmq.client.*;

import java.io.IOException;

public class ReceiveLogs {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        Channel channel = new BaseRabbitMq().getChannelInstance();

        //声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();

        //把exchange绑定到队列中去
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        //把绑定到队列上的数据消费掉
        channel.basicConsume(queueName, true, consumer);
    }
}