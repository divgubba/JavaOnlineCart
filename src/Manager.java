//Assignment 2: Divya Gubba
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
	
	public static void mngrCatalog(ArrayList<Books> ALbook, ArrayList<DVDs> ALDvd, Connection con) {
    	//Scanner object to read input
    	Scanner optionInput = new Scanner(System.in);
    	//ArrayLists for book/Audiobook, and DVDs (used to add and remove from list, works as dynamic array)

    	//variable for menu option
    	int intOption;
    	//variable for codes to remove from dvd/book/audiobook
    	int codeOption;
    	
        do {
			//display menu to the user
			displayMenu(con);

        	//allows user to enter option 
        	String userInpOptNum = optionInput.next();        	
        	     
        	//to check user option for validity and return the valid integer
        	intOption= checkNumValid(userInpOptNum);
        	
    		//If not valid(options not int 1,2,3,4,5,6,9),loop back to redisplay menu
    		if(intOption<1 || intOption>9 || intOption == 8 ){

    			//print out error message
    			System.out.println("This option is not acceptable");

    			//continue used to get out of inner loop and will go to while statement 
    			//  where if userInput is not 8, which in this case it would not be, it will go back to do 
    			continue;
    		}
    		
    		//Switch cases for user option 
    		switch (intOption) {
    		// Adds book
    		case 1: 
    			addBook(ALbook, false);
    			
    			break;
    		// Adds AudioBook
    		case 2:
    			addBook(ALbook, true);
    			break;
    		// Add Dvd
    		case 3:
    			addDvd(ALDvd);
    			break;
    		// Remove Book
    		case 4:
    			//Prompt to enter code to remove item by code
    			System.out.println("Enter ISBN code to remove: ");
    			//Allow for user input
    			String userInpBookCode = optionInput.next();
    			//check that the input is valid
    			codeOption = checkNumValid(userInpBookCode);
    			//will either remove book based on code or display message/menu/catalog
    			removeBook (ALbook, ALDvd, codeOption);
    			break;
    		// Remove DVD
    		case 5:
    			//Prompt to enter code to remove item by code
    			System.out.println("Enter DVD code to remove: ");
    			//Allow for user input
    			String userInpDVDCode = optionInput.next();
    			//check that the input is valid
    			codeOption = checkNumValid(userInpDVDCode);
    			//will either remove DVD based on code or display message/menu/catalog
    			removeDVD (ALbook, ALDvd, codeOption);    			
    			break;
    		// Display Catalog
    		case 6:
    			displayCatalog(ALbook, ALDvd);
    			break;
    		//Create backup file
    		case 7:
    			fileReadWrite fReadWrite = new fileReadWrite();
    			try {
					fReadWrite.backupFile(ALbook, ALDvd);
				} catch (IOException e) {
					e.printStackTrace();
				}
    			break;
    		//Exit Store
    		case 9:
    			break;
    		}
    
        } while( intOption != 9);  	
        
       //close scanner object
       //optionInput.close();
	}


//Method, Original Menu display to the user 
	private static void displayMenu(Connection con) throws SQLException{
		//PreparedStatement stmt = con.prepareStatement("SELECT * FROM \"TestSchema\".\"Books\" WHERE \"ISBN\"='1412'");
		//ResultSet Rs = stmt.executeQuery();
		//while (Rs.next()) {
		//	System.out.println(Rs.getString("Author")+ " "+ Rs.getString("ISBN")+ " "+ Rs.getFloat("Price")+ " "+ Rs.getString("Title"));
		//}
		//PreparedStatement stmt1 = con.prepareStatement("INSERT INTO \"TestSchema\".\"Books\"(\"Author\", \"ISBN\", \"Price\", \"Title\") VALUES ('myauthor', '12222', 5, 'mytitle');");
		//ResultSet Rs1 = stmt.executeQuery();

	    System.out.println("**Welcome to the Comets Books and DVDs Store**");
	
	    System.out.println("Choose from the following options: ");
	    // *******************REPLACED WITH DATABASE
	    PreparedStatement stmt = con.prepareStatement("SELECT * FROM \"MyTestDB\".\"catalogmenu\"");
	    ResultSet Rs = stmt.executeQuery();
	    while (Rs.next()) {
			System.out.println(Rs.getInt("menu_id")+ " - "+ Rs.getString("menu_description"));
	    	
	    }
/*	    System.out.println("1 - Add Book");
	    System.out.println("2 - Add AudioBook");
	    System.out.println("3 - Add DVD");
	    System.out.println("4 - Remove Book");
	    System.out.println("5 - Remove DVD");
	    System.out.println("6 - Display Catalog");
	    System.out.println("7 - Create Backup File");
	    System.out.println("9 - Exit Catalog Section");
*/	}
	
