VERSION = $(shell git rev-parse HEAD)
JENKINS_URL = http://jenkins-master:8080/
JENKINS_ADMIN_USERNAME = admin
JENKINS_ADMIN_PASSWORD = admin
SLAVE_NAME = Blackberry
SLAVE_SECRET = 528f79e92d2f494b4be7ff5c6fb3020952a91ac1e6b0892899554c2f7eaec29e

build-master:
	docker build -t jenkins-master:$(VERSION) ./master

build-slave:
	docker build -t jenkins-slave:$(VERSION) ./slave

build: build-master build-slave

run-master:
	docker run \
		--name jenkins-master \
		-itd \
		--rm \
		-v jenkins_home:/var/jenkins_home/ \
		-p 8080:8080 -p 50000:50000 \
		-e JENKINS_URL=http://localhost:8080/ \
		-e JENKINS_ADMIN_USERNAME=$(JENKINS_ADMIN_USERNAME) \
		-e JENKINS_ADMIN_PASSWORD=$(JENKINS_ADMIN_PASSWORD) \
		jenkins-master:$(VERSION)

run-slave:
	docker run \
	--name jenkins-slave \
	-itd \
	--rm \
	-v /var/run/docker.sock:/var/run/docker.sock \
	-e NAME=$(SLAVE_NAME) \
	-e JENKINS_URL=$(JENKINS_URL) \
	-e SECRET=$(SLAVE_SECRET) \
	--link jenkins-master \
	jenkins-slave:$(VERSION)

stop-master:
	docker stop jenkins-master

stop-slave:
	docker stop jenkins-slave

stop: stop-slave stop-master