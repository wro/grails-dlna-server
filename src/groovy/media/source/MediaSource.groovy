package media.source

interface MediaSource {
    String getName()
    String getLocation()
    String getDescription()

    static abstract class RejectedField extends RuntimeException {}
    static class NameRequired extends RejectedField {}
    static class DuplicateName extends RejectedField {}
    static class LocationRequired extends RejectedField {}
    static class DuplicateLocation extends RejectedField {}
    static class NotFound extends RuntimeException {}
}