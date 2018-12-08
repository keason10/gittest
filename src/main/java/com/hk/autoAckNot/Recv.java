package com.hk.autoAckNot;

import com.hk.BaseRabbitMq;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Recv {
  private final static String QUEUE_NAME = "hello";

  public static void receive() throws Exception{
      final Channel channel = new BaseRabbitMq().getChannelInstance();
      channel.queueDeclare(QUEUE_NAME, false, false, false, null);
      System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

      //设置手动确认
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

              //设置手动确认
              channel.basicAck(envelope.getDeliveryTag(), false);
              System.out.println(" [x] Received " + message + "'");
          }
      };

      //设置手动确认
      channel.basicConsume(QUEUE_NAME, false, consumer);
    }

    public static void main(String[] args)throws Exception {
        receive();
    }
}