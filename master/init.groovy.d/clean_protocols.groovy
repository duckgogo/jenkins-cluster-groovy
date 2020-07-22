import java.util.logging.Logger
import jenkins.model.Jenkins

def instance = Jenkins.instance

Logger.global.info("Disable unsafe protocols")
HashSet<String> newProtocols = new HashSet<String>(instance.getAgentProtocols())
newProtocols.removeAll(Arrays.asList("JNLP3-connect", "JNLP2-connect", "JNLP-connect", "CLI-connect"))
instance.setAgentProtocols(newProtocols)

instance.save()