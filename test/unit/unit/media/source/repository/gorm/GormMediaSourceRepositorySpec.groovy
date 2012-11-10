package unit.media.source.repository.gorm

import gorm.MediaSource
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import media.source.repository.MediaSourceRepository
import media.source.repository.MediaSourceRepositorySpec
import media.source.repository.gorm.GormMediaSourceRepository

@TestMixin(DomainClassUnitTestMixin)
class GormMediaSourceRepositorySpec extends MediaSourceRepositorySpec {

    def repository = new GormMediaSourceRepository()

    def setup() {
        mockDomain(MediaSource)
    }

    @Override
    MediaSourceRepository getRepository() {
        repository
    }
}
