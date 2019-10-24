/*
 * Copyright 2017 S. Koulouzis, Wang Junchao, Huan Zhou, Yang Hu 
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
package nl.uva.sne.drip.drip.provisioner;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * This is a provision Message consumer
 *
 *
 * @author S. Koulouzis
 */
public class Consumer extends DefaultConsumer {

    private final Channel channel;
//    Map<String, String> em = new HashMap<>();
    private final Logger logger;
    private final String messageBrokerHost;

    public Consumer(Channel channel, String messageBrokerHost) throws IOException, TimeoutException {
        super(channel);
        this.channel = channel;
        this.messageBrokerHost = messageBrokerHost;
        logger = Logger.getLogger(Consumer.class.getName());

    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        //Create the reply properties which tells us where to reply, and which id to use.
        //No need to change anything here 
        AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder()
                .correlationId(properties.getCorrelationId())
                .build();

        String response = "";

        //The queue only moves bytes so we need to convert them to string 
        String message = new String(body, "UTF-8");

        String tempInputDirPath = System.getProperty("java.io.tmpdir") + File.separator + "Input-" + Long.toString(System.nanoTime()) + File.separator;
        File tempInputDir = new File(tempInputDirPath);
        if (!(tempInputDir.mkdirs())) {
            throw new FileNotFoundException("Could not create input directory: " + tempInputDir.getAbsolutePath());
        }

        response = "response";

        logger.log(Level.INFO, "Sending Response: '{'0'}'{0}", response);
//            Logger.getLogger(Consumer.class.getName()).log(Level.INFO, "Sending Response: {0}", response);
        //We send the response back. No need to change anything here 
        channel.basicPublish("", properties.getReplyTo(), replyProps, response.getBytes("UTF-8"));
        channel.basicAck(envelope.getDeliveryTag(), false);

    }

}
