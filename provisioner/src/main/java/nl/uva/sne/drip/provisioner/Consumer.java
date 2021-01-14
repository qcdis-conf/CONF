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
package nl.uva.sne.drip.provisioner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.uva.sne.drip.model.Message;
import nl.uva.sne.drip.model.tosca.ToscaTemplate;

/**
 *
 * This is a provision Message consumer
 *
 *
 * @author S. Koulouzis
 */
public class Consumer extends DefaultConsumer {

    private final Channel channel;
    private final Logger logger;
    private final ObjectMapper objectMapper;
    private final Properties properties;

    public Consumer(Channel channel, Properties properties) throws IOException, TimeoutException {
        super(channel);
        this.properties = properties;
        this.channel = channel;
        logger = Logger.getLogger(Consumer.class.getName());
        this.objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException, FileNotFoundException, JsonProcessingException {
        Message responceMessage = null;
        AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder()
                .correlationId(properties.getCorrelationId())
                .build();
        try {
            //Create the reply properties which tells us where to reply, and which id to use.
            //No need to change anything here

            Message message = objectMapper.readValue(new String(body, "UTF-8"), Message.class);
            logger.log(Level.INFO, "Got Request: {0}", objectMapper.writeValueAsString(message));

            String tempInputDirPath = System.getProperty("java.io.tmpdir") + File.separator + "Input-" + Long.toString(System.nanoTime()) + File.separator;
            File tempInputDir = new File(tempInputDirPath);
            if (!(tempInputDir.mkdirs())) {
                throw new FileNotFoundException("Could not create input directory: " + tempInputDir.getAbsolutePath());
            }

            CloudStormService service = new CloudStormService(this.properties, message.getToscaTemplate());
            boolean dryRun = false;
            ToscaTemplate toscaTemplate = service.execute(dryRun);

            responceMessage = new Message();
            responceMessage.setCreationDate(System.currentTimeMillis());
            responceMessage.setToscaTemplate(toscaTemplate);
        } catch (Exception ex) {
            responceMessage = handleException(ex);
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            String response = objectMapper.writeValueAsString(responceMessage);

            logger.log(Level.INFO, "Sending Response: '{'0'}'{0}", response);
            channel.basicPublish("", properties.getReplyTo(), replyProps, response.getBytes("UTF-8"));
            channel.basicAck(envelope.getDeliveryTag(), false);
        }

    }

    private Message handleException(Exception ex) {
        Message errorMessage = new Message();
        errorMessage.setCreationDate(System.currentTimeMillis());
        errorMessage.setException(ex);
        return errorMessage;
    }

}
