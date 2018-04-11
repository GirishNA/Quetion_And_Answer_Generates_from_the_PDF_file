package testingPgms;

public class RegexTesting {

	public static void main(String[] args) {

		String str = "Clearance to deal in exceptional 2. circumstances will be provided in writing.  Once clearance is received, the following applies: 1. ";
		
		String res = "";
		
		res = str.replaceAll("\\d.", "-");
		
		System.out.println(res);
	}

}
