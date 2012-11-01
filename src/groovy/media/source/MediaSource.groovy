package media.source

interface MediaSource {
    String getName()
    String getLocation()
    String getDescription()

    static class NameRequired extends RuntimeException {}
    static class DuplicateName extends RuntimeException {}
    static class LocationRequired extends RuntimeException {}
    static class DuplicateLocation extends RuntimeException {}
}