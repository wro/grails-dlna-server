import media.source.repository.gorm.GormMediaSourceRepository
import unit.media.source.interactor.AddMediaSourceInteractor

beans = {
    def byType = {
        it.autowire = 'byType'
    }

    mediaSourceRepository(GormMediaSourceRepository)

    addMediaSource(AddMediaSourceInteractor, byType)
}
