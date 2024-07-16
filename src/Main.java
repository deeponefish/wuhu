//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, SQLException, InterruptedException {
        System.out.println("正在初始化学生管理系统...");
        Operation operation = new Operation();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Thread.sleep(1000);
            System.out.println("-----欢迎来到学生管理系统-----");
            System.out.println("请选择如下操作");
            System.out.println("1.添加学生");
            System.out.println("2.学生查询");
            System.out.println("3.学生信息修改");
            System.out.println("4.学生删除");
            System.out.println("5.退出系统");

            switch (scanner.nextInt()) {
                case 1:
                    operation.insert(scanner);
                    break;
                case 2: {
                    System.out.println("请输入学生id,，输入0可查询全部");
                    MyScanner.scanner.nextLine();
                    int i = scanner.nextInt();
                    scanner.nextLine();
                    operation.find(i);
                    break;
                }
                case 3: {
                    scanner.nextLine();
                    while (true) {
                        System.out.println("请输入要修改的学生id");
                        int i = scanner.nextInt();
                        scanner.nextLine();
                        int a = operation.modify(i, scanner);
                        if (a == 0) {
                            continue;
                        }
                        break;
                    }
                }
                 case 4:{
                     System.out.println("请输入要删除的学生id");
                     scanner.nextLine();
                     operation.delete(scanner.nextInt(),scanner);
                     break;
                 }

            }
        }




    }
}