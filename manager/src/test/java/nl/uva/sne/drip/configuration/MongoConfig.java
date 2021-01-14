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
package nl.uva.sne.drip.configuration;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

/**
 *
 * @author S. Koulouzis
 */
@Configuration
@ComponentScan(basePackages = {"nl.uva.sne.drip", "nl.uva.sne.drip.api",
    "nl.uva.sne.drip.configuration", "nl.uva.sne.drip.dao", "nl.uva.sne.drip.model", "nl.uva.sne.drip.service"})
public class MongoConfig extends AbstractMongoConfiguration {

    public static int MONGO_TEST_PORT = 12345;
    public static String MONGO_TEST_HOST = "localhost";

    @Override
    protected String getDatabaseName() {
        return "test-db";
    }

    @Override
    protected String getMappingBasePackage() {
        return "nl.uva.sne.drip";
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(MONGO_TEST_HOST, MONGO_TEST_PORT);
    }
}
