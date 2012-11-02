package media.source.interactor.remove

import media.source.MediaSource
import media.source.repository.MediaSourceRepository

class RemoveMediaSourceInteractor implements RemoveMediaSource {

    MediaSourceRepository repository

    @Override
    void remove(MediaSource mediaSource, RemoveMediaSource.Presenter presenter) {
        withNotFound(presenter) {
            repository.remove(mediaSource.name, mediaSource.location)
            presenter.removed()
        }
    }

    private static void withNotFound(RemoveMediaSource.Presenter presenter, Closure c) {
        try {
            c()
        } catch (MediaSource.NotFound ignored) {
            presenter.mediaSourceNotFound()
        }
    }
}
