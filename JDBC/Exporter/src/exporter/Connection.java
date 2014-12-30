package exporter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Connects to a RDBMS via JDBC, send a select, that
 * can also be created, and give the result back or write it in a file.
 * @author Melanie Goebel
 * @version 2014-12-15
 */
public class Connection {
	private int anz = 0; // counts of rows of the results

	/**
	 * Sends a select command to the RDBMS
	 * @param hostname the hostname of the RDBMS
	 * @param user the username 
	 * @param password the password from the username
	 * @param database the used database
	 * @param command the select command 
	 * @param delimeter character to seperate fields
	 * @param filename if selected, file to save result, otherwise text as output only
	 */
	public String sendSelectCommand (String hostname,String user,String password,String database, String command, String delimeter, String filename){
		MysqlDataSource ds = new MysqlDataSource();
		ds.setServerName(hostname);
		ds.setUser(user);
		ds.setPassword(password);
		ds.setDatabaseName(database);
		File file;
		FileWriter rf;
		BufferedWriter bf;
		try {
			System.out.println("Connect to server...");
			java.sql.Connection con = ds.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(command);
			ResultSetMetaData restit = rs.getMetaData(); // for the rows
			this.anz = restit.getColumnCount(); // how much rows the result is

			String result = "";
			if(filename == null || filename.equals("")){
				System.out.println("Result of '"+command+"': \n");
				while(rs.next()){
					for(int i = 1; i <= anz;i++){
						result += rs.getString(i)+delimeter; // rs.get String always begins with 1 not 0.
					}
					result += "\n";
				}
				rs.close(); st.close(); con.close(); // close everything, so it can be open later again
				return result;
			}else{
				file = new File(filename);
				rf = new FileWriter(file);
				bf = new BufferedWriter(rf); 
				while(rs.next()){
					for(int i = 1; i <= anz;i++){
						bf.write(rs.getString(i)+delimeter);
					}
					bf.newLine();
				}
				rs.close(); st.close(); con.close(); bf.close();rf.close(); // close everything, so it can be open later again
				return "Output saved in "+filename;
			}
		} catch (SQLException e) {
			System.err.println("Failed to connect.");
			System.exit(1);
		} catch (FileNotFoundException ex) {
			System.err.println("File "+filename+" can not be created");
			System.exit(1);
		} catch (IOException ex) {
			System.err.println("Failed to write in "+filename);
			System.exit(1);
		}
		return null;
	}
	/**
	 * Creates a select with the arguments
	 * @param sort field to sort by
	 * @param sortdirection direction of sort
	 * @param condition a sql-syntax correct condition
	 * @param displayedfields which fields should be displayed
	 * @param table the used table
	 * @return sql-syntax correct select
	 */
	public String createSelect(String sort, String sortdirection, String condition, String displayedfields, String table){
		String befehl = "select "+displayedfields+" from "+table;
		if(condition != null && condition.equals("") == false)
			befehl +=  " where "+condition;
		if(sort != null && sort.equals("") == false)
			befehl += " order by "+sort+" "+sortdirection;
		befehl += " ;";
		return befehl;
	}
}

