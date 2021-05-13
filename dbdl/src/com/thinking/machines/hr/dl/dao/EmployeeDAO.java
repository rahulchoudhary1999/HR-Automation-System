package com.thinking.machines.hr.dl.dao;
import com.thinking.machines.hr.dl.interfaces.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dto.*;
import java.io.*;
import java.util.*;
import java.util.Date;
import java.text.*;
import java.math.*;
import java.sql.*;
public class EmployeeDAO implements EmployeeDAOInterface
{
public void add(EmployeeDTOInterface employeeDTO) throws DAOException
{
String name=employeeDTO.getName();
int designationCode=employeeDTO.getDesignationCode();
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
String dateOfBirth=simpleDateFormat.format(employeeDTO.getDateOfBirth());
BigDecimal basicSalary=employeeDTO.getBasicSalary();
String g=employeeDTO.getGender();
String gender;
if(g.equals("Male"))gender="M";
else gender="F";
boolean isIndian=employeeDTO.isIndian();
String panNumber=employeeDTO.getPANNumber();
String aadharCardNumber=employeeDTO.getAadharCardNumber();
try
{
Connection connection;
connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from designation where code=?");
preparedStatement.setInt(1,designationCode);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid designation code :"+designationCode);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select * from employee where pan_number=?");
preparedStatement.setString(1,panNumber);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Pan Number already exists :"+panNumber);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select * from employee where aadhar_card_number=?");
preparedStatement.setString(1,aadharCardNumber);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Aadhar Card Number already exists :"+aadharCardNumber);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into employee(name,designation_code,date_of_birth,basic_salary,gender,is_indian,pan_number,aadhar_card_number) values(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,name);
preparedStatement.setInt(2,designationCode);
preparedStatement.setString(3,dateOfBirth);
preparedStatement.setBigDecimal(4,basicSalary);
preparedStatement.setString(5,gender);
preparedStatement.setBoolean(6,isIndian);
preparedStatement.setString(7,panNumber);
preparedStatement.setString(8,aadharCardNumber);
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int employeeId=resultSet.getInt(1)+100000;
resultSet.close();
preparedStatement.close();
connection.close();
String newEmployeeId="EMP"+String.valueOf(employeeId);
employeeDTO.setEmployeeId(newEmployeeId);
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public void update(EmployeeDTOInterface employeeDTO) throws DAOException
{
try
{
String gg=employeeDTO.getEmployeeId();
if(gg.length()<=3)throw new DAOException("Invalid Employee Id :"+gg);
int employeeIdNumericPart=Integer.parseInt(gg.substring(3));
if(employeeIdNumericPart<=100000)throw new DAOException("Invalid Employee Id :"+gg);
employeeIdNumericPart=employeeIdNumericPart-100000;
String name=employeeDTO.getName();
int designationCode=employeeDTO.getDesignationCode();
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
String dateOfBirth=simpleDateFormat.format(employeeDTO.getDateOfBirth());
BigDecimal basicSalary=employeeDTO.getBasicSalary();
String gender;
if(employeeDTO.getGender().equals("Male"))gender="M";
else gender="F";
boolean isIndian=employeeDTO.isIndian();
String panNumber=employeeDTO.getPANNumber();
String aadharCardNumber=employeeDTO.getAadharCardNumber();
Connection connection;
connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from designation where code=?");
preparedStatement.setInt(1,designationCode);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid designation code :"+designationCode);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select * from employee where employee_id=?");
preparedStatement.setInt(1,employeeIdNumericPart);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Employee Id: "+gg);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select * from employee where pan_number=? and employee_id!=?");
preparedStatement.setString(1,panNumber);
preparedStatement.setInt(2,employeeIdNumericPart);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("PAN Number already exists :"+panNumber);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select * from employee where aadhar_card_number=? and employee_id!=?");
preparedStatement.setString(1,aadharCardNumber);
preparedStatement.setInt(2,employeeIdNumericPart);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Aadhar Card Number already exists :"+aadharCardNumber);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("update employee set name=?,designation_code=?,date_of_birth=?,basic_salary=?,gender=?,is_indian=?,pan_number=?,aadhar_card_number=? where employee_id=?");
preparedStatement.setString(1,name);
preparedStatement.setInt(2,designationCode);
preparedStatement.setString(3,dateOfBirth);
preparedStatement.setBigDecimal(4,basicSalary);
preparedStatement.setString(5,gender);
preparedStatement.setBoolean(6,isIndian);
preparedStatement.setString(7,panNumber);
preparedStatement.setString(8,aadharCardNumber);
preparedStatement.setInt(9,employeeIdNumericPart);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public void delete(String employeeId) throws DAOException
{
try
{
if(employeeId.length()<=3)throw new DAOException("Invalid Employee Id: "+employeeId);
int employeeIdNumericPart=Integer.parseInt(employeeId.substring(3));
if(employeeIdNumericPart<=100000)throw new DAOException("Invalid Employee Id: "+employeeId);
employeeIdNumericPart=employeeIdNumericPart-100000;
Connection connection;
connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from employee where employee_id=?");
preparedStatement.setInt(1,employeeIdNumericPart);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Employee Id: "+employeeId);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("delete from employee where employee_id=?");
preparedStatement.setInt(1,employeeIdNumericPart);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public int getCount() throws DAOException
{
int count=0;
try
{
Connection connection;
connection=DAOConnection.getConnection();
Statement statement;
statement=connection.createStatement();
ResultSet resultSet;
resultSet=statement.executeQuery("select count(*) as count from employee");
resultSet.next();
count=resultSet.getInt("count");
resultSet.close();
statement.close();
connection.close();
return count;
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public List<EmployeeDTOInterface> getAll() throws DAOException
{
List<EmployeeDTOInterface> employees=new LinkedList<>();
try
{
Connection connection;
connection=DAOConnection.getConnection();
Statement statement;
statement=connection.createStatement();
ResultSet resultSet;
resultSet=statement.executeQuery("select * from employee");
int code;
String name;
int designationCode;
Date dateOfBirth;
BigDecimal basicSalary;
String gender;
boolean isIndian;
String panNumber;
String aadharCardNumber;
String employeeId;
EmployeeDTOInterface employee;
while(resultSet.next())
{
code=resultSet.getInt(1)+100000;
employeeId="EMP"+String.valueOf(code);
name=resultSet.getString(2);
designationCode=resultSet.getInt(3);
dateOfBirth=resultSet.getDate(4);
basicSalary=resultSet.getBigDecimal(5);
gender=resultSet.getString(6);
isIndian=resultSet.getBoolean(7);
panNumber=resultSet.getString(8);
aadharCardNumber=resultSet.getString(9);
employee=new EmployeeDTO();
employee.setEmployeeId(employeeId);
employee.setName(name);
employee.setDesignationCode(designationCode);
employee.setDateOfBirth(dateOfBirth);
employee.setBasicSalary(basicSalary);
if(gender.equals("M"))
{
employee.setGender(EmployeeDTOInterface.MALE);
}
else
{
employee.setGender(EmployeeDTOInterface.FEMALE);
}
employee.isIndian(isIndian);
employee.setPANNumber(panNumber);
employee.setAadharCardNumber(aadharCardNumber);
employees.add(employee);
}
resultSet.close();
statement.close();
connection.close();
return employees;
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public EmployeeDTOInterface getByEmployeeId(String employeeId) throws DAOException
{
try
{
if(employeeId.length()<=3)throw new DAOException("Invalid Employee Id :"+employeeId);
int employeeIdNumericPart=Integer.parseInt(employeeId.substring(3));
if(employeeIdNumericPart<=100000)throw new DAOException("Invalid Employee Id :"+employeeId);
employeeIdNumericPart=employeeIdNumericPart-100000;
Connection connection;
connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from employee where employee_id=?");
preparedStatement.setInt(1,employeeIdNumericPart);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Employee Id :"+employeeId);
}
EmployeeDTOInterface employeeDTO;
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(employeeId);
employeeDTO.setName(resultSet.getString(2));
employeeDTO.setDesignationCode(resultSet.getInt(3));
employeeDTO.setDateOfBirth(resultSet.getDate(4));
employeeDTO.setBasicSalary(resultSet.getBigDecimal(5));
String gender=resultSet.getString(6);
if(gender.equals("M"))employeeDTO.setGender(EmployeeDTOInterface.MALE);
else employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
employeeDTO.isIndian(resultSet.getBoolean(7));
employeeDTO.setPANNumber(resultSet.getString(8));
employeeDTO.setAadharCardNumber(resultSet.getString(9));
resultSet.close();
preparedStatement.close();
connection.close();
return employeeDTO;
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public EmployeeDTOInterface getByPANNumber(String panNumber) throws DAOException
{
try
{
Connection connection;
connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from employee where pan_number=?");
preparedStatement.setString(1,panNumber);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid PAN Number :"+panNumber);
}
EmployeeDTOInterface employeeDTO;
employeeDTO=new EmployeeDTO();
String employeeId="EMP"+String.valueOf(resultSet.getInt(1)+100000);
employeeDTO.setEmployeeId(employeeId);
employeeDTO.setName(resultSet.getString(2));
employeeDTO.setDesignationCode(resultSet.getInt(3));
employeeDTO.setDateOfBirth(resultSet.getDate(4));
employeeDTO.setBasicSalary(resultSet.getBigDecimal(5));
String gender=resultSet.getString(6);
if(gender.equals("M"))employeeDTO.setGender(EmployeeDTOInterface.MALE);
else employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
employeeDTO.isIndian(resultSet.getBoolean(7));
employeeDTO.setPANNumber(resultSet.getString(8));
employeeDTO.setAadharCardNumber(resultSet.getString(9));
resultSet.close();
preparedStatement.close();
connection.close();
return employeeDTO;
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public EmployeeDTOInterface getByAadharCardNumber(String aadharCardNumber) throws DAOException
{
try
{
Connection connection;
connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from employee where aadhar_card_number=?");
preparedStatement.setString(1,aadharCardNumber);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid Aadhar Card Number :"+aadharCardNumber);
}
EmployeeDTOInterface employeeDTO;
employeeDTO=new EmployeeDTO();
String employeeId="EMP"+String.valueOf(resultSet.getInt(1)+100000);
employeeDTO.setEmployeeId(employeeId);
employeeDTO.setName(resultSet.getString(2));
employeeDTO.setDesignationCode(resultSet.getInt(3));
employeeDTO.setDateOfBirth(resultSet.getDate(4));
employeeDTO.setBasicSalary(resultSet.getBigDecimal(5));
String gender=resultSet.getString(6);
if(gender.equals("M"))employeeDTO.setGender(EmployeeDTOInterface.MALE);
else employeeDTO.setGender(EmployeeDTOInterface.FEMALE);
employeeDTO.isIndian(resultSet.getBoolean(7));
employeeDTO.setPANNumber(resultSet.getString(8));
employeeDTO.setAadharCardNumber(resultSet.getString(9));
resultSet.close();
preparedStatement.close();
connection.close();
return employeeDTO;
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public boolean employeeIdExists(String employeeId) throws DAOException
{
try
{
if(employeeId.length()<=3)return false;
int employeeIdNumericPart=Integer.parseInt(employeeId.substring(3));
if(employeeIdNumericPart<=100000)return false;
employeeIdNumericPart=employeeIdNumericPart-100000;
Connection connection;
connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from employee where employee_id=?");
preparedStatement.setInt(1,employeeIdNumericPart);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
return true;
}
resultSet.close();
preparedStatement.close();
connection.close();
return false;
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public boolean panNumberExists(String panNumber) throws DAOException
{
try
{
Connection connection;
connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from employee where pan_number=?");
preparedStatement.setString(1,panNumber);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
return true;
}
resultSet.close();
preparedStatement.close();
connection.close();
return false;
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException
{
try
{
Connection connection;
connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from employee where aadhar_card_number=?");
preparedStatement.setString(1,aadharCardNumber);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
return true;
}
resultSet.close();
preparedStatement.close();
connection.close();
return false;
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public boolean designationCodeExists(int designationCode) throws DAOException
{
try
{
Connection connection;
connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from employee where designation_code=?");
preparedStatement.setInt(1,designationCode);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
return true;
}
resultSet.close();
preparedStatement.close();
connection.close();
return false;
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
}