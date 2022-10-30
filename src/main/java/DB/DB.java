package DB;


import entity.Teacher;
import entity.Student;
import java.util.HashMap;
import java.util.Map;

public interface DB {
    Map<Integer, Student> students = new HashMap<Integer, Student>();
    Map<Integer, Teacher> teachers = new HashMap<Integer, Teacher>();
    String APP_USERNAME = "ADMIN";
    String APP_PASSWORD = "123";

    static boolean studentsListIsNotEmpty() {
        if (DB.students.isEmpty()) {
            System.err.println("The list is empty.");
            return false;
        }
        return true;
    }

    static boolean teachersListIsNotEmpty() {
        if (DB.teachers.isEmpty()) {
            System.err.println("The list is empty.");
            return false;
        }
        return true;
    }
}
