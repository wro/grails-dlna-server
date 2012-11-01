package spring

import grails.plugin.spock.IntegrationSpec
import media.source.interactor.list.ListMediaSourcesInteractor
import media.source.interactor.remove.RemoveMediaSourceInteractor
import media.source.repository.gorm.GormMediaSourceRepository
import org.springframework.context.ApplicationContext
import media.source.interactor.add.AddMediaSourceInteractor

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

    def "list media sources interactor should have been wired"() {
        expect:
        listMediaSources.repository == mediaSourceRepository
    }

    private ListMediaSourcesInteractor getListMediaSources() {
        context.getBean(ListMediaSourcesInteractor)
    }

    def "test"() {
        expect:
        removeMediaSource.repository == mediaSourceRepository
    }

    private RemoveMediaSourceInteractor getRemoveMediaSource() {
        context.getBean(RemoveMediaSourceInteractor)
    }

}
