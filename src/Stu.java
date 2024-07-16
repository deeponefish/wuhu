import javax.xml.transform.Result;
import java.sql.*;

public class Stu {
    public static void main(String[] args) throws ClassNotFoundException {
        try(Connection connection=DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/study","root","139866");
            Statement statement= connection.createStatement()){
            statement.executeUpdate("insert into student values(13122,2323)");

            ResultSet set=statement.executeQuery("select * from student");
            while(set.next()){
                System.out.println(set.getString(1));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
}}
