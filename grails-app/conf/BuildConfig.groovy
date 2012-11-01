grails.servlet.version = "2.5"
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6

grails.project.dependency.resolution = {
    inherits("global") {}
    log "error"
    checksums true

    repositories {
        inherits true
        mavenRepo('http://nexus.bonos.be:9090/content/groups/public')
        mavenLocal()
    }
    credentials {
        realm = 'Sonatype Nexus Repository Manager'
        host = 'nexus.bonos.be'
        username = 'wrombaut'
        password = 'pIggy12fRog'
    }

    dependencies {
        runtime 'mysql:mysql-connector-java:5.1.20'
    }

    plugins {
        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.8.0"
        runtime ":resources:1.1.6"

        build ":tomcat:$grailsVersion"

        compile ":database-migration:1.2"
        compile ':cache:1.0.1'
        compile ':spock:0.7'
    }
}

