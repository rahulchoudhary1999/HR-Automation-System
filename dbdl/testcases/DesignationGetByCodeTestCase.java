import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dto.*;
import java.util.*;
class DesignationGetByCodeTestCase
{
public static void main(String gg[])
{
int code=Integer.parseInt(gg[0]);
try
{
DesignationDTOInterface designation;
designation=new DesignationDAO().getByCode(code);
System.out.println("Code : "+designation.getCode());
System.out.println("Title : "+designation.getTitle());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}