import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
class DesignationDeleteTestCase
{
public static void main(String gg[])
{
try
{
int code=Integer.parseInt(gg[0]);
new DesignationDAO().delete(code);
System.out.println("Designation with code : "+code+" deleted");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}