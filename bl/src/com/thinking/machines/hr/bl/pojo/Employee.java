package com.thinking.machines.hr.bl.pojo;
import com.thinking.machines.hr.bl.interfaces.*;
import java.util.*;//Date ke liye
import java.math.*;//BigDecimal ke liye
public class Employee implements EmployeeInterface 
{
private String employeeId;
private String name;
private DesignationInterface designation;
private Date dateOfBirth;
private BigDecimal basicSalary;
private boolean isIndian;
private String gender;
private String panNumber;
private String aadharCardNumber;
public Employee()
{
this.employeeId="";
this.name="";
this.designation=null;
this.dateOfBirth=null;
this.basicSalary=null;
this.isIndian=false;
this.gender="";
this.panNumber="";
this.aadharCardNumber="";
}
public void setEmployeeId(String employeeId)
{
this.employeeId=employeeId;
}
public String getEmployeeId()
{
return this.employeeId;
}
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
public void setDesignation(DesignationInterface designation)
{
this.designation=designation;
}
public DesignationInterface getDesignation()
{
return this.designation;
}
public void setDateOfBirth(Date dateOfBirth)
{
this.dateOfBirth=dateOfBirth;
}
public Date getDateOfBirth()
{
return this.dateOfBirth;
}
public void setBasicSalary(BigDecimal basicSalary)
{
this.basicSalary=basicSalary;
}
public BigDecimal getBasicSalary()
{
return this.basicSalary;
}
public void isIndian(boolean isIndian)
{
this.isIndian=isIndian;
}
public boolean isIndian()
{
return this.isIndian;
}
public void setGender(GENDER gender)
{
if(gender==GENDER.MALE)
{
this.gender="Male";
}
else
{
this.gender="Female";
}
}
public String getGender()
{
return this.gender;
}
public void setPANNumber(String panNumber)
{
this.panNumber=panNumber;
}
public String getPANNumber()
{
return this.panNumber;
}
public void setAadharCardNumber(String aadharCardNumber)
{
this.aadharCardNumber=aadharCardNumber;
}
public String getAadharCardNumber()
{
return this.aadharCardNumber;
}
public boolean equals(Object object)
{
if(!(object instanceof EmployeeInterface))return false;
EmployeeInterface other;
other=(EmployeeInterface)object;
return this.employeeId.equalsIgnoreCase(other.getEmployeeId());
}
public int compareTo(EmployeeInterface employee)
{
return this.employeeId.toUpperCase().compareTo(employee.getEmployeeId().toUpperCase());
}
public int hashCode()
{
return this.employeeId.hashCode();
}
}
