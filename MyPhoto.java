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

public class MyPhoto extends JdbcMySQL{
	
	  private Connection con = null; //Database objects 
	  
	  //private String dataresource = "jdbc:mysql://140.113.92.87/phipster?allowPublicKeyRetrieval=TRUE&serverTimezone=UTC&autoReconnect=TRUE";

	  private String dataresource = "jdbc:mysql://140.113.178.3/phipster?allowPublicKeyRetrieval=TRUE&serverTimezone=UTC&autoReconnect=TRUE";
	  
	  private Statement stat1 = null;

	  private Statement stat2 = null;
	  
	  private Statement stat3 = null;

	  private Statement stat4 = null;

	  private ResultSet rs1 = null; 

	  private ResultSet rs2 = null; 
	  	 
	  private ResultSet rs3 = null;

	  private ResultSet rs4 = null; 
	  
	  private PreparedStatement pst = null; 

	  private PreparedStatement pst1 = null; 
	  
	  String pass;
	  
	  String[] photoSearch={"","","","","","","","","","","","","","","",""};

	  String[] accountname={"","","","","","","","","","","","","","","",""};

	  String[] date={"","","","","","","","","","","","","","","",""};
	  
	  //archive
	   String[] archiveAccountname={"","","","","","","","","","","","","","","",""};
	  
	  String[] archiveSearch={"","","","","","","","","","","","","","","",""};

	   String[] archiveDate={"","","","","","","","","","","","","","","",""};
	  
	  private Image[] image = new Image[16];
	  
	  private String inserttableSQL1 = "insert into "; 
	  
	  private String inserttableSQL2 = " (search,image,accountname,topic,uploaddate) values(?,?,?,?,?)"; 
	  
	  private String inserttableSQL3 = "follow (following) values(?)"; 

	  private String selecttableSQL1 = "select * from ";
	  
	  private String createtableSQL1="CREATE TABLE IF NOT EXISTS ";
	  
	  private String createtableSQL2=" (search varchar(80),image LONGBLOB,accountname varchar(80),topic varchar(100),uploaddate varchar(80),accountphoto LONGBLOB,archive LONGBLOB,following varchar(80),followed varchar(80));";
	  
	  private String createtableSQL3="CREATE TABLE IF NOT EXISTS ";
	  
	  private String createtableSQL4="follow (following varchar(80));";
	  
	  private String createarchivetableSQL3="CREATE TABLE IF NOT EXISTS ";
	  
	  private String createarchivetableSQL4="archive (search varchar(80), image LONGBLOB,accountname varchar(80),uploaddate varchar(80),accountphoto LONGBLOB);";
	  private String insertarchivetableSQL1 = "insert into ";
	  private String insertarchivetableSQL2 = "archive (search,image,accountname,uploaddate) values(?,?,?,?)";
	  private String selectArchivetableSQL1 = "select * from ";
	  
	  boolean alreadyfollow=false;
	  
	  Image[] archiveImage= new Image[16];
	  
	  String[] following=new String[1000];//={"","","","","","","","","","","","","","","",""};
	  /*Photo/*
	  1,search varchar(80),
	  2,image LONGBLOB,
	  3,accountname varchar(80),
	  4,accountphoto LONGBLOB,
	  5,archive LONGBLOB,
	  */
	  
	  /*Follow/*
	  1,following varchar(80),
	  2,followed varchar(80)
	  */
	  
