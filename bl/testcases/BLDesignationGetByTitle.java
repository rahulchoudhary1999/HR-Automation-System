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
class BLDesignationGetByTitle
{
public static void main(String gg[])
{
String title=Keyboard.getString("Enter a title to get code :");
try
{
DesignationManagerInterface designationManager;
designationManager=DesignationManager.getDesignationManager();
DesignationInterface designation=designationManager.getByTitle(title);
int code=designation.getCode();
System.out.println("Designation title : "+title+" and Code : "+code);
}catch(BLException b)
{
List<String> list=b.getExceptions();
for(int i=0;i<list.size();i++)
{
String g=list.get(i);
System.out.println(g);
}

}
}
}