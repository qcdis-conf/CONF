/*
 * Copyright 2017 S. Koulouzis.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.uva.sne.drip.rpc;

import java.io.File;
import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.RpcClient;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author S. Koulouzis.
 */
public class Planner {

    private static final String REQUEST_QUEUE_NAME = "planner_queue";
    private final Connection connection;
    private final Channel channel;
    private final String replyQueueName;

    public Planner(String messageBrokerHost) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(messageBrokerHost);
        factory.setHost(messageBrokerHost);
        factory.setPort(AMQP.PROTOCOL.PORT);
        //factory.setUsername("guest");
        //factory.setPassword("pass");

        connection = factory.newConnection();
        channel = connection.createChannel();
        // create a single callback queue per client not per requests. 
        replyQueueName = channel.queueDeclare().getQueue();
    }

    public String plan(File inputToscaFile) throws IOException, TimeoutException, InterruptedException {

        //Build a correlation ID to distinguish responds 
        final String corrId = UUID.randomUUID().toString();
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        byte[] fileContentsBytes = Files.readAllBytes(Paths.get(inputToscaFile.getAbsolutePath()));
        channel.basicPublish("", REQUEST_QUEUE_NAME, props, fileContentsBytes);

        final BlockingQueue<String> response = new ArrayBlockingQueue(1);

        channel.basicConsume(replyQueueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                if (properties.getCorrelationId().equals(corrId)) {
                    response.offer(new String(body, "UTF-8"));
                }
            }
        });
//        channel.close();
//        connection.close();
        return response.take();
    }

    public void close() throws IOException, TimeoutException {
        if (channel != null && channel.isOpen()) {
            channel.close();
        }
        if (connection != null && connection.isOpen()) {
            connection.close();
        }
    }
}
