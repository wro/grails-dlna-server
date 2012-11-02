package media.source.repository.gorm

import gorm.MediaSource as Domain
import media.source.MediaSource
import media.source.repository.MediaSourceRepository

class GormMediaSourceRepository implements MediaSourceRepository {

    @Override
    void create(MediaSource mediaSource) {
        def entity = new MediaSourceWrapper(delegate: new Domain(mediaSource))
        entity.check()
        entity.delegate.save()
    }

    @Override
    Set<MediaSource> getMediaSources() {
        Domain.findAll().collect { new MediaSourceWrapper(delegate: it)}
    }

    @Override
    void remove(String name, String location) {
        def domain = Domain.findByNameAndLocation(name, location)
        if (!domain) throw new MediaSource.NotFound()
        domain.delete()
    }

    class MediaSourceWrapper implements MediaSource {
        @Delegate Domain delegate

        void check() {
            if (!delegate.validate()) {
                if (nameRequired) throw new MediaSource.NameRequired()
                if (locationRequired) throw new MediaSource.LocationRequired()
                if (duplicateName) throw new MediaSource.DuplicateName()
                if (duplicateLocation) throw new MediaSource.DuplicateLocation()
            }
        }

        private boolean isNameRequired() {
            nameNull || nameBlank
        }

        private boolean isNameNull() {
            checkField('nullable', 'name')
        }

        private boolean isNameBlank() {
            checkField('blank', 'name')
        }

        private boolean isLocationRequired() {
            locationNull || locationBlank
        }

        private boolean isLocationNull() {
            checkField('nullable', 'location')
        }

        private boolean isLocationBlank() {
            checkField('blank', 'location')
        }

        private boolean isDuplicateName() {
            checkField('unique', 'name')
        }

        private boolean isDuplicateLocation() {
            checkField('unique', 'location')
        }

        private boolean checkField(String code, String field) {
            delegate.errors.fieldErrors.any { it.code == code && it.field == field }
        }
    }
}
