import java.util.logging.Logger
import jenkins.model.Jenkins
import com.cloudbees.plugins.credentials.domains.Domain
import com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl
import com.cloudbees.plugins.credentials.CredentialsScope

def domain = Domain.global()
def store = Jenkins.instance.getExtensionList("com.cloudbees.plugins.credentials.SystemCredentialsProvider")[0].getStore()

def properties = new ConfigSlurper().parse(new File("/tmp/conf/credentials.properties").toURI().toURL())
properties.credentials.each {
  def credential = new UsernamePasswordCredentialsImpl(
    CredentialsScope.GLOBAL,
    it.key,
    it.value.description,
    it.value.username,
    it.value.password
  )
  store.addCredentials(domain, credential)
}