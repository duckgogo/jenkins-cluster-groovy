FROM jenkins/jenkins:lts-alpine
ARG JAVA_OPTS
ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false ${JAVA_OPTS:-}" \
    JENKINS_OPTS= \
    JENKINS_URL= \
    JENKINS_ADMIN_USERNAME= \
    JENKINS_ADMIN_PASSWORD= 
COPY plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/plugins.txt
COPY init.groovy.d/*.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY conf/ /tmp/conf/
COPY jobs/ /tmp/jobs/