import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.*;
import java.text.*;
import java.util.*;
import java.io.*;
import java.math.*;
public class EmployeeGetAllTestCase
{
public static void main(String gg[])
{
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
try
{
List<EmployeeDTOInterface> employees;
employees=new EmployeeDAO().getAll();
for(EmployeeDTOInterface employee :employees)
{
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
}
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
