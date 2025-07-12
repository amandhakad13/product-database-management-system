package test;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.ProductDAO;
import entity.Product;

public class ProductTest1 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ProductDAO pdao = new ProductDAO();
		
		int choice = 0;
		
		
		do {
			System.out.println("-----------Product Menu-----------");
			System.out.println("1. Insert Record");
			System.out.println("2. Delete Record");
			System.out.println("3. Display All Record");
			System.out.println("4. Search Record");
			System.out.println("6. Exit The Application");
			System.out.println();
			System.out.print("Enter Your Choice : ");
			choice = sc.nextInt();
		
			switch(choice) {
				case 1 : {
					System.out.print("Enter id : ");
					int id = sc.nextInt();
					System.out.print("Enter name : ");
					String name = sc.next();
					System.out.print("Enter price : ");
					float price = sc.nextFloat();
					System.out.print("Enter quantity : ");
					int quantity = sc.nextInt();
					System.out.print("Enter manufacture date : ");
					String manu_date = sc.next();
					System.out.print("Enter discount : ");
					float discount = sc.nextFloat();
					
					Product p = new Product(id, name, price, quantity, manu_date, discount);
					int isInsert=0;
					try {
						isInsert = pdao.insert(p);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					if(isInsert>0)
						System.out.println("Record Stored Successfully");
					else 
						System.out.println("Something Went Wrong");
				}
				break;
					
				case 2 : {
					System.out.print("Enter id : ");
					int id = sc.nextInt();
					Product p = null;
					try {
						p = pdao.search(id);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(p==null) {
						System.out.println("Prodcut not found");
					}
					else {
						String ch;
						System.out.print("Do you want to delete Y/N : ");
						ch = sc.next();
						if(ch.equalsIgnoreCase("y")) {
							int isDelete = 0;
							try {
								isDelete = pdao.delete(id);
							} catch (SQLException e) {
								e.printStackTrace();
							}
							if(isDelete==1) {
								System.out.println("Prodcuted Deleted");
							}
							else {
								System.out.println("Something went wrong");
							}
						}
						else {
							System.out.println("Operation Terminate");
						}
					}	
				}
				break;
					
				case 3 : {
					List<Product> pl = null;
					try {
						pl = pdao.display();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					for(Product ps : pl) {
						System.out.println(ps);
					}
				}
				break;
					
				case 4 : {
					System.out.print("Enter id : ");
					int id = sc.nextInt();
					Product p = null;
					try {
						p = pdao.search(id);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if(p==null) {
						System.out.println("Proudct not found");
					}
					else {
						System.out.println(p);
					}
					break;
				}
					
				case 6 :
					System.out.println("Thank You For Using Our Program");
					break;
					
				default :
					System.out.println("Invalid Choice");
					break;
			}
		} while(choice != 6);
		
		sc.close();
	}

}
