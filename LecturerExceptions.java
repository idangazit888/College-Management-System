package Collegeproject;

public class LecturerExceptions {

    public static class LecturerNotExistsException extends Exception {
        public LecturerNotExistsException(String msg) {
            super(msg);
        }

        public LecturerNotExistsException() {
            super("Lecturer does not exist.");
        }
    }

    public static class LecturerNotDRorProfException extends Exception {
        public LecturerNotDRorProfException(String msg) {
            super(msg);
        }

        public LecturerNotDRorProfException() {
            super("Lecturer is not a DR/PROF.");
        }
    }

}