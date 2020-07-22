job('Clean Docker Images') {
    triggers {
        cron('0 0 * * 0')
    }
    steps {
        shell("docker rmi \$(docker images -qa) --force || exit 0")
    }
}