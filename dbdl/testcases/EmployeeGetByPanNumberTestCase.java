import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import java.util.*;
import java.math.*;
import java.text.*;
import com.thinking.machines.common.*;
class EmployeeGetByPanNumberTestCase
{
public static void main(String gg[])
{
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
try
{
String panNumber=Keyboard.getString("Enter Pan Number :");
EmployeeDTOInterface employee=new EmployeeDAO().getByPANNumber(panNumber);
System.out.println("Employee Id. :"+employee.getEmployeeId());
System.out.println("Name :"+employee.getName());
System.out.println("Designation Code :"+employee.getDesignationCode());
System.out.println("Basic Salary :"+employee.getBasicSalary().toPlainString());
System.out.println("D.O.B :"+sdf.format(employee.getDateOfBirth()));
System.out.println("Is Indian :"+employee.isIndian());
System.out.println("Gender :"+employee.getGender());
System.out.println("PAN Number :"+employee.getPANNumber());
System.out.println("Aadhar Card Number :"+employee.getAadharCardNumber());
System.out.println("--------------------------------------------------------------");
}catch(Exception e)
{
System.out.println(e.getMessage());
}
}
}
