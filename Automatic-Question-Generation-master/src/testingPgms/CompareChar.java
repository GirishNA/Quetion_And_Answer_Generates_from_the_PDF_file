package testingPgms;

public class CompareChar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str = "abcdxyzertd";
		
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i) > 'z') {
				System.out.println("------------>");
			}else {
				System.out.println(str.charAt(i));
			}
		}
	}

}
