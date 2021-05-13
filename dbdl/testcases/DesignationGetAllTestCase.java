import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dto.*;
import java.util.*;
class DesignationGetAllTestCase
{
public static void main(String gg[])
{
try
{
List<DesignationDTOInterface> designations;
designations=new DesignationDAO().getAll();
for(DesignationDTOInterface d:designations)
{
System.out.println("Code : "+d.getCode());
System.out.println("Title : "+d.getTitle());
System.out.println("--------------------------------------------------------");
}
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}