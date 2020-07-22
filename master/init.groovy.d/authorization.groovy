import jenkins.model.*
import hudson.security.*

def env = System.getenv()
def instance = Jenkins.getInstance()

def realm = new HudsonPrivateSecurityRealm(false)
instance.setSecurityRealm(realm)

def strategy = new hudson.security.GlobalMatrixAuthorizationStrategy()
strategy.add(Jenkins.MasterComputer.CREATE, "anonymous")
strategy.add(Jenkins.READ, "authenticated")
def adminUsername = env.JENKINS_ADMIN_USERNAME ?: "admin"
strategy.add(Jenkins.ADMINISTER, adminUsername)

instance.setAuthorizationStrategy(strategy)
instance.save()