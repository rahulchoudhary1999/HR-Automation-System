import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dao.*;
class EmployeeGetCountTestCase
{
public static void main(String gg[])
{
try
{
System.out.println("Number of employee :"+new EmployeeDAO().getCount());
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}