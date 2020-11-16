//Assignment 2: Divya Gubba
/*
 * The program will work as an online store that sells dvds and books. The program will allow the user
 *  to choose to display the books inventory or dvds inventory in order from low to high price, will allow 
 *  the user to add a book or dvd to cart and display the items and their corresponding prices added to to cart, 
 *  and will allow the user to checkout the books, cancel the order, or exit the store. 
 * The program allows the user the 8 options to choose from which are explained above, and will allow the user 
 *  to enter them until user decides to exit the store. 
 * The program calculates the total price of the items added to cart by the user and displays the cart and the total
 *  price to the user. 
 * 
 */
import java.util.ArrayList;
import java.util.Collections;
//import java.util.List;
import java.util.Scanner;
public class Customer {
	static int myStaticVar = 0;
    public static void customerCart(final ArrayList<Books> bookArr, final ArrayList<DVDs> dvdArr){
    	//Scanner object to read input
    	Scanner optionInput = new Scanner(System.in);
    	/*
    	// Books Arraylist to buy from
    	ArrayList<Books> bookArr = new ArrayList<Books>();
    	//Initialize arraylist wtih these books
    	bookArr.add(new Books("Sarah B.", "Intro to Java", 45.99, 4123));
    	bookArr.add(new Books("Trevor A.", "Intro to C++", 89.34, 1234));
    	bookArr.add(new Books("Devon R.", "Python", 100.00, 4632));
    	bookArr.add(new Books("Steve F.", "Perl", 25.00, 1536));
    	bookArr.add(new Books("Rachel M.", "C#", 49.99, 6345));
    	
    	// DVD Arraylist to buy from 
    	//title, String director, double price, int year, int dvdCode
    	//Initialize arraylist wtih these dvds
       	ArrayList<DVDs> dvdArr = new ArrayList<DVDs>();
    	dvdArr.add(new DVDs("Snow White", "Disney", 19.99, 1950, 4121));
    	dvdArr.add(new DVDs("Cinderella", "Disney", 24.99, 1940, 4114));
    	dvdArr.add(new DVDs("Dumb", "Disney", 17.99, 1945, 4156));
    	dvdArr.add(new DVDs("Bambi", "Disney", 21.99, 1936, 4133));           	
    	dvdArr.add(new DVDs("Frozen", "Disney", 24.99, 2016, 4143));
    	*/
        //array for names of items user wishes to purchase (books or dvds)
        //corresponding prices to what user wishes to purchase
        ArrayList<String> cartUserArr = new ArrayList<String>();
        ArrayList<Double> cartPriceArr = new ArrayList<Double>();
        ArrayList<Integer> cartCodeArr = new ArrayList<Integer>();
        
        //counter for cart
        int cartCount = 0;
        
        //Initialization of variables 
        String strBooks = "Books";
        String strDvds = "DVDs";
        //Within a loop, if user enters option that is not 1,2,3,4,5,6,7,8, display 
        //the message "This option is not acceptable" and loop back to redisplay 
        //the menu. continue until correct option is input.    
        int intOption;
        int codeOption;
        do {
			//display menu to the user
			displayMenu();

        	//allows user to enter option 
        	String userInpOptNum = optionInput.next();
        
        	// CHANGEVALIDATION
        	//to check user option for validity and return the valid integer
        	intOption= checkInputInvNum(userInpOptNum);
        	
        	
    		//If not valid(options not int from 1-9),loop back to redisplay menu
    		if(intOption<1 || intOption>9){

    			//print out error message
    			System.out.println("This option is not acceptable");

    			//continue used to get out of inner loop and will go to while statement 
    			//  where if userInput is not 8, which in this case it would not be, it will go back to do 
    			continue;
    		}
    		
    		//Switch cases for user option 
    		switch (intOption) {
    		case 1:
    			//option 1: call method displayArrays() for displaying Books in order from lowest to highest price
    			displayArrays(bookArr, dvdArr,strBooks);
    			break;
    			
    		case 2:
    			//option 2: call method displayArrays() for displaying DVDs in order from lowest to highest price
    			displayArrays(bookArr, dvdArr, strDvds);
    			break;
    			
    		case 3:
    			int nISBN = 0;
    			nISBN = addBook (bookArr);
    			boolean bBookFound = false;
    			//ADD BOOK TO CART BY ISBN NUM:
    			if( nISBN != 0) {
		    		for( Books bk : bookArr) {
		    			if(bk.getISBN() == nISBN) {
		    				//private static void onlineCart(String strTitle, double dPrice, int ISBN, ArrayList<String> cartItemsArr, ArrayList<Double> cartPriceArr, ArrayList<Integer> cartCodeArr ) {
		    	    		onlineCart(bk.getTitle(), bk.getPrice(), bk.getISBN(), cartUserArr, cartPriceArr, cartCodeArr);
		    	    		bBookFound = true;
		    	    		break;
		    			}
		    		}
		    		if(bBookFound == false) {
		    			System.out.println("The Book ISBN not found");
		    		}
    			}
    			break;
/*
    			//option 3: call method getInventoryNumber() to be able to add books to cart
    			int invUserOptionBook = getInventoryNumber();
    			//System.out.print(invUserOptionBook);
            	//if user chose -1, redisplay menu
            	if (invUserOptionBook == -1) {
            		break;
            	}else {
            		//add book to cart based on invUserOptionBook
            		onlineCart(invUserOptionBook, bookArr.get(invUserOptionBook-1).getTitle(), bookArr.get(invUserOptionBook-1).getPrice(), cartUserArr, cartPriceArr, cartCount);
            		//cartCount is incremented to keep count of how many items are in cart
            		cartCount++;
            		break;        		
            	}
            	   */        	
    		case 4:
    			//ADD DVD TO CART BY ISBN NUM:
    			int nDvdCode = 0;
    			boolean bDVDFound = false;
    			nDvdCode = addDvd (dvdArr);
    			//ADD BOOK TO CART BY ISBN NUM:
    			if( nDvdCode != 0) {
		    		for( DVDs dvd : dvdArr) {
		    			if(dvd.getDvdCode() == nDvdCode) {
		    	    		onlineCart(dvd.getTitle(), dvd.getPrice(), dvd.getDvdCode(), cartUserArr, cartPriceArr, cartCodeArr);
		    	    		bDVDFound = true;
		    	    		break;
		    			}
		    		}
    			}
	    		if(bDVDFound == false) {
	    			System.out.println("The Book ISBN not found");
	    		}
    			break;

/*
    			//option 4: call method getInventoryNumber() to be able to add DVDs to cart
    			int invUserOptionDvd = getInventoryNumber();
            	//if user chose -1, redisplay menu
            	if (invUserOptionDvd == -1) {
            		break;
            	}else {
            		//add dvd to cart based on invUserOptionDvd
            		onlineCart(invUserOptionDvd, dvdArr.get(invUserOptionDvd-1).getTitle(), dvdArr.get(invUserOptionDvd-1).getPrice(), cartUserArr, cartPriceArr, cartCount);
            		//cartCount is incremented to keep count of how many items are in cart
            		cartCount++;
            		break;
            	}
    			*/
    		case 5:
    			//Delete book from cart BY ISBN NUM:
    			//Prompt to enter code to remove item by code
    			System.out.println("Enter ISBN code to remove book: ");
    			//Allow for user input
    			String userInpBookCode1 = optionInput.next();
    			//check that the input is valid
    			codeOption = checkNumValid(userInpBookCode1);
    			//will either remove book based on code or display message/menu/catalog
    			//removeBook (ArrayList <Books> bList, ArrayList <DVDs> DvDList, int codeISBN)
    			removeBook (cartUserArr, cartPriceArr, cartCodeArr, codeOption);
    			break;

    		case 6:
    			//Delete DVD from cart BY ISBN NUM:
    			//Prompt to enter code to remove item by code
    			System.out.println("Enter ISBN code to remove dvd: ");
    			//Allow for user input
    			String userInpDvdCode1 = optionInput.next();
    			//check that the input is valid
    			codeOption = checkNumValid(userInpDvdCode1);
    			//will either remove book based on code or display message/menu/catalog
    			//private static void removeDVD (final ArrayList <String> dCartTitleList, final ArrayList <Double> dCartPriceList, final ArrayList<Integer> dCartCodeArr, int dvdCode)
    			removeDVD (cartUserArr, cartPriceArr, cartCodeArr, codeOption);
    			break;

    		case 7:
    			// View Cart: If arrays are empty displays "your cart is empty" followed by menu
    			if (cartUserArr.size() == 0) {
    				System.out.println("Your cart is empty.");
    				displayMenu();
    			}
    			//getTotal method is called to get the current total
    			double totCartPrice = getTotal(cartPriceArr);
    			//overloaded method used to display inventory
    			displayArrays(cartUserArr, cartPriceArr, totCartPrice);
    			break;
    			
    		case 8:
    			//Checkout: call method getTotal() which accepts as input
    			double totalPrice = getTotal(cartPriceArr);
    			System.out.print("Total price of items in cart: ");
    			System.out.printf("%.2f", totalPrice);
    		
    			//clear arrays after displaying the total for the user and set items in cart counter back to 0
    			clearArrays(cartUserArr, cartPriceArr);
    			cartCount = 0;
    			break;
    		/*
    		case 7:
    			//Cancel Order: to cancel the order clear arrays and set items in cart counter back to 0
    			clearArrays(cartUserArr, cartPriceArr);
    			cartCount = 0;
    			break;  */  			
    			
    		case 9:
    			break;
    		}
    		
    		//until option 8 is entered, keep looping
        } while( intOption != 9);
        
        //optionInput.close();
   }

