package com.hk.autoAck;

import com.hk.BaseRabbitMq;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Recv {
  private final static String QUEUE_NAME = "hello";

  public static void receive() throws Exception{
      final Channel channel = new BaseRabbitMq().init();
      channel.queueDeclare(QUEUE_NAME, false, false, false, null);
      System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
      channel.basicQos(1);
      Consumer consumer = new DefaultConsumer(channel) {
          @Override
          public void handleDelivery(String consumerTag, Envelope envelope,
                                     AMQP.BasicProperties properties, byte[] body)
                  throws IOException {
              String message = new String(body, "UTF-8");
              try {
                  Thread.sleep(2000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.out.println(" [x] Received " + message + "'");
          }
      };
      channel.basicConsume(QUEUE_NAME, true, consumer);
    }

    public static void main(String[] args)throws Exception {
        receive();
    }
}