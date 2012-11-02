package media.source.repository.fake

import media.source.MediaSource
import media.source.repository.MediaSourceRepository

class FakeMediaSourceRepository implements MediaSourceRepository {

    Set<MediaSource> mediaSources = []

    @Override
    void create(MediaSource mediaSource) {
        def entity = new FakeMediaSource(mediaSource)
        entity.check()
        mediaSources << entity
    }

    @Override
    void remove(String name, String location) {
        def toRemove = mediaSources.find { it.name == name && it.location == location }
        if (!toRemove) throw new MediaSource.NotFound()
        mediaSources.remove(toRemove)
    }

    class FakeMediaSource implements MediaSource {
        String name
        String location
        String description

        FakeMediaSource(MediaSource mediaSource) {
            name = mediaSource.name
            location = mediaSource.location
            description = mediaSource.description
        }

        void check() {
            if (!name?.trim()) throw new MediaSource.NameRequired()
            if (!location?.trim()) throw new MediaSource.LocationRequired()
            if (mediaSources.any { it.name == name }) throw new MediaSource.DuplicateName()
            if (mediaSources.any { it.location == location}) throw new MediaSource.DuplicateLocation()
        }
    }
}
