import java.io.*;

public class test {

	public static void main(String[] args) {
	
		File e = new File("C:\\E\\");
		File[] eCont = e.listFiles();
		
		System.out.println(eCont.length);

	}

}