	  public MyPhoto() 
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
	   public Image[] SelectTable (int status,int rest,String account) throws Exception
	  {
	    try
	    {
			int i=0;
			stat2 = con.createStatement();
			rs2 = stat2.executeQuery("select * from "+account+" LIMIT "+status+","+rest+";");
			while(rs2.next())
			{
				InputStream is= rs2.getBinaryStream("image");
				image[i]=new Image(is);
				is.close();
				i++;
			}
	    }
	    catch(SQLException e)
	    {
	      System.out.println("SelectTable(status,rest) Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
		  return image;

	    }
	  }
	  public void createTable(String account) 
	  { 
	    try 
	    {
		  pst1 = con.prepareStatement("CREATE TABLE IF NOT EXISTS "+account+" (search varchar(80),image LONGBLOB,accountname varchar(80),topic varchar(100),uploaddate varchar(80),accountphoto LONGBLOB,archive LONGBLOB,following varchar(80),followed varchar(80));"); 
		  pst1.executeUpdate();
	    } 
	    catch(Exception e) 
	    { 
	      System.out.println("CreateTable Exception :" + e.toString()); 
	    }
	    finally 
	    { 
	      Close(); 
	    } 
	  }
	  //following
	  public void createFollowTable(String account) 
	  { 
	    try 
	    {
		  pst1 = con.prepareStatement(createtableSQL3+account+createtableSQL4); 
		  pst1.executeUpdate();
	    } 
	    catch(Exception e) 
	    { 
	      System.out.println("createFollowTable Exception :" + e.toString()); 
	    }
	    finally 
	    { 
	      Close(); 
	    } 
	  }
	   public void createFollowerTable(String account) 
	  { 
	    try 
	    {
		  pst1 = con.prepareStatement(createtableSQL3+account+"follower (follower varchar(80));"); 
		  pst1.executeUpdate();
	    } 
	    catch(Exception e) 
	    { 
	      System.out.println("createFollowTable Exception :" + e.toString()); 
	    }
	    finally 
	    { 
	      Close(); 
	    } 
	  }
	   public void createArchiveTable(String account) 
	  { 
	    try 
	    {
		  pst1 = con.prepareStatement(createarchivetableSQL3+account+createarchivetableSQL4); 
		  //System.out.println(createtableSQL1+account+createtableSQL2);
		  pst1.executeUpdate();
	    } 
	    catch(Exception e) 
	    { 
	      System.out.println("createArchiveTable Exception :" + e.toString()); 
	    }
	    finally 
	    { 
	      Close(); 
	    } 
	  }
	   public void insertArchiveTable(String word,InputStream inputStream,String account,String date) throws Exception
	  {
	    try
	    {
		 // InputStream fin=new FileInputStream(file);
		  InputStream fin=inputStream;
	      pst = con.prepareStatement(insertarchivetableSQL1+account+insertarchivetableSQL2); 
		  //System.out.println(inserttableSQL);
	      pst.setString(1, word);
	     // pst.setBinaryStream(2,fin,(int)file.length());
		  pst.setBlob(2,fin);
		  pst.setString(3, account);
		  pst.setString(4,date);
		  

	      pst.executeUpdate();

	    }
	    catch(Exception e)
	    {
	      System.out.println("Jdbc insertArchiveTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
	    }
	  }
	  public void insertArchiveTable(String word,InputStream inputStream,String account,String date,String myaccount) throws Exception
	  {
	    try
	    {
		 // InputStream fin=new FileInputStream(file);
		  InputStream fin=inputStream;
	      pst = con.prepareStatement(insertarchivetableSQL1+myaccount+insertarchivetableSQL2); 
		  //System.out.println(inserttableSQL);
	      pst.setString(1, word);
	     // pst.setBinaryStream(2,fin,(int)file.length());
		  pst.setBlob(2,fin);
		  pst.setString(3, account);
		  pst.setString(4,date);
		  

	      pst.executeUpdate();

	    }
	    catch(Exception e)
	    {
	      System.out.println("Jdbc insertArchiveTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
	    }
	  }
	  //String search,File file,String account,File profilephoto
	  public void insertTable(String search,File file,String account,String topic,String date)
	  {
	    try
	    {
		  InputStream fin=new FileInputStream(file);
		  //InputStream pfin=new FileInputStream(profilephoto);		  
	      pst = con.prepareStatement(inserttableSQL1+account+inserttableSQL2);
	      //System.out.println(inserttableSQL1+account+inserttableSQL2);
		  pst.setString(1,search);
	      pst.setBinaryStream(2,fin,(int)file.length());
		  pst.setString(3,account);
		  pst.setString(4,topic);
		  pst.setString(5,date);
	      //pst.setBinaryStream(4,pfin,(int)profilephoto.length());
	      pst.executeUpdate();

	    }
	    catch(Exception e)
	    {
	      System.out.println("MyPhoto InsertTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
	    }
	  }
	  
	  public void cancelfollowing(String account,String follow) throws Exception
	  {
		   /*
		  stmt = conn.createStatement();
		  String sql = "DELETE FROM Registration " +
                   "WHERE id = 101";
		  stmt.executeUpdate(sql);
		  */
	    try
	    {
		  stat1 = con.createStatement();
	      stat1.executeUpdate("delete from "+account+"follow where following='"+follow+"';");
	    }
	    catch(Exception e)
	    {
	      System.out.println("MyPhoto cancelfollowing Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
	    }
	  }
	    public void cancelfollower(String account,String follower) throws Exception
	  {
		   /*
		  stmt = conn.createStatement();
		  String sql = "DELETE FROM Registration " +
                   "WHERE id = 101";
		  stmt.executeUpdate(sql);
		  */
	    try
	    {
		  stat1 = con.createStatement();
	      stat1.executeUpdate("delete from "+account+"follower where follower='"+follower+"';");
	    }
	    catch(Exception e)
	    {
	      System.out.println("MyPhoto cancelfollowing Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
	    }
	  }
	  public void insertFollowing(String account,String following) //not
	  {
	    try
	    {
		  //InputStream fin=new FileInputStream(file);		  
	      pst = con.prepareStatement(inserttableSQL1+account+inserttableSQL3);
	      //System.out.println(inserttableSQL1+account+inserttableSQL2);
		  pst.setString(1,following);
	      
	      //pst.setBinaryStream(4,pfin,(int)profilephoto.length());
	      pst.executeUpdate();

	    }
	    catch(Exception e)
	    {
	      System.out.println("MyPhoto insertFollowing Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
	    }
	  }
	   public void insertFollower(String account,String follower) 
	  {
	    try
	    {
		  //InputStream fin=new FileInputStream(file);		  
	      pst = con.prepareStatement(inserttableSQL1+account+"follower (follower) values(?)");
	      //System.out.println(inserttableSQL1+account+inserttableSQL2);
		  pst.setString(1,follower);
	      
	      //pst.setBinaryStream(4,pfin,(int)profilephoto.length());
	      pst.executeUpdate();

	    }
	    catch(Exception e)
	    {
	      System.out.println("MyPhoto insertFollowing Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
	    }
	  }
	  /*
	  public void insertProfilephoto(File profilephoto) ///not yet
	  {
	    try
	    {
		  InputStream pfin=new FileInputStream(profilephoto);
	      pst = con.prepareStatement(inserttableSQL1+account+inserttableSQL2);
	      System.out.println(inserttableSQL1+account+inserttableSQL2);
		  pst.setString(1,search);
	      pst.setBinaryStream(2,fin,(int)file.length());
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
	  */
	  
	  public boolean alreadyfollowing(String account,String follow) throws Exception
	  {
		try 
	    { 
	      stat1 = con.createStatement(); 
		  rs1 = stat1.executeQuery("SELECT following AS follow FROM "+account+"follow WHERE following=\""+ follow +"\"; ");
		  //System.out.println("SELECT passwordi AS pass FROM useri WHERE accounti=\""+ account +"\"; ");
		  
		  if(rs1.next())
		  {
			  alreadyfollow=true;
		  }
		  else
		  {
			  alreadyfollow=false;
		  }
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("alreadyfollowing Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close();
		  return alreadyfollow;

	    } 
	  }
	  
	  public String[] followingTable(String account) throws Exception
	  {
		try 
	    { int i=0;
		  int j=0;		
	      stat1 = con.createStatement(); 
		  rs1 = stat1.executeQuery("SELECT count(*) AS num FROM "+account+"follow;");
		  while(rs1.next())
		  {
			  j=rs1.getInt("num");
		  }
		  for(int k=0;k<j;k++)
			following[k]=new String();
		 //System.out.println("myphoto j:"+j);
		  stat2 = con.createStatement();
		  rs2 = stat2.executeQuery("SELECT following AS follow FROM "+account+"follow;");
		  while(rs2.next())
			{
				following[i]=rs2.getString("follow");
				i++;
			} 
	    }
	    catch(SQLException e) 
	    { 
	      System.out.println("followingTable Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close();
		  return following;
	    } 
	  }
	  
	  public int getCount(String account)throws Exception
	  {
		  int j=0;
	      stat1 = con.createStatement();
		  rs1 = stat1.executeQuery("SELECT COUNT(*) as num FROM "+account+";");
		 // System.out.println("SELECT COUNT(*) as num FROM "+account+";");

		  while(rs1.next())
			  j=rs1.getInt("num");
		  return j;

	  }
	   public int getFollowingCount(String account)throws Exception
	  {
		  int j=0;
	      stat1 = con.createStatement();
		  rs1 = stat1.executeQuery("SELECT COUNT(*) as num FROM "+account+"follow;");
		 // System.out.println("SELECT COUNT(*) as num FROM "+account+";");

		  while(rs1.next())
			  j=rs1.getInt("num");
		  return j;

	  }
	   public int getFollowerCount(String account)throws Exception
	  {
		  int j=0;
	      stat1 = con.createStatement();
		  rs1 = stat1.executeQuery("SELECT COUNT(*) as num FROM "+account+"follower;");
		 // System.out.println("SELECT COUNT(*) as num FROM "+account+";");

		  while(rs1.next())
			  j=rs1.getInt("num");
		  return j;

	  }
	  public int getArchiveCount(String account)throws Exception
	  {
		  int j=0;
	      stat1 = con.createStatement();
		  rs1 = stat1.executeQuery("SELECT COUNT(*) as num FROM "+account+"archive;");
		  //System.out.println("SELECT COUNT(*) as num FROM "+account+"archive;");

		  while(rs1.next())
			  j=rs1.getInt("num");
		  return j;

	  }
	  
	  public Image[] SelectTable (String account) throws Exception
	  {
	    try
	    {
		  int i=0;
		  int j=0;
	      stat2 = con.createStatement();
		  rs2 = stat2.executeQuery("SELECT COUNT(*) as num FROM "+account+";");
		  while(rs2.next())
			  j=rs2.getInt("num");
		  //System.out.println("myphoto j:"+j);
		  if(j<16)
		  {
			stat3 = con.createStatement();
			rs3 = stat3.executeQuery(selecttableSQL1+account+";");
			//System.out.println("myphoto "+selecttableSQL1+account+";");

			while(rs3.next())
			{
				
				//System.out.println(rs3.next());
				InputStream is= rs3.getBinaryStream("image");
				image[i]=new Image(is);
				//System.out.println("myphoto "+image[i]);
				is.close();
				i++;
			}
		  }
		  else
		  {
			stat3 = con.createStatement();
			rs3 = stat3.executeQuery(selecttableSQL1+account+" LIMIT "+Integer.toString(j-16) +",16;");
			//System.out.println(selecttableSQL1+account+" LIMIT "+Integer.toString(j-16) +",16;");

			while(rs3.next())
			{
				InputStream is= rs3.getBinaryStream("image");
				image[i]=new Image(is);
				is.close();
				i++;
			}
	      }
	    }
	    catch(SQLException e)
	    {
	      System.out.println("SelectTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
		  return image;

	    }
	  }
	   public Image[] SelectArchiveTable (String account) throws Exception
	  {
	    try
	    {
		  int i=0;
		  int j=0;
	      stat2 = con.createStatement();
		  rs2 = stat2.executeQuery("SELECT COUNT(*) as num FROM "+account+"archive;");
		  while(rs2.next())
			  j=rs2.getInt("num");
		  //System.out.println("myphoto j:"+j);
		  if(j<16)
		  {
			stat3 = con.createStatement();
			rs3 = stat3.executeQuery(selectArchivetableSQL1+account+"archive;");
			//System.out.println("myphoto "+selectArchivetableSQL1+account+";");

			while(rs3.next())
			{
				
				//System.out.println(rs3.next());
				InputStream is= rs3.getBinaryStream(2);
				archiveImage[i]=new Image(is);
				//System.out.println("myphoto "+image[i]);
				is.close();
				i++;
			}
		  }
		  else
		  {
			stat3 = con.createStatement();
			rs3 = stat3.executeQuery(selectArchivetableSQL1+account+" LIMIT "+Integer.toString(j-16) +",16;");
			//System.out.println(selectArchivetableSQL1+account+" LIMIT "+Integer.toString(j-16) +",16;");

			while(rs3.next())
			{
				InputStream is= rs3.getBinaryStream(2);
				archiveImage[i]=new Image(is);
				is.close();
				i++;
			}
	      }
	    }
	    catch(SQLException e)
	    {
	      System.out.println("SelectTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
		  return archiveImage;

	    }
	  }
	  
	  public Image[] SelectArchiveTable (String account,int status,int rest) throws Exception
	  {
	    try
	    {
		  int i=0;
		  
			stat3 = con.createStatement();
			rs3 = stat3.executeQuery( "select * from "+account+"archive LIMIT "+status+","+rest+";");

			while(rs3.next())
			{
				InputStream is= rs3.getBinaryStream(2);
				archiveImage[i]=new Image(is);
				is.close();
				i++;
			}
	      
	    }
	    catch(SQLException e)
	    {
	      System.out.println("SelectTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
		  return archiveImage;

	    }
	  }
	  
	  public String[] SelectSearchTable(String account,int status,int rest) throws Exception
	  {
	    try
	    {
		    int i=0;
			stat2 = con.createStatement();
			rs2 = stat2.executeQuery("select * FROM "+account+" limit "+status+","+rest+";");
			//rs2 = stat2.executeQuery(selectSQL+"LIMIT "+Integer.toString(j-16) +",16;");
			//System.out.println("rs2.next()="+rs2.next());
			while(rs2.next())
			{
				//System.out.println(i);
				photoSearch[i]=rs2.getString(1);
				//System.out.println("SelectSearchTable: "+photoSearch[i]+", i="+i);
				i++;
			}
	      
	    }
	    catch(SQLException e)
	    {
	      System.out.println("SelectSearchTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
		  return photoSearch;

	    }
	  }
	  public String[] SelectArchiveSearchTable(String account) throws Exception
	  {
	    try
	    {
		  int i=0;
		  int j=0;
	      stat1 = con.createStatement();
		  rs1 = stat1.executeQuery("SELECT COUNT(*) as num FROM "+account+"archive;");
		  while(rs1.next())
			  j=rs1.getInt("num");
		  //System.out.println("j:"+j);
		  if(j<16)
		  {
			stat2 = con.createStatement();
			rs2 = stat2.executeQuery("select * FROM "+account+"archive;");
			while(rs2.next())
			{
				archiveSearch[i]=rs2.getString(1);
				i++;
			}
		  }
		  else
		  {
			stat2 = con.createStatement();
			
			rs2 = stat2.executeQuery("select search as searchword FROM "+account+"archive limit "+Integer.toString(j-16)+",16;");
			//rs2 = stat2.executeQuery(selectSQL+"LIMIT "+Integer.toString(j-16) +",16;");
			while(rs2.next())
			{
				archiveSearch[i]=rs2.getString("searchword");
				i++;
			}
	      }
	    }
	    catch(SQLException e)
	    {
	      System.out.println("SelectArchiveSearchTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
		  return archiveSearch;

	    }
	  }
	  public String[] SelectArchiveSearchTable(String account,int status,int rest) throws Exception
	  {
	    try
	    {
		  int i=0;
		  
			stat2 = con.createStatement();
			
			rs2 = stat2.executeQuery("select search as searchword FROM "+account+"archive limit "+status+","+rest+";");
			//rs2 = stat2.executeQuery(selectSQL+"LIMIT "+Integer.toString(j-16) +",16;");
			while(rs2.next())
			{
				archiveSearch[i]=rs2.getString("searchword");
				i++;
			}
	      
	    }
	    catch(SQLException e)
	    {
	      System.out.println("SelectArchiveSearchTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
		  return archiveSearch;

	    }
	  }
	  public String[] SelectAccountTable(String account) throws Exception
	  {
	    try
	    {
		  int i=0;
		  int j=0;
	      stat1 = con.createStatement();
		  rs1 = stat1.executeQuery("SELECT COUNT(*) as num FROM "+account+";");
		  while(rs1.next())
			  j=rs1.getInt("num");
		  if(j<16)
		  {
			stat2 = con.createStatement();
			//rs2 = stat2.executeQuery(selectSQL+";");
			rs2 = stat2.executeQuery("select * from "+account+";");
			while(rs2.next())
			{
				accountname[i]=rs2.getString(3);
				i++;
			}
		  }
		  else
		  {
			stat2 = con.createStatement();
			
			rs2 = stat2.executeQuery("select * from "+account+" limit "+Integer.toString(j-16)+",16;");
			while(rs2.next())
			{
				accountname[i]=rs2.getString(3);
				i++;
			}
	      }
	    }
	    catch(SQLException e)
	    {
	      System.out.println("SelectAccountTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
		  return accountname;

	    }
	  }
	  public String[] SelectAccountTable(String account,int status,int rest) throws Exception
	  {
	    try
	    {
		  int i=0;
		
			stat2 = con.createStatement();
			
			rs2 = stat2.executeQuery("select * from "+account+" limit "+status+","+rest+";");
			while(rs2.next())
			{
				accountname[i]=rs2.getString(3);
				i++;
			}
	      
	    }
	    catch(SQLException e)
	    {
	      System.out.println("SelectAccountTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
		  return accountname;

	    }
	  }
	   public String[] SelectArchiveAccountTable(String account) throws Exception
	  {
	    try
	    {
		  int i=0;
		  int j=0;
	      stat1 = con.createStatement();
		  rs1 = stat1.executeQuery("SELECT COUNT(*) as num FROM "+account+"archive;");
		  while(rs1.next())
			  j=rs1.getInt("num");
		  
		  if(j<16)
		  {
			  
			stat2 = con.createStatement();
			//rs2 = stat2.executeQuery(selectSQL+";");
			rs2 = stat2.executeQuery("select * from "+account+"archive;");
			while(rs2.next())
			{
				archiveAccountname[i]=rs2.getString(3);
				i++;
			}
		  }
		  else
		  {
			stat2 = con.createStatement();
			
			rs2 = stat2.executeQuery("select * from "+account+"archive limit "+Integer.toString(j-16)+",16;");
			while(rs2.next())
			{
				
				archiveAccountname[i]=rs2.getString(3);
				i++;
			}
	      }
	    }
	    catch(SQLException e)
	    {
	      System.out.println("SelectArchiveAccountTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
		  return archiveAccountname;

	    }
	  }
	   public String[] SelectArchiveAccountTable(String account,int status,int rest) throws Exception
	  {
	    try
	    {
		  int i=0;
		  
			
			stat2 = con.createStatement();
			
			rs2 = stat2.executeQuery("select * from "+account+"archive limit "+status+","+rest+";");
			while(rs2.next())
			{
				archiveAccountname[i]=rs2.getString(3);
				i++;
			}
	      
	    }
	    catch(SQLException e)
	    {
	      System.out.println("SelectArchiveAccountTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
		  return archiveAccountname;

	    }
	  }
	  public String[] SelectDateTable(String account) throws Exception
	  {
	    try
	    {
		  int i=0;
		  int j=0;
	      stat1 = con.createStatement();
		  rs1 = stat1.executeQuery("SELECT COUNT(*) as num FROM "+account+";");
		  while(rs1.next())
			  j=rs1.getInt("num");
		  if(j<16)
		  {
			stat2 = con.createStatement();
			//rs2 = stat2.executeQuery(selectSQL+";");
			rs2 = stat2.executeQuery("select * from "+account+";");
			while(rs2.next())
			{
				date[i]=rs2.getString(5);
				i++;
			}
		  }
		  else
		  {
			stat2 = con.createStatement();
			
			rs2 = stat2.executeQuery("select * from "+account+" limit "+Integer.toString(j-16)+",16;");
			while(rs2.next())
			{
				date[i]=rs2.getString(5);
				i++;
			}
	      }
	    }
	    catch(SQLException e)
	    {
	      System.out.println("SelectAccountTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
		  return date;

	    }
	  }
	  public String[] SelectDateTable(String account,int status,int rest) throws Exception
	  {
	    try
	    {
		  int i=0;
		
			stat2 = con.createStatement();
			
			rs2 = stat2.executeQuery("select * from "+account+" limit "+status+","+rest+";");
			while(rs2.next())
			{
				//System.out.println(i);
				date[i]=rs2.getString(5);
				i++;
			}
	      
	    }
	    catch(SQLException e)
	    {
	      System.out.println("SelectDateTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
		  return date;

	    }
	  }
	    public String[] SelectArchiveDateTable(String account) throws Exception
	  {
	    try
	    {
		  int i=0;
		  int j=0;
	      stat1 = con.createStatement();
		  rs1 = stat1.executeQuery("SELECT COUNT(*) as num FROM "+account+"archive;");
		  while(rs1.next())
			  j=rs1.getInt("num");
		  //System.out.println("j:"+j);
		  if(j<16)
		  {
			  //System.out.println("<16");
			stat2 = con.createStatement();
			//rs2 = stat2.executeQuery(selectSQL+";");
			rs2 = stat2.executeQuery("select * from "+account+"archive;");
			while(rs2.next())
			{
				archiveDate[i]=rs2.getString(5);
				i++;
			}
		  }
		  else
		  {
			// System.out.println(">=16");
			stat2 = con.createStatement();
			
			rs2 = stat2.executeQuery("select * from "+account+"archive limit "+Integer.toString(j-16)+",16;");
			while(rs2.next())
			{
				//System.out.println(i);
				archiveDate[i]=rs2.getString(4);
				//photoSearch[i]="haha";
				//System.out.println(photoSearch[i]+" "+i);
				i++;
			}
	      }
	    }
	    catch(SQLException e)
	    {
	      System.out.println("SelectArchiveDateTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
		  return archiveDate;

	    }
	  }
	  public String[] SelectArchiveDateTable(String account,int status,int rest) throws Exception
	  {
	    try
	    {
			int i=0;
			stat2 = con.createStatement();
			rs2 = stat2.executeQuery("select * from "+account+"archive limit "+status+","+rest+";");
			//System.out.println("select * from "+account+"archive limit "+status+","+rest+";");
			while(rs2.next())
			{
				
				archiveDate[i]=rs2.getString(4);
				//System.out.println("archivedate"+archiveDate[i]);
				i++;
			}
	      
	    }
	    catch(SQLException e)
	    {
	      System.out.println("SelectArchiveDateTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
		  return archiveDate;

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
		  if(rs2!=null)
	      {
	        rs2.close();
	        rs2 = null;
	      }
		  if(rs3!=null)
	      {
	        rs3.close();
	        rs3 = null;
	      }
	      if(stat1!=null)
	      {
	        stat1.close();
	        stat1 = null;
	      }
		  if(stat2!=null)
	      {
	        stat2.close();
	        stat2 = null;
	      }
		  if(stat3!=null)
	      {
	        stat3.close();
	        stat3 = null;
	      }
	      if(pst!=null)
	      {
	        pst.close();
	        pst = null;
	      }
		  if(pst1!=null)
	      {
	        pst1.close();
	        pst1 = null;
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
