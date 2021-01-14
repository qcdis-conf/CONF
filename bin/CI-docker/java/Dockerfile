FROM maven:3.6.3-jdk-11

MAINTAINER S. Koulouzis

ENV SONAR_PROPERTIES_FILE=uva-conf.properties
ENV GIT_URL=https://gitlab.articonf.eu:4430/uva/conf.git
ENV FOLDER_NAME=conf
ENV BRANCH=develop

# Install SonarQube Dependency
RUN wget https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-4.2.0.1873-linux.zip && \
    unzip sonar-scanner-cli-4.2.0.1873-linux.zip && \
    mv sonar-scanner-4.2.0.1873-linux /opt/sonar-scanner
    

COPY clone_git.sh clone_git.sh
RUN ./clone_git.sh $BRANCH $GIT_URL $FOLDER_NAME
COPY build.sh build.sh
RUN ./build.sh $FOLDER_NAME
COPY send_to_sonarqube.sh send_to_sonarqube.sh 
COPY ${SONAR_PROPERTIES_FILE} ${SONAR_PROPERTIES_FILE}
COPY pull_git.sh pull_git.sh

CMD ./pull_git.sh $FOLDER_NAME && \ 
    ./build.sh $FOLDER_NAME && \
    ./send_to_sonarqube.sh $FOLDER_NAME $SONAR_HOST $SONAR_LOGIN $SONAR_PROPERTIES_FILE
    
    
    
# docker build -t java_ci --build-arg SONAR_PROPERTIES_FILE=uva-conf.properties --build-arg GIT_URL=https://gitlab.articonf.eu:4430/uva/conf.git --build-arg FOLDER_NAME=conf --build-arg FOLDER_NAME=develop .

# docker run -it java_ci
