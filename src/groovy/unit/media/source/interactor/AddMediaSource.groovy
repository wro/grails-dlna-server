package unit.media.source.interactor

import media.source.MediaSource

public interface AddMediaSource {
    void addMediaSource(MediaSource input, Presenter presenter)

    interface Presenter {
        void addedMediaSource()
        void rejectedField(Throwable e)
    }
}