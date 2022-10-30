package dao;
import DB.DBConfig;
import entity.Teacher;
import util.ApplicationUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class TeacherDao {
    public static void addTeacher(Teacher teacher) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = DBConfig.getConnection();
            String sql = "INSERT INTO smappone.teacher(name, surname, phone, email) VALUES (?,?,?,?)";
            if(Objects.nonNull(connection)){
                statement = connection.prepareStatement(sql);
                statement.setString(1, teacher.getName());
                statement.setString(2, teacher.getSurname());
                statement.setString(3, teacher.getPhone());
                statement.setString(4, teacher.getEmail());
                statement.execute();
            }
        } catch (SQLException e) {
            ApplicationUtil.printError("The teacher wasn't added to the database.");
        }finally {
            connection.close();
            statement.close();
        }
    }


    public static List<Teacher> getAllTeachers() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Teacher> teachers = new ArrayList<Teacher>();
        try {
            connection = DBConfig.getConnection();
            String sql = "SELECT s.id, s.name, s.surname, s.phone, s.email FROM smappone.teacher s";
            if(Objects.nonNull(connection)){
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();

                while(resultSet.next()){
                    Teacher teacher = new Teacher();
                    teacher.setID(resultSet.getInt("id"));
                    teacher.setName(resultSet.getString("name"));
                    teacher.setSurname(resultSet.getString("surname"));
                    teacher.setPhone(resultSet.getString("phone"));
                    teacher.setEmail(resultSet.getString("email"));
                    teachers.add(teacher);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred in connection to database");
        }finally {
            connection.close();
            statement.close();
            resultSet.close();
        }
        return teachers;
    }

    public static Teacher getOneTeacher(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Teacher teacher = new Teacher();
        try{
            connection = DBConfig.getConnection();
            if(Objects.nonNull(connection)){
                String sql = "SELECT s.id, s.name, s.surname, s.phone, s.email FROM smappone.teacher s WHERE id = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    teacher.setID(resultSet.getInt("id"));
                    teacher.setName(resultSet.getString("name"));
                    teacher.setSurname(resultSet.getString("surname"));
                    teacher.setPhone(resultSet.getString("phone"));
                    teacher.setEmail(resultSet.getString("email"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred in connection to database");
        }finally {
            connection.close();
            statement.close();
            resultSet.close();
        }
        return teacher;
    }

    public static void updateTeacher(Teacher teacher) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "UPDATE smappone.teacher s SET  s.name = ?, s.surname = ?, s.phone = ?, s.email = ? WHERE id = ?";
        try{
            connection = DBConfig.getConnection();
            if(Objects.nonNull(connection)){
                statement = connection.prepareStatement(sql);
                statement.setString(1, teacher.getName());
                statement.setString(2, teacher.getSurname());
                statement.setString(3, teacher.getPhone());
                statement.setString(4, teacher.getEmail());
                statement.setInt(5, teacher.getID());
                statement.execute();
            }
        } catch (SQLException e) {
            System.out.println("Error occurred in connection to database");
        }finally {
            connection.close();
            statement.close();
        }
    }
    public static void deleteTeacher (int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "DELETE FROM smappone.teacher WHERE id=?";
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
