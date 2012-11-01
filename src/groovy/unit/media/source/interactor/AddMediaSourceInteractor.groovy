package unit.media.source.interactor

import media.source.MediaSource
import media.source.repository.MediaSourceRepository

class AddMediaSourceInteractor implements AddMediaSource {

    MediaSourceRepository repository

    @Override
    void addMediaSource(MediaSource input, AddMediaSource.Presenter presenter) {
        try {
            repository.create(input)
            presenter.addedMediaSource()
        } catch (MediaSource.NameRequired | MediaSource.LocationRequired | MediaSource.DuplicateName | MediaSource.DuplicateLocation e) {
            presenter.rejectedField(e)
        }
    }
}
