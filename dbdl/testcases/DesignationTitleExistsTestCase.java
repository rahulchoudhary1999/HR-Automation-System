import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dto.*;
import java.util.*;
class DesignationTitleExistsTestCase
{
public static void main(String gg[])
{
String title=gg[0];
try
{
boolean designation;
designation=new DesignationDAO().titleExists(title);
System.out.println(designation);
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}