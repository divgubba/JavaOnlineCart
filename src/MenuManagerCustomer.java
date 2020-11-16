
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class MenuManagerCustomer {
	public static void main(String[] args) {
		
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/MyTestDB", "divyag", "divyag12");
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			if(con != null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} 

		
			Scanner scan = new Scanner(System.in);
			
	    	// Books Arraylist to buy from
	    	ArrayList<Books> bookArr = new ArrayList<Books>();
	    	//Initialize arraylist wtih these books
	    	bookArr.add(new Books("Sarah B.", "Intro to Java", 45.99, 4123));
	    	bookArr.add(new Books("Trevor A.", "Intro to C++", 89.34, 1234));
	    	//bookArr.add(new Books("Devon R.", "Python", 100.00, 4632));
	    	//bookArr.add(new Books("Steve F.", "Perl", 25.00, 1536));
	    	//bookArr.add(new Books("Rachel M.", "C#", 49.99, 6345));
	    	
	    	// DVD Arraylist to buy from 
	    	//title, String director, double price, int year, int dvdCode
	    	//Initialize arraylist wtih these dvds
	       	ArrayList<DVDs> dvdArr = new ArrayList<DVDs>();
	    	dvdArr.add(new DVDs("Snow White", "Disney", 19.99, 1950, 4121));
	    	dvdArr.add(new DVDs("Cinderella", "Disney", 24.99, 1940, 4114));
	    	//dvdArr.add(new DVDs("Dumb", "Disney", 17.99, 1945, 4156));
	    	//dvdArr.add(new DVDs("Bambi", "Disney", 21.99, 1936, 4133));           	
	    	//dvdArr.add(new DVDs("Frozen", "Disney", 24.99, 2016, 4143));
	    	
			Validator vldtr = new Validator();
			String roleChoice;
		do {	
			displayRoleMenu();
			roleChoice = scan.nextLine();
			//loop for checking A, B, or C and until empty
			while(vldtr.isNonEmptyString(roleChoice) == false && roleChoice != "A" && roleChoice != "B" && roleChoice != "C" ) {
				System.out.println("Please select valid choice (A,B, or C): ");		
				roleChoice = scan.nextLine();
			}	
			//calling for A,B,C
			optionDecision(roleChoice, bookArr, dvdArr, con);		
		//loops until C is chosen
		}while(roleChoice!="C");	
			
			
		}
		
		public static void displayRoleMenu() {
			System.out.println("**Welcome to the Comets Books and DVDs Store**");
			System.out.println("Please select your role:");
			System.out.println("A – store manager");
			System.out.println("B – customer");
			System.out.println("C – exit store");
		}
		
		public static void optionDecision(String sRole, ArrayList<Books> bookArr, ArrayList<DVDs> dvdArr, Connection cnnct) {
			fileReadWrite fReadWrite = new fileReadWrite();
			Scanner inpScan = new Scanner(System.in);
			Validator vldtr = new Validator();
			boolean validCred = false;
			String usernameInp;
			String passwordInp;
			switch(sRole) {
			case "A":
				System.out.println("Please enter your username: ");
				//to enter username
				usernameInp = inpScan.nextLine();
				while(vldtr.isNonEmptyString(usernameInp) == false) {
					System.out.println("Please enter non empty username: ");		
					usernameInp = inpScan.nextLine();
				}	
				//password
				System.out.println("Please enter your password: ");
				//to enter password
				passwordInp = inpScan.nextLine();
				while(vldtr.isNonEmptyString(passwordInp) == false) {
					System.out.println("Please enter non empty password: ");		
					passwordInp = inpScan.nextLine();
				}	
				//return true if valid credentials, false if not
				validCred = fReadWrite.readFile(usernameInp, passwordInp);
				if (validCred == true) {
					String args[]=new String[1];
					Manager.mngrCatalog(bookArr, dvdArr, cnnct);
				}else {
					System.out.print("Unrecognized Credentials");
				}
				
				break;
			case "B":
					String args[] = new String[1];
					//display menu for shopping section
					Customer.customerCart(bookArr, dvdArr);
				break;
			case "C":
				//program terminates
					System.exit(0);
				break;
			
			}
			
		}
		

}
