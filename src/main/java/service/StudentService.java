package service;

import DB.DB;

import dao.StudentDao;
import util.ApplicationUtil;

import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

import entity.Student;
public class StudentService {
    public static int size = 0;
    public static int validateNumberOfStudent(int input, Scanner sc) {
        OUTER_LOOP: while (true){
            for (Map.Entry<Integer, Student> entry: DB.students.entrySet()) {
                if(entry.getKey() == input){
                    break OUTER_LOOP;
                }
            }
            System.out.print("There is no such student. Please enter the valid number:  ");
            input = ApplicationUtil.validateStringInputAndReturnNumber(sc.nextLine());
        }
        return input;
    }

    public static void setStudentInfo(Student student, Scanner sc) {
        System.out.println("----- Please enter student's data -----");

        System.out.print("\t  Name  ->  ");
        student.setName(sc.nextLine());
        System.out.print("\t  Surname  ->  ");
        student.setSurname(sc.nextLine());
        System.out.print("\t  Email  ->  ");
        student.setEmail(sc.nextLine());
        System.out.print("\t  Phone number  ->  ");
        student.setPhone(sc.nextLine());
        System.out.println();
    }


    public static void addStudent() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Student newStudent = new Student();

        setStudentInfo(newStudent, sc);
        size++;
        StudentDao.addStudent(newStudent);
        System.out.println("Student successfully added to database.");
//        DB.students.put(size, newStudent); //adding new student to students table

//        System.out.println(size + ". " + DB.students.get(size)); //printing out the last student added
    }

    public static void getAllStudents() throws SQLException {

            System.err.println("\t\t\t\t\t\t--> Please see the list of all students <--");
//            for (Map.Entry<Integer, Student> entry : DB.students.entrySet()) {
//                System.out.println(entry.getKey() + ". " + entry.getValue());
//            }
            StudentDao.getAllStudents().forEach(System.out::println);

    }

    public static void getOneStudent() throws SQLException {
        Scanner sc = new Scanner(System.in);
        getAllStudents();
        System.out.print("\nPlease enter the id of the student you want to be printed: ");
//            int input = validateNumberOfStudent(ApplicationUtil.validateStringInputAndReturnNumber(sc.nextLine()), sc);
//            System.out.println("\n" + input + ". " + DB.students.get(input));
        int id = sc.nextInt();
        System.out.println(StudentDao.getOneStudent(id));
    }



    public static void updateStudent() throws SQLException {
        Scanner sc = new Scanner(System.in);
        getAllStudents();
        System.out.print("\nPlease enter the id of student you want to be updated: ");
        int id = sc.nextInt();
        sc.nextLine();
        Student student = StudentDao.getOneStudent(id);
        setStudentInfo(student, sc);
        StudentDao.updateStudent(student);
        System.out.print("The student is updated.");
    }


    public static void deleteStudent() throws SQLException {
        Scanner sc = new Scanner(System.in);
        getAllStudents();
        System.out.print("\nPlease enter the id of student you want to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        StudentDao.deleteStudent(id);
        System.out.println("Successfully deleted.");
    }
}
