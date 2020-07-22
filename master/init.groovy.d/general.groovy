import java.util.logging.Logger
import jenkins.model.*
import hudson.*
import hudson.model.*

def instance = Jenkins.getInstance()

// No jobs on master
Logger.global.info("Setting Jenkins workers")
instance.setNumExecutors(0)
instance.setNoUsageStatistics(false)

// Jenkins URL
Logger.global.info("Setting Jenkins location")
def url = System.getenv("JENKINS_URL")
def jenkinsLocationConfiguration = JenkinsLocationConfiguration.get()
jenkinsLocationConfiguration.setUrl(url)
jenkinsLocationConfiguration.save()

// Jenkins WS
Logger.global.info("Setting Jenkins buildsDir")
f = Jenkins.class.getDeclaredField("buildsDir")
f.setAccessible(true)
f.set(instance, '/var/jenkins_home/builds/${ITEM_FULLNAME}')
instance.save()
