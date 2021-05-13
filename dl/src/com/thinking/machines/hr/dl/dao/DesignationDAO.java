package com.thinking.machines.hr.dl.dao;
import java.util.*;
import java.io.*;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
public class DesignationDAO implements DesignationDAOInterface
{
public void add(DesignationDTOInterface designationDTO) throws DAOException
{ 
try
{
File file=new File(DESIGNATION_DATA_FILE);
int vCode=0;
String codeString="";
int count=0;
String countString="";
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.writeBytes("0         ");
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes("0         ");
randomAccessFile.writeBytes("\n");
}
randomAccessFile.seek(0);
int lastGeneratedCode=Integer.parseInt(randomAccessFile.readLine().trim());
count=Integer.parseInt(randomAccessFile.readLine().trim());
String vTitle=designationDTO.getTitle().trim();
String fTitle;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
fTitle=randomAccessFile.readLine();
if(vTitle.equalsIgnoreCase(fTitle))
{
randomAccessFile.close();
throw new DAOException("Designation : "+vTitle+" exists.");
}
}
vCode=lastGeneratedCode+1;
randomAccessFile.writeBytes(String.valueOf(vCode));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(vTitle);
randomAccessFile.writeBytes("\n");
count++;
codeString=String.valueOf(vCode);
while(codeString.length()<10) codeString+=" ";
countString=String.valueOf(count);
while(countString.length()<10) countString+=" ";
randomAccessFile.seek(0);
randomAccessFile.writeBytes(codeString+"\n"+countString+"\n");
randomAccessFile.close();
designationDTO.setCode(vCode);
}catch(IOException ioException)
{ 
throw new DAOException(ioException.getMessage());
}
}
public void update(DesignationDTOInterface designationDTO) throws DAOException
{ 
try
{
int vCode=designationDTO.getCode();
if(vCode<=0)throw new DAOException("Invalid Designation code"+vCode);
String vTitle=designationDTO.getTitle().trim();
if(vTitle==null||vTitle.length()==0)throw new DAOException("Invalid Designation title"+vTitle);
File file=new File(DESIGNATION_DATA_FILE);
if(file.exists()==false) throw new DAOException("Invalid Designation code"+vCode);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid Desgination code"+vCode);
}
int lastGeneratedCode=Integer.parseInt(randomAccessFile.readLine().trim());
if(vCode>lastGeneratedCode)
{
randomAccessFile.close();
throw new DAOException("Invalid Designation code"+vCode);
}
randomAccessFile.readLine();
int fCode;
String fTitle;
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
if(fCode==vCode)
{
found=true;
break;
}
randomAccessFile.readLine();
}
if(found==false)
{
randomAccessFile.close();
throw new DAOException("Invalid Designation code"+vCode);
}
randomAccessFile.seek(0);
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fCode!=vCode && vTitle.equalsIgnoreCase(fTitle))
{
randomAccessFile.close();
throw new DAOException("Invalid Designation title"+vTitle);
}
}
File tmpFile=new File("tmp.tmp");
if(tmpFile.exists())tmpFile.delete();
RandomAccessFile tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
randomAccessFile.seek(0);
if(tmpRandomAccessFile.length()==0)
{
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n"+randomAccessFile.readLine()+"\n");
} 
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
if(vCode!=fCode)
{
tmpRandomAccessFile.writeBytes(fCode+"\n"+randomAccessFile.readLine()+"\n");
}else
{
tmpRandomAccessFile.writeBytes(vCode+"\n"+vTitle+"\n");
randomAccessFile.readLine();
}
}
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
}catch(IOException ioexception)
{
throw new DAOException(ioexception.getMessage());
}
}
public void delete(int code) throws DAOException
{ 
try
{
if(code<=0)throw new DAOException("Invalid Designation code"+code);
File file=new File(DESIGNATION_DATA_FILE);
if(file.exists()==false) throw new DAOException("Invalid Designation code:"+code);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid Desgination code"+code);
}
int lastGeneratedCode=Integer.parseInt(randomAccessFile.readLine().trim());
if(code>lastGeneratedCode) 
{
randomAccessFile.close();
throw new DAOException("Invalid Designation code::"+code);
}
randomAccessFile.readLine();
int fCode;
String fTitle="";
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fCode==code)
{
found=true;
break;
}
}
if(found==false)
{
randomAccessFile.close();
throw new DAOException("Invalid Designation Code:"+code);
}
//yadi designation code ke against employeeId exist krta hai toh 
//designation code ko delete nhi krna hai
//Over here we will be checking if the designation code exists in
//employee.data
//if yes,we won't delete it

