import java.io.File;

import com.learnprogrammingnow.ttsclient.dao.SQLiteHelper;


public class Main {

	public static void main(String[] args) {
		SQLiteHelper sqliteHelper = SQLiteHelper.getDB("database.db");
		System.out.println("SQLiteHelper: " + sqliteHelper);
	}

}
