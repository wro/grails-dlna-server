package media.source.repository

import media.source.MediaSource

interface MediaSourceRepository {
    void create(MediaSource mediaSource)
    Set<MediaSource> getMediaSources()
    void remove(String name, String location)
}