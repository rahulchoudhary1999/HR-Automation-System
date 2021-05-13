import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.common.*;
import com.thinking.machines.hr.dl.dao.*;
class DesignationDeleteTestCase
{
public static void main(String gg[])
{
int designationCode=Keyboard.getInt("Enter Designation Code you want to delete:");
try
{

new DesignationDAO().delete(designationCode);
System.out.println(designationCode+" designation codedeleted successfully");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}