AGET_URL=${JENKINS_URL}/jnlpJars/agent.jar
JNLP_URL=${JENKINS_URL}/computer/${NAME}/slave-agent.jnlp
curl ${AGET_URL} -o agent.jar
java -jar agent.jar -jnlpUrl ${JNLP_URL} -secret ${SECRET} $@