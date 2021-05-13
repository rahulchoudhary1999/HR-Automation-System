import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dto.*;
import java.util.*;
class DesignationGetByTitleTestCase
{
public static void main(String gg[])
{
try
{
String title=gg[0];
DesignationDTOInterface designation;
designation=new DesignationDAO().getByTitle(title);
System.out.println("Code : "+designation.getCode());
System.out.println("Title : "+designation.getTitle());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}