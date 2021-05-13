import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import java.util.*;
import java.math.*;
import java.text.*;
import com.thinking.machines.common.*;
class EmployeePanNumberExistsTestCase
{
public static void main(String gg[])
{
try
{
String panNumber=Keyboard.getString("Enter Pan Number:");
boolean panNumberExists=new EmployeeDAO().panNumberExists(panNumber);
System.out.println("PAN Number exists:"+panNumberExists);
}catch(Exception e)
{
System.out.println(e.getMessage());
}
}
}