package unit.media.source.repository.fake

import media.source.repository.MediaSourceRepository
import media.source.repository.fake.FakeMediaSourceRepository
import unit.media.source.repository.MediaSourceRepositorySpec

class FakeMediaSourceRepositorySpec extends MediaSourceRepositorySpec {

    def repository = new FakeMediaSourceRepository()

    MediaSourceRepository getRepository() {
        repository
    }

}