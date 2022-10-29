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

public class JdbcMySQL {

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

	  private Image[] image = new Image[16];

	  private String inserttableSQL = "insert into image (search,image,accountname,topic,uploaddate) values(?,?,?,?,?)";

	  private String selectSQL = "select * from image ";
	  
	  private String selectSQL1 = "select * from image ";
	  
	  private String selectSQL2 = "select * from image ";
	  
	  private String selectSQL3 = "select * from image ";
	  
	  private String selectSQL4 = "select * from image ";
	  
	  private String selectSQL5 = "select * from image ";
	  
	  private String selectSQL6 = "select * from image ";
	  
	  private String selectCountSQL = "select count(*) as num from image ";
	  
	  private String selectCountSQL1 = "select count(*) as num from image ";
	  
	  private String selectCountSQL2 = "select count(*) as num from image ";

	  private String selectCountSQL3 = "select count(*) as num from image ";
	  
	  private String selectCountSQL5 = "select count(*) as num from image ";

	  
	  String search;
	  
	  String[] accountname={"","","","","","","","","","","","","","","",""};

	  String[] words;
	  
	  String[] photoSearch={"","","","","","","","","","","","","","","",""};
	  
	  String[] topic={"","","","","","","","","","","","","","","",""};
	  
	  String[] topicsearch={"","","","","","","","","","","","","","","",""};
	  
	  String[] topicaccount={"","","","","","","","","","","","","","","",""};
	  
	  String[] date={"","","","","","","","","","","","","","","",""};

	  
	  public JdbcMySQL()
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

