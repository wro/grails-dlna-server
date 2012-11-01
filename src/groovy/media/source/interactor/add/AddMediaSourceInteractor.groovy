package media.source.interactor.add

import media.source.MediaSource
import media.source.repository.MediaSourceRepository

class AddMediaSourceInteractor implements AddMediaSource {

    MediaSourceRepository repository

    @Override
    void addMediaSource(MediaSource input, AddMediaSource.Presenter presenter) {
        try {
            repository.create(input)
            presenter.addedMediaSource()
        } catch (MediaSource.RejectedField e) {
            presenter.rejectedField(e)
        }
    }
}
