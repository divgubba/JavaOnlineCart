import java.util.ArrayList;

public class Validator implements Acceptable {

	@Override
	public boolean isNonEmptyString(String s) {
		// TODO Auto-generated method stub
		if (s != null && s.trim().length() != 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isPositiveInput(double d) {
		if (d > 0.0) {
			return true;
		}
		return false;
	}

	// Method to check if input is Double
	public static boolean isDouble(String inpLine) {
		try {
			Double.parseDouble(inpLine);
			return true;
		} catch (NumberFormatException nfe) {
		}
		return false;
	}

	// Method to check if input is Integer
	public static boolean isInt(String inpLine) {
		try {
			Integer.parseInt(inpLine);
			return true;
		} catch (NumberFormatException nfe) {
		}
		return false;
	}

	// Method to check ISBN code
	public static int checkBookCode(ArrayList<Books> bList, int codeISBN) {
		// if valid, returns 0; if not valid, returns 1
		int validity = 0;
		// ***************
		// System.out.println("In CheckBookCode");
		// ***************
		// Check if ISBN code is in any of the ArrayLists and if not display message
		for (int i = 0; i < bList.size(); i++) {
			// if bList.get(i).getISBN() == codeISBN, means isValid
			if (bList.get(i).getISBN() == codeISBN) {
				// ***************
				// System.out.println("Im checking if validity is 1 or 0");
				// ***************
				System.out.println("Book with the ISBN code, " + codeISBN + ", already exists");
				validity = 1;
			}
		}
		return validity;
	}

	// Method to check dvd code
	public static int checkDvdCode(ArrayList<DVDs> DvDList, int dvdCode) {
		int validity = 0;
		// Check if dvd code is in any of the ArrayLists and if not display message
		for (int i = 0; i < DvDList.size(); i++) {
			if (DvDList.get(i).getDvdCode() == dvdCode) {
				System.out.println("Book with the ISBN code, " + dvdCode + ", already exists");
				validity = 1;
			}
		}
		return validity;
	}
}
