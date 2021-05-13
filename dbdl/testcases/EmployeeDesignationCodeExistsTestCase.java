import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import java.util.*;
import java.math.*;
import java.text.*;
import com.thinking.machines.common.*;
class EmployeeDesignationCodeExistsTestCase
{
public static void main(String gg[])
{
try
{
int designationCode=Keyboard.getInt("Enter Designation Code:");
boolean a=new EmployeeDAO().designationCodeExists(designationCode);
System.out.println("Designation Code Exists : "+a);
}catch(Exception e)
{
System.out.println(e.getMessage());
}
}
}