package socketprg;

import java.util.ArrayList;
import java.util.List;

public class HandlerBai2 {
	private static List<String> emailList = new ArrayList<String>();

	
	static String chuanhoa(String name) {
		String[] arrName = name.split("\\s+");
		int lastElm = arrName.length - 1;
		String email = arrName[lastElm];
		
		for (int i = 0 ; i < arrName.length -1 ; i ++) {
			email += arrName[i].charAt(0);
		}
		
		email = email.toLowerCase() + "@ptithcm.edu.vn";
		emailList.add(email);
		
		return printEmail();
		
	}
	
	static String printEmail() {
		String result = "";
		for(String e : emailList) {
			result += e + "\n";
		}
		
		return result;
	}

}
