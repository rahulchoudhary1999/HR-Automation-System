import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import java.util.*;
import java.math.*;
import java.text.*;
import com.thinking.machines.common.*;
class EmployeeAadharCardNumberExistsTestCase
{
public static void main(String gg[])
{
try
{
String aadharCardNumber=Keyboard.getString("Enter AadharCardNumber:");
boolean aadharCardNumberExists=new EmployeeDAO().aadharCardNumberExists(aadharCardNumber);
if(aadharCardNumberExists)
{
System.out.println("Aadhar Card Number Exists");
}
else
{
System.out.println("Aadhar Card Number Not Exists");
}
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}