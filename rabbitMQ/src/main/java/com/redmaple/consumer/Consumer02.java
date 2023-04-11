package com.redmaple.consumer;

import com.rabbitmq.client.*;
import com.redmaple.common.RabbitConnection;

import java.io.IOException;

/**
 * @author 若成风  消费者确认消息消费成功，手动ack应答模式
 * @description
 * @date 2021/6/27 23:24
 * @copyright (c) 2021, all rights reserved
 **/
public class Consumer02 {
    private static final String QUEUE_NAME = "mayikt_1001";

    public static void main(String[] args) throws  Exception {
        //1.创建连接
        Connection connection = RabbitConnection.getConnection();
        //2 创建通道
        Channel channel = connection.createChannel();
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("====消费者获取到的消息：" + msg);
                // 手动ack应答模式
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        // 3.监听队列
        channel.basicConsume(QUEUE_NAME, false, defaultConsumer);
    }

}
