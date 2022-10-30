package main;

import service.StudentService;
import service.TeacherService;
import util.ApplicationUtil;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private final static String APP_MENU =
            """
                    1. Student
                    2. Teacher
                    """;

    private final static String STUDENT_MENU =
            """
                    1. Add student
                    2. Get all students
                    3. Get one student
                    4. Update student
                    5. Delete student""";

    private final static String TEACHER_MENU =
            """
                    1. Add teacher
                    2. Get all teachers
                    3. Get one teacher
                    4. Update teacher
                    5. Delete teacher""";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        int selectedOperation;
        int numberOfTries = 3;

        System.out.println("\n\t\t<<<<<<<<< WELCOME >>>>>>>>>>\n");
        while (true) {
            System.out.print("- Please enter the username: ");
            String username = sc.nextLine();
            System.out.print("- Please enter the password: ");
            String password = sc.nextLine();
            if (ApplicationUtil.validateLoginInformation(username, password)) {
                break;
            } else {
                numberOfTries--;
                if (numberOfTries == 0) {
                    System.out.println("\nOH, THERE IS A SPY!!! Just kiddin', have a nice day! :)");
                    return;
                } else if (numberOfTries == 2) {
                    System.out.println("The information is incorrect. You have 2 more chances.");
                } else {
                    System.out.println("Oops, wrong again! Last chance, try again please.");
                }

            }
        }


        System.out.println("\n................................................\n");
        System.out.println(APP_MENU);
        System.out.print("Please choose the mode number: ");
        selectedOperation = ApplicationUtil.validateStringInputAndReturnNumber(sc.nextLine());
        while (selectedOperation > 2 || selectedOperation < 1) {
            System.out.print("Selected operation is wrong. Please enter either 1 or 2: ");
            selectedOperation = ApplicationUtil.validateStringInputAndReturnNumber(sc.nextLine());
        }

        while (true) {
            System.out.println("\n................................................\n");
            if (selectedOperation == 1) {
                System.out.println(STUDENT_MENU);
                System.out.println("Please choose the number of operation to execute:");
                selectedOperation = ApplicationUtil.validateStringInputAndReturnNumber(sc.nextLine());
                while (selectedOperation > 5 || selectedOperation < 1) {
                    System.out.print("Please enter a number from 1 to 5: ");
                    selectedOperation = ApplicationUtil.validateStringInputAndReturnNumber(sc.nextLine());
                }
                switch (selectedOperation) {
                    case 1 -> StudentService.addStudent();
                    case 2 -> StudentService.getAllStudents();
                    case 3 -> StudentService.getOneStudent();
                    case 4 -> StudentService.updateStudent();
                    case 5 -> StudentService.deleteStudent();
                }
            } else {
                System.out.println(TEACHER_MENU);
                System.out.println("Please choose the number of operation to execute:");
                selectedOperation = ApplicationUtil.validateStringInputAndReturnNumber(sc.nextLine());
                while (selectedOperation > 5 || selectedOperation < 1) {
                    System.out.print("Please enter a number from 1 to 5: ");
                    selectedOperation = ApplicationUtil.validateStringInputAndReturnNumber(sc.nextLine());
                }
                switch (selectedOperation) {
                    case 1 -> TeacherService.addTeacher();
                    case 2 -> TeacherService.getAllTeachers();
                    case 3 -> TeacherService.getOneTeacher();
                    case 4 -> TeacherService.updateTeacher();
                    case 5 -> TeacherService.deleteTeacher();
                }

            }

            System.out.println("\n\t1 - for Student mode\n\t2 - for Teacher mode\n\t3 - to exit\nPlease choose the operation: ");
            selectedOperation = ApplicationUtil.validateStringInputAndReturnNumber(sc.nextLine());
            while (selectedOperation > 3 || selectedOperation < 1) {
                System.out.println("Please enter either 1, 2 or 3: ");
                selectedOperation = ApplicationUtil.validateStringInputAndReturnNumber(sc.nextLine());
            }

            if (selectedOperation == 3) {
                System.err.println("\n\t\t<<<<<<<<< HAVE A NICE DAY  >>>>>>>>>>");
                return;
            }
        }

    }
}