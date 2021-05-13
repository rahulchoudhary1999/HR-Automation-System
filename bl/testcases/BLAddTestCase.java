import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import java.util.*;
class BLAddTestCase
{
public static void main(String gg[])
{
String title=gg[0];
try
{
DesignationInterface designation;
designation=new Designation();
designation.setTitle(title);

DesignationManager d=DesignationManager.getDesignationManager();
d.add(designation);
System.out.println("Designation : "+title+" added with code as : "+designation.getCode());
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