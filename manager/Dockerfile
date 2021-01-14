FROM openjdk:11
COPY target/manager-3.0.0.jar manager-3.0.0.jar

CMD jar -xf manager-3.0.0.jar BOOT-INF/classes/application.properties && \
    sed -ie "s#^message.broker.host=.*#message.broker.host=$RABBITMQ_HOST#" BOOT-INF/classes/application.properties && \ 
    sed -ie "s#^db.host=.*#db.host=$MONGO_HOST#" BOOT-INF/classes/application.properties && \ 
    sed -ie "s#^sure-tosca.base.path=.*#sure-tosca.base.path=$SURE_TOSCA_BASE_PATH#" BOOT-INF/classes/application.properties && \
    cat BOOT-INF/classes/application.properties && \
    jar -uf manager-3.0.0.jar BOOT-INF/classes/application.properties && \
    java -jar manager-3.0.0.jar
