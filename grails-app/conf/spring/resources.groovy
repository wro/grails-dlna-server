import media.source.interactor.add.AddMediaSourceInteractor
import media.source.interactor.list.ListMediaSourcesInteractor
import media.source.repository.gorm.GormMediaSourceRepository
import media.source.interactor.add.AddMediaSourceInteractor

beans = {
    def byType = {
        it.autowire = 'byType'
    }

    mediaSourceRepository(GormMediaSourceRepository)

    addMediaSource(AddMediaSourceInteractor, byType)
    listMediaSources(ListMediaSourcesInteractor, byType)
}
