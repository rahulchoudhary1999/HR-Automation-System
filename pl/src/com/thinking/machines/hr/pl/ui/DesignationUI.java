package com.thinking.machines.hr.pl.ui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.thinking.machines.hr.pl.exceptions.*;
import com.thinking.machines.hr.pl.model.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import javax.swing.table.JTableHeader;
import javax.swing.border.Border;
import javax.swing.border.*;
import javax.swing.JPanel.*;
//import javax.swing.table.*;
import javax.swing.table.TableColumn.*;
import java.lang.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.List;
public class DesignationUI extends JFrame implements DocumentListener
{
private char mode='v';
private JLabel titleLabel;
private JLabel statusCaptionLabel;
private JLabel searchCaptionLabel;
private JTextField searchTextField;
private JButton cancelButton;
private JTable table;
private JScrollPane jsp;
private JLabel detailsCaptionLabel;
private JPanel detailsPanel;
private JLabel designationCaptionLabel;
private JLabel designationLabel;
private JPanel buttonsPanel;
private JButton b1,b2,b3,b4,b5;
private Container container;
private Dimension d;
private DesignationModel designationModel;
private JTableHeader tableHeader;
private Border blackLineBorder;
private JTextField designationTextField;
private JFileChooser fileChooser;
private FileNameExtensionFilter f1;
private File selectedFile;
//done
public DesignationUI()
{
titleLabel=new JLabel("Designation Master");
statusCaptionLabel=new JLabel("NOT FOUND");
searchCaptionLabel=new JLabel("Search");
searchTextField=new JTextField();
searchTextField.getDocument().addDocumentListener(this);
cancelButton=new JButton(new ImageIcon(getClass().getResource("/images/cross_Icon.png")));
cancelButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
searchTextField.setText("");
}
});
designationModel=new DesignationModel();
table=new JTable(designationModel);
table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
public void valueChanged(ListSelectionEvent e)
{
if(!e.getValueIsAdjusting())
{
int row=table.getSelectedRow();
try{
String title=designationModel.getDesignationAt(row).getTitle();
designationLabel.setText(title);
}catch(ModelException me)
{
System.out.println(me.getMessage());
}
}
}
});
jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
detailsCaptionLabel=new JLabel("Details");
detailsPanel=new JPanel();
designationCaptionLabel=new JLabel("Designation");
designationLabel=new JLabel("");
designationTextField=new JTextField();
designationTextField.setVisible(false);
buttonsPanel=new JPanel();
b1=new JButton(new ImageIcon(getClass().getResource("/images/add_Icon.png")));
b2=new JButton(new ImageIcon(getClass().getResource("/images/edit_Icon.png")));
b3=new JButton(new ImageIcon(getClass().getResource("/images/cancel_Icon.png")));
b4=new JButton(new ImageIcon(getClass().getResource("/images/delete_Icon.png")));
b5=new JButton(new ImageIcon(getClass().getResource("/images/pdf_Icon.png")));
//programming related to add/edit/cancel/delete/exportToPdf button
if(table.getRowCount()!=0)
{
b3.setEnabled(false);
}
else
{
searchTextField.setEnabled(false);
cancelButton.setEnabled(false);
table.setEnabled(false);
b2.setEnabled(false);
b3.setEnabled(false);
b4.setEnabled(false);
b5.setEnabled(false);
designationLabel.setText("");
}
b1.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
if(mode=='v')
{
searchTextField.setEnabled(false);
cancelButton.setEnabled(false);
table.setEnabled(false);
designationTextField.setVisible(true);
designationTextField.setText("");
designationLabel.setText("");
b1.setEnabled(true);
b2.setEnabled(false);
b3.setEnabled(true);
b4.setEnabled(false);
b5.setEnabled(false);
b1.setIcon(new ImageIcon(getClass().getResource("/images/save_Icon.png")));
mode='s';
}
else
{
String title=designationTextField.getText();
if(title.trim().length()==0)JOptionPane.showMessageDialog(null,"Designation Required");
else{
//DesignationInterface designation=new Designation();
//designation.setTitle(title);
//designationModel.add(designation);
int k=designationModel.add(title);
table.addRowSelectionInterval(k,k);
table.scrollRectToVisible(table.getCellRect(k,0, true));
}
mode='v';
//designationLabel.setVisible(title);
//atleast 1 record is there
searchTextField.setEnabled(true);
cancelButton.setEnabled(true);
table.setEnabled(true);
b1.setEnabled(true);
b2.setEnabled(true);
b3.setEnabled(false);
b4.setEnabled(true);
b5.setEnabled(true);
//designationLabel.setText("");
b1.setIcon(new ImageIcon(getClass().getResource("/images/add_Icon.png")));
designationTextField.setVisible(false);

}
}
});
b3.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
if(table.getRowCount()!=0)
{//atleast 1 record is there
searchTextField.setEnabled(true);
cancelButton.setEnabled(true);
table.setEnabled(true);
b1.setEnabled(true);
b2.setEnabled(true);
b3.setEnabled(false);
b4.setEnabled(true);
b5.setEnabled(true);
designationLabel.setText("");
b1.setIcon(new ImageIcon(getClass().getResource("/images/add_Icon.png")));
b2.setIcon(new ImageIcon(getClass().getResource("/images/edit_Icon.png")));
mode='v';
designationTextField.setVisible(false);
}
else
{//zero records
searchTextField.setEnabled(false);
cancelButton.setEnabled(false);
table.setEnabled(false);
b1.setEnabled(true);
b2.setEnabled(false);
b3.setEnabled(false);
b4.setEnabled(false);
b5.setEnabled(false);
designationLabel.setText("");
b1.setIcon(new ImageIcon(getClass().getResource("/images/add_Icon.png")));
b2.setIcon(new ImageIcon(getClass().getResource("/images/edit_Icon.png")));
mode='v';
designationTextField.setVisible(false);
}
}
});
b2.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
DesignationInterface designation=null;
if(table.getSelectedRow()==(-1))
{
JOptionPane.showMessageDialog(null,"Select a row");
}
else
{
if(mode=='v')
{
searchTextField.setEnabled(false);
cancelButton.setEnabled(false);
table.setEnabled(false);
b1.setEnabled(false);
b2.setEnabled(true);
b3.setEnabled(true);
b4.setEnabled(false);
b5.setEnabled(false);
designationLabel.setText("");
designationTextField.setVisible(true);
int i=table.getSelectedRow();
try{
designation=designationModel.getDesignationAt(i);
}catch(ModelException me)
{
System.out.println(me.getMessage());
}
String title=designation.getTitle();
if(title.trim().length()==0)JOptionPane.showMessageDialog(null,"Designation Required");
designationTextField.setText(title);
b2.setIcon(new ImageIcon(getClass().getResource("/images/update_Icon.png")));
mode='u';
}else
{
try{
int i=table.getSelectedRow();
designation=designationModel.getDesignationAt(i);
}catch(ModelException me)
{
System.out.println(me.getMessage());
}
String title=designationTextField.getText();
designation.setTitle(title);
try{
designationModel.update(designation);
int rowIndex=designationModel.indexOf(designation);
table.setRowSelectionInterval(rowIndex,rowIndex);
table.scrollRectToVisible(table.getCellRect(rowIndex,0,true));
searchTextField.setEnabled(true);
cancelButton.setEnabled(true);
table.setEnabled(true);
b1.setEnabled(true);
b2.setEnabled(true);
b3.setEnabled(false);
b4.setEnabled(true);
b5.setEnabled(true);
//designationLabel.setText("");
b2.setIcon(new ImageIcon(getClass().getResource("/images/edit_Icon.png")));
designationTextField.setVisible(false);
mode='v';
}catch(ModelException me)
{
JOptionPane.showMessageDialog(null,me.getMessage());
}
}
}
}
});
b4.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
if(table.getSelectedRow()==(-1))
{
JOptionPane.showMessageDialog(null,"Select a row");
}
else
{
int i=table.getSelectedRow();
DesignationInterface designation;
try{
designation=designationModel.getDesignationAt(i);
String title=designation.getTitle();
int code=designation.getCode();
int e=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete "+title+" ?","Confirmation",JOptionPane.YES_NO_OPTION);
if(e==0)//yes
{
designationModel.delete(code);
JOptionPane.showMessageDialog(null,"Deleted successfully");
}
else//no
{
return;
}
}catch(ModelException me)
{
JOptionPane.showMessageDialog(null,me.getMessage());
}
designationLabel.setText("");
//JOptionPane.showMessageDialog(null,"Deleted successfully");
}
}
});
b5.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
//export
fileChooser=new JFileChooser(){
public void approveSelection()
{
File file=getSelectedFile();
if(!(new File(file.getParent())).exists() && getDialogType()==SAVE_DIALOG)
{
JOptionPane.showMessageDialog(null,"The file name is not valid.","Save As",JOptionPane.WARNING_MESSAGE);
return;
}
String ext=file.getPath();
int i=ext.lastIndexOf(".");
if(i==(-1))
{
ext=ext+".pdf";
}
else
{
if(!(ext.substring(i+1).equals("pdf")))
{
ext=ext.substring(0,i)+".pdf";
}
}
file=new File(ext);
setSelectedFile(file);
if(file.exists() && getDialogType()==SAVE_DIALOG)
{
int confirm=JOptionPane.showConfirmDialog(null,file.getName()+" already exists! Would you like to overwrite it?","File already exists",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
if(confirm!=JOptionPane.YES_OPTION)return;
}
super.approveSelection();
}
};
fileChooser.setAcceptAllFileFilterUsed(false);
f1=new FileNameExtensionFilter("Pdf files","pdf");
fileChooser.addChoosableFileFilter(f1);
fileChooser.showSaveDialog(null);
selectedFile=fileChooser.getSelectedFile();
//System.out.println(selectedFile.getParent());
//System.out.println(selectedFile.getName());
designationModel.exportToPdf(selectedFile);
}
});
Font titleFont=new Font("Verdana",Font.PLAIN,24);
titleLabel.setFont(titleFont);
Font dataFont=new Font("Georgia",Font.PLAIN,18);
statusCaptionLabel.setFont(dataFont);
statusCaptionLabel.setForeground(Color.red);
searchCaptionLabel.setFont(dataFont);
searchTextField.setFont(dataFont);
  //Border line = new LineBorder(Color.BLACK);
  //Border margin = new EmptyBorder(8, 8, 15, 10);
  //Border compound = new CompoundBorder(line, margin);
  //cancelButton.setBorder(compound);
