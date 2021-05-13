import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.managers.*;
import java.math.*;
import java.text.*;
import java.io.*;
import java.util.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.common.*;
class BLEmployeeDeleteTestCase
{
public static void main(String gg[])
{
try
{
String employeeId=Keyboard.getString("Enter a employee id to delete : ");
EmployeeManager employeeManager;
employeeManager=EmployeeManager.getEmployeeManager();
employeeManager.delete(employeeId);
System.out.println("Employee deleted with employeeId : "+employeeId);
}catch(BLException b)
{
List<String> list=b.getExceptions();
for(int i=0;i<list.size();i++)
{
String g=list.get(i);
System.out.println(g);
}
}
}
}
