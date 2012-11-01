package spring

import grails.plugin.spock.IntegrationSpec
import media.source.repository.gorm.GormMediaSourceRepository
import org.springframework.context.ApplicationContext
import unit.media.source.interactor.AddMediaSourceInteractor

class SpringResourcesSpec extends IntegrationSpec {

    def grailsApplication

    def "media source repository should have been wired"() {
        expect:
        mediaSourceRepository
    }

    private ApplicationContext getContext() {
        grailsApplication.getMainContext()
    }

    private GormMediaSourceRepository getMediaSourceRepository() {
        context.getBean(GormMediaSourceRepository)
    }

    def "add media source interactor should have been wired"() {
        expect:
        addMediaSource.repository == mediaSourceRepository
    }

    private AddMediaSourceInteractor getAddMediaSource() {
        context.getBean(AddMediaSourceInteractor)
    }

}
