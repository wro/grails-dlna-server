package media.source.interactor.remove

import media.source.MediaSource

interface RemoveMediaSource {
    void remove(MediaSource mediaSource, Presenter presenter)

    interface Presenter {
        void removed()
    }
}