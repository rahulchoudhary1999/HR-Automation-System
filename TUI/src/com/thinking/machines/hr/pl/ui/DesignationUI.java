package com.thinking.machines.hr.pl.ui;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.common.*;
import java.util.*;
import java.text.*;
public class DesignationUI
{
public void add()
{
while(true)
{
String title=Keyboard.getString("Enter title: ");
DesignationInterface designation;
designation=new Designation();
designation.setTitle(title);
try
{
DesignationManager designationManager=DesignationManager.getDesignationManager();
designationManager.add(designation);
System.out.println("Successfully added with designation code: "+designation.getCode());
//System.out.println("-------------------------------------");
break;
}catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to enter data again(Y/N): ");
if(k.equals("Y")==false && k.equals("N")==false)
{
System.out.println("Invalid input");
return;
}
if(k.equals("Y"))
{
continue;
}
else
{
break;
}
}
}
}
public void edit()
{
while(true)
{
int code=Keyboard.getInt("Enter code: ");
String title=Keyboard.getString("Enter title: ");
DesignationInterface designation;
designation=new Designation();
designation.setCode(code);
designation.setTitle(title);
try
{
DesignationManager designationManager;
designationManager=DesignationManager.getDesignationManager();
designationManager.update(designation);
System.out.println("Successfully updated...");
//System.out.println("-------------------------------------");
break;
}
catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to enter data again(Y/N): ");
if(k.equals("Y")==false && k.equals("N")==false)
{
System.out.println("Invalid input");
return;
}
if(k.equals("Y"))
{
continue;
}
else
{
break;
}
}
}
}
public void display()
{
while(true)
{
System.out.println("1. Code Wise Order");
System.out.println("2. Title Wise Order");
System.out.println("3. Exit");
int ch;
while(true)
{
ch=Keyboard.getInt("Enter your choice:");
List<DesignationInterface> list=null;
if(ch<1||ch>3)
{
System.out.println("Invalid Input");
return;
}
else
{
if(ch==1)
{
try
{
DesignationManager designationManager=DesignationManager.getDesignationManager();
list=designationManager.getDesignations(DesignationInterface.CODE);
for(DesignationInterface designation:list)System.out.printf("Designation code %d ,title %s\n",designation.getCode(),designation.getTitle());
//System.out.println("-------------------------------------"); 
break;
}
catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to choose choice again(Y/N): ");
if(k.equals("Y")==false && k.equals("N")==false)
{
System.out.println("Invalid input");
return;
}
if(k.equals("Y"))
{
continue;
}
else
{
break;
}
}
}
else if(ch==2)
{
try
{
DesignationManager designationManager=DesignationManager.getDesignationManager();
list=designationManager.getDesignations(DesignationInterface.TITLE);
for(DesignationInterface designation:list)System.out.printf("Designation code %d ,title %s\n",designation.getCode(),designation.getTitle());
//System.out.println("-------------------------------------"); 
break;
}
catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
}
}else if(ch==3)
{
return;
}
}
}
}
}
public void search()
{
while(true)
{
System.out.println("1. Get designation by code: ");
System.out.println("2. Get designation by title: ");
System.out.println("3. Exit");
int ch=Keyboard.getInt("Enter your choice :");
while(true)
{
DesignationInterface designation;
if(ch<1||ch>3)
{
System.out.println("Invalid input");
return;
}
else
{
if(ch==1)
{
try{
DesignationManager designationManager=DesignationManager.getDesignationManager();
designation=designationManager.getByCode(Keyboard.getInt("Enter designation code:"));
System.out.printf("Designation code %d ,title %s\n",designation.getCode(),designation.getTitle());
//System.out.println("-------------------------------------");
break;
}catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to choose choice again(Y/N): ");
if(k.equals("Y")==false && k.equals("N")==false)
{
System.out.println("Invalid input");
return;
}
if(k.equals("Y"))
{
continue;
}
else
{
break;
}
}
}
else if(ch==2)
{
try{
DesignationManager designationManager=DesignationManager.getDesignationManager();
designation=designationManager.getByTitle(Keyboard.getString("Enter designation title:"));
System.out.printf("Designation code %d ,title %s\n",designation.getCode(),designation.getTitle());
//System.out.println("-------------------------------------");
break;
}catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to choose choice again(Y/N): ");
if(k.equals("Y")==false && k.equals("N")==false)
{
System.out.println("Invalid input");
return;
}
if(k.equals("Y"))
{
continue;
}
else
{
break;
}
}
}
else if(ch==3)
{
return;
}
}
}
}//while ends
}
public void delete()
{
while(true)
{
try
{
DesignationManager designationManager;
designationManager=DesignationManager.getDesignationManager();
designationManager.delete(Keyboard.getInt("Enter code to delete designation: "));
System.out.println("SuccessFully deleted");
//System.out.println("-------------------------------------");
break;
}
catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to type code again to delete(Y/N): ");
if(k.equals("Y")==false && k.equals("N")==false)
{
System.out.println("Invalid input");
return;
}
if(k.equals("Y"))
{
continue;
}
else
{
break;
}
}
}
}
void checkExistance()
{
while(true)
{
System.out.println("1. Code exists");
System.out.println("2. Title exists");
System.out.println("3. Exit");
int ch;
boolean exist;
ch=Keyboard.getInt("Enter your choice :");
if(ch<1||ch>3)
{
System.out.println("Invalid Input");
return;
}
while(true)
{
DesignationManager designationManager=DesignationManager.getDesignationManager();
if(ch==1)
{
try
{
exist=designationManager.codeExists(Keyboard.getInt("Enter code to check its existance: "));
System.out.println("Code exist: "+exist);
break;
}catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to type again(Y/N): ");
if(k.equals("Y")==false && k.equals("N")==false)
{
System.out.println("Invalid input");
return;
}
if(k.equals("Y"))continue;
else break;
}//catch ends here
}
else if(ch==2)
{
try
{
exist=designationManager.titleExists(Keyboard.getString("Enter title to check its existance: "));
System.out.println("Title exists: "+exist);
break;
}catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to type again(Y/N): ");
if(k.equals("Y")==false && k.equals("N")==false)
{
System.out.println("Invalid input");
return;
}
if(k.equals("Y"))continue;
else break;
}//catch ends here
}//else if ends here
else if(ch==3)
{
return;
}//else if ends here
}
}
}
}