    private static int addBook(final ArrayList<Books> bookArr) {
		// TODO Auto-generated method stub
    	Scanner userInputScan = new Scanner(System.in);
    	Validator vldtr = new Validator();
    	int inpISBN = 0;
    	
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
    	} while(vldtr.isPositiveInput(inpISBN) == false);
    	
    	return inpISBN;
	}

	private static int addDvd(final ArrayList<DVDs> dvdArr) {
		// TODO Auto-generated method stub
    	Scanner userInputScan = new Scanner(System.in);
    	Validator vldtr = new Validator();
    	int inpDCode = 0;
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
    	} while(vldtr.isPositiveInput(inpDCode) == false);
    	
    	return inpDCode;
	}

		//Method, Original Menu display to the user 
        private static void displayMenu(){

	        System.out.println("**Welcome to the Comets Books and DVDs Store**");

	        System.out.println("Choose from the following options: ");

	        String [] displayMenuArr = new String [] {"Browse books inventory (price low to high)",
	        		"Browse DVDs inventory (price low to high)",
	        		"Add a book to the cart", 
	        		"Add a DVD to the cart", 
	        		"Delete a book from cart",
	        		"Delete a DVD from cart",
	        		"View cart", 
	        		"Checkout", 
	        		"Done Shopping" };

	        for(int i = 0; i < displayMenuArr.length; i++){
	        	System.out.println( i+1 + " - " + displayMenuArr[i]);
	        }
        }
