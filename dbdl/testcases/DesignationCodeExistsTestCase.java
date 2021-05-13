import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dto.*;
import java.util.*;
class DesignationCodeExistsTestCase
{
public static void main(String gg[])
{
try
{
int code=Integer.parseInt(gg[0]);
boolean designation;
designation=new DesignationDAO().codeExists(code);
System.out.println(designation);
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}