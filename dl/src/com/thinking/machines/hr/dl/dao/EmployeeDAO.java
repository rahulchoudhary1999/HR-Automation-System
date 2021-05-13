package com.thinking.machines.hr.dl.dao;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dto.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
public class EmployeeDAO implements EmployeeDAOInterface
{
public void add(EmployeeDTOInterface employeeDTO) throws DAOException
{
String vEmployeeId;
String vName=employeeDTO.getName().trim();
int vDesignationCode=employeeDTO.getDesignationCode();
Date vDateOfBirth=employeeDTO.getDateOfBirth();
BigDecimal vBasicSalary=employeeDTO.getBasicSalary();
boolean vIsIndian=employeeDTO.isIndian();
String vGender=employeeDTO.getGender();//trim nhi kiya kuki enum ka use krwaya tha toh hamne he values rakhwayi thi bina spaces ke
String vPanNumber=employeeDTO.getPANNumber().trim();
String vAadharCardNumber=employeeDTO.getAadharCardNumber().trim();
boolean designationCodeExists=new DesignationDAO().codeExists(vDesignationCode);
if(designationCodeExists==false)throw new DAOException("Invalid Designation Code :"+vDesignationCode);
try
{
File file=new File(EMPLOYEE_DATA_FILE);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
String newEmployeeId;
int lastGeneratedEmployeeId=100000;
int count=0;
if(randomAccessFile.length()==0)
{
randomAccessFile.writeBytes(String.format("%-10d",lastGeneratedEmployeeId));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.format("%-10d",count));
randomAccessFile.writeBytes("\n");
randomAccessFile.seek(0);
}
lastGeneratedEmployeeId=Integer.parseInt(randomAccessFile.readLine().trim());
count=Integer.parseInt(randomAccessFile.readLine().trim());
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
if(count==0)
{
lastGeneratedEmployeeId++;
count++;
newEmployeeId="EMP"+String.format("%d",lastGeneratedEmployeeId);
randomAccessFile.writeBytes(newEmployeeId);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vName);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vDesignationCode));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(simpleDateFormat.format(vDateOfBirth));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vBasicSalary.toPlainString());
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vIsIndian));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vGender);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vPanNumber);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vAadharCardNumber);
randomAccessFile.writeBytes("\n");
randomAccessFile.seek(0);
randomAccessFile.writeBytes(String.format("%-10d",lastGeneratedEmployeeId));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.format("%-10d",count));
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
employeeDTO.setEmployeeId(newEmployeeId);
return;
}
String fPanNumber;
String fAadharCardNumber;
boolean foundPanNumber=false;
boolean foundAadharCardNumber=false;
int i;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(i=0;i<7;i++)randomAccessFile.readLine();
fPanNumber=randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(foundPanNumber==false && vPanNumber.equalsIgnoreCase(fPanNumber))
{
foundPanNumber=true;
}
if(foundAadharCardNumber==false && vAadharCardNumber.equalsIgnoreCase(fAadharCardNumber))
{
foundAadharCardNumber=true;
}
if(foundPanNumber==true && foundAadharCardNumber==true)break;
}
if(foundPanNumber==true && foundAadharCardNumber==true)
{
randomAccessFile.close();
throw new DAOException("PAN Number :"+vPanNumber+"and Aadhar Card Number :"+vAadharCardNumber+"exists");
}
if(foundPanNumber==true && foundAadharCardNumber==false)
{
randomAccessFile.close();
throw new DAOException("Aadhar Card Number:"+vAadharCardNumber+"exists");
}
if(foundPanNumber==false && foundAadharCardNumber==true)
{
randomAccessFile.close();
throw new DAOException("PAN Number:"+vPanNumber+"exists");
}
lastGeneratedEmployeeId++;
count++;
newEmployeeId="EMP"+String.format("%d",lastGeneratedEmployeeId);
randomAccessFile.writeBytes(newEmployeeId);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vName);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vDesignationCode));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(simpleDateFormat.format(vDateOfBirth));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vBasicSalary.toPlainString());
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vIsIndian));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vGender);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vPanNumber);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vAadharCardNumber);
randomAccessFile.writeBytes("\n");
randomAccessFile.seek(0);
randomAccessFile.writeBytes(String.format("%-10d",lastGeneratedEmployeeId));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.format("%-10d",count));
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
employeeDTO.setEmployeeId(newEmployeeId);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public void update(EmployeeDTOInterface employeeDTO) throws DAOException
{
String vEmployeeId=employeeDTO.getEmployeeId();
String vName=employeeDTO.getName();
int vDesignationCode=employeeDTO.getDesignationCode();
boolean designationCodeExists=new DesignationDAO().codeExists(vDesignationCode);
if(!designationCodeExists)throw new DAOException("Invalid Designation Code:"+vDesignationCode);
Date vDateOfBirth=employeeDTO.getDateOfBirth();
BigDecimal vBasicSalary=employeeDTO.getBasicSalary();
boolean vIsIndian=employeeDTO.isIndian();
String vGender=employeeDTO.getGender();
String vPANNumber=employeeDTO.getPANNumber();
String vAadharCardNumber=employeeDTO.getAadharCardNumber();
if(vEmployeeId.length()<=3)
{
throw new DAOException("Invalid Employee Id:"+vEmployeeId);
}
int employeeIdNumericPart;
try
{
employeeIdNumericPart=Integer.parseInt(vEmployeeId.substring(3));
}catch(NumberFormatException numberFormatException)
{
throw new DAOException("Invalid Employee Id:"+vEmployeeId);
}
if(employeeIdNumericPart<=100000)throw new DAOException("Invalid Employee Id:"+vEmployeeId);
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(!file.exists())throw new DAOException("Invalid Employee Id:"+vEmployeeId);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid Employee Id:"+vEmployeeId);
}
int lastGeneratedEmployeeId=Integer.parseInt(randomAccessFile.readLine().trim());
if(employeeIdNumericPart>lastGeneratedEmployeeId)
{
randomAccessFile.close();
throw new DAOException("Invalid Employee Id:"+vEmployeeId);
}
randomAccessFile.readLine();
boolean foundEmployeeId=false;
boolean foundPANNumber=false;
boolean foundAadharCardNumber=false;
String fEmployeeId;
String fPANNumber;
String fAadharCardNumber;
long positionOfRecordToUpdate=0;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
if(!foundEmployeeId)positionOfRecordToUpdate=randomAccessFile.getFilePointer();
fEmployeeId=randomAccessFile.readLine();
for(int i=1;i<=6;i++)randomAccessFile.readLine();
fPANNumber=randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(!foundEmployeeId && vEmployeeId.equals(fEmployeeId))
{
foundEmployeeId=true;
}
if(foundPANNumber==false && fEmployeeId.equals(vEmployeeId)==false && vPANNumber.equalsIgnoreCase(fPANNumber))
{
foundPANNumber=true;
}
if(foundAadharCardNumber==false && fEmployeeId.equals(vEmployeeId)==false && vAadharCardNumber.equalsIgnoreCase(fAadharCardNumber))
{
foundAadharCardNumber=true;
}
if(foundEmployeeId && foundPANNumber && foundAadharCardNumber)break;
}
if(foundEmployeeId==false)
{
randomAccessFile.close();
throw new DAOException("Invalid EmployeeId :"+vEmployeeId);
}
if(foundPANNumber && !foundAadharCardNumber)
{
randomAccessFile.close();
throw new DAOException("PAN Number exists:"+vPANNumber);
}
if(!foundPANNumber && foundAadharCardNumber)
{
randomAccessFile.close();
throw new DAOException("Aadhar Card Number exists:"+vAadharCardNumber);
}
if(foundPANNumber && foundAadharCardNumber)
{
randomAccessFile.close();
throw new DAOException("PAN Number"+vPANNumber+"and Aadhar Card Number exists:"+vAadharCardNumber);
}
randomAccessFile.seek(positionOfRecordToUpdate);
for(int i=1;i<=9;i++)randomAccessFile.readLine();
File tmpFile=new File("tmp.tmp");
tmpFile.delete();
RandomAccessFile tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
}
randomAccessFile.seek(positionOfRecordToUpdate);
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
randomAccessFile.writeBytes(vEmployeeId);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vName);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vDesignationCode));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(simpleDateFormat.format(vDateOfBirth));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vBasicSalary.toPlainString());
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vIsIndian));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vGender);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vPANNumber);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vAadharCardNumber);
randomAccessFile.writeBytes("\n");
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
long currentIndex=randomAccessFile.getFilePointer();
randomAccessFile.setLength(currentIndex);
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
}
public void delete(String employeeId) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false)throw new DAOException("Invalid employee Id:"+employeeId);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid employee Id:"+employeeId);
}
randomAccessFile.readLine();
int count=Integer.parseInt(randomAccessFile.readLine().trim());
String fEmployeeId;
boolean found=false;
long positionOfRecordToDelete=0;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
positionOfRecordToDelete=randomAccessFile.getFilePointer();
fEmployeeId=randomAccessFile.readLine();
for(int i=1;i<=8;i++)randomAccessFile.readLine();
if(fEmployeeId.equals(employeeId))
{
found=true;
break;
}
}
if(found==false)
{
randomAccessFile.close();
throw new DAOException("Invalid employee Id:"+employeeId);
}
File tmpFile=new File("tmp.tmp");
if(tmpFile.exists())tmpFile.delete();
RandomAccessFile tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
}
randomAccessFile.seek(positionOfRecordToDelete);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine());
randomAccessFile.writeBytes("\n");
}
randomAccessFile.setLength(randomAccessFile.getFilePointer());
randomAccessFile.seek(0);
randomAccessFile.readLine();
count--;
randomAccessFile.writeBytes(String.format("%-10d",count));
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
}
public int getCount() throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false)return 0;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return 0;
}
randomAccessFile.readLine();
int count=Integer.parseInt(randomAccessFile.readLine().trim());
randomAccessFile.close();
return count;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public List<EmployeeDTOInterface> getAll() throws DAOException
{
try
{
List<EmployeeDTOInterface> employees=new LinkedList<>();
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false)
{
return employees;
}
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return employees;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
EmployeeDTOInterface employeeDTO;
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(randomAccessFile.readLine());
employeeDTO.setName(randomAccessFile.readLine());
employeeDTO.setDesignationCode(Integer.parseInt(randomAccessFile.readLine()));
try
{
employeeDTO.setDateOfBirth(simpleDateFormat.parse(randomAccessFile.readLine()));
}
catch(ParseException parseException)
{
}
employeeDTO.setBasicSalary(new BigDecimal(randomAccessFile.readLine()));
employeeDTO.isIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
String vGender=randomAccessFile.readLine();
if(vGender.equals("Male"))
{
employeeDTO.setGender(EmployeeDTOInterface.MALE);
}
else
{
employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
}
employeeDTO.setPANNumber(randomAccessFile.readLine());
employeeDTO.setAadharCardNumber(randomAccessFile.readLine());
employees.add(employeeDTO);
}
randomAccessFile.close();
return employees;
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public EmployeeDTOInterface getByEmployeeId(String employeeId) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false)throw new DAOException("Invalid Employee Id"+employeeId);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid Employee Id"+employeeId);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
EmployeeDTOInterface employeeDTO;
String fEmployeeId;
int i;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId=randomAccessFile.readLine();
if(employeeId.equals(fEmployeeId)==false)
{
for(i=1;i<=8;i++)randomAccessFile.readLine();
continue;
}
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(randomAccessFile.readLine());
employeeDTO.setDesignationCode(Integer.parseInt(randomAccessFile.readLine()));
employeeDTO.setDateOfBirth(simpleDateFormat.parse(randomAccessFile.readLine()));
employeeDTO.setBasicSalary(new BigDecimal(randomAccessFile.readLine()));
employeeDTO.isIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
if(randomAccessFile.readLine().equals("Male"))
{
employeeDTO.setGender(EmployeeDTOInterface.MALE);
}
else
{
employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
}
employeeDTO.setPANNumber(randomAccessFile.readLine());
employeeDTO.setAadharCardNumber(randomAccessFile.readLine());
randomAccessFile.close();
return employeeDTO;
}
randomAccessFile.close();
throw new DAOException("Invalid Employee Id"+employeeId);
}catch(IOException ioException)
{
System.out.println(ioException);
}
catch(ParseException parseException)
{
System.out.println("parseException");
}
return null;
}
public EmployeeDTOInterface getByPANNumber(String panNumber) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false)throw new DAOException("Invalid PAN Number"+panNumber);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid PAN Number"+panNumber);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
EmployeeDTOInterface employeeDTO;
String fEmployeeId;
String fName;
int fDesignationCode;
Date fDateOfBirth;
BigDecimal fBasicSalary;
boolean fIsIndian;
String fGender;
String fPanNumber;
String fAadharCardNumber;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fDesignationCode=Integer.parseInt(randomAccessFile.readLine());
fDateOfBirth=simpleDateFormat.parse(randomAccessFile.readLine());
fBasicSalary=new BigDecimal(randomAccessFile.readLine());
fIsIndian=Boolean.parseBoolean(randomAccessFile.readLine());
fGender=randomAccessFile.readLine();
fPanNumber=randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(fPanNumber.equals(panNumber))
{
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(fName);
employeeDTO.setDesignationCode(fDesignationCode);
employeeDTO.setDateOfBirth(fDateOfBirth);
employeeDTO.setBasicSalary(fBasicSalary);
employeeDTO.isIndian(fIsIndian);
if(fGender.equals("Male"))
{
employeeDTO.setGender(EmployeeDTOInterface.MALE);
}
else
{
employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
}
employeeDTO.setPANNumber(fPanNumber);
employeeDTO.setAadharCardNumber(fAadharCardNumber);
randomAccessFile.close();
return employeeDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid Pan Number :"+panNumber);
}catch(IOException ioException)
{
System.out.println(ioException);
}
catch(ParseException parseException)
{
System.out.println("parseException");
}
return null;
}
public EmployeeDTOInterface getByAadharCardNumber(String aadharCardNumber) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false)throw new DAOException("Invalid Aadhar Card Number:"+aadharCardNumber);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid Aadhar Card Number:"+aadharCardNumber);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
EmployeeDTOInterface employeeDTO;
String fEmployeeId;
String fName;
int fDesignationCode;
Date fDateOfBirth;
BigDecimal fBasicSalary;
boolean fIsIndian;
String fGender;
String fPanNumber;
String fAadharCardNumber;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fDesignationCode=Integer.parseInt(randomAccessFile.readLine());
fDateOfBirth=simpleDateFormat.parse(randomAccessFile.readLine());
fBasicSalary=new BigDecimal(randomAccessFile.readLine());
fIsIndian=Boolean.parseBoolean(randomAccessFile.readLine());
fGender=randomAccessFile.readLine();
fPanNumber=randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(fAadharCardNumber.equals(aadharCardNumber))
{
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(fName);
employeeDTO.setDesignationCode(fDesignationCode);
employeeDTO.setDateOfBirth(fDateOfBirth);
employeeDTO.setBasicSalary(fBasicSalary);
employeeDTO.isIndian(fIsIndian);
if(fGender.equals("Male"))
{
employeeDTO.setGender(EmployeeDTOInterface.MALE);
}
else
{
employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
}
employeeDTO.setPANNumber(fPanNumber);
employeeDTO.setAadharCardNumber(fAadharCardNumber);
randomAccessFile.close();
return employeeDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid Aadhar Card Number:"+aadharCardNumber);
}catch(IOException ioException)
{
System.out.println(ioException);
}
catch(ParseException parseException)
{
System.out.println("parseException");
}
return null;
}
public boolean employeeIdExists(String employeeId) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false)return false;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fEmployeeId;
int i;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId=randomAccessFile.readLine();
if(employeeId.equals(fEmployeeId))
{
randomAccessFile.close();
return true;
}
for(i=1;i<=8;i++)randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
return false;
}
public boolean panNumberExists(String panNumber) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false)return false;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fPanNumber;
int i;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(i=1;i<=7;i++)randomAccessFile.readLine();
fPanNumber=randomAccessFile.readLine();
if(fPanNumber.equals(panNumber))
{
randomAccessFile.close();
return true;
}
randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
return false;
}
public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false)return false;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fAadharCardNumber;
int i;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(i=1;i<=8;i++)randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(fAadharCardNumber.equals(aadharCardNumber))
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
return false;
}
public boolean designationCodeExists(int designationCode) throws DAOException
{
try
{
File file=new File(EMPLOYEE_DATA_FILE);
if(file.exists()==false)return false;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fDesignationCode;
int i;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
randomAccessFile.readLine();
fDesignationCode=Integer.parseInt(randomAccessFile.readLine());
if(fDesignationCode==designationCode)
{
randomAccessFile.close();
return true;
}
for(i=1;i<=6;i++)randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
return false;
}
}