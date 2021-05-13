




import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.managers.*;
import java.util.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.common.*;
class BLDesignationTitleExists
{
public static void main(String gg[])
{
String title=Keyboard.getString("Enter designation title:");
try
{
DesignationManagerInterface designationManager;
designationManager=DesignationManager.getDesignationManager();
boolean titleExists=designationManager.titleExists(title);
System.out.printf("Is Designation title exists :" +titleExists);
}catch(BLException blException)
{
List<String> list=blException.getExceptions();
for(String g:list)
System.out.println(g);
}
}
}