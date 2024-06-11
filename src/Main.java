//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
import java.io.*;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static HashSet<Student> SAVE;
    public static void main(String[] args)throws FileNotFoundException {
        System.out.println("正在初始化学生管理系统...");
        download();
        Scanner scanner=new Scanner(System.in);
        while(true){
             System.out.println("-----欢迎来到学生管理系统-----");
             System.out.println("请选择如下操作");
             System.out.println("1.添加学生");
             System.out.println("2.学生查询");
             System.out.println("3.学生信息修改");
             System.out.println("4.学生删除");
             System.out.println("5.退出系统");

             switch (scanner.nextInt()){
                 case 1:
                     insert(scanner);
                     break;
                 case 2:{
                     System.out.println("请输入学生id");
                     scanner.nextLine();
                     int i= scanner.nextInt();
                     scanner.nextLine();
                     find(i);
                     break;
                 }
                 case 3:{
                     System.out.println("请输入要修改的学生id");
                     scanner.nextLine();
                     modify(scanner.nextInt(),scanner);
                     break;
                 }
                 case 4:{
                     System.out.println("请输入要删除的学生id");
                     scanner.nextLine();
                     delete(scanner.nextInt(),scanner);
                     break;
                 }
                 case 5: {
                     System.out.println("正在保存学生信息...");
                     upload();
                     System.out.println("感谢你的使用，再见！");
                     return;
                 }
             };
         }

     }
    private static void insert(Scanner scanner){
        Student student=new Student();
        System.out.println("请输入学生id");
        student.id=scanner.nextInt();
        scanner.nextLine();
        System.out.println("请输入学生姓名");
        student.name=scanner.nextLine();
        System.out.println("请输入学生性别");
        student.sex=scanner.nextLine();
        System.out.println("请输入学生生日");
        student.bir=scanner.nextLine();
        System.out.println("请输入学生电话");
        student.number=scanner.nextLine();
        System.out.println("请输入学生班级");
        student.clas=scanner.nextLine();
        System.out.println("请输入学生年级");
        student.grade=scanner.nextLine();
        SAVE.add(student);
        System.out.println(student);
    }
    @SuppressWarnings("unchecked")
    private static void download(){
        File file=new File("data.txt");
        if(file.exists()&& file.length() > 0){
            try(ObjectInputStream stream=new ObjectInputStream
                    (new FileInputStream(file))){
                SAVE=(HashSet <Student>)stream.readObject();
                System.out.println("数据加载完毕");
            }catch(IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }else{
            SAVE=new HashSet<>();
            System.out.println("系统无信息存储，已建立新库");
        }


    };
    private static void upload(){
        try(ObjectOutputStream stream=new ObjectOutputStream
                (new FileOutputStream("data.txt"))){
            stream.writeObject(SAVE);
            stream.flush();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private static void find(int id){
        for (Student student:SAVE) {
            if(student.id==id){
                System.out.println(student);
                return;
            }
        }
        System.out.println("未能查询到学生信息");
    }

    private static void modify(int id,Scanner scanner){
        for (Student student:SAVE) {
            if(student.id==id){
                System.out.println("请选择要修改的属性（1-4分别为姓名、" +
                        "生日、电话号码、年级");
                switch (scanner.nextInt()){
                    case 1:
                        System.out.println("请输入新姓名");
                        scanner.nextLine();
                        student.name=scanner.nextLine();
                        break;
                    case 2:
                        System.out.println("请重新输入生日");
                        scanner.nextLine();
                        student.bir=scanner.nextLine();
                        break;
                    case 3:
                        System.out.println("请重新输入电话号码");
                        scanner.nextLine();
                        student.number=scanner.nextLine();
                        break;
                    case 4:
                        System.out.println("请更新年级");
                        scanner.nextLine();
                        student.grade=scanner.nextLine();
                        break;
                }
            }
        }
    }
    private static void delete(int id,Scanner scanner){
        for (Student student:SAVE) {
            if(student.id==id){
                System.out.println(student);
                System.out.println("确定要删除该学生信息吗？y/n");
                scanner.nextLine();
                if(scanner.nextLine().equals("y")){
                    SAVE.remove(student);
                    return;
                }else{
                    System.out.println("请重新输入");
                }
            }
        }
        System.out.println("有没有一种可能我们学校就没有这个人");
    }


}