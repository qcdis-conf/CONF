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

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author S. Koulouzis
 */
public class RPCServer {

    /**
     * @return the prop
     */
    public static Properties getProp() {
        return prop;
    }

    /**
     * @param aProp the prop to set
     */
    public static void setProp(Properties aProp) {
        prop = aProp;
    }

    private static Properties prop;

    public static void main(String[] argv) throws MalformedURLException {
        init(argv);
        start();
    }

    private static void start() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(getProp().getProperty("message.broker.host"));
        factory.setPassword(getProp().getProperty("message.broker.username"));
        factory.setUsername(getProp().getProperty("message.broker.password"));
        factory.setPort(AMQP.PROTOCOL.PORT);
        Logger.getLogger(RPCServer.class.getName()).log(Level.INFO, "Connected to: {0}", getProp().getProperty("message.broker.host"));
        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            //We define the queue name 
            channel.queueDeclare(getProp().getProperty("message.broker.queue.provisioner", "provisioner"), false, false, false, null);
            DefaultConsumer c;
            c = new nl.uva.sne.drip.provisioner.Consumer(channel, getProp());

            //Start listening for messages 
            channel.basicConsume(getProp().getProperty("message.broker.queue.provisioner", "provisioner"), false, c);

            //Block so we don't close the channel
            while (true) {

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RPCServer.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (IOException | TimeoutException ex) {
            Logger.getLogger(RPCServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void init(String[] argv) {
        setProp(new Properties());
        if (argv.length >= 1) {
            try {
                getProp().load(new FileInputStream(argv[0]));
            } catch (IOException ex) {
                Logger.getLogger(RPCServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String resourceName = "application.properties";
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
                getProp().load(resourceStream);
            } catch (IOException ex) {
                Logger.getLogger(RPCServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
