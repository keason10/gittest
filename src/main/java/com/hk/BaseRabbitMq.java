package com.hk;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName:
 * @Description:
 * @auther: yant09
 * @date: 2018/11/5 15:54
 */
public class BaseRabbitMq {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    public Channel init()  {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            return channel;
        } catch (IOException e) {
            logger.error("BaseRabbitMq init exception", e);
        } catch (TimeoutException e) {
            logger.error("BaseRabbitMq init exception", e);
        }
        return null;
    }
}