	  public int getSearchCount(String search)throws Exception
	  {
		 int j=0;
		 //System.out.println(search);
		 words = search.split("\\ ");
		 //System.out.println("here");
		if(words.length!=0)
		{

			String searchQuery2="SELECT COUNT(*) AS searchnum FROM phipster.image WHERE search LIKE '%"+words[0]+"%'";

			for(int i=1;i<words.length;i++)
			{

				//stat3 = con.createStatement();
				searchQuery2+=" OR search LIKE '%"+words[i]+"%'";

				//rs3 = stat3.executeQuery("SELECT image FROM phipster.image WHERE search LIKE '"+ words +"';");
			}
			//searchQuery1+=";";
			//searchQuery2+=";";

			//System.out.println("search:" + searchQuery);



	      stat3 = con.createStatement();
		  rs3 = stat3.executeQuery(searchQuery2+";");
		  //System.out.println(searchQuery2+";");
		}
		  while(rs3.next())
			  j=rs3.getInt("searchnum");
		  return j;
	  }
	  /*
	  public void setSearch(String s)
	  {
		  search=s;
		  words = search.split("\\ ");
		  for(String ss:words)
		  {
			  System.out.println("search:" + s);
		  }

	  }
	  */
	  public Image[] searchWord(String search)throws Exception
	  {

		if(words.length!=0)
		{
			String searchQuery1="SELECT image AS searchimage FROM phipster.image WHERE search LIKE '%"+words[0]+"%'";
			String searchQuery2="SELECT COUNT(*) AS searchnum FROM phipster.image WHERE search LIKE '%"+words[0]+"%'";

			for(int i=1;i<words.length;i++)
			{
				searchQuery1+=" OR search LIKE '%"+words[i]+"%'";
				searchQuery2+=" OR search LIKE '%"+words[i]+"%'";
			}

			try
			{
			  int i=0;
			  int j=0;
			  stat3 = con.createStatement();
			  rs3 = stat3.executeQuery(searchQuery2+";");
			  while(rs3.next())
				  j=rs3.getInt("searchnum");
			  if(j<16)
			  {
				stat4 = con.createStatement();
				rs4 = stat4.executeQuery(searchQuery1+";");
				while(rs4.next())
				{
					InputStream is= rs4.getBinaryStream("searchimage");
					image[i]=new Image(is);
					is.close();
					i++;

				}
			  }
			  else
			  {
				stat4 = con.createStatement();
				rs4 = stat4.executeQuery(searchQuery1+"LIMIT "+Integer.toString(j-16) +",16;");
				while(rs4.next())
				{
					InputStream is= rs4.getBinaryStream("searchimage");
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
		else
		{
			Close();
			return null;


		}

	  }
	  //String word,File file,String account,File profilephoto
	  
	  public void insertTable(String word,File file,String account,String topic,String date) throws Exception
	  {
	    try
	    {
		  InputStream fin=new FileInputStream(file);
	      pst = con.prepareStatement("insert into image (search,image,accountname,topic,uploaddate) values(?,?,?,?,?)"); 
	      pst.setString(1, word);
	      pst.setBinaryStream(2,fin,(int)file.length());
		  pst.setString(3, account);
		  pst.setString(4,topic);
		  pst.setString(5,date);
	      pst.executeUpdate();

	    }
	    catch(Exception e)
	    {
	      System.out.println("Jdbc InsertTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
	    }
	  }

	  public int getCount()throws Exception
	  {
		  int j=0;
	      stat1 = con.createStatement();
		  rs1 = stat1.executeQuery("SELECT COUNT(*) as num FROM image;");
		  while(rs1.next())
			  j=rs1.getInt("num");
		  return j;

	  }
	  
	  public int getFollowingPhotoCount(String account,String[] following)throws Exception
	  {
		  int j=0;
		  int k=1;
		  selectCountSQL=selectCountSQL+"where accountname='"+following[0]+"'";
		  
		  while(following[k]!=null)
		  {
				selectCountSQL+=" or accountname='"+following[k]+"'";
				//System.out.println("jdbc f:"+k+" "+selectCountSQL);
				k++;
		  }
	      stat1 = con.createStatement();
		  rs1 = stat1.executeQuery(selectCountSQL+";");
		  
		  while(rs1.next())
			  j=rs1.getInt("num");
		  return j;
	  }
	  public Image[] SelectTable () throws Exception
	  {
	    try
	    {
		  int i=0;
		  int j=0;
	      stat1 = con.createStatement();
		  rs1 = stat1.executeQuery("SELECT COUNT(*) as num FROM image;");
		  while(rs1.next())
			  j=rs1.getInt("num");
		  if(j<16)
		  {
			stat2 = con.createStatement();
			rs2 = stat2.executeQuery(selectSQL+";");
			while(rs2.next())
			{
				InputStream is= rs2.getBinaryStream("image");
				image[i]=new Image(is);
				is.close();
				i++;

			}
		  }
		  else
		  {
			stat2 = con.createStatement();
			rs2 = stat2.executeQuery(selectSQL+"LIMIT "+Integer.toString(j-16) +",16;");
			while(rs2.next())
			{
				InputStream is= rs2.getBinaryStream("image");
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
	  
	public Image[] SelectFollowingTable (String account,String[] following) throws Exception
    {
		 try
		 {
		int i=0;
		int j=0;
		int k=1;
		selectSQL=selectSQL+"where accountname='"+following[0]+"'";
		selectCountSQL1=selectCountSQL1+"where accountname='"+following[0]+"'";
		
		while(following[k]!=null)
		{
		selectCountSQL1+=" or accountname='"+following[k]+"'";
		//System.out.println("following table:"+k+" "+selectCountSQL1);
		k++;
		}
		//System.out.println("following table: "+selectCountSQL1);
		   stat1 = con.createStatement();
		rs1 = stat1.executeQuery(selectCountSQL1);
		while(rs1.next())
		 j=rs1.getInt("num");
		//System.out.println("j:"+j);
		k=1;
		if(j<16)
		{
	   stat2 = con.createStatement();
	   while(following[k]!=null)
	   {
		selectSQL+=" or accountname='"+following[k]+"'";
		k++;
	   }
	   rs2 = stat2.executeQuery(selectSQL+";");
	   while(rs2.next())
	   {
		InputStream is= rs2.getBinaryStream("image");
		image[i]=new Image(is);
		//System.out.println("jdbc "+image[i]);
		is.close();
		i++;
	   }
		}
		else
		{
	   stat2 = con.createStatement();
	   while(following[k]!=null)
	   {
		selectSQL+=" or accountname='"+following[k]+"'";
		k++;
	   }
	   selectSQL+=" limit "+Integer.toString(j-16) +",16;";
	   rs2 = stat2.executeQuery(selectSQL+";");
	   while(rs2.next())
	   {
		InputStream is= rs2.getBinaryStream("image");
		image[i]=new Image(is);
		//System.out.println("jdbc "+image[i]);
		is.close();
		i++;
	   }  
		}
		 }
		 catch(SQLException e)
		 {
		   System.out.println("SelectFollowingTable Exception :" + e.toString());
		 }
		 finally
		 {
		   Close();
		return image;

		 }
   }
	  
	  public Image[] SelectFollowingTable (String account,String[] following,int status,int rest) throws Exception
	  {
	    try
	    {
		  selectSQL6="select * from image ";
		  int i=0;
		  int k=1;
		  selectSQL6=selectSQL6+"where accountname='"+following[0]+"'";
		  
			stat2 = con.createStatement();
			while(following[k]!=null)
			{
				selectSQL6+=" or accountname='"+following[k]+"'";
				k++;
			}
			selectSQL6+=" limit "+status +","+rest+";";
			//System.out.println(selectSQL6);
			rs2 = stat2.executeQuery(selectSQL6);
			//System.out.println(selectSQL6);
			while(rs2.next())
			{
				InputStream is= rs2.getBinaryStream("image");
				image[i]=new Image(is);
				//System.out.println("jdbc "+image[i]);
				is.close();
				i++;
			}  
		}
	    
	    catch(SQLException e)
	    {
	      System.out.println("SelectFollowingTable(String account,String[] following,int status,int rest) Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
		  return image;

	    }
	  }
	   public Image[] SelectWordTable (String search,int status,int rest) throws Exception
	  {
		words = search.split("\\ ");
	    if(words.length!=0)
		{
			String searchQuery1="SELECT * FROM phipster.image WHERE search LIKE '%"+words[0]+"%'";
			//System.out.println(searchQuery1);
			for(int i=1;i<words.length;i++)
			{
				searchQuery1+=" OR search LIKE '%"+words[i]+"%'";
			}

			try
			{
			    int i=0;
				stat4 = con.createStatement();
				rs4 = stat4.executeQuery(searchQuery1+" LIMIT "+status +","+rest+";");
				//System.out.println(searchQuery1+" LIMIT "+status +","+rest+";");
				while(rs4.next())
				{
					InputStream is= rs4.getBinaryStream(2);
					image[i]=new Image(is);
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
			  return image;
			}

		}
		else
		{
			Close();
			return null;
		}
	  }
	   public String[] SelectWordSearchTable (String search,int status,int rest) throws Exception
	  {
		   words = search.split("\\ ");
	    if(words.length!=0)
		{
			String searchQuery1="SELECT * FROM phipster.image WHERE search LIKE '%"+words[0]+"%'";
			String searchQuery2="SELECT COUNT(*) AS searchnum FROM phipster.image WHERE search LIKE '%"+words[0]+"%'";

			for(int i=1;i<words.length;i++)
			{
				searchQuery1+=" OR search LIKE '%"+words[i]+"%'";
				searchQuery2+=" OR search LIKE '%"+words[i]+"%'";
			}
			String[] s=new String[100000];
			try
			{
			  int i=0;
				stat4 = con.createStatement();
				rs4 = stat4.executeQuery(searchQuery1+"LIMIT "+status +","+rest+";");
				while(rs4.next())
				{
					s[i]= rs4.getString(1);
					
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
			  return s;

			}

		}
		else
		{
			Close();
			return null;


		}
	  }
	  public String[] SelectWordAccountTable (String search,int status,int rest) throws Exception
	  {
		   words = search.split("\\ ");
	    if(words.length!=0)
		{
			String searchQuery1="SELECT * FROM phipster.image WHERE search LIKE '%"+words[0]+"%'";
			String searchQuery2="SELECT COUNT(*) AS searchnum FROM phipster.image WHERE search LIKE '%"+words[0]+"%'";

			for(int i=1;i<words.length;i++)
			{
				searchQuery1+=" OR search LIKE '%"+words[i]+"%'";
				searchQuery2+=" OR search LIKE '%"+words[i]+"%'";
			}
			String[] s=new String[100000];
			try
			{
			  int i=0;
				stat4 = con.createStatement();
				rs4 = stat4.executeQuery(searchQuery1+"LIMIT "+status +","+rest+";");
				while(rs4.next())
				{
					s[i]= rs4.getString(3);
					
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
			  return s;

			}

		}
		else
		{
			Close();
			return null;


		}
	  }
	   public String[] SelectWordDateTable (String search,int status,int rest) throws Exception
	  {
		   words = search.split("\\ ");
	    if(words.length!=0)
		{
			String searchQuery1="SELECT * FROM phipster.image WHERE search LIKE '%"+words[0]+"%'";
			String searchQuery2="SELECT COUNT(*) AS searchnum FROM phipster.image WHERE search LIKE '%"+words[0]+"%'";

			for(int i=1;i<words.length;i++)
			{
				searchQuery1+=" OR search LIKE '%"+words[i]+"%'";
				searchQuery2+=" OR search LIKE '%"+words[i]+"%'";
			}
			String[] s=new String[100000];
			try
			{
			  int i=0;
				stat4 = con.createStatement();
				rs4 = stat4.executeQuery(searchQuery1+"LIMIT "+status +","+rest+";");
				while(rs4.next())
				{
					s[i]= rs4.getString(5);
					
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
			  return s;

			}

		}
		else
		{
			Close();
			return null;


		}
	  }
	  public String[] SelectFollowingSearch (String account,String[] following,int status,int rest) throws Exception
	  {
	    try
	    {
		  int i=0;
		  int k=1;
		  selectSQL2="select * from image ";
		  selectSQL2=selectSQL2+"where accountname='"+following[0]+"'";
		  
		 
			stat2 = con.createStatement();
			while(following[k]!=null)
			{
				selectSQL2+=" or accountname='"+following[k]+"'";
				k++;
			}
			selectSQL2+=" limit "+status+","+rest+";";
			rs2 = stat2.executeQuery(selectSQL2+";");
			while(rs2.next())
			{
				photoSearch[i]=rs2.getString(1);
				i++;
			}  
		  
	    }
	    catch(SQLException e)
	    {
	      System.out.println("SelectFollowingTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
		  return photoSearch;

	    }
	  }
	  
	  public String[] SelectFollowingSearch (String account,String[] following) throws Exception
	  {
	    try
	    {
		  int i=0;
		  int j=0;
		  int k=1;
		  selectSQL2=selectSQL2+"where accountname='"+following[0]+"'";
		  selectCountSQL2=selectCountSQL2+"where accountname='"+following[0]+"'";
		  
		  while(following[k]!=null)
		  {
				selectCountSQL2+=" or accountname='"+following[k]+"'";
				//System.out.println("following table:"+k+" "+selectCountSQL2);
				k++;
		  }
		  //System.out.println("following table: "+selectCountSQL2);
	      stat1 = con.createStatement();
		  rs1 = stat1.executeQuery(selectCountSQL2);
		  while(rs1.next())
			  j=rs1.getInt("num");
		  //System.out.println("j:"+j);
		  k=1;
		  if(j<16)
		  {
			stat2 = con.createStatement();
			while(following[k]!=null)
			{
				selectSQL2+=" or accountname='"+following[k]+"'";
				k++;
			}
			rs2 = stat2.executeQuery(selectSQL2+";");
			while(rs2.next())
			{
				photoSearch[i]=rs2.getString(1);
				i++;
			}
		  }
		  else
		  {
			stat2 = con.createStatement();
			while(following[k]!=null)
			{
				selectSQL2+=" or accountname='"+following[k]+"'";
				k++;
			}
			selectSQL2+=" limit "+Integer.toString(j-16) +",16;";
			rs2 = stat2.executeQuery(selectSQL2+";");
			while(rs2.next())
			{
				photoSearch[i]=rs2.getString(1);
				i++;
			}  
		  }
	    }
	    catch(SQLException e)
	    {
	      System.out.println("SelectFollowingTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
		  return photoSearch;

	    }
	  }
	  
	  public String[] SelectFollowingAccount (String account,String[] following,int status,int rest) throws Exception
	  {
	    try
	    {
		  int i=0;
		  int k=1;
		  selectSQL3="select * from image ";
		  selectSQL3=selectSQL3+"where accountname='"+following[0]+"'";
		  
			stat2 = con.createStatement();
			while(following[k]!=null)
			{
				selectSQL3+=" or accountname='"+following[k]+"'";
				k++;
			}
			selectSQL3+=" limit "+status +","+rest+";";
			rs2 = stat2.executeQuery(selectSQL3);
			while(rs2.next())
			{
				accountname[i]=rs2.getString(3);
				i++;
			}  
		 
	    }
	    catch(SQLException e)
	    {
	      System.out.println("SelectFollowingTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
		  return accountname;

	    }
	  }
	  
	  
	  public String[] SelectFollowingAccount (String account,String[] following) throws Exception
	  {
	    try
	    {
		  int i=0;
		  int j=0;
		  int k=1;
		  selectSQL3=selectSQL3+"where accountname='"+following[0]+"'";
		  selectCountSQL3=selectCountSQL3+"where accountname='"+following[0]+"'";
		  
		  while(following[k]!=null)
		  {
				selectCountSQL3+=" or accountname='"+following[k]+"'";
				//System.out.println("following table:"+k+" "+selectCountSQL2);
				k++;
		  }
		  //System.out.println("SelectFollowingAccount: "+selectCountSQL2);
	      stat1 = con.createStatement();
		  rs1 = stat1.executeQuery(selectCountSQL3);
		  while(rs1.next())
			  j=rs1.getInt("num");
		  //System.out.println("j:"+j);
		  k=1;
		  if(j<16)
		  {
			stat2 = con.createStatement();
			while(following[k]!=null)
			{
				selectSQL3+=" or accountname='"+following[k]+"'";
				k++;
			}
			rs2 = stat2.executeQuery(selectSQL3+";");
			while(rs2.next())
			{
				accountname[i]=rs2.getString(3);
				i++;
			}
		  }
		  else
		  {
			stat2 = con.createStatement();
			while(following[k]!=null)
			{
				selectSQL3+=" or accountname='"+following[k]+"'";
				k++;
			}
			selectSQL3+=" limit "+Integer.toString(j-16) +",16;";
			rs2 = stat2.executeQuery(selectSQL3);
			while(rs2.next())
			{
				accountname[i]=rs2.getString(3);
				i++;
			}  
		  }
	    }
	    catch(SQLException e)
	    {
	      System.out.println("SelectFollowingTable Exception :" + e.toString());
	    }
	    finally
	    {
	      Close();
		  return accountname;

	    }
	  }
	  public Image[] SelectTable (int status,int rest) throws Exception
	  {
	    try
	    {
			int i=0;
			stat2 = con.createStatement();
			rs2 = stat2.executeQuery(selectSQL+"LIMIT "+status+","+rest+";");
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
	   public String[] SelectDateTable (String account,String[] following,int status,int rest) throws Exception
	  {
	    try
	    {
		  int i=0;
		  int k=1;
		  selectSQL5=selectSQL5+"where accountname='"+following[0]+"'";
		  
			stat2 = con.createStatement();
			while(following[k]!=null)
			{
				selectSQL3+=" or accountname='"+following[k]+"'";
				k++;
			}
			selectSQL5+=" limit "+status+","+rest+";";
			rs2 = stat2.executeQuery(selectSQL5);
			while(rs2.next())
			{
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
	  public String[] SelectSearchTable() throws Exception
	  {
	    try
	    {
		  int i=0;
		  int j=0;
	      stat1 = con.createStatement();
		  rs1 = stat1.executeQuery("SELECT COUNT(*) as num FROM image;");
		  while(rs1.next())
			  j=rs1.getInt("num");
		  //System.out.println("j:"+j);
		  if(j<16)
		  {
			  //System.out.println("<16");
			stat2 = con.createStatement();
			//rs2 = stat2.executeQuery(selectSQL+";");
			rs2 = stat2.executeQuery("select search AS searchword from image;");
			//System.out.println("rs2.next()="+rs2.next());
			while(rs2.next())
			{
				photoSearch[i]=rs2.getString("searchword");
				i++;
			}
		  }
		  else
		  {
			 //System.out.println(">=16");
			stat2 = con.createStatement();
			
			rs2 = stat2.executeQuery("select * from image limit "+Integer.toString(j-16)+",16;");
			//rs2 = stat2.executeQuery(selectSQL+"LIMIT "+Integer.toString(j-16) +",16;");
			//System.out.println("rs2.next()="+rs2.next());
			while(rs2.next())
			{
				//System.out.println(i);
				photoSearch[i]=rs2.getString(1);
				//photoSearch[i]="haha";
				//System.out.println(photoSearch[i]+" "+i);
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
		  return photoSearch;

	    }
	  }
	  
	  public String[] SelectSearchTable(int status,int rest) throws Exception
	  {
	    try
	    {
		  int i=0;
		 
		  //System.out.println(">=16");
		  stat2 = con.createStatement();
			
		  rs2 = stat2.executeQuery("select * from image limit "+status+","+rest+";");
		  //rs2 = stat2.executeQuery(selectSQL+"LIMIT "+Integer.toString(j-16) +",16;");
	   	  //System.out.println("rs2.next()="+rs2.next());
		  while(rs2.next())
		  {
			  //System.out.println(i);
			  photoSearch[i]=rs2.getString(1);
			  //photoSearch[i]="haha";
			  //System.out.println(photoSearch[i]+" "+i);
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
		  return photoSearch;

	    }
	  }
	  
	  
	  
	  
	  public String[] SelectDateTable(int status,int rest) throws Exception
	  {
	    try
	    {
		  int i=0;
		  
		  //System.out.println(">=16");
		  stat2 = con.createStatement();
			
		  rs2 = stat2.executeQuery("select * from image limit "+status+","+rest+";");
		  //rs2 = stat2.executeQuery(selectSQL+"LIMIT "+Integer.toString(j-16) +",16;");
		  //System.out.println("rs2.next()="+rs2.next());
		  while(rs2.next())
		  {
		  	//System.out.println(i);
			date[i]=rs2.getString(5);
			//photoSearch[i]="haha";
			//System.out.println(photoSearch[i]+" "+i);
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
		  return date;

	    }
	  }
	  
	  public String[] SelectDateTable() throws Exception
	  {
	    try
	    {
		  int i=0;
		  int j=0;
	      stat1 = con.createStatement();
		  rs1 = stat1.executeQuery("SELECT COUNT(*) as num FROM image;");
		  while(rs1.next())
			  j=rs1.getInt("num");
		  //System.out.println("j:"+j);
		  if(j<16)
		  {
			  //System.out.println("<16");
			stat2 = con.createStatement();
			//rs2 = stat2.executeQuery(selectSQL+";");
			rs2 = stat2.executeQuery("select * from image;");
			//System.out.println("rs2.next()="+rs2.next());
			while(rs2.next())
			{
				date[i]=rs2.getString(5);
				i++;
			}
		  }
		  else
		  {
			 //System.out.println(">=16");
			stat2 = con.createStatement();
			
			rs2 = stat2.executeQuery("select * from image limit "+Integer.toString(j-16)+",16;");
			//rs2 = stat2.executeQuery(selectSQL+"LIMIT "+Integer.toString(j-16) +",16;");
			//System.out.println("rs2.next()="+rs2.next());
			while(rs2.next())
			{
				//System.out.println(i);
				date[i]=rs2.getString(5);
				//photoSearch[i]="haha";
				//System.out.println(photoSearch[i]+" "+i);
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
		  return date;

	    }
	  }
	  
	  public String[] SelectTopic() throws Exception
	  {
	    try
	    {
		  int i=0;
		  int j=0;
	      stat1 = con.createStatement();
		  rs1 = stat1.executeQuery("SELECT COUNT(*) as num FROM image;");
		  while(rs1.next())
			  j=rs1.getInt("num");
		  //System.out.println("j:"+j);
		  if(j<16)
		  {
			  //System.out.println("<16");
			stat2 = con.createStatement();
			//rs2 = stat2.executeQuery(selectSQL+";");
			rs2 = stat2.executeQuery("select topic as topicwords from image;");
			//System.out.println("rs2.next()="+rs2.next());
			while(rs2.next())
			{
				topic[i]=rs2.getString("topicwords");
				i++;
			}
		  }
		  else
		  {
			 System.out.println(">=16");
			stat2 = con.createStatement();
			
			rs2 = stat2.executeQuery("select * from image limit "+Integer.toString(j-16)+",16;");
			//rs2 = stat2.executeQuery(selectSQL+"LIMIT "+Integer.toString(j-16) +",16;");
			//System.out.println("rs2.next()="+rs2.next());
			while(rs2.next())
			{
				//System.out.println(i);
				topic[i]=rs2.getString(4);
				//topic[i]="haha";
				//System.out.println(topic[i]+" "+i);
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
		  return topic;

	    }
	  }
	  
	  public String[] SelectAccountTable(int status,int rest) throws Exception
	  {
	    try
	    {
		  int i=0;
		  
		  //System.out.println(">=16");
		  stat2 = con.createStatement();
			
		  rs2 = stat2.executeQuery("select * from image limit "+status+","+rest+";");
		  //rs2 = stat2.executeQuery(selectSQL+"LIMIT "+Integer.toString(j-16) +",16;");
		  //System.out.println("rs2.next()="+rs2.next());
		  while(rs2.next())
		  {
			//System.out.println(i);
			accountname[i]=rs2.getString(3);
			//photoSearch[i]="haha";
			//System.out.println("in jdbc"+accountname[i]+" "+i);
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
		  return accountname;
	    }
	  }
	  
	  public String[] SelectAccountTable() throws Exception
	  {
	    try
	    {
		  int i=0;
		  int j=0;
	      stat1 = con.createStatement();
		  rs1 = stat1.executeQuery("SELECT COUNT(*) as num FROM image;");
		  while(rs1.next())
			  j=rs1.getInt("num");
		  //System.out.println("j:"+j);
		  if(j<16)
		  {
			  //System.out.println("<16");
			stat2 = con.createStatement();
			//rs2 = stat2.executeQuery(selectSQL+";");
			rs2 = stat2.executeQuery("select * from image;");
			//System.out.println("rs2.next()="+rs2.next());
			while(rs2.next())
			{
				accountname[i]=rs2.getString(3);
				//System.out.println("in jdbc "+accountname[i]+" "+i);
				i++;
			}
		  }
		  else
		  {
			 //System.out.println(">=16");
			stat2 = con.createStatement();
			
			rs2 = stat2.executeQuery("select * from image limit "+Integer.toString(j-16)+",16;");
			//rs2 = stat2.executeQuery(selectSQL+"LIMIT "+Integer.toString(j-16) +",16;");
			//System.out.println("rs2.next()="+rs2.next());
			while(rs2.next())
			{
				//System.out.println(i);
				accountname[i]=rs2.getString(3);
				//photoSearch[i]="haha";
				//System.out.println("in jdbc"+accountname[i]+" "+i);
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
		  return accountname;

	    }
	  }
	  
	  public int getTopicCount(String topic)throws Exception
	  {
		  int i=0;
		  int j=0;
	      stat1 = con.createStatement();
		  rs1 = stat1.executeQuery("SELECT COUNT(*) as num FROM image where topic like '%"+topic+"%';");
		  //System.out.println("SELECT COUNT(*) as num FROM image where topic like '%"+topic+"%';");
		  while(rs1.next())
			  j=rs1.getInt("num");
		  //System.out.println("j="+j);
		  return j;
	  }
	  
	  public Image[] SelectTopicTable (String topic) throws Exception
	  {
		try
		{
			int i=0;
			int j=0;
			stat1 = con.createStatement();
			rs1 = stat1.executeQuery("SELECT COUNT(*) as num FROM image where topic like '%"+topic+"%';");
			while(rs1.next())
				j=rs1.getInt("num");
			//System.out.println("j:"+j);
			if(j<16)
			{
			   stat2 = con.createStatement();
			   rs2 = stat2.executeQuery(selectSQL1+"where topic like '%"+topic+"%';");
			  // System.out.println(selectSQL1+"where topic like '%"+topic+"%';");
			   while(rs2.next())
			   {
					InputStream is= rs2.getBinaryStream("image");
					image[i]=new Image(is);
					//System.out.println("jdbc "+image[i]);
					is.close();
					i++;

			   }
			}
			else
			{
			   stat2 = con.createStatement();
			   rs2 = stat2.executeQuery(selectSQL1+"where topic like '%"+topic+"%' LIMIT "+Integer.toString(j-16) +",16;");
			   //System.out.println(selectSQL1+"where topic like '%"+topic+"%' LIMIT "+Integer.toString(j-16) +",16;");
			   while(rs2.next())
			   {
					InputStream is= rs2.getBinaryStream("image");
					image[i]=new Image(is);
					is.close();
					i++;
			   }
			}
		 }
		 catch(SQLException e)
		 {
		   System.out.println("SelectTopicTable Exception :" + e.toString());
		 }
		 finally
		 {
		   Close();
		return image;

		 }
	   }
	   
	   public Image[] SelectTopicTable (String topic,int status,int rest) throws Exception
	  {
		try
		{
			int i=0;
			stat2 = con.createStatement();
			rs2 = stat2.executeQuery(selectSQL1+"where topic like '%"+topic+"%' LIMIT "+status+","+rest+";");
			//System.out.println(selectSQL1+"where topic like '%"+topic+"%' LIMIT "+Integer.toString(j-16) +",16;");
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
		  System.out.println("SelectTopicTable Exception :" + e.toString());
		}
		finally
		{
		    Close();
			return image;

		}
	   }
	   
	   public String[] SelectTopicSearch (String topic) throws Exception
	  {
		try
		{
			int i=0;
			int j=0;
			stat1 = con.createStatement();
			rs1 = stat1.executeQuery("SELECT COUNT(*) as num FROM image where topic like '%"+topic+"%';");
			while(rs1.next())
				j=rs1.getInt("num");
			//System.out.println("j:"+j);
			if(j<16)
			{
			   stat2 = con.createStatement();
			   rs2 = stat2.executeQuery(selectSQL4+"where topic like '%"+topic+"%';");
			   //System.out.println(selectSQL4+"where topic like '%"+topic+"%';");
			   while(rs2.next())
			   {
				    topicsearch[i]=rs2.getString(1);
					i++;

			   }
			}
			else
			{
			   stat2 = con.createStatement();
			   rs2 = stat2.executeQuery(selectSQL4+"where topic like '%"+topic+"%' LIMIT "+Integer.toString(j-16) +",16;");
			  
//			  System.out.println(selectSQL4+"where topic like '%"+topic+"%' LIMIT "+Integer.toString(j-16) +",16;");
			   while(rs2.next())
			   {
					topicsearch[i]=rs2.getString(1);
					i++;
			   }
			}
		 }
		 catch(SQLException e)
		 {
		   System.out.println("SelectTopicTable Exception :" + e.toString());
		 }
		 finally
		 {
		   Close();
		   return topicsearch;

		 }
	   }
	   
	   public String[] SelectTopicSearch (String topic,int status,int rest) throws Exception
	  {
		try
		{
			int i=0;
			
			   stat2 = con.createStatement();
			   rs2 = stat2.executeQuery(selectSQL4+"where topic like '%"+topic+"%' LIMIT "+status+","+rest+";");
			   //System.out.println(selectSQL4+"where topic like '%"+topic+"%' LIMIT "+Integer.toString(j-16) +",16;");
			   while(rs2.next())
			   {
					topicsearch[i]=rs2.getString(1);
					i++;
			   }
			
		 }
		 catch(SQLException e)
		 {
		   System.out.println("SelectTopicTable Exception :" + e.toString());
		 }
		 finally
		 {
		   Close();
		   return topicsearch;

		 }
	   }
	   
	    public String[] SelectTopicDate (String topic) throws Exception
	  {
		try
		{
			int i=0;
			int j=0;
			stat1 = con.createStatement();
			rs1 = stat1.executeQuery("SELECT COUNT(*) as num FROM image where topic like '%"+topic+"%';");
			while(rs1.next())
				j=rs1.getInt("num");
			//System.out.println("j:"+j);
			if(j<16)
			{
			   stat2 = con.createStatement();
			   rs2 = stat2.executeQuery(selectSQL4+"where topic like '%"+topic+"%';");
			   while(rs2.next())
			   {
				    date[i]=rs2.getString(5);
					i++;

			   }
			}
			else
			{
			   stat2 = con.createStatement();
			   rs2 = stat2.executeQuery(selectSQL4+"where topic like '%"+topic+"%' LIMIT "+Integer.toString(j-16) +",16;");
			 
			   while(rs2.next())
			   {
					date[i]=rs2.getString(5);
					i++;
			   }
			}
		 }
		 catch(SQLException e)
		 {
		   System.out.println("SelectTopicTable Exception :" + e.toString());
		 }
		 finally
		 {
		   Close();
		   return date;

		 }
	   }
	   
	   public String[] SelectTopicDate (String topic,int status,int rest) throws Exception
	  {
		try
		{
			int i=0;
			
			   stat2 = con.createStatement();
			   rs2 = stat2.executeQuery(selectSQL4+"where topic like '%"+topic+"%' LIMIT "+status+","+rest+";");
			  
			   while(rs2.next())
			   {
					date[i]=rs2.getString(5);
					i++;
			   }
			
		 }
		 catch(SQLException e)
		 {
		   System.out.println("SelectTopicTable Exception :" + e.toString());
		 }
		 finally
		 {
		   Close();
		   return date;

		 }
	   }
	   
	    public String[] SelectTopicAccount (String topic) throws Exception
	  {
		try
		{
			int i=0;
			int j=0;
			stat1 = con.createStatement();
			rs1 = stat1.executeQuery("SELECT COUNT(*) as num FROM image where topic like '%"+topic+"%';");
			while(rs1.next())
				j=rs1.getInt("num");
			
			if(j<16)
			{
			   stat2 = con.createStatement();
			   rs2 = stat2.executeQuery(selectSQL4+"where topic like '%"+topic+"%';");
			   while(rs2.next())
			   {
				    topicaccount[i]=rs2.getString(3);
					i++;

			   }
			}
			else
			{
			   stat2 = con.createStatement();
			   rs2 = stat2.executeQuery(selectSQL4+"where topic like '%"+topic+"%' LIMIT "+Integer.toString(j-16) +",16;");
			  
			   while(rs2.next())
			   {
					topicaccount[i]=rs2.getString(3);
					i++;
			   }
			}
		 }
		 catch(SQLException e)
		 {
		   System.out.println("SelectTopicTable Exception :" + e.toString());
		 }
		 finally
		 {
		   Close();
		   return topicaccount;

		 }
	   }
	   
	   public String[] SelectTopicAccount (String topic,int status,int rest) throws Exception
	  {
		try
		{
			int i=0;
			
			   stat2 = con.createStatement();
			   rs2 = stat2.executeQuery(selectSQL4+"where topic like '%"+topic+"%' LIMIT "+status+","+rest+";");
			   //System.out.println(selectSQL4+"where topic like '%"+topic+"%' LIMIT "+Integer.toString(j-16) +",16;");
			   while(rs2.next())
			   {
					topicaccount[i]=rs2.getString(3);
					i++;
			   }
			
		 }
		 catch(SQLException e)
		 {
		   System.out.println("SelectTopicTable Exception :" + e.toString());
		 }
		 finally
		 {
		   Close();
		   return topicaccount;

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
