import com.sun.corba.se.pept.encoding.OutputObject;

import java.io.Serializable;
import java.io.*;

public class Student implements java.io.Serializable {
    int id ;
    String name;
    String sex;
    String bir; //出生日期怎么表示？
    String number;
    String clas;//重名如何处理？
    String grade;

    @Override
    public String toString() {
        return "学生档案："+name+","+sex+","+"今年"+clas+"班"+"大"+grade +
                ",学生id为"+id+ ","+"电话号码："+number+",出生日期为"+bir;
    }
}
