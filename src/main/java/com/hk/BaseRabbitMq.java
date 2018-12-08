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
    private static  Channel channel = null;
    private static Logger logger = LoggerFactory.getLogger(BaseRabbitMq.class);
    static {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (IOException e) {
            logger.error("BaseRabbitMq init exception", e);
        } catch (TimeoutException e) {
            logger.error("BaseRabbitMq init exception", e);
        }
    }

    public Channel getChannelInstance() {
        return channel;
    }
}
