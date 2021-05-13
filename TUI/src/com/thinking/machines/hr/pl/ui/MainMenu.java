package com.thinking.machines.hr.pl.ui;
import com.thinking.machines.hr.pl.ui.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.common.*;
import java.util.*;
import java.text.*;
public class MainMenu
{
public void show()
{
int ch;
while(true)
{
System.out.println("Menu");
System.out.println("----------------------");
System.out.println("1. Designation Master");
System.out.println("2. Employee Master");
System.out.println("3. Exit");
ch=Keyboard.getInt("Enter your choice: ");
if(ch<1 || ch>3)
{
System.out.println("Invalid Input");
return;
}
else
{
if(ch==1)
{
while(true)
{
System.out.println("Designation Master");
System.out.println("----------------------");
System.out.println("1. Add");
System.out.println("2. Edit");
System.out.println("3. Delete");
System.out.println("4. Search");
System.out.println("5. Display");
System.out.println("6. CheckExistance");
System.out.println("7. Exit");
ch=Keyboard.getInt("Enter your choice: ");
if(ch<1 || ch>7)
{
System.out.println("Invalid Input");
return;
}
else
{
if(ch==1)
{
new DesignationUI().add();
}
else
{
if(ch>=2 && ch<=6)
{
int count=0;
try{
count=DesignationManager.getDesignationManager().getCount();
}catch(BLException blException)
{
//do nothing
}
if(count==0)
{
System.out.println("No Records Added");
return;
}
else
{
if(ch==2)
{
new DesignationUI().edit();
}
if(ch==3)
{
new DesignationUI().delete();
}
if(ch==4)
{
new DesignationUI().search();
}
if(ch==5)
{
new DesignationUI().display();
}
if(ch==6)
{
new DesignationUI().checkExistance();
}
}//else ends here
}
else if(ch==7)
{
break;
}
}
}
}
}
if(ch==2)
{
//employeeMaster
while(true)
{
System.out.println("Employee Master");
System.out.println("----------------------");
System.out.println("1. Add");
System.out.println("2. Edit");
System.out.println("3. Delete");
System.out.println("4. Search");
System.out.println("5. Display");
System.out.println("6. CheckExistance");
System.out.println("7. CountOfEmployees");
System.out.println("8. CountOfEmployeesWithSameDesignation");
System.out.println("9. Exit");
ch=Keyboard.getInt("Enter your choice: ");
if(ch<1 || ch>9)
{
System.out.println("Invalid input");
}
else
{
if(ch==1)
{
new EmployeeUI().add();
}
else
{
if(ch>=2 && ch<=8)
{
int count=0;
try{
count=EmployeeManager.getEmployeeManager().getCount();
}catch(BLException blException)
{
//do nothing
}
if(count==0)
{
System.out.println("No Records Added");
}
else
{
if(ch==2)
{
new EmployeeUI().edit();
}
if(ch==3)
{
new EmployeeUI().delete();
}
if(ch==4)
{
new EmployeeUI().search();
}
if(ch==5)
{
new EmployeeUI().display();
}
if(ch==6)
{
new EmployeeUI().checkExistance();
}
if(ch==7)
{
new EmployeeUI().countOfEmployees();
}
if(ch==8)
{
new EmployeeUI().countOfEmployeesWithSameDesignation();
}
}
}
else if(ch==9)
{
break;
}
}
}
}
}
if(ch==3)
{
return;
}
}//else ends here
}
}
}