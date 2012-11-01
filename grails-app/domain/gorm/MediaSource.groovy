package gorm

import media.source.MediaSource as Entity

class MediaSource {
    String name
    String location
    String description

    static constraints = {
        name(blank: false, unique: true)
        location(blank: false, unique: true)
        description(nullable: true)
    }

    MediaSource(Entity entity) {
        name = entity.name
        location = entity.location
        description = entity.description
    }
}
