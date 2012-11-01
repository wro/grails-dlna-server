package media.source.interactor.list

import media.source.repository.MediaSourceRepository

class ListMediaSourcesInteractor implements ListMediaSources {

    MediaSourceRepository repository

    @Override
    void listMediaSources(ListMediaSources.Presenter presenter) {
        presenter.renderMediaSources(repository.mediaSources)
    }
}
