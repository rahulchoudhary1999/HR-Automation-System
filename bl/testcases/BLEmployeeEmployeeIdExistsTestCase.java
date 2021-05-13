import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.managers.*;
import java.util.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.common.*;
class BLEmployeeEmployeeIdExistsTestCase
{
public static void main(String gg[])
{
try
{
EmployeeManager employeeManager=EmployeeManager.getEmployeeManager();
boolean employeeIdExists=employeeManager.employeeIdExists(Keyboard.getString("Enter EmployeeId:"));
System.out.println("Is Employee exists :" +employeeIdExists);
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