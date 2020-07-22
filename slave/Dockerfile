FROM alpine:3.12
ARG UTILITIES
ENV NAME= \
    JENKINS_URL= \
    SECRET=
COPY docker-entrypoint.sh /
RUN apk add --no-cache \
        openjdk8 \
        curl \
        bash \
        docker \
        git \
        make \
        py-pip \
        jq \
        ${UTILITIES} \
    && pip install \
        unp \
        awscli \
    && mkdir -p /var/jenkins_workspace/ \
    && chmod 755 /var/jenkins_workspace/ \
    && chmod u+x /docker-entrypoint.sh
VOLUME [ "/var/jenkins_workspace/" ]
WORKDIR /var/jenkins_workspace/
ENTRYPOINT [ "bash", "/docker-entrypoint.sh" ]
