package jsons;

import org.json.simple.JSONObject;

public class JsonConfig {
	public static void main(String[] args) {
		
		JSONObject passDem = new JSONObject();
		passDem.put("InsertPassword_length", InsertPassLengtCheck());
		passDem.put("InsertPassword_includes", InsertPassIncludesCheck());
		passDem.put("LogInTrys", LogInAtt());
		passDem.put("is_vip", new Boolean(true));

	      System.out.print(passDem);
	   }

	private static Object LogInAtt() {
		
		return null;
	}

	private static Object InsertPassIncludesCheck() {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object InsertPassLengtCheck() {
		// TODO Auto-generated method stub
		return null;
	}


		
	}


