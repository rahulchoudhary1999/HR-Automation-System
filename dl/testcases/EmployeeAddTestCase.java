import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import java.util.*;
import java.math.*;
import java.text.*;
import com.thinking.machines.common.*;
public class EmployeeAddTestCase
{
public static void main(String gg[])
{
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
try
{
String name=Keyboard.getString("Enter name :");
int designationCode=Keyboard.getInt("Enter designation code :");
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
EmployeeDTOInterface.GENDER gender;
if(g.equals("M"))
{
gender=EmployeeDTOInterface.MALE;
}
else
{
gender=EmployeeDTOInterface.FEMALE;
}
String panNumber=Keyboard.getString("Enter PAN Number :");
String aadharCardNumber=Keyboard.getString("Enter Aadhar Card Number :");
EmployeeDTOInterface employeeDTO=new EmployeeDTO();
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setDateOfBirth(dateOfBirth);
employeeDTO.isIndian(isIndian);
employeeDTO.setGender(gender);
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
employeeDAO.add(employeeDTO);
String employeeId=employeeDTO.getEmployeeId();
System.out.println("Employee added with employee id. as :"+employeeId);
}catch(DAOException daoException)
{
System.out.println(daoException);
}
catch(ParseException parseException)
{
System.out.println(parseException);
}
}
}