import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
import java.io.*;
import java.io.File;
import javax.swing.JFileChooser;
import javafx.scene.image.Image;

public class LogIn {
	
	  private Connection con = null; //Database objects 
	  
	 // private String dataresource = "jdbc:mysql://140.113.92.87/phipster?allowPublicKeyRetrieval=TRUE&serverTimezone=UTC&autoReconnect=TRUE";

	  private String dataresource = "jdbc:mysql://140.113.178.3/phipster?allowPublicKeyRetrieval=TRUE&serverTimezone=UTC&autoReconnect=TRUE";
	  
	  private Statement stat1 = null;
	  
	  private Statement stat2 = null;
  
	  private ResultSet rs1 = null; 
	  
	  private ResultSet rs2 = null; 
	  	 
	  private PreparedStatement pst = null; 
	  
	  String pass;
	  
	  boolean reuse=false;
	  
	  Image profile;
	  
	  private String inserttableSQL = "insert into useri (accounti,passwordi) values(?,?)"; 
	  
	  private String selectSQL = "select * from useri ";  
	  
	  private String updatetableSQL = "UPDATE useri SET accountphoto = ?  WHERE accounti = '";
	  
	  public LogIn() 
	  { 
	    try
	    {
	      Class.forName("com.mysql.cj.jdbc.Driver");
	      con = DriverManager.getConnection(dataresource, "newuser", "nctuece87");

	    }
	    catch(ClassNotFoundException e)
	    {
	      System.out.println("DriverClassNotFound :"+e.toString());
	    }
	    catch(SQLException x)
	    {
	      System.out.println("Exception :"+x.toString());
	    }
	  } 
	 
	  public void insertTable(String account ,String password) 
	  { 
	    try 
	    {
	      pst = con.prepareStatement(inserttableSQL); //save
	      
	      pst.setString(1, account); 
	      pst.setString(2,password); 
	      pst.executeUpdate(); 
	      
	    } 
	    catch(Exception e) 
	    { 
	      System.out.println("InsertTable Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  } 
	  
	  public void updateTable(String account,File file) throws Exception
	  {
	    try
	    {
		  InputStream fin=new FileInputStream(file);
		  updatetableSQL+=account+"';";
	      pst = con.prepareStatement(updatetableSQL); 
		  pst.setBinaryStream(1,fin,(int)file.length());  
	      pst.executeUpdate();

	    }
	    catch(Exception e)
	    {
	      System.out.println("updateTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
	    }
	  }
	  
	  public Image selectProfile(String account) throws Exception
	  {
	    try
	    {
		  selectSQL="select * from useri ";
		  stat2 = con.createStatement();
		  selectSQL+=" where accounti='"+account+"';";
		  System.out.println(selectSQL);
		  rs2 = stat2.executeQuery(selectSQL);
		  while(rs2.next())
			{
				InputStream is= rs2.getBinaryStream("accountphoto");
				profile=new Image(is);
				is.close();
			}  
		}
	    
	    catch(SQLException e)
	    {
	      System.out.println("selectProfile Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
		  return profile;

	    }
	  }
	  
	  public String SelectTable (String account) throws Exception
	  { 
	    
	    try 
	    { 
	      stat1 = con.createStatement(); 
		  rs1 = stat1.executeQuery("SELECT passwordi AS pass FROM useri WHERE accounti=\""+ account +"\"; ");
		  //System.out.println("SELECT passwordi AS pass FROM useri WHERE accounti=\""+ account +"\"; ");
		  while(rs1.next())
		  {
			  
			  pass=rs1.getString("pass");
		  }
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("SelectTable Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close();
		  return pass;

	    } 
	  } 
	  
	  public boolean reuseAccount(String account) throws Exception
	  {
		try 
	    { 
	      stat1 = con.createStatement(); 
		  rs1 = stat1.executeQuery("SELECT accounti AS account FROM useri WHERE accounti=\""+ account +"\"; ");
		  //System.out.println("SELECT passwordi AS pass FROM useri WHERE accounti=\""+ account +"\"; ");
		  while(rs1.next())
		  {
			  reuse=true;
		  }
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("SelectTable Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close();
		  return reuse;

	    } 
	  }
	  	  
	  private void Close() 
	  { 
	    try 
	    { 
	      if(rs1!=null) 
	      { 
	        rs1.close(); 
	        rs1 = null; 
	      } 
	      if(stat1!=null) 
	      { 
	        stat1.close(); 
	        stat1 = null; 
	      } 
		  
	      if(pst!=null) 
	      { 
	        pst.close(); 
	        pst = null; 
	      } 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("Close Exception :" + e.toString()); 
	    } 
	  }   
	
	public static void main(String[] args) {

	}

}
