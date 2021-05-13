package com.thinking.machines.hr.pl.ui;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.common.*;
import java.util.*;
import java.text.*;
import java.math.*;
public class EmployeeUI
{
public void add()
{
String vEmployeeId;
String vName;
int vDesignationCode;
Date vDateOfBirth;
BigDecimal vBasicSalary;
boolean vIsIndian;
String vGender;
String vPanNumber;
String vAadharCardNumber;
DesignationInterface designation;
EmployeeInterface employee;
EmployeeManager employeeManager;
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
while(true)
{
vName=Keyboard.getString("Enter name: ");
vDesignationCode=Keyboard.getInt("Enter designation code: ");
try{
designation=DesignationManager.getDesignationManager().getByCode(vDesignationCode);
}catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to add again(Y/N): ");
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
vBasicSalary=new BigDecimal(Keyboard.getString("Enter basic salary: "));
try
{
vDateOfBirth=sdf.parse(Keyboard.getString("Enter dateOfBirth dd/MM/yyyy: "));
}catch(ParseException parseException)
{
vDateOfBirth=null;
System.out.println(parseException);
}
vGender=Keyboard.getString("Enter gender(M/F): ");
if(vGender.equals("M")==false && vGender.equals("F")==false)
{
System.out.println("Invalid input");
return;
}
String i=Keyboard.getString("Is the employee an Indian resident(Y/N): ");
if(i.equals("Y")==false && i.equals("N")==false)
{
System.out.println("Invalid input");
return;
}
vIsIndian=i.equals("Y");
vPanNumber=Keyboard.getString("Enter PAN Number: ");
vAadharCardNumber=Keyboard.getString("Enter Aadhar Card Number: ");
employee=new Employee();
employee.setName(vName);
employee.setDesignation(designation);
employee.setBasicSalary(vBasicSalary);
employee.setDateOfBirth(vDateOfBirth);
if(vGender.equals("M"))employee.setGender(EmployeeInterface.MALE);
else employee.setGender(EmployeeInterface.FEMALE);
employee.isIndian(vIsIndian);
employee.setPANNumber(vPanNumber);
employee.setAadharCardNumber(vAadharCardNumber);
try
{
employeeManager=EmployeeManager.getEmployeeManager();
employeeManager.add(employee);
System.out.println("SuccessFully added with employeeId: "+employee.getEmployeeId());
break;
}catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to add title again(Y/N): ");
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
String vEmployeeId;
String vName;
int vDesignationCode;
Date vDateOfBirth;
BigDecimal vBasicSalary;
boolean vIsIndian;
String vGender;
String vPanNumber;
String vAadharCardNumber;
DesignationInterface designation;
EmployeeInterface employee;
EmployeeManager employeeManager;
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
while(true)
{
vEmployeeId=Keyboard.getString("Enter employeeId: ");
vName=Keyboard.getString("Enter name: ");
vDesignationCode=Keyboard.getInt("Enter designation code: ");
try{
designation=DesignationManager.getDesignationManager().getByCode(vDesignationCode);
}catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to add again(Y/N): ");
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
vBasicSalary=new BigDecimal(Keyboard.getString("Enter basic salary: "));
try
{
vDateOfBirth=sdf.parse(Keyboard.getString("Enter dateOfBirth dd/MM/yyyy: "));
}catch(ParseException parseException)
{
vDateOfBirth=null;
System.out.println(parseException);
}
vGender=Keyboard.getString("Enter gender(M/F): ");
if(vGender.equals("M")==false && vGender.equals("F")==false)
{
System.out.println("Invalid input");
return;
}
String i=Keyboard.getString("Is the employee an Indian resident(Y/N): ");
if(i.equals("Y")==false && i.equals("N")==false)
{
System.out.println("Invalid input");
return;
}
vIsIndian=i.equals("Y");
vPanNumber=Keyboard.getString("Enter PAN Number: ");
vAadharCardNumber=Keyboard.getString("Enter Aadhar Card Number: ");
employee=new Employee();
employee.setEmployeeId(vEmployeeId);
employee.setName(vName);
employee.setDesignation(designation);
employee.setBasicSalary(vBasicSalary);
employee.setDateOfBirth(vDateOfBirth);
if(vGender.equals("M"))employee.setGender(EmployeeInterface.MALE);
else employee.setGender(EmployeeInterface.FEMALE);
employee.isIndian(vIsIndian);
employee.setPANNumber(vPanNumber);
employee.setAadharCardNumber(vAadharCardNumber);
try
{
employeeManager=EmployeeManager.getEmployeeManager();
employeeManager.update(employee);
System.out.println("SuccessFully updated...");
break;
}catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to add title again(Y/N): ");
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
public void delete()
{
String vEmployeeId;
EmployeeManager employeeManager;
while(true)
{
vEmployeeId=Keyboard.getString("Enter employeeId: ");
try{
employeeManager=EmployeeManager.getEmployeeManager();
employeeManager.delete(vEmployeeId);
System.out.println("Successfully deleted...");
break;
}catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to enter employeeId again(Y/N): ");
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
public void search()
{
int ch;
while(true)//while outer starts here
{
System.out.println("1. Search by Employee Id: ");
System.out.println("2. Search by PAN Card Number: ");
System.out.println("3. Search by Aadhar Card Number: ");
System.out.println("4. Exit");
while(true)//while inner
{
ch=Keyboard.getInt("Enter your choice: ");
if(ch<1||ch>4)
{
System.out.println("Invalid input");
return;
}
else if(ch==1)//else ch==1 starts here
{
try
{
EmployeeInterface employee=EmployeeManager.getEmployeeManager().getByEmployeeId(Keyboard.getString("Enter employee id: "));
System.out.println("EmployeeId: "+employee.getEmployeeId());
System.out.println("Name: "+employee.getName());
DesignationInterface designation=employee.getDesignation();
System.out.printf("Designation code %d,title %s\n",designation.getCode(),designation.getTitle());
System.out.println("BasicSalary: "+employee.getBasicSalary().toPlainString());
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
System.out.println("DateOfBirth :"+sdf.format(employee.getDateOfBirth()));
System.out.println("IsIndian: "+employee.isIndian());
System.out.println("Gender: "+employee.getGender());
System.out.println("AadharCardNumber: "+employee.getAadharCardNumber());
System.out.println("PanCardNumber: "+employee.getPANNumber());
break;
}catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to enter again(Y/N): ");
if(k.equals("Y")==false && k.equals("N")==false)
{
System.out.println("Invalid input");
return;
}
if(k.equals("Y"))continue;
else break;
}
}else if(ch==2)
{
try
{
EmployeeInterface employee=EmployeeManager.getEmployeeManager().getByPANNumber(Keyboard.getString("Enter PAN Card Number: "));
System.out.println("EmployeeId: "+employee.getEmployeeId());
System.out.println("Name: "+employee.getName());
DesignationInterface designation=employee.getDesignation();
System.out.printf("Designation code %d,title %s\n",designation.getCode(),designation.getTitle());
System.out.println("BasicSalary: "+employee.getBasicSalary().toPlainString());
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
System.out.println("DateOfBirth :"+sdf.format(employee.getDateOfBirth()));
System.out.println("IsIndian: "+employee.isIndian());
System.out.println("Gender: "+employee.getGender());
System.out.println("AadharCardNumber: "+employee.getAadharCardNumber());
System.out.println("PanCardNumber: "+employee.getPANNumber());
break;
}catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to enter again(Y/N): ");
if(k.equals("Y")==false && k.equals("N")==false)
{
System.out.println("Invalid input");
return;
}
if(k.equals("Y"))continue;
else break;
}
}
else if(ch==3)
{
try
{
EmployeeInterface employee=EmployeeManager.getEmployeeManager().getByAadharCardNumber(Keyboard.getString("Enter Aadhar Card Number: "));
System.out.println("EmployeeId: "+employee.getEmployeeId());
System.out.println("Name: "+employee.getName());
DesignationInterface designation=employee.getDesignation();
System.out.printf("Designation code %d,title %s\n",designation.getCode(),designation.getTitle());
System.out.println("BasicSalary: "+employee.getBasicSalary().toPlainString());
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
System.out.println("DateOfBirth :"+sdf.format(employee.getDateOfBirth()));
System.out.println("IsIndian: "+employee.isIndian());
System.out.println("Gender: "+employee.getGender());
System.out.println("AadharCardNumber: "+employee.getAadharCardNumber());
System.out.println("PanCardNumber: "+employee.getPANNumber());
break;
}catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to enter again(Y/N): ");
if(k.equals("Y")==false && k.equals("N")==false)
{
System.out.println("Invalid input");
return;
}
if(k.equals("Y"))continue;
else break;
}
}
else if(ch==4)
{
return;
}//ch==4
}//while inner ends here
}//while outer ends here
}
public void display()
{
int ch;
while(true)//while outer starts here
{
System.out.println("1. Employee Id wise order: ");
System.out.println("2. Employee Name wise order: ");
System.out.println("3. Exit");
while(true)//while inner
{
ch=Keyboard.getInt("Enter your choice: ");
if(ch<1||ch>3)
{
System.out.println("Invalid input");
return;
}
else if(ch==1)//else ch==1 starts here
{
try
{
List<EmployeeInterface> employees=EmployeeManager.getEmployeeManager().getAll(EmployeeInterface.CODE);
for(EmployeeInterface employee:employees)
{
System.out.println("EmployeeId: "+employee.getEmployeeId());
System.out.println("Name: "+employee.getName());
DesignationInterface designation=employee.getDesignation();
System.out.printf("Designation code %d,title %s\n",designation.getCode(),designation.getTitle());
System.out.println("BasicSalary: "+employee.getBasicSalary().toPlainString());
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
System.out.println("DateOfBirth :"+sdf.format(employee.getDateOfBirth()));
System.out.println("IsIndian: "+employee.isIndian());
System.out.println("Gender: "+employee.getGender());
System.out.println("AadharCardNumber: "+employee.getAadharCardNumber());
System.out.println("PanCardNumber: "+employee.getPANNumber());
System.out.println("-----------------------------------------------");
}
break;
}catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to enter again(Y/N): ");
if(k.equals("Y")==false && k.equals("N")==false)
{
System.out.println("Invalid input");
return;
}
if(k.equals("Y"))continue;
else break;
}
}else if(ch==2)
{
try
{
List<EmployeeInterface> employees=EmployeeManager.getEmployeeManager().getAll(EmployeeInterface.NAME);
for(EmployeeInterface employee:employees)
{
System.out.println("EmployeeId: "+employee.getEmployeeId());
System.out.println("Name: "+employee.getName());
DesignationInterface designation=employee.getDesignation();
System.out.printf("Designation code %d,title %s\n",designation.getCode(),designation.getTitle());
System.out.println("BasicSalary: "+employee.getBasicSalary().toPlainString());
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
System.out.println("DateOfBirth :"+sdf.format(employee.getDateOfBirth()));
System.out.println("IsIndian: "+employee.isIndian());
System.out.println("Gender: "+employee.getGender());
System.out.println("AadharCardNumber: "+employee.getAadharCardNumber());
System.out.println("PanCardNumber: "+employee.getPANNumber());
System.out.println("-----------------------------------------------");
}
break;
}catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to enter again(Y/N): ");
if(k.equals("Y")==false && k.equals("N")==false)
{
System.out.println("Invalid input");
return;
}
if(k.equals("Y"))continue;
else break;
}
}
else if(ch==3)
{
return;
}
}//while inner ends here
}//while outer ends here
}
public void checkExistance()
{
int ch;
while(true)//while outer starts here
{
System.out.println("1. Employee Id exists: ");
System.out.println("2. PAN Card Number exists: ");
System.out.println("3. Aadhar Card Number exists: ");
System.out.println("4. Is Designation Alloted: ");
System.out.println("5. Exit");
while(true)//while inner
{
ch=Keyboard.getInt("Enter your choice: ");
if(ch<1||ch>5)
{
System.out.println("Invalid input");
return;
}
else if(ch==1)//else ch==1 starts here
{
try
{
boolean exist=EmployeeManager.getEmployeeManager().employeeIdExists(Keyboard.getString("Enter employee id: "));
System.out.println("Employee Id exists: "+exist);
break;
}catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to enter again(Y/N): ");
if(k.equals("Y")==false && k.equals("N")==false)
{
System.out.println("Invalid input");
return;
}
if(k.equals("Y"))continue;
else break;
}
}else if(ch==2)
{
try
{
boolean exist=EmployeeManager.getEmployeeManager().panNumberExists(Keyboard.getString("Enter PAN Card Number: "));
System.out.println("PAN Card Number: "+exist);
break;
}catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to enter again(Y/N): ");
if(k.equals("Y")==false && k.equals("N")==false)
{
System.out.println("Invalid input");
return;
}
if(k.equals("Y"))continue;
else break;
}
}
else if(ch==3)
{
try
{
boolean exist=EmployeeManager.getEmployeeManager().aadharCardNumberExists(Keyboard.getString("Enter Aadhar Card Number: "));
System.out.println("Aadhar Card Number exists: "+exist);
break;
}catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to enter again(Y/N): ");
if(k.equals("Y")==false && k.equals("N")==false)
{
System.out.println("Invalid input");
return;
}
if(k.equals("Y"))continue;
else break;
}
}
else if(ch==4)
{
try
{
boolean exist=EmployeeManager.getEmployeeManager().isDesignationAlloted(Keyboard.getInt("Enter Designation Code: "));
System.out.println("Is Designation Alloted: "+exist);
break;
}catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to enter again(Y/N): ");
if(k.equals("Y")==false && k.equals("N")==false)
{
System.out.println("Invalid input");
return;
}
if(k.equals("Y"))continue;
else break;
}
}
else if(ch==5)
{
return;
}
}//while inner ends here
}//while outer ends here
}
public void countOfEmployees()
{
try
{
int count=EmployeeManager.getEmployeeManager().getCount();
System.out.println("Count of employees: "+count);
}catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
}
}
public void countOfEmployeesWithSameDesignation()
{
while(true)
{
try
{
int count=EmployeeManager.getEmployeeManager().countOfEmployeesWithSameDesignation(Keyboard.getInt("Enter Designation Code: "));
System.out.println("Count of employees with same designation: "+count);
break;
}catch(BLException blException)
{
List<String> exceptions=blException.getExceptions();
for(String g:exceptions)System.out.println(g);
String k=Keyboard.getString("Do you want to enter again(Y/N): ");
if(k.equals("Y")==false && k.equals("N")==false)
{
System.out.println("Invalid input");
return;
}
if(k.equals("Y"))continue;
else break;
}//catch ends here
}//while here
}
}