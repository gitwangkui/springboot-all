package com.redmaple.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.redmaple.common.RabbitConnection;

/**
 * @author 若成风
 * @description
 * @date 2021/6/27 23:19
 * @copyright (c) 2021, all rights reserved
 **/
public class Producer {

    private static final String QUEUE_NAME = "mayikt_1001";

    public static void main(String[] args) throws Exception {
        // 1.创建连接
        Connection connection = RabbitConnection.getConnection();
        // 2.创建通道
        Channel channel = connection.createChannel();
        // 3. 设置消息并发送
        String msg = "This is my first RabbitMQ message!";
        System.out.println("==生产者发送消息：" + msg);
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        channel.close();
        connection.close();

    }

}
