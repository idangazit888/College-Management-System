package Collegeproject;

public class CommitteeExceptions {

    public static class CommitteeNotExistsException extends Exception {
        public CommitteeNotExistsException(String msg) {
            super(msg);
        }

        public CommitteeNotExistsException() {
            super("Committee does not exist.");
        }

    }

    public static class ChairmanExistsException extends Exception {
        public ChairmanExistsException(String msg) {
            super(msg);
        }

        public ChairmanExistsException() {
            super("The lecturer is on the list of committee members.");
        }

    }

    public static class LecturerNotExistsInCommitteeException extends Exception {
        public LecturerNotExistsInCommitteeException(String msg) {
            super(msg);
        }

        public LecturerNotExistsInCommitteeException() {
            super("The lecturer not exists on the list of committee members.");
        }

    }

    public static class ChairmanDegreeException extends Exception {
        public ChairmanDegreeException(String msg) {
            super(msg);
        }

        public ChairmanDegreeException() {
            super("Only DR or PROF can be chairman. Committee not added.");
        }

    }

    public static class CommitteeDegreeTypeException extends Exception {
        public CommitteeDegreeTypeException(String msg) {
            super(msg);
        }

        public CommitteeDegreeTypeException() {
            super("Lecturer degree does not match the committee degree type. Lecturer not added.");
        }

    }
}
