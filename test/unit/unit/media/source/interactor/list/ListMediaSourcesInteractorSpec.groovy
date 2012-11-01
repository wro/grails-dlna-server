package unit.media.source.interactor.list

import media.source.interactor.list.ListMediaSources
import media.source.interactor.list.ListMediaSourcesInteractor
import media.source.repository.fake.FakeMediaSourceRepository
import spock.lang.Specification

import static unit.media.source.fixtures.MediaSourceFixtures.getAlternateMediaSource
import static unit.media.source.fixtures.MediaSourceFixtures.getValidMediaSource

class ListMediaSourcesInteractorSpec extends Specification {

    def interactor = new ListMediaSourcesInteractor()
    def presenter = Mock(ListMediaSources.Presenter)

    def repository = new FakeMediaSourceRepository()

    def setup() {
        interactor.repository = repository
    }

    def "list all media sources"() {
        given:
        repository.create(validMediaSource)
        repository.create(alternateMediaSource)

        when:
        interactor.listMediaSources(presenter)

        then:
        1 * presenter.renderMediaSources(repository.mediaSources)
        0 * _
    }

}
