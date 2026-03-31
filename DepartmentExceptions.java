package Collegeproject;

public class DepartmentExceptions {
    public static class DepartmentExistsException extends Exception {
        public DepartmentExistsException(String msg) {
            super(msg);
        }

        public DepartmentExistsException() {
            super("Department already exist.");
        }
    }

    public static class DepartmentNotExistsException extends Exception {
        public DepartmentNotExistsException(String msg) {
            super(msg);
        }

        public DepartmentNotExistsException() {
            super("Department not exist.");
        }
    }

    public static class LecturerAlreadyBelongDepartmentException extends Exception {
        public LecturerAlreadyBelongDepartmentException(String msg) {
            super(msg);
        }

        public LecturerAlreadyBelongDepartmentException() {
            super("This lecturer already belongs to a department.");
        }
    }
}
