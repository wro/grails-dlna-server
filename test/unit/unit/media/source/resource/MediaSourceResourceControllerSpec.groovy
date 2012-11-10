package unit.media.source.resource

import grails.test.mixin.TestFor
import media.source.MediaSource
import media.source.MediaSourceResourceController
import media.source.interactor.add.AddMediaSource
import media.source.interactor.list.ListMediaSources
import media.source.interactor.remove.RemoveMediaSource
import spock.lang.Specification

import static media.source.fixtures.MediaSourceFixtures.getAlternateMediaSource
import static media.source.fixtures.MediaSourceFixtures.getValidMediaSource

@TestFor(MediaSourceResourceController)
class MediaSourceResourceControllerSpec extends Specification {

    def addMediaSource = Mock(AddMediaSource)
    def listMediaSources = Mock(ListMediaSources)
    def removeMediaSource = Mock(RemoveMediaSource)

    def setup() {
        controller.addMediaSource = addMediaSource
        controller.listMediaSources = listMediaSources
        controller.removeMediaSource = removeMediaSource
    }

    def "add a media source"() {
        given:
        params.name = validMediaSource.name
        params.location = validMediaSource.location
        params.description = validMediaSource.description

        when:
        controller.update()

        then:
        1 * addMediaSource.addMediaSource({checkInput(it, validMediaSource)}, controller)
        0 * _
    }

    private static boolean checkInput(MediaSource input, MediaSource mediaSource) {
        assert input.name == mediaSource.name
        assert input.location == mediaSource.location
        assert input.description == mediaSource.description
        return true
    }

    def "handle successfully adding a media source"() {
        when:
        controller.addedMediaSource()

        then:
        response.status == 201
        response.text == ''
    }

    def "handle rejected field"() {
        when:
        controller.rejectedField(new FakeException())

        then:
        response.status == 412
        response.text == FakeException.class.simpleName
    }

    def "list all media sources"() {
        when:
        controller.show()

        then:
        1 * listMediaSources.listMediaSources(controller)
        0 * _
    }

    def "render sorted json list of media sources"() {
        given:
        def sources = [validMediaSource, alternateMediaSource] as Set<MediaSource>

        when:
        controller.renderMediaSources(sources)

        then:
        response.json == [
                [name: alternateMediaSource.name, location: alternateMediaSource.location, description: alternateMediaSource.description],
                [name:  validMediaSource.name, location: validMediaSource.location, description: validMediaSource.description]
        ]
        response.status == 200
    }

    def "remove a media source"() {
        given:
        params.name = validMediaSource.name
        params.location = validMediaSource.location

        when:
        controller.delete()

        then:
        1 * removeMediaSource.remove({it.name == validMediaSource.name && it.location == validMediaSource.location}, controller)
        0 * _
    }

    def "handle removal"() {
        when:
        controller.removed()

        then:
        response.status == 204
        response.text == ''
    }

    def "test"() {
        when:
        controller.mediaSourceNotFound()

        then:
        response.status == 404
        response.text == ''
    }

    class FakeException extends RuntimeException {}
}
