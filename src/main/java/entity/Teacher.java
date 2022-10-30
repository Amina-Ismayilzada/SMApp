package entity;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Teacher {
    private String name;
    private String surname;
    private int ID;
    private String email;
    private String phone;


    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {

        this.surname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        Scanner sc = new Scanner(System.in);
        String regexPattern = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
                + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";
        while (!Pattern.compile(regexPattern).matcher(email).matches()) {
            System.out.println("Invalid input. Please enter the real email: ");
            email = sc.nextLine();
        }
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        Scanner sc = new Scanner(System.in);
        String regexPattern = "[0-9]+";
        while (!Pattern.compile(regexPattern).matcher(phone).matches()) {
            System.out.println("Invalid input. Please enter the real phone number: ");
            phone = sc.nextLine();
        }
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "Teacher {" +
                " ID = " + ID +
                " Name = '" + name + '\'' +
                ", Surname = '" + surname + '\'' +
                ", Email = '" + email + '\'' +
                ", Phone = '" + phone + '\'' +
                "}";
    }
}