//Method used to check if valid integer is entered using try catch and will return an integer value 
    private static int checkNumValid(String enterText) {
       	boolean error = false;
		Scanner userInputScan = new Scanner(System.in);
    	do {
    		try {
    			//check if text entered is correct
    			Integer.parseInt(enterText);
    			//this means there is no error
    			error = false;    			
    		}catch (NumberFormatException e){
    			//set error = true to loop again and ask for valid input
    			error = true;
    			System.out.println("Enter valid int: ");
   				//need to ask for user input again & need to get this checked too
   				//in the try
   				enterText = userInputScan.next();
     		}
    		
   		}while (error == true);
   			//will return int because the point is to as for valid input(an
   			//integer)
   			//method used to convert String to Integer is parseInt()
  			return Integer.parseInt(enterText);        	
    }

//Method to add Book
    private static void addBook (ArrayList <Books> bList, boolean audbook) {
    	Scanner userInputScan = new Scanner(System.in);
    	Validator vldtr = new Validator();
    	String inpTitle = "";
    	String inpAuthor = "";
    	double inpRunTime = 0.0;
    	double inpPrice = 0.0;
    	int inpISBN = 0;
    	int validity = 0;
    	//Allow user to first enter ISBN code because need to be unique codes
    	//Check that code entered is integer
    	do {
    		System.out.println("Enter ISBN code: ");
    		//string input
    		String inpStrISBN = userInputScan.nextLine();
    		//String input used to check if it is int
    		if (Validator.isInt(inpStrISBN) == true) {
    			inpISBN = Integer.valueOf(inpStrISBN);
    		}
    		while (Validator.isInt(inpStrISBN) == false) {
    			System.out.println("Enter correct ISBN code: ");
    			inpStrISBN = userInputScan.nextLine();
    			if (Validator.isInt(inpStrISBN) == true) {
    				inpISBN = Integer.valueOf(inpStrISBN);
    			}
    		}
      		//need to check that ISBN has not been used before
    		//if valid, validity = 0; if not valid, validity = 1
    		validity = Validator.checkBookCode (bList, inpISBN);
    		//*****************
    		//System.out.println("isValid = " + validity + " and ISBN is : " + inpISBN);
    		//*****************
    	} while(vldtr.isPositiveInput(inpISBN) == false|| validity == 1);
    	
    	//Allow user to enter Book titles
    	do {
        	System.out.println("Enter book title: ");
	    	inpTitle = userInputScan.nextLine();   		
    	} while(vldtr.isNonEmptyString(inpTitle) == false);
    	
    	//Allow user to enter author name
    	do {
        	System.out.println("Enter author name: ");
    		inpAuthor = userInputScan.nextLine();
    	} while(vldtr.isNonEmptyString(inpAuthor) == false);
    	
    	//*******************
    	//	System.out.println("Enter price of book: $");
    	//	inpPrice = 2;
    	//********************
    	
    	//Allow user to enter book price  
    	do {
			System.out.println("Enter price of book: $");
			//string input 
			String inpStrPrice = userInputScan.nextLine();
			//string input used to check if it is double
			if (Validator.isDouble(inpStrPrice) == true) {
				inpPrice = Double.valueOf(inpStrPrice);
			}
			while (Validator.isDouble(inpStrPrice) == false) {
				System.out.println("Enter correct price of book: $");
				inpStrPrice = userInputScan.nextLine();	
				if (Validator.isDouble(inpStrPrice) == true) {
					inpPrice = Double.valueOf(inpStrPrice);
				}
			} 
    	} while(vldtr.isPositiveInput(inpPrice) == false);	
    	
    	//just adds book not audiobook to cart
    	if(audbook != true) {
        	//book object instantiated, add to ArrayList
        	Books book = new Books( inpAuthor, inpTitle, inpPrice, inpISBN);
        	bList.add(book); 
    	}
    	
    	//If audiobook, allow user to enter run time, and check if its valid double
	    if (audbook == true) {	
	    	do {
	    		System.out.println("Enter running time: ");
		    	String inpStrRunTime = userInputScan.nextLine();
		    	//string input used to chec if it is double
		    	if(Validator.isDouble(inpStrRunTime) == true) {
		    		inpRunTime = Double.valueOf(inpStrRunTime);
		   		}
		   		while (Validator.isDouble(inpStrRunTime) == false) {
		   			System.out.println("Enter correct running time: ");
		   			inpStrRunTime = userInputScan.nextLine();
	    			if (Validator.isDouble(inpStrRunTime) == true) {
	    				inpRunTime = Double.valueOf(inpStrRunTime);	    			
	    			}
		    	}
		    	AudioBooks audBook = new AudioBooks(inpAuthor, inpTitle, inpPrice, inpISBN, inpRunTime);  
		    	bList.add(audBook);	
		    	//add book from here to ArrayList<Books> bookArr = new ArrayList<Books>(); for customer catalog
		    	//add book objects to dvdBooksOnline class
		    	ArrayList<Books> bookCart = new ArrayList<Books>();
		    	
		    	
		    	
		    	
	    	} while(vldtr.isPositiveInput(inpRunTime) == false);
			//Just adds audiobook to cart
	    }
	    
    	
    }
    
 //Method to add DVDs
    private static void addDvd(ArrayList <DVDs> DList) {
    	Scanner userInputScan = new Scanner(System.in);
    	Validator vldtr = new Validator();
    	String inpTitle = "";
    	String inpDirector = "";
    	double inpPrice = 0.0;
    	int inpDCode = 0;
    	int inpYear = 0;
    	int validity = 0;
    	
    	//Allow user to first enter DVD code because need to be unique codes
    	//Check that code entered is integer
    	do {
    		System.out.println("Enter DVD code: ");
    		//string input
    		String inpStrDCode = userInputScan.nextLine();
    		//String input used to check if it is int
    		if (Validator.isInt(inpStrDCode) == true) {
    			inpDCode = Integer.valueOf(inpStrDCode);
    		}
    		while (Validator.isInt(inpStrDCode) == false) {
    			System.out.println("Enter correct DVD code: ");
    			inpStrDCode = userInputScan.nextLine();
    			if (Validator.isInt(inpStrDCode) == true) {
    				inpDCode = Integer.valueOf(inpStrDCode);
    			}
    		}
    		//need to check that ISBN has not been used before
    		//if valid, validity = 0; if invalid, validity = 1
    		validity = Validator.checkDvdCode (DList, inpDCode);
    		//**************
    		//System.out.println("Validity: " + validity + "and code: " + inpDCode);
    		//*************
    	} while(inpDCode<0 || validity == 1);
    	
    	//Allow user to enter DVD title
    	do {
	    	System.out.println("Enter DVD title: ");
	    	inpTitle = userInputScan.nextLine();   		
    	} while(vldtr.isNonEmptyString(inpTitle) == false);
    	
    	//Allow user to enter Directors name
    	do {
    		System.out.println("Enter Directors name: ");
    		inpDirector = userInputScan.nextLine();
    	} while(vldtr.isNonEmptyString(inpDirector) == false);
    	
    	//Allow user to enter DVD price
    	do {
    		System.out.println("Enter price of DVD: $");
    		//string input
    		String inpStrPrice = userInputScan.nextLine();
    		//String input used to check if double
    		if (Validator.isDouble(inpStrPrice) == true) {
    			//converts string to double and holds value in var.
    			inpPrice = Double.valueOf(inpStrPrice);
    		}
    		while (Validator.isDouble(inpStrPrice) == false) {
    			System.out.println("Enter correct Price of DVD: $");
    			inpStrPrice = userInputScan.nextLine();
    			if (Validator.isDouble(inpStrPrice) == true) {
        			//converts string to double and holds value in var.
        			inpPrice = Double.valueOf(inpStrPrice);
        		}
    		}
       	} while(vldtr.isPositiveInput(inpPrice) == false);
    	    	
    	//Allow user to enter year price
    	do {
    		System.out.println("Enter Year of DVD: ");
    		//string input
    		String inpStrYear = userInputScan.nextLine();
    		//String input used to check if it is int
    		if (Validator.isInt(inpStrYear) == true) {
    			inpYear = Integer.valueOf(inpStrYear);
    		}
    		while (Validator.isInt(inpStrYear) == false) {
    			System.out.println("Enter Year of DVD: ");
    			inpStrYear = userInputScan.nextLine();
    			if (Validator.isInt(inpStrYear) == true) {
    				inpYear = Integer.valueOf(inpStrYear);
    			}
    		}
    	} while(vldtr.isPositiveInput(inpYear) == false);
    	
    	//DVD object instantiated, add to ArrayList
    	DVDs dvd = new DVDs( inpTitle,  inpDirector,  inpPrice,  inpYear,  inpDCode);
    	DList.add(dvd);    	
    }
 // Method to Remove Books
    private static void removeBook (ArrayList <Books> bList, ArrayList <DVDs> DvDList, int codeISBN) {
    	//remove book
    	boolean bfound = false;
    	for (int i = 0; i < bList.size(); i++) {
    		if (bList.get(i).getISBN() == codeISBN) {
    			bList.remove(i);   
    			bfound = true;
    			//remove from Catalog
    			displayCatalog(bList, DvDList);
    			break;
    		} 
    	}
    		if (bfound == false) {
    			//if ISBN is not found, display message and return to main menu
    			System.out.println("The Book doesn't exist in the Catalog");
    			//return to main menu********
    		}    	  		
    }
    
