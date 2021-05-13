import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.common.*;
import java.util.*;
import java.text.*;
class BLEmployeeGetByPANNumberTestCase
{
public static void main(String gg[])
{
try
{
EmployeeManager employeeManager=EmployeeManager.getEmployeeManager();
EmployeeInterface employee=employeeManager.getByPANNumber(Keyboard.getString("Enter panNumber:"));
System.out.println(employee.getEmployeeId());
System.out.println(employee.getName());
DesignationInterface designation=employee.getDesignation();
System.out.println("----------------------------------------");
System.out.println(designation.getCode());
System.out.println(designation.getTitle());
System.out.println("-----------------------------------------");
System.out.println(employee.getBasicSalary());
System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(employee.getDateOfBirth()));
System.out.println(employee.isIndian());
System.out.println(employee.getGender());
System.out.println(employee.getAadharCardNumber());
System.out.println(employee.getPANNumber());
}catch(BLException blException)
{
List<String> list=blException.getExceptions();
for(String g:list) 
System.out.println(g);
}
}
}