if(new EmployeeDAO().designationCodeExists(code)) 
{
throw new DAOException("Employee exists with Designation as"+fTitle);
}
//ye kam upr bhi krskte the lkin hamko check krna tha ki code valid 
//hai ya nhi
File tmpFile=new File("tmp.tmp");
if(tmpFile.exists())tmpFile.delete();
RandomAccessFile tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
randomAccessFile.seek(0);
if(tmpRandomAccessFile.length()==0)
{
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n"+randomAccessFile.readLine()+"\n");
}
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fCode!=code)
{
tmpRandomAccessFile.writeBytes(fCode+"\n"+fTitle+"\n");
}
}
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
int count=Integer.parseInt(tmpRandomAccessFile.readLine().trim());
count--;
String countString=String.valueOf(count);
while(countString.length()<10)countString+=" ";
randomAccessFile.writeBytes(countString+"\n");
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
}catch(IOException ioexception)
{
throw new DAOException(ioexception.getMessage());
}
}
public int getCount() throws DAOException
{ 
int count=0;
try
{
File file=new File(DESIGNATION_DATA_FILE);
if(file.exists()==false)return 0;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return 0;
}
//randomAccessFile.seek(0);
randomAccessFile.readLine();
count=Integer.parseInt(randomAccessFile.readLine().trim());
randomAccessFile.close();
return count;
}catch(IOException ioexception)
{
throw new DAOException(ioexception.getMessage());
}
}
public List<DesignationDTOInterface> getAll() throws DAOException
{
try
{
List<DesignationDTOInterface> designations=new LinkedList<DesignationDTOInterface>();
//List<DesignationDTOInterface> designations=new LinkedList<>();ese bhi likh sakte hai compiler apne aap <> iske bich 
//mai likh dega DesignationDTOInterface or isko diamond operator khete hai gadhe lkin interview wala agr samjane 
//ko bole ki diamond operator ko toh ham bta sakte hai 
File file=new File(DESIGNATION_DATA_FILE);
if(file.exists()==false)return designations;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return designations;
}
//randomAccessFile.seek(0); ye likhne ki jarurat nhi kuki file yadi khuli hai toh woh internal pointer file 
//first byte ho hi point krr raha hoga
randomAccessFile.readLine();
randomAccessFile.readLine();
DesignationDTOInterface designationDTO;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
designationDTO=new DesignationDTO();
designationDTO.setCode(Integer.parseInt(randomAccessFile.readLine()));
designationDTO.setTitle(randomAccessFile.readLine());
designations.add(designationDTO);
}
randomAccessFile.close();
return designations;
}catch(IOException ioexception)
{
throw new DAOException(ioexception.getMessage());
}
}
public DesignationDTOInterface getByCode(int code) throws DAOException
{
//DesignationDTOInterface designationDTO=null;
try
{
if(code<=0)throw new DAOException("Invalid Designation Code:"+code);
File file=new File(DESIGNATION_DATA_FILE);
if(file.exists()==false)
{
//return designationDTO;
throw new DAOException("Invalid Designation code"+code);
}
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
//return designationDTO;
throw new DAOException("Invalid Designation code"+code);
}
//randomAccessFile.seek(0);
int lastGeneratedCode=Integer.parseInt(randomAccessFile.readLine().trim());
if(code>lastGeneratedCode)
{
randomAccessFile.close();
throw new DAOException("Invalid Designation code"+code);
}
randomAccessFile.readLine();
String fTitle;
int fCode;
DesignationDTOInterface designationDTO;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fCode==code)
{
designationDTO=new DesignationDTO();
designationDTO.setCode(fCode);
designationDTO.setTitle(fTitle);
randomAccessFile.close();
return designationDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid Designation Code"+code);
}catch(IOException ioexception)
{
throw new DAOException(ioexception.getMessage());
}
}
public DesignationDTOInterface getByTitle(String title) throws DAOException
{
//DesignationDTOInterface designationDTO=null;
try
{
if(title==null||title.trim().length()==0) throw new DAOException("Invalid Designation Title"+title);
File file=new File(DESIGNATION_DATA_FILE);
if(file.exists()==false)
{
//return designationDTO;
throw new DAOException("Invalid Designation Title"+title);
}
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
//return designationDTO;
throw new DAOException("Invalid Designation Title"+title);
}
//randomAccessFile.seek(0);
randomAccessFile.readLine();
randomAccessFile.readLine();
DesignationDTOInterface designationDTO;
String fTitle;
int fCode;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fTitle.equalsIgnoreCase(title.trim()))
{
designationDTO=new DesignationDTO();
designationDTO.setCode(fCode);
designationDTO.setTitle(fTitle);
randomAccessFile.close();
return designationDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid Designation Title"+title);
}catch(IOException ioexception)
{
throw new DAOException(ioexception.getMessage());
}
}
public boolean codeExists(int code) throws DAOException
{
try
{
if(code<=0) return false;
File file=new File(DESIGNATION_DATA_FILE);
if(file.exists()==false)return false;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
//randomAccessFile.seek(0);
int lastGeneratedCode=Integer.parseInt(randomAccessFile.readLine().trim());
if(code>lastGeneratedCode)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
int vCode;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vCode=Integer.parseInt(randomAccessFile.readLine());
if(vCode==code)
{
randomAccessFile.close();
return true;
}
randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch(IOException ioexception)
{
throw new DAOException(ioexception.getMessage());
}
}
public boolean titleExists(String title) throws DAOException
{
//String vTitle="";
try
{
if(title==null||title.trim().length()==0) return false;
File file=new File(DESIGNATION_DATA_FILE);
if(file.exists()==false)return false;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
//randomAccessFile.seek(0);
randomAccessFile.readLine();
randomAccessFile.readLine();
String vTitle;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
vTitle=randomAccessFile.readLine();
if(vTitle.equalsIgnoreCase(title))
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
return false;
}catch(IOException ioexception)
{
throw new DAOException(ioexception.getMessage());
}
}
}