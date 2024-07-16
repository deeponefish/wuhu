import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Operation {
    static Connection connect() throws SQLException {
        //连接数据库
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/study", "root", "139866");
        return connection;
    }

    void insert(Scanner scanner) throws SQLException {
        Student student = new Student();
        System.out.println("请输入学生id");
        student.id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("请输入学生姓名");
        student.name = scanner.nextLine();
        System.out.println("请输入学生性别");
        student.sex = scanner.nextLine();
        System.out.println("请输入学生生日");
        student.bir = scanner.nextLine();
        System.out.println("请输入学生电话");
        student.number = scanner.nextLine();
        System.out.println("请输入学生班级");
        student.clas = scanner.nextLine();
        System.out.println("请输入学生年级");
        student.grade = scanner.nextLine();
        String sql = "INSERT INTO student (id, name, sex, bir, number, clas, grade) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connect().prepareStatement(sql)) {

            preparedStatement.setInt(1, student.id);
            preparedStatement.setString(2, student.name);
            preparedStatement.setString(3, student.sex);
            preparedStatement.setString(4, student.bir);
            preparedStatement.setString(5, student.number);
            preparedStatement.setString(6, student.clas);
            preparedStatement.setString(7, student.grade);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(student);
        }
    }

    void find(int id) {
        try (Statement statement = connect().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            if (id == 0) {
                ResultSet set = statement.executeQuery("select * from student");
                while (set.next()) {
                    int iid = set.getInt("id");
                    String name = set.getString("name");
                    String sex = set.getString("sex");
                    String bir = set.getString("bir");
                    String number = set.getString("number");
                    String clas = set.getString("clas");
                    String grade = set.getString("grade");

                    // 输出查询到的数据
                    System.out.println("ID: " + iid + ", Name: " + name + ", Sex: " + sex +
                            ", Birthday: " + bir + ", Number: " + number +
                            ", Class: " + clas + ", Grade: " + grade);
                }

            } else {
                ResultSet set = statement.executeQuery("select * from student where id=" + id);
                if (!set.next()) {
                    System.out.println("学校没这个人");
                    return;
                }
                set.beforeFirst();
                while (set.next()) {
                    int iid = set.getInt("id");
                    String name = set.getString("name");
                    String sex = set.getString("sex");
                    String bir = set.getString("bir");
                    String number = set.getString("number");
                    String clas = set.getString("clas");
                    String grade = set.getString("grade");

                    // 输出查询到的数据
                    System.out.println("ID: " + iid + ", Name: " + name + ", Sex: " + sex +
                            ", Birthday: " + bir + ", Number: " + number +
                            ", Class: " + clas + ", Grade: " + grade);
                }
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    int modify(int id, Scanner scanner) throws SQLException {
        try (Statement statement = connect().createStatement()) {
            while (true) {
                if (!statement.executeQuery("select * from student where id=" + id).next()) {
                    System.out.println("妹有这个人噢");
                    Thread.sleep(500);
                    return 0;
                }
                System.out.println("请选择要修改的属性（name、" +
                        "id,sex,bir,number,clas,grade）");
                Set<String> validFields = new HashSet<>();
                Collections.addAll(validFields, "name", "id", "sex",
                        "bir", "number", "clas", "grade");
                String ziduan = scanner.nextLine();
                if (validFields.contains(ziduan)) {
                    ResultSet set = statement.executeQuery("select " + ziduan +
                            " from student where id=" + id);
                    if (set.next()) {

                        System.out.println("您要修改的内容为" + set.getString(1) + ",如确认无误," +
                                "请输入想要更正的内容");
                    } else {
                        System.out.println("原值为空，请输入想要更正的内容");
                    }
                    String real = scanner.nextLine();
                    statement.executeUpdate("update student set " + ziduan + "='"
                            + real +
                            "'where id=" + id);
                    System.out.println("修改成功！");
                    return 1;
                } else {
                    System.out.println("没这种属性，麻烦重新输入QAQ");
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    ;

    int delete(int id, Scanner scanner) throws SQLException {
        try (Statement statement = connect().createStatement()) {
            if (!statement.executeQuery("select * from student where id=" + id).next()) {
                System.out.println("妹有这个人噢");
                Thread.sleep(50);
                return 0;
            } else {
                statement.executeUpdate("delete from student where id=" + id);
                System.out.println("删除成功！");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

}





