package com.thinking.machines.hr.pl.model;
import javax.swing.table.AbstractTableModel;
import com.thinking.machines.hr.pl.exceptions.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import java.util.List;
import java.lang.*;
import javax.swing.*;
import com.itextpdf.text.*;
import com.itextpdf.text.Font.*;
import com.itextpdf.text.pdf.*;
import java.io.*;
import java.net.*;
public class DesignationModel extends AbstractTableModel
{
private List<DesignationInterface> plDesignations;
private DesignationManager designationManager;
private String title[];
public DesignationModel()
{
populateDataStructure();
}
void populateDataStructure()
{
title=new String[2];
title[0]="Sr.No";
title[1]="Designation";
designationManager=DesignationManager.getDesignationManager();
try
{
plDesignations=designationManager.getDesignations();
}catch(BLException blException)
{
System.out.println(blException);
}
}
public int getRowCount()
{
return plDesignations.size();
}
public int getColumnCount()
{
return title.length;
}
public String getColumnName(int columnIndex)
{
return title[columnIndex];
}
public Object getValueAt(int rowIndex,int columnIndex)
{
if(columnIndex==0)return (rowIndex+1);
if(columnIndex==1)return plDesignations.get(rowIndex).getTitle();
return null;
}
public boolean isCellEditable(int rowIndex,int coloumnIndex)
{
return false;
}
public Class getColumnClass(int columnIndex)
{
if(columnIndex==0)return Integer.class;
if(columnIndex==1)return String.class;
return null;
}
public DesignationInterface getDesignationAt(int rowIndex) throws ModelException
{
if(rowIndex<0||rowIndex>=plDesignations.size())throw new ModelException("Invalid row index");
return plDesignations.get(rowIndex);
}
public int add(String title)
{
int k=0;
try
{
DesignationInterface designation=new Designation();
designation.setTitle(title);
designationManager.add(designation);
plDesignations=designationManager.getDesignations();
fireTableDataChanged();
DesignationInterface d;
while(k<plDesignations.size())
{
d=plDesignations.get(k);
if(d.getTitle().equals(title.trim()))
{
break;
}
k++;
}
}catch(BLException blException)
{
List<String> list=blException.getExceptions();
for(String i:list)JOptionPane.showMessageDialog(null,i);
}
return k;
}
public DesignationInterface getDesignation(String text,boolean isCaseSensitive,boolean isPartialSearch)throws ModelException
{
DesignationInterface designation;
if(text.trim().length()==0)throw new ModelException("Invalid");
if(isPartialSearch==true)
{
if(isCaseSensitive==true)
{
int i=0;
while(i<plDesignations.size())
{
designation=plDesignations.get(i);
if(designation.getTitle().startsWith(text))
{
return designation;
}
i++;
}
}
else
{
int i=0;
while(i<plDesignations.size())
{
designation=plDesignations.get(i);
if(designation.getTitle().toUpperCase().startsWith(text.toUpperCase()))
{
return designation;
}
i++;
}
}
}
else
{
if(isCaseSensitive==true)
{
int i=0;
while(i<plDesignations.size())
{
designation=plDesignations.get(i);
if(designation.getTitle().equals(text))
{
return designation;
}
i++;
}
}
else
{
int i=0;
while(i<plDesignations.size())
{
designation=plDesignations.get(i);
if(designation.getTitle().equalsIgnoreCase(text))
{
return designation;
}
i++;
}
}
}
throw new ModelException("Designation not found");
}
public int indexOf(DesignationInterface designation)throws ModelException
{
int i=0;
while(i<plDesignations.size())
{
if(plDesignations.get(i).getTitle().equals(designation.getTitle()))
{
return i;
}
i++;
}
throw new ModelException("Invalid designation");
}
public void update(DesignationInterface designation)throws ModelException
{
try{
designationManager.update(designation);
plDesignations=designationManager.getDesignations();
fireTableDataChanged();
}catch(BLException be)
{
List<String> exceptions=be.getExceptions();
String e="";
for(String g:exceptions)
{
e=e+g+"\n";
}
throw new ModelException(e);
}
}
public void delete(int code)throws ModelException
{
try
{
designationManager.delete(code);
plDesignations=designationManager.getDesignations();
fireTableDataChanged();
}catch(BLException be)
{
List<String> exceptions=be.getExceptions();
String e="";
for(String g:exceptions)
{
e=e+g+"\n";
}
throw new ModelException(e);
}
}


public void exportToPdf(File selectedFile)
{
try
{
DesignationInterface designation;
String title="";
Font titleFont;
Font dataFont;
titleFont=new Font(FontFamily.HELVETICA,15,Font.BOLD,BaseColor.RED);
dataFont=new Font(FontFamily.HELVETICA,10,Font.BOLD,BaseColor.BLACK);
PdfPTable table1=null;
int size=plDesignations.size();
int pageSize=25;
int noOfPages;
int sn=0;
int cp=0;
Document document=new Document();
int result=(size)%(pageSize);
if(result!=0)
{
noOfPages=(size/pageSize)+1;
}
else
{
noOfPages=(size/pageSize);
}//if else ends here
boolean newPage=true;
int i=0;
PdfWriter.getInstance(document,new FileOutputStream(selectedFile));
document.open();
while(i<size)
{
if(newPage==true)
{
cp++;
Font font =new Font(FontFamily.HELVETICA,20,Font.BOLD,BaseColor.BLUE);
PdfPTable table=new PdfPTable(2);
//table.setWidthPercentage(100);
table.setWidths(new int[]{2,24});
table.setTotalWidth(527);
table.setLockedWidth(true);
table.getDefaultCell().setFixedHeight(40);
//Image img=Image.getInstance("C:/Icon Archieve/logo.png");
Image img=Image.getInstance(getClass().getResource("/images/logo.png"));
PdfPCell cell1=new PdfPCell(img,true);
cell1.setRowspan(2);
PdfPCell cell2=new PdfPCell(new Paragraph("Choudhary Enterprise",font));
cell2.setRowspan(2);
cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
PdfPCell cell3=new PdfPCell(new Paragraph(" "));
cell3.setColspan(2);
PdfPCell cell4=new PdfPCell(new Paragraph("List Of Designations",new Font(FontFamily.HELVETICA,14,Font.BOLD,BaseColor.BLACK) ));
cell4.setColspan(2);
cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
PdfPCell cell5=new PdfPCell(new Paragraph("Page"+cp+"/"+noOfPages));
cell5.setColspan(2);
cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
cell1.setBorder(PdfPCell.NO_BORDER);
cell2.setBorder(PdfPCell.NO_BORDER);
cell3.setBorder(PdfPCell.NO_BORDER);
cell4.setBorder(PdfPCell.NO_BORDER);
cell5.setBorder(PdfPCell.NO_BORDER);
table.addCell(cell1);
table.addCell(cell2);
table.addCell(cell3);
table.addCell(cell4);
table.addCell(cell5);
document.add(table);
document.add(new Paragraph(" "));
document.add(new Paragraph(" "));
table1=new PdfPTable(2);
table1.setWidthPercentage(100);
table1.setTotalWidth(288);
table1.setLockedWidth(true);
//table.setTotalWidth(288);
//table.setLockedWidth(true);
//table.setWidths(new float[]{1,2,2});
table1.setWidths(new float[]{1,2});
PdfPCell id=new PdfPCell(new Paragraph("S.No.",titleFont));
PdfPCell designationTitle=new PdfPCell(new Paragraph("Designation",titleFont));
id.setHorizontalAlignment(Element.ALIGN_RIGHT);
designationTitle.setHorizontalAlignment(Element.ALIGN_CENTER);
table1.addCell(id);
table1.addCell(designationTitle);
newPage=false;
}
//extract ith object from ds
designation=plDesignations.get(i);
sn++;
title=designation.getTitle();
PdfPCell snCell=new PdfPCell(new Paragraph(" "+sn));
snCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
PdfPCell titleCell=new PdfPCell(new Paragraph(title,dataFont));
titleCell.setHorizontalAlignment(Element.ALIGN_LEFT);
table1.addCell(snCell);
table1.addCell(titleCell);
//footer
if((sn%pageSize)==0||sn==size)
{
document.add(table1);
document.add(new Paragraph(""));
document.add(new Paragraph(""));
document.add(new Paragraph(""));
document.add(new Paragraph("Software by:- Rahul Choudhary",new Font(FontFamily.HELVETICA,15,Font.BOLD,BaseColor.BLACK)));
if(sn<size)
{
document.newPage();
newPage=true;
}
}
i++;
}//while loop ktm
document.close();
}catch(Exception e)
{

}
}
}
