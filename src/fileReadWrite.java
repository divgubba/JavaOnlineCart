import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
public class fileReadWrite{
	public boolean readFile(String strUser, String strPswd) {
		boolean validCred = false;
		//Hashmap for username,password 
		HashMap<String, String> upMap = new HashMap<String, String>();
		
		try {
			String strLine;
			String token=",";
			
			//array holding username and password
			String[] arrayUserPass = new String[2];
			
			// read & open file
			BufferedReader buffRead = new BufferedReader(new FileReader("credentials.txt"));
			while((strLine = buffRead.readLine()) != null) {
				// split username and password by comma
				arrayUserPass = strLine.split(token);
				
				//validate
				if(arrayUserPass[0].trim().isEmpty() == false || arrayUserPass[1].trim().isEmpty() == false) {			
					// stores key = username, value = password
					upMap.put(arrayUserPass[0], arrayUserPass[1]);
				}
			}
			//Check whether username and password input matches to text file 
			if (upMap.containsKey(strUser) && upMap.get(strUser).equals(strPswd) ) {
				validCred = true;
				//if matches so returns true, open catalog section
				//OnlineCart.main(args);
			}else {
				validCred = false;
			}

			buffRead.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			// catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Unable to read file");
			// catch block 
			e.printStackTrace();
		}
		return validCred;
		
	}
	
	//“catalog_backup_2018_11_19_19_45_32.txt” backup file needs to be created 
	// all current catalog items and their attributes should be in text file 
	public void backupFile(ArrayList<Books> Abook, ArrayList<DVDs> Advd) throws IOException {
		String fileBackupName = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		String fileCompleteName = "catalog_backup_" + fileBackupName;
		BufferedWriter writeToFile = new BufferedWriter(new FileWriter(fileCompleteName));
		System.out.println("**Catalog**");
    	//Display Books and Audiobooks in Array List
		System.out.println("Books and AudioBooks:");
    	for(int b = 0; b < Abook.size(); b++) {   		
    		writeToFile.write(Abook.get(b).toString());
    	}
    	//Display DVDs in Array List
		System.out.println("DVDs:" );			 
    	for(int d = 0; d < Advd.size(); d++) {
    		writeToFile.write(Advd.get(d).toString());
    	}
    	writeToFile.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
