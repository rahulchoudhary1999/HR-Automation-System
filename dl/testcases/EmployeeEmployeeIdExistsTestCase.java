import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import java.util.*;
import java.math.*;
import java.text.*;
import com.thinking.machines.common.*;
class EmployeeEmployeeIdExistsTestCase
{
public static void main(String gg[])
{
try
{
String employeeId=Keyboard.getString("Enter employeeId:");
boolean employeeIdExists=new EmployeeDAO().employeeIdExists(employeeId);
if(employeeIdExists)
{
System.out.println("EmployeeId Exists");
}
else
{
System.out.println("EmployeeId Not Exists");
}
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}