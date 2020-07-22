job('Example - Deploy Production Environment') {
  authorization {
    blocksInheritance()
    permissions("developer-1", ["hudson.model.Item.Workspace", "hudson.model.Item.Read", "hudson.model.Item.Build", "hudson.model.Item.Cancel"])
  }

  parameters {
    stringParam('PACKAGE_HASH', '', 'Hash of package you want to deploy to test')
    stringParam('BRANCHNAME', 'release', 'Branch containing deployment scripts to be used')
  }

  scm {
    git {
      remote {
	    url('https://bitbucket.org/example/example')
	    credentials("BitBucketUser")
      }
      branch('${BRANCHNAME}')
	  extensions {
	    relativeTargetDirectory('example')
        cloneOptions {
          timeout(120)
        }
	  }
    }
  }

  steps {
    shell('make deploy prod ${PACKAGE_HASH}')
  }
  
  publishers {
    postBuildScripts {
      steps {
        shell('make send-msg failed ${BRANCHNAME} ${PACKAGE_HASH} ${BUILD_USER}')
      }
      onlyIfBuildFails(true)
      onlyIfBuildSucceeds(false)
    }
    postBuildScripts {
      steps {
        shell('make send-msg success ${BRANCHNAME} ${PACKAGE_HASH} ${BUILD_USER}')
      }
      onlyIfBuildFails(false)
      onlyIfBuildSucceeds(true)
    }
  }
}

