package com.hk.publishAndSubscribe;

import com.hk.BaseRabbitMq;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

public class EmitLog {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        Channel channel = new BaseRabbitMq().getChannelInstance();

        //声明一个exchange
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        String message = getMessage(argv);

        //向exchange发布消息，messages are routed to the queue with the name specified by routingKey, if it exists.
        for (int i = 0; i < 50; i++) {
            channel.basicPublish(EXCHANGE_NAME, "", null, (message + " " + i).getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "' " + i);
        }


        channel.close();
    }

    private static String getMessage(String[] strings) {
        if (strings.length < 1) {
            return "info: Hello World!";
        }
        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) {
            return "";
        }
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}
