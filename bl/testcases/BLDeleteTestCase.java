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
class BLDeleteTestCase
{
public static void main(String gg[])
{
try
{
int vCode=Keyboard.getInt("Enter a code to delete : ");
DesignationManagerInterface designationManager;
designationManager=DesignationManager.getDesignationManager(); 
designationManager.delete(vCode);
System.out.printf("designation deleted with code as %d",vCode);
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