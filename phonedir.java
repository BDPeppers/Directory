import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
//Briana Peppers
/*Purpose:maintains a list of records containing names and phone numbers,
 *prompts user to manipulate records
 *Algorithm:Create and display a menu of commands. Allow user input to be used a calling
 *mechanism for methods that complete the respective command
 *Data Structure:LinkedList
 *Input:user commands
 *Output:execution of commands
 * 
 *All scanner input should appear bold
 *current record = last record added
 */
class First{
	String first;
}
class Last{
	String last;
}
class Number{
	String number;
}
class phone implements Comparable{
	String first, last, number;
	public phone(String last, String first, String number) {
		this.last=last;
		this.first=first;
		this.number=number;
	}
	public String toString() {
		return "FirstName:" + first.toString() + 
				   " LastName:" + last.toString() + 
				   " PhoneNumber:" + number.toString();
	}
	public int compareTo(Object o) {
		phone a = (phone)o;
		if(this.last.compareTo(a.last)<0) {
			return -1;
		}
		if(this.last.compareTo(a.last)==0) { 
		return 0;
		}
		if(this.last.compareTo(a.last)>0) {
			return 1;
		}
		return 0;
	}
	
}
public class phonedir {
	static String format1 = "\\d{10}";
	static String format2 = "(?:\\d{3}-){2}\\d{4}";
	static String format3 = "\\(\\d{3}\\)\\d{3}-?\\d{4}";
	static Scanner input = new Scanner(System.in);
	
	static LinkedList<phone> records = new LinkedList<phone>();
	static LinkedList<First> firstnames = new LinkedList<First>();
	static LinkedList<Last> lastnames = new LinkedList<Last>();
	static LinkedList<Number> numbers = new LinkedList<Number>();
	
	public static void main(String [] args) {
		menu();
	}
	
	public static  void menu(){
		//display menu after every command finishes
		//displays menu ~ can exit menu - bring to a home screen
		//checks for valid commands throws exceptions
		String a = "a show records"; String d = "d delete"; String f = "f change first name";
		String l = "l change last name";	String n ="n add";	String p ="p change number";
		String s = "s select record to be current record";	String q ="q quit";
		System.out.println("MENU");
		System.out.printf("%-21s%-11s%s%n%n", a,d,f);
		System.out.printf("%-21s%-11s%s%n%n", l,n,p);
		System.out.printf("%-39s%s%n%n", s,q);
		String command = input.next();
		switch(command) {
		case "a" :
			allRecords();
			break;//terminates the switch
		case "d":
			deleteRecord();
			break;
		case "f":
			changeFirst();
			break;
		case "l":
			changeLast();
			break;
		case "n":
			addRecord();
			break;
		case "p":
			changeNumber();
			break;
		case "s":
			select();
			break;
		case "q":
			System.out.println("....Press the b key to return to menu");
			System.out.println("Press q again to power off");
			String quit = input.next();
			while(!quit.equalsIgnoreCase("q")|!quit.equalsIgnoreCase("b")) {
				if(quit.equalsIgnoreCase("q")) {
					System.exit(0);
				}if(quit.equalsIgnoreCase("b")) {
					input.nextLine();
					break;
				}else {
					System.out.println("Invalid Entry");
				}
	
			}
			break;
		default:
			System.out.println("Invalid Entry ->Try again");
		}
		menu();
	}
	public static void allRecords() {
		//shows all records
		System.out.printf("%-14s%-15s%s%n%n", "Last Name","First Name","Phone Number");
		Collections.sort(records);
		for(phone x: records) {
			System.out.printf("%-14s%-15s%s%n%n", x.last, x.first,x.number);
		}
	}
	public static void deleteRecord() {
		//delete the current record
			while(records.isEmpty()) {
				System.out.println("There are no records to delete\n");
				return;
			}
			records.removeLast();
			firstnames.removeLast();
			lastnames.removeLast();
			numbers.removeLast();

		}
	public static void changeFirst() { 
		//change the first name in the current record
			while(records.isEmpty()) {
				System.out.println("There are no records\n");
				return;
			}
			System.out.println("Enter new first name for the current record");
			String newname=input.next();
			firstnames.getLast().first=newname;
			records.getLast().first=newname;

		}
	public static void changeLast() {
		//change the last name in the current record
			while(records.isEmpty()) {
				System.out.println("There are no records\n");
				return;
			}
			System.out.println("Enter new last name for the current record");
			String newname = input.next();
			lastnames.getLast().last=newname;
			records.getLast().last=newname;
		}
		public static void addRecord() {
		//adds a new record	
			First addf = new First();
			Last addl = new Last();
			Number addn = new Number();
			
			System.out.println("ENTER FIRST NAME");
			String first2=input.next();
			addf.first=first2;	
			firstnames.add(addf);
			
			System.out.println("ENTER LAST NAME");
			String last2=input.next();
			addl.last=last2;
			lastnames.add(addl);
			System.out.println("ENTER PHONE NUMBER");
			String number = input.next();
			while(!number.matches(format1)&& !number.matches(format2) && !number.matches(format3)) {
				System.out.println("Please follow the format xxx-xxx-xxxx");
				number = input.next();
			}
			addn.number=number;
			numbers.add(addn);
			phone add = new phone(last2, first2, number);
			records.add(add);
			System.out.println(add);
		}

		public static void changeNumber() {
		//change the phone number in the current record
			while(records.isEmpty()) {
				System.out.println("There are no records");
				return;
			}
			System.out.println("Enter new number for current record");
			String number = input.next();
			while(!number.matches(format1)&&!number.matches(format2)&&!number.matches(format3)) {
				System.out.println("Please follow the format xxx-xxx-xxxx");
				number = input.next();
			}
			numbers.getLast().number=number;
			records.getLast().number=number;

		}
		public static void select() {
		//selects a record from the record list to become the current record
			while(firstnames.isEmpty()|firstnames.size()==1) {
				System.out.println("There are not enough records");
				return;
			}
			System.out.println("Enter the # of the of the record you want to be the current");
			System.out.printf("%-14s%-15s%s%n%n", "Last Name","First Name","Phone Number");
			Collections.sort(records);
			for(phone x: records) {
				int num =records.indexOf(x)+1;
				System.out.printf("%d %-13s%-16s%s%n%n", num, x.last, x.first,x.number);
				
			}
			System.out.println("Enter #");
			int grab = input.nextInt();
			while(grab<1|grab>records.size()) {
				System.out.println("Invalid Entry");
				grab=input.nextInt();
			}
			phone current = records.get(grab-1);
			records.remove(grab-1);
			records.addLast(current);
			for(phone x: records) {
				int num =1;
				System.out.printf("%d%-14s%-15s%s%n%n", num, x.last, x.first,x.number);
				num++;
			}
			System.out.println("NOTE:Show all records will reorganize the records");
		}
}
