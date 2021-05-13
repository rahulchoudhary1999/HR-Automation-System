package com.thinking.machines.hr.bl.interfaces;
import java.util.*;//Date ke liye
import java.math.*;//BigDecimal ke liye
public interface EmployeeInterface extends java.io.Serializable,Comparable<EmployeeInterface>
{
public enum GENDER{MALE,FEMALE};
public static final GENDER MALE=GENDER.MALE;
public static final GENDER FEMALE=GENDER.FEMALE;
public enum ATTRIBUTES{CODE,NAME};
final static public ATTRIBUTES CODE=ATTRIBUTES.CODE;
final static public ATTRIBUTES NAME=ATTRIBUTES.NAME;
public void setEmployeeId(String employeeId);
public String getEmployeeId();
public void setName(String name);
public String getName();
public void setDesignation(DesignationInterface designation);
public DesignationInterface getDesignation();
public void setDateOfBirth(Date dateOfBirth);
public Date getDateOfBirth();
public void setBasicSalary(BigDecimal basicSalary);
public BigDecimal getBasicSalary();
public void isIndian(boolean isIndian);
public boolean isIndian();
public void setGender(GENDER gender);
public String getGender();
public void setPANNumber(String panNumber);
public String getPANNumber();
public void setAadharCardNumber(String aadharCardNumber);
public String getAadharCardNumber();
}
