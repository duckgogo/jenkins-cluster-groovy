import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.plugin.JenkinsJobManagement
import java.util.logging.Logger

Logger.global.info("Running seed init script")
jobManagement = new JenkinsJobManagement(System.out, [:], new File('.'))
new File('/tmp/jobs').eachFileMatch(~/.*.groovy/) { file ->
    deployConfigFile(file.name, file.text, file.name)
}

private void deployConfigFile(String name, String content, String comment) {
  Logger.global.info "Deploy config file '${name}'"
    new DslScriptLoader(jobManagement).with {
      runScript(content)
    }
}