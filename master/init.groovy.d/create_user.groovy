import java.util.logging.Logger
import jenkins.model.*
import hudson.security.*

def env = System.getenv()
def instance = Jenkins.getInstance()

Logger.global.info("Setting Admin user")
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
def adminUsername = env.JENKINS_ADMIN_USERNAME ?: "admin"
def adminPassword = env.JENKINS_ADMIN_PASSWORD ?: "admin"
hudsonRealm.createAccount(adminUsername, adminPassword)
instance.setSecurityRealm(hudsonRealm)
instance.save()