cancelButton.setFont(new Font("Georgia",Font.PLAIN,18));
table.setRowHeight(24);
table.setFont(dataFont);
table.getColumnModel().getColumn(0).setPreferredWidth(5);
table.getColumnModel().getColumn(1).setPreferredWidth(300);
tableHeader=table.getTableHeader();
tableHeader.setFont(new Font("Verdana",Font.BOLD,18));
tableHeader.setResizingAllowed(false);
tableHeader.setReorderingAllowed(false);
table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
detailsCaptionLabel.setFont(dataFont);
Color color=new Color(192,192,192);
blackLineBorder=BorderFactory.createLineBorder(color);
//blackLineBorder=BorderFactory.createLineBorder(Color.decode(#FF0000));
detailsPanel.setBorder(blackLineBorder);
buttonsPanel.setBorder(blackLineBorder);
designationCaptionLabel.setFont(new Font("Times New Roman",Font.BOLD,20));
designationLabel.setFont(dataFont);
designationTextField.setFont(dataFont);
Font buttonsFont=new Font("Times New Roman",Font.BOLD,18);
b1.setFont(buttonsFont);
b2.setFont(buttonsFont);
b3.setFont(buttonsFont);
b4.setFont(buttonsFont);
b5.setFont(buttonsFont);
int lm=20;
int tm=0;
int w=800;
int h=670;
titleLabel.setBounds(lm+10,tm+10,300,40+10);
statusCaptionLabel.setBounds(lm+350+10+10+3+200+30+20,tm+10+40+5,200,30);
searchCaptionLabel.setBounds(lm+10,tm+10+40+10+5+30,80,30);
searchTextField.setBounds(lm+10+80+10,tm+10+40+10+5+30,300+200+90,30);
cancelButton.setBounds(lm+10+80+10+300+5+200+90,tm+10+40+10+5+30,45,30);
jsp.setBounds(lm+10,tm+10+40+10+5+30+30+10+10,440+200+90,120+100);
detailsPanel.setBounds(lm+10,tm+10+40+10+5+30+30+10+10+120+12+5+100,440+200+90,200);
//designationCaptionLabel.setBounds(lm+10+20,tm+10+40+10+5+30+30+10+10+120+12+5+10+10,100,30);
container=getContentPane();
container.setLayout(null);
container.add(titleLabel);
container.add(statusCaptionLabel);
container.add(searchCaptionLabel);
container.add(searchTextField);
container.add(cancelButton);
container.add(jsp);
//Insets insets=detailsPanel.getInsets();
//Dimension size=designationCaptionLabel.getPreferredSize();
designationCaptionLabel.setBounds(10+10,10,150,40);
designationLabel.setBounds(10+150+10+10,10,300,40);
designationTextField.setBounds(10+150+10+10,12,300,30);
buttonsPanel.setBounds((730/2)-(525/2),10+40+10+10,525,100);
detailsPanel.setLayout(null);
detailsPanel.add(designationCaptionLabel);
detailsPanel.add(designationLabel);
detailsPanel.add(designationTextField);
buttonsPanel.setLayout(null);
b1.setBounds(10+10,15,80,70);
b2.setBounds(10+10+80+20,15,80,70);
b3.setBounds(10+10+80+20+80+20,15,80,70);
b4.setBounds(10+10+80+20+80+20+80+20,15,80,70);
b5.setBounds(10+10+80+20+80+20+80+20+80+20,15,80,70);
buttonsPanel.add(b1);
buttonsPanel.add(b2);
buttonsPanel.add(b3);
buttonsPanel.add(b4);
buttonsPanel.add(b5);
detailsPanel.add(buttonsPanel);
//detailsPanel.add(designationLabel);
//detailsPanel.add(buttonsPanel);
container.add(detailsPanel);
setTitle("HR Automation System");
setIconImage(new ImageIcon(getClass().getResource("/images/hr_icon.png")).getImage());
d=Toolkit.getDefaultToolkit().getScreenSize();
setLocation((d.width/2)-(w/2),(d.height/2)-(h/2));
setSize(w,h);
setDefaultCloseOperation(EXIT_ON_CLOSE);
}
public void changedUpdate(DocumentEvent de)
{
System.out.println("ChangedUpdate");
}
public void removeUpdate(DocumentEvent de)
{
if(searchTextField.getText().trim().length()==0)statusCaptionLabel.setText("NOT FOUND");
searchDesignation();
}
public void insertUpdate(DocumentEvent de)
{
if(searchTextField.getText().trim().length()==0)statusCaptionLabel.setText("NOT FOUND");
searchDesignation();
}
public void searchDesignation()
{
String text=searchTextField.getText();
try{
DesignationInterface designation=designationModel.getDesignation(text,false,true);
int i=designationModel.indexOf(designation);
table.addRowSelectionInterval(i,i);
table.scrollRectToVisible(table.getCellRect(i,0,true));
statusCaptionLabel.setText("    FOUND");
}catch(ModelException me)
{
statusCaptionLabel.setText("NOT FOUND");
}
}
}
