package com.redmaple.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.redmaple.common.RabbitConnection;

/**
 * @author 若成风  生成消息确认投递到mq，需要开启消息确认机制
 * @description
 * @date 2021/6/27 23:19
 * @copyright (c) 2021, all rights reserved
 **/
public class Producer02 {

    private static final String QUEUE_NAME = "mayikt_1001";

    public static void main(String[] args) throws Exception {
        // 1.创建连接
        Connection connection = RabbitConnection.getConnection();
        // 2.创建通道
        Channel channel = connection.createChannel();
        // 开启了确认消息机制
        channel.confirmSelect();
        // 3. 设置消息并发送
        String msg = "This is my first RabbitMQ message! + 02";
        System.out.println("==生产者发送消息：" + msg);
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        if (channel.waitForConfirms()) {
            System.out.println("==生产者发送消息成功");
        } else {
            System.out.println("==生产者发送消息失败");
        }




        channel.close();
        connection.close();
    }

}
