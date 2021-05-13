import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.common.*;
import com.thinking.machines.hr.dl.dao.*;
class EmployeeDeleteTestCase
{
public static void main(String gg[])
{
String employeeId=Keyboard.getString("Enter employeeId you want to delete:");
try
{
new EmployeeDAO().delete(employeeId);
System.out.println(employeeId+" employee id deleted successfully");
}catch(Exception e)
{
System.out.println(e.getMessage());
}
}
}