package media.source

import media.source.interactor.add.AddMediaSource
import media.source.interactor.list.ListMediaSources
import media.source.interactor.remove.RemoveMediaSource

class MediaSourceResourceController implements AddMediaSource.Presenter, ListMediaSources.Presenter, RemoveMediaSource.Presenter {
    AddMediaSource addMediaSource
    ListMediaSources listMediaSources
    RemoveMediaSource removeMediaSource

    def update() {
        addMediaSource.addMediaSource(input, this)
    }

    private Input getInput() {
        new Input(name: params.name, location: params.location, description: params.description)
    }

    @Override
    void addedMediaSource() {
        response.status = 201
        render(text: '')
    }

    @Override
    void rejectedField(Throwable e) {
        response.status = 412
        render(text: e.class.simpleName)
    }

    def show() {
        listMediaSources.listMediaSources(this)
    }

    @Override
    void renderMediaSources(Set<MediaSource> mediaSources) {
        render(contentType: 'application/json') {
            array {
                mediaSources.sort {x,y-> x.name <=> y.name }.each {
                    m(name: it.name, location: it.location, description: it.description)
                }
            }
        }
    }

    def delete() {
        removeMediaSource.remove(input, this)
    }

    @Override
    void removed() {
        response.status = 204
        render(text: '')
    }

    class Input implements MediaSource {
        String name
        String location
        String description
    }
}