/*
        //getInventoryNumber method to check validity of user selected option and loop until valid input is entered
        //then, will return the option number.
        private static int getInventoryNumber() {
        	//Scanner object to allow for input
        	Scanner invUserInp = new Scanner(System.in);
        	
        	int correctedIntInput = 0;
        	//method asks user to enter inventory number they wish to purchase from list in option 1
        	System.out.println("Enter inventory number you wish to purchase from list, and enter -1 if you wish to redisplay menu and not purchase: ");
        	//need to check that user input is a valid integer, if not will loop until integer is entered
        	while (!(invUserInp.hasNextInt()))
    		{
    			System.out.println("Please enter an integer."); 
    			invUserInp.next();
    		}
        	
        	//correctedIntInput is now a variable with the valid integer
    		correctedIntInput = invUserInp.nextInt();
    		
    		// Checks if choice is from 1-5, and if not the message is returned and will loop until it is from 1-5
    		while (correctedIntInput ==0 || correctedIntInput > 5 || correctedIntInput < -1)
    		{
    			System.out.println("Please enter an integer between 1 to 5, or -1:");
    			correctedIntInput = invUserInp.nextInt();
    		}
    		//invInp.close();
    		return correctedIntInput;	
        }
     */   
        //Method used to check if valid integer is entered using try catch and will return an integer value 
        private static int checkInputInvNum(String enterText) {
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
        // Method to Remove Books
        private static void removeBook (final ArrayList <String> bCartTitleList, final ArrayList <Double> bCartPriceList, final ArrayList<Integer> bCartCodeArr, int codeISBN) {
        	//remove book
        	boolean bfound = false;
        	for (int i = 0; i < bCartTitleList.size(); i++) {
        		if (bCartCodeArr.get(i) == codeISBN) {
        			bCartTitleList.remove(i);   
        			bCartPriceList.remove(i);
        			bCartCodeArr.remove(i);
        			bfound = true;
        			//remove from Cart
        			//displayCatalog(bList, DvDList);
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
        private static void removeDVD (final ArrayList <String> dCartTitleList, final ArrayList <Double> dCartPriceList, final ArrayList<Integer> dCartCodeArr, int dvdCode) {
        	//remove DVD
        	boolean bfound = false;
        	for (int i = 0; i < dCartTitleList.size(); i++) {
        		if (dCartCodeArr.get(i) == dvdCode) {
        			dCartTitleList.remove(i);  
        			dCartPriceList.remove(i);
        			dCartCodeArr.remove(i);
        			bfound = true;
        			//remove from Cart
        			//displayCatalog(bList, DvDList);
        			break;
        		} 
        	}
        		if (bfound == false) {
        			//if ISBN is not found, display message and return to main menu
        			System.out.println("The DVD doesn't exist in the Catalog");
        			//return to main menu********
        		}    	  		
        }

        //if option 1 or 2 is entered, this method will display inventory by lowest to highest prices
        private static void displayArrays(final ArrayList<Books> itemsBArray, final ArrayList<DVDs> itemsDArray, String itemType){
          //array for inventory number for displaying in table
 
            if(itemType == "Books") {
            	Collections.sort(itemsBArray);
                System.out.printf("%-20s%-21s%8s\n", "ISBN Code           ",itemType, "  Prices");
                System.out.printf("%s\n","-------------------------------------------------");            
                //display Inventory Number, Books, Prices
               for (int i = 0; i < itemsBArray.size(); i++) {
                	System.out.printf("%-20s%-20s%3s%1.2f\n", Integer.toString(itemsBArray.get(i).getISBN()), itemsBArray.get(i).getTitle(), "$", itemsBArray.get(i).getPrice());
                }

            }
            if(itemType == "DVDs") {
            	Collections.sort(itemsDArray);            	
                System.out.printf("%-20s%-21s%8s\n", "DVD  Code           ",itemType, "  Prices");
                System.out.printf("%s\n","-------------------------------------------------");            
            
	            //display Inventory Number, Books, Prices
	            for (int i = 0; i < itemsDArray.size(); i++) {
	            	System.out.printf("%-20s%-20s%3s%1.2f\n", Integer.toString(itemsDArray.get(i).getDvdCode()), itemsDArray.get(i).getTitle(), "$", itemsDArray.get(i).getPrice());
	            }
            }
        }	
                
        //method for allowing selected items to be added to the online cart
        private static void onlineCart(String strTitle, double dPrice, int ISBN, final ArrayList<String> cartItemsArr, final ArrayList<Double> cartPriceArr, final ArrayList<Integer> cartCodeArr ) {
        	//add the correct item and corresponding price to the cart array
        	//inventoryNum-1 is the right index
        	//need to check if cart is full before adding inventory
        	//cart items are set to the books/Dvd array while cart prices are set to the prices of books/dvds array
        	cartItemsArr.add(strTitle);
        	cartPriceArr.add(Double.valueOf(dPrice));
        	cartCodeArr.add(ISBN);
        }
        
        
        private static void displayArrays(final ArrayList<String> cartItemsArr, final ArrayList<Double> cartPriceArr, double total) {
        	
        	//if cartItemsArr are empty display "your cart is empty" followed by the menu
        	if(cartItemsArr.size() == 0) {
        		System.out.println("your cart is empty");
        		
        	//display the cart items for overloaded method
        	}else {
        		System.out.printf("%s\n","Items             Prices"); 
        		System.out.printf("%s\n","------------------------"); 
        		for (int k = 0; k < cartItemsArr.size(); k++) {
        			System.out.printf("%-17s%1s%6.2f\n",cartItemsArr.get(k), "$", cartPriceArr.get(k).doubleValue());
        		}
         		System.out.printf("%s\n","------------------------"); 
       			System.out.printf("%-17s%1s%6.2f\n","Total + tax", "$", total);
                		
        	}     	        	
        }
        
        //the total price is calculated in this method
        private static double getTotal (final ArrayList<Double> cartPriceArr) {
        	//method returns total by looping over the prices and calculating the total
        	double priceTotal = 0;
        	double priceTotalTax = 0;
        	
        	//using an enhanced for loop to calculate total price
        	for (Double i: cartPriceArr) {
        		priceTotal += i.doubleValue();
        	}
        	//total price with tax is calculated and returned
        	priceTotalTax= priceTotal * 1.0825;
        	
        	return priceTotalTax;
        }
        
        //method to clear cart after checkout or if canceling the order
        private static void clearArrays(final ArrayList<String> cartItemsArr, final ArrayList<Double> cartPriceArr) {
        	cartItemsArr.clear();
        	cartPriceArr.clear();
        }
}		

