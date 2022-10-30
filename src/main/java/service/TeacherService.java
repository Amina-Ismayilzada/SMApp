package service;

import DB.DB;
import dao.TeacherDao;
import entity.Teacher;
import util.ApplicationUtil;

import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class TeacherService {
    public static int size = 0;

    public static int validateNumberOfTeacher(int input, Scanner sc) {
        OUTER_LOOP:
        while (true) {
            for (Map.Entry<Integer, Teacher> entry : DB.teachers.entrySet()) {
                if (entry.getKey() == input) {
                    break OUTER_LOOP;
                }
            }
            System.out.print("There is no such teacher. Please enter the valid number:  ");
            input = ApplicationUtil.validateStringInputAndReturnNumber(sc.nextLine());
        }
        return input;
    }

    public static void setTeacherInfo(Teacher teacher, Scanner sc) {
        System.out.println("----- Please enter teacher's data -----");

        System.out.print("\t  Name  ->  ");
        teacher.setName(sc.nextLine());
        System.out.print("\t  Surname  ->  ");
        teacher.setSurname(sc.nextLine());
        System.out.print("\t  Email  ->  ");
        teacher.setEmail(sc.nextLine());
        System.out.print("\t  Phone number  ->  ");
        teacher.setPhone(sc.nextLine());
        System.out.println();
    }


    public static void addTeacher() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Teacher newTeacher = new Teacher();

        setTeacherInfo(newTeacher, sc);
        size++;

        TeacherDao.addTeacher(newTeacher);
        System.out.println("Teacher successfully added to database.");
//        DB.teachers.put(size, newTeacher); //adding new teacher to teachers table

//        System.out.println(size + ". " + DB.teachers.get(size)); //printing out the last teacher added
    }

    public static void getAllTeachers() throws SQLException {
//        if (DB.teachersListIsNotEmpty()) {
        System.err.println("\t\t\t\t\t\t--> Please see the list of all teachers <--");
//            for (Map.Entry<Integer, Teacher> entry : DB.teachers.entrySet()) {
//                System.out.println(entry.getKey() + ". " + entry.getValue());
//            }
//        }
        TeacherDao.getAllTeachers().forEach(System.out::println);

    }

    public static void getOneTeacher() throws SQLException {
        Scanner sc = new Scanner(System.in);
//        if (DB.teachersListIsNotEmpty()) {
        getAllTeachers();
        System.out.print("\nPlease enter the ID of the teacher you want to be printed: ");
//            int input = validateNumberOfTeacher(ApplicationUtil.validateStringInputAndReturnNumber(sc.nextLine()), sc);
//            System.out.println("\n" + input + ". " + DB.teachers.get(input));
//        }
        int id = sc.nextInt();
        System.out.println(TeacherDao.getOneTeacher(id));

    }

    public static void updateTeacher() throws SQLException {
        Scanner sc = new Scanner(System.in);
//        if (DB.teachersListIsNotEmpty()) {
        getAllTeachers();
        System.out.print("\nPlease enter the ID of teacher you want to be updated: ");
        int id = sc.nextInt();
        sc.nextLine();
        Teacher teacher = TeacherDao.getOneTeacher(id);
        setTeacherInfo(teacher, sc);
        TeacherDao.updateTeacher(teacher);
//            int input = validateNumberOfTeacher(ApplicationUtil.validateStringInputAndReturnNumber(sc.nextLine()), sc);
//            setTeacherInfo(DB.teachers.get(input), sc);
        System.out.print("The teacher is updated:");
//            System.out.println("\n" + input + ". " + DB.teachers.get(input));
//        }

    }

    public static void deleteTeacher() throws SQLException {
        Scanner sc = new Scanner(System.in);
//        if (DB.teachersListIsNotEmpty()) {
        getAllTeachers();
        System.out.print("\nPlease enter the ID of teacher you want to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        TeacherDao.deleteTeacher(id);
//            int input = validateNumberOfTeacher(ApplicationUtil.validateStringInputAndReturnNumber(sc.nextLine()), sc);
//            DB.teachers.remove(input);
        System.out.println("Successfully deleted.");
//        }
    }
}
