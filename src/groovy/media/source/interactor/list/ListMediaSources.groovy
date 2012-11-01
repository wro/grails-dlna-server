package media.source.interactor.list

import media.source.MediaSource

interface ListMediaSources {
    void listMediaSources(Presenter presenter)

    interface Presenter {
        void renderMediaSources(Set<MediaSource> mediaSources)
    }
}