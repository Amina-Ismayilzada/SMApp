package util;

import DB.DB;
import main.Main;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class ApplicationUtil {
    public static int validateStringInputAndReturnNumber(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            printError("Invalid input.");
            return -1;
        }
    }

    public static void printError(String errorMessage) {
        System.err.println("\t ! ! ! ATTENTION ! ! !");
        System.out.println(errorMessage);
    }

    public static void isStopped(Scanner sc) throws SQLException {
        System.out.print("\nWould you like to continue? (y/n):   ");
        String input = sc.next();
        if (Objects.equals(input, "Y") || Objects.equals(input, "y")) {
            Main.main(null);
        } else {
            System.err.println("\n\t\t<<<<<<<<< HAVE A NICE DAY  >>>>>>>>>>");
        }
    }

    public static boolean validateLoginInformation(String username, String password){
        return (username.equals(DB.APP_USERNAME) && password.equals(DB.APP_PASSWORD));
    }


}
