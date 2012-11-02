package unit.media.source.repository

import media.source.MediaSource
import media.source.repository.MediaSourceRepository
import spock.lang.Specification

import static unit.media.source.fixtures.MediaSourceFixtures.getAlternateMediaSource
import static unit.media.source.fixtures.MediaSourceFixtures.getDuplicateLocation
import static unit.media.source.fixtures.MediaSourceFixtures.getValidMediaSource

abstract class MediaSourceRepositorySpec extends Specification {

    abstract MediaSourceRepository getRepository()

    def "name is required"() {
        given:
        def source = validMediaSource
        source.name = value

        when:
        repository.create(source)

        then:
        thrown MediaSource.NameRequired

        where:
        value << [null, '', ' ']
    }

    def "location is required"() {
        given:
        def source = validMediaSource
        source.location = value

        when:
        repository.create(source)

        then:
        thrown MediaSource.LocationRequired

        where:
        value << [null, '', ' ']
    }

    def "name is unique field"() {
        given:
        repository.create(validMediaSource)

        when:
        repository.create(validMediaSource)

        then:
        thrown MediaSource.DuplicateName
    }

    def "location is unique field"() {
        given:
        repository.create(validMediaSource)

        when:
        repository.create(duplicateLocation)

        then:
        thrown MediaSource.DuplicateLocation
    }

    def "create a media source"() {
        expect:
        repository.create(validMediaSource)
    }

    def "retrieve all media sources"() {
        given:
        repository.create(validMediaSource)
        repository.create(alternateMediaSource)

        when:
        def sources = repository.mediaSources

        then:
        sources.size() == 2
        sources.any { it.name == alternateMediaSource.name && it.location == alternateMediaSource.location && it.description == alternateMediaSource.description}
        sources.any { it.name == validMediaSource.name && it.location == validMediaSource.location && it.description == validMediaSource.description}
    }

    def "remove a media source by it's name and location"() {
        given:
        repository.create(validMediaSource)
        repository.create(alternateMediaSource)

        when:
        repository.remove(validMediaSource.name, validMediaSource.location)

        then:
        repository.mediaSources.size() == 1
    }

    def "when removing a media source that does not exist throw not found"() {
        when:
        repository.remove(validMediaSource.name, 'invalid')

        then:
        thrown MediaSource.NotFound
    }

}
