import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Generator {

	public static void main(String[] args) throws IOException {

		File dict = new File("english.txt");
		Scanner read = new Scanner(dict);
		ArrayList<String> wordBank = new ArrayList<>();
		
		while (read.hasNextLine()) {
			wordBank.add(read.nextLine());
		}
		
		printRandomPassword(wordBank);
		
		read.close();
	
		Scanner in = new Scanner(System.in);
		
//		System.out.println("Please enter a length for your password: ");
//		int length = in.nextInt();
//		
//		menu(length);
		
		in.close();

	}
	
	public static void menu(int length) {
		
		boolean isActive = true;
		
		System.out.println("Options for new password of length " + length);
		
		while (isActive) {
			
		}
		
	}
	
	public static String genRandomChars() {
		String out = "";
		
		return out;		
	}
	
	public static String genRandomNums() {
		String out = "";
		
		return out;
		
	}
	
	public static String genRandomSymbs() {
		String out = "";
		
		return out;	
	}
	
	public static void printRandomPassword(ArrayList<String> bank) {
		
		int size = bank.size();
		int times = 0;
		String pass = "";
		
		while (times < 4) {
			int random = (int)(Math.random() * (size + 1));
			pass += bank.get(random) + " ";
			times++;
		}
		
		System.out.println(pass);
		
	}

}
