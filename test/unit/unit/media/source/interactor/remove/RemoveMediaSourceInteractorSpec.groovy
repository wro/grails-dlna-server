package unit.media.source.interactor.remove

import media.source.interactor.remove.RemoveMediaSource
import media.source.interactor.remove.RemoveMediaSourceInteractor
import media.source.repository.fake.FakeMediaSourceRepository
import spock.lang.Specification
import unit.media.source.fixtures.MediaSourceFixtures

import static unit.media.source.fixtures.MediaSourceFixtures.getValidMediaSource

class RemoveMediaSourceInteractorSpec extends Specification {

    def interactor = new RemoveMediaSourceInteractor()
    def presenter = Mock(RemoveMediaSource.Presenter)
    def repository = new FakeMediaSourceRepository()

    def setup() {
        interactor.repository = repository
    }

    def "remove a media source"() {
        given:
        repository.create(validMediaSource)

        when:
        interactor.remove(validMediaSource, presenter)

        then:
        repository.mediaSources.size() == 0
        1 * presenter.removed()
        0 * _
    }

}