// Method to Remove DVDs
    private static void removeDVD (ArrayList <Books> bList, ArrayList <DVDs> DvDList, int dvdCode) {
    	//remove DVD
    	boolean dfound = false;
    	for (int i = 0; i < DvDList.size(); i++) {
    		if (DvDList.get(i).getDvdCode()== dvdCode) {
    			DvDList.remove(i);   
    			dfound = true;
    			//remove from Catalog
    			displayCatalog(bList, DvDList);
    			break;
    		} 
    	}
    		if (dfound == false) {
    			//if ISBN is not found, display message and return to main menu
    			System.out.println("The Book doesn't exist in the Catalog");
    			//return to main menu********
    		}    	  		
    }
    
 // Method to display Catalog
    private static void displayCatalog(ArrayList <Books> bList, ArrayList <DVDs> DvDList) {
    	System.out.println("**Catalog**");
    	//Display Books and Audiobooks in Array List
		System.out.println("Books and AudioBooks:");
    	for(int b = 0; b < bList.size(); b++) {   		
    		System.out.println(bList.get(b).toString());
    	}
    	//Display DVDs in Array List
		System.out.println("DVDs:" );			 
    	for(int d = 0; d < DvDList.size(); d++) {
    		System.out.println(DvDList.get(d).toString());
    	}    	
    }    

}
