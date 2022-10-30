package dao;

import DB.DBConfig;
import entity.Student;
import util.ApplicationUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentDao {
    public static void addStudent(Student student) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = DBConfig.getConnection();
            String sql = "INSERT INTO smappone.student(name, surname, phone, email) VALUES (?,?,?,?)";
            if(Objects.nonNull(connection)){
                statement = connection.prepareStatement(sql);
                statement.setString(1, student.getName());
                statement.setString(2, student.getSurname());
                statement.setString(3, student.getPhone());
                statement.setString(4, student.getEmail());
                statement.execute();
            }
        } catch (SQLException e) {
            ApplicationUtil.printError("The student wasn't added to the database.");
        }finally {
            connection.close();
            statement.close();
        }
    }


    public static List<Student> getAllStudents() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Student> students = new ArrayList<Student>();
        try {
            connection = DBConfig.getConnection();
            String sql = "SELECT s.id, s.name, s.surname, s.phone, s.email FROM smappone.student s";
            if(Objects.nonNull(connection)){
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();

                while(resultSet.next()){
                    Student student = new Student();
                    student.setID(resultSet.getInt("id"));
                    student.setName(resultSet.getString("name"));
                    student.setSurname(resultSet.getString("surname"));
                    student.setPhone(resultSet.getString("phone"));
                    student.setEmail(resultSet.getString("email"));
                    students.add(student);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred in connection to database");
        }finally {
            connection.close();
            statement.close();
            resultSet.close();
        }
        return students;
    }

    public static Student getOneStudent(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Student student = new Student();
        try{
            connection = DBConfig.getConnection();
            if(Objects.nonNull(connection)){
                String sql = "SELECT s.id, s.name, s.surname, s.phone, s.email FROM smappone.student s WHERE id = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    student.setID(resultSet.getInt("id"));
                    student.setName(resultSet.getString("name"));
                    student.setSurname(resultSet.getString("surname"));
                    student.setPhone(resultSet.getString("phone"));
                    student.setEmail(resultSet.getString("email"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred in connection to database");
        }finally {
            connection.close();
            statement.close();
            resultSet.close();
        }
        return student;
    }

    public static void updateStudent(Student student) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "UPDATE smappone.student s SET  s.name = ?, s.surname = ?, s.phone = ?, s.email = ? WHERE id = ?";
        try{
            connection = DBConfig.getConnection();
            if(Objects.nonNull(connection)){
                statement = connection.prepareStatement(sql);
                statement.setString(1, student.getName());
                statement.setString(2, student.getSurname());
                statement.setString(3, student.getPhone());
                statement.setString(4, student.getEmail());
                statement.setInt(5, student.getID());
                statement.execute();
            }
        } catch (SQLException e) {
            System.out.println("Error occurred in connection to database");
        }finally {
            connection.close();
            statement.close();
        }
    }
    public static void deleteStudent (int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "DELETE FROM smappone.student WHERE id=?";
        try{
            connection = DBConfig.getConnection();
            if(Objects.nonNull(connection)){
                statement = connection.prepareStatement(sql);
                statement.setInt(1,id);
                statement.execute();
            }
        } catch (SQLException e) {
            System.out.println("Error occurred in connection to database");
        }finally {
            connection.close();
            statement.close();
        }
    }


}
