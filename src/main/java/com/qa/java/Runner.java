package com.qa.java;

import java.sql.SQLException;
import java.util.Scanner;


public class Runner {
 static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		DBConnection con = new DBConnection();
		String result;
		boolean flag = true;
		do {

			System.out.println("Which operation would you like to perform:");
			menu();
			result = scanner.nextLine();
			result = result.equals("") ? "exit" : result;
			
			switch (result) {
			case "create":
				System.out.println("Enter a name");
				String name = scanner.nextLine();
				con.createUser(name);
				break;
			case "read":
				System.out.println("Enter an id");
				int id = scanner.nextInt();
				con.readOne(id);
				break;
			case "read all":
				con.readAll();
				break;
			case "delete":
				System.out.println("Select an id you would like to delete");
				int deleteid = scanner.nextInt();
				con.delete(deleteid);
				break;
			case "update":
				System.out.println("Which id would you like to upadte");
				int uid = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Create a name");
				String Uname = scanner.nextLine();
				con.Update(uid, Uname);
				break;
			case "exit":
				flag = false;
				System.out.println("Exiting App");
				break;

			default:
				System.out.println("Invalid operation, try again.");
				break;
			}

		} while (flag);
		con.tearDown();
		scanner.close();
	}

	public static void menu() {
		System.out.println("1. Create \n 2. read\n 3.Read all\n 4. Delete\n 5. Update\n");
	}

}
