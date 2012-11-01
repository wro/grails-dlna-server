package media.source.interactor.remove

import media.source.MediaSource
import media.source.repository.MediaSourceRepository

class RemoveMediaSourceInteractor implements RemoveMediaSource {

    MediaSourceRepository repository

    @Override
    void remove(MediaSource mediaSource, RemoveMediaSource.Presenter presenter) {
        repository.remove(mediaSource.name, mediaSource.location)
        presenter.removed()
    }
}
