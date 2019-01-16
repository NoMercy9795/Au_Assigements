package advance;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Advance {
       
	private static final String USERNAME ="sql12273536";
	private static final String PASSWORD ="accolite";
	private static final String CONN_STRING ="jdbc:mysql://sql12.freemysqlhosting.net/sql12273536?useSSL=false";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
            
//		Class.forName("con.mysql.cj.jdbc.Driver");
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
			System.out.println("connected");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from emp");
                        String fileContent = "<accolite>";
                        FileWriter fileWriter = new FileWriter("XML_Data.xml");
                        fileWriter.write(fileContent);
                        System.out.println(fileContent);
                        fileWriter.write("\n");
                        
			while(rs.next()) {
//				System.out.println(rs.getInt(1)+ " " + rs.getString(2) + " " + rs.getString(3) );
                                 fileWriter.write("<employee>"+"\n");
                                fileWriter.write("<id>"+rs.getInt(1)+"</id>"+"\n");
                                fileWriter.write("<name>"+rs.getString(2)+"</name>"+"\n");
                                fileWriter.write("<design>"+rs.getString(3)+"</design>"+"\n");
                                fileWriter.write("</employee>"+"\n");
                                System.out.println("<employee>");
                                System.out.println("<id>"+rs.getInt(1)+"</id>");
                                System.out.println("<name>"+rs.getString(2)+"</name>");
                                System.out.println("<design>"+rs.getString(3)+"</design>");
                                System.out.println("</employee>");
			}
                        System.out.println("</accolite>");
                        fileWriter.write("</accolite>");
                        fileWriter.close();
			
		} catch (SQLException e) {
			
			System.err.println(e);
		}finally{
			if(conn!=null) {
				conn.close();
			}
		}
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/advance", "root", "root");
                    Statement st=con.createStatement();
                    DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                    Document doc = docBuilder.parse (new File("XML_Data.xml"));
                    doc.getDocumentElement().normalize();
                    System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());
                    NodeList listOfPersons = doc.getElementsByTagName("employee");
                    for(int s=0; s<listOfPersons.getLength(); s++){
                        Node firstPersonNode = listOfPersons.item(s);
                        if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){
                            Element firstPersonElement = (Element)firstPersonNode;
                            NodeList idList = firstPersonElement.getElementsByTagName("id");
                            Element idElement =(Element)idList.item(0);
                            NodeList textFNList = idElement.getChildNodes();
                            String id=((Node)textFNList.item(0)).getNodeValue().trim();
                            NodeList nameList = firstPersonElement.getElementsByTagName("name");
                            Element nameElement =(Element)nameList.item(0);
                            NodeList textMNList = nameElement.getChildNodes();
                            String name= ((Node)textMNList.item(0)).getNodeValue().trim();
                            NodeList designList = firstPersonElement.getElementsByTagName("design");
                            Element designElement =(Element)designList.item(0);
                            NodeList textLNList = designElement.getChildNodes();
                            String design= ((Node)textLNList.item(0)).getNodeValue().trim();
                            int i=st.executeUpdate("insert into user(id,name,design) values('"+id+"','"+name+"','"+design+"')");
                        }
                        }
                            System.out.println("Data is successfully inserted!");
                        }catch (Exception err) {
                        System.out.println(" " + err.getMessage ());
                    }

	}
 }
 