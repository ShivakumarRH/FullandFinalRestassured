package payeezy.datadriven;

public class jsaj {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "test string (67)";
		
//		s.indexOf("(");
		int abc=s.lastIndexOf("(");
		/*
						 * 
						 * s = s.substring(s.indexOf("(")); s = s.substring(s.indexOf(")")); //s =
						 * s.substring(0, s.indexOf(")"));
						 */
		System.out.println(abc);
	}

}
