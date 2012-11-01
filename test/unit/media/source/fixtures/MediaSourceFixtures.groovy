package media.source.fixtures

import media.source.MediaSource

class MediaSourceFixtures {

    static MediaSource getValidMediaSource() {
        new MediaSourceFixture(name: 'name', location: 'location', description: 'description')
    }

    static MediaSource getDuplicateLocation() {
        new MediaSourceFixture(name: 'valid-name', location: 'location')
    }

    static MediaSource getAlternateMediaSource() {
        new MediaSourceFixture(name: 'alternate-name', location: 'alternate-location', description: 'alternate-description')
    }

    static class MediaSourceFixture implements MediaSource {
        String name
        String location
        String description
    }

}
