package unit.media.source.interactor.add

import media.source.MediaSource
import media.source.interactor.add.AddMediaSource
import media.source.interactor.add.AddMediaSourceInteractor
import media.source.repository.fake.FakeMediaSourceRepository
import spock.lang.Specification

import static unit.media.source.fixtures.MediaSourceFixtures.getDuplicateLocation
import static unit.media.source.fixtures.MediaSourceFixtures.getLocationRequired
import static unit.media.source.fixtures.MediaSourceFixtures.getNameRequired
import static unit.media.source.fixtures.MediaSourceFixtures.getValidMediaSource

class AddMediaSourceInteractorSpec extends Specification {

    def interactor = new AddMediaSourceInteractor()
    def repository = new FakeMediaSourceRepository()

    def presenter = Mock(AddMediaSource.Presenter)

    def setup() {
        interactor.repository = repository
    }

    def "handle name required"() {
        when:
        interactor.addMediaSource(nameRequired, presenter)

        then:
        repository.mediaSources.size() == 0
        1 * presenter.rejectedField(_ as MediaSource.NameRequired)
        0 * _
    }

    def "handle location required"() {
        when:
        interactor.addMediaSource(locationRequired, presenter)

        then:
        repository.mediaSources.size() == 0
        1 * presenter.rejectedField(_ as MediaSource.LocationRequired)
        0 * _
    }

    def "handle duplicate name"() {
        given:
        repository.create(validMediaSource)

        when:
        interactor.addMediaSource(validMediaSource, presenter)

        then:
        repository.mediaSources.size() == 1
        1 * presenter.rejectedField(_ as MediaSource.DuplicateName)
        0 * _
    }

    def "test"() {
        given:
        repository.create(validMediaSource)

        when:
        interactor.addMediaSource(duplicateLocation, presenter)

        then:
        repository.mediaSources.size() == 1
        1 * presenter.rejectedField(_ as MediaSource.DuplicateLocation)
        0 * _
    }

    def "add a media source"() {
        when:
        interactor.addMediaSource(validMediaSource, presenter)

        then:
        repository.mediaSources.find { it.name == validMediaSource.name && it.location == validMediaSource.location && it.description == validMediaSource.description }
        1 * presenter.addedMediaSource()
        0 * _
    }

}
