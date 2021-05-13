import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import java.util.*;
import java.math.*;
import java.text.*;
import com.thinking.machines.common.*;
class BLEmployeeAddTestCase
{
public static void main(String gg[])
{
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
try
{
DesignationManager designationManager=DesignationManager.getDesignationManager();
String name=Keyboard.getString("Enter name :");
DesignationInterface designation=designationManager.getByCode(Keyboard.getInt("Enter designation code :"));
Date dateOfBirth=simpleDateFormat.parse(Keyboard.getString("Enter dateOfBirth dd/mm/yyyy :"));
BigDecimal basicSalary=new BigDecimal(Keyboard.getString("Enter basic salary :"));
String i=Keyboard.getString("Is the employee an Indian resident(Y/N) :");
if(i.equals("Y")==false && i.equals("N")==false)
{
System.out.println("Invalid Input\n");
return;
}
boolean isIndian=i.equals("Y");
String g=Keyboard.getString("Enter gender (M/F):");
if(g.equals("M")==false && g.equals("F")==false)
{
System.out.println("Invalid input\n");
return;
}
EmployeeInterface.GENDER gender;
if(g.equals("M"))
{
gender=EmployeeInterface.MALE;
}
else
{
gender=EmployeeInterface.FEMALE;
}
String panNumber=Keyboard.getString("Enter PAN Number :");
String aadharCardNumber=Keyboard.getString("Enter Aadhar Card Number :");
EmployeeInterface employee=new Employee();
employee.setName(name);
employee.setDesignation(designation);
employee.setBasicSalary(basicSalary);
employee.setDateOfBirth(dateOfBirth);
employee.isIndian(isIndian);
employee.setGender(gender);
employee.setPANNumber(panNumber);
employee.setAadharCardNumber(aadharCardNumber);
EmployeeManager employeeManager=EmployeeManager.getEmployeeManager();
employeeManager.add(employee);
String employeeId=employee.getEmployeeId();
System.out.println("Employee added with employee id. as :"+employeeId);
}catch(BLException blException)
{
List<String> exception=blException.getExceptions();
for(String g:exception)
System.out.println(g);
}
catch(ParseException parseException)
{
System.out.println(parseException);
}
}
}