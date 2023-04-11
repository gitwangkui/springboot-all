package com.redmaple.common;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author 若成风
 * @description
 * @date 2021/6/27 23:15
 * @copyright (c) 2021, all rights reserved
 **/
public class RabbitConnection {
    /**
     * @description: 获取连接
     * @author: 若成风
     * @date: 2021/6/27 23:15
     * @return:
     */
    public static Connection getConnection() throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("meite");
        connectionFactory.setPassword("meite");
        // 设置VirtualHost（类似redis的数据库）
        connectionFactory.setVirtualHost("/mayikt");
        return connectionFactory.newConnection();
    }

}
