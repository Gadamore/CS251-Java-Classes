package sol8;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class hwk8 {
	public void main(String[] args){
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter template filename: ");
		String tLine = scan.nextLine();
		System.out.print("Enter dictionary filename: ");
		String dLine = scan.nextLine();
		System.out.print("Enter output filename: ");
		String oLine = scan.nextLine();
		
		Dictionary dictionary = loadDictionary(dLine);
		Template template = loadTemplate(tLine);
		
		try{
			PrintWriter out = new PrintWriter(template.fill(dictionary));
			
			out.println("stuff");
			out.close();
		}
		catch(FileNotFoundException e){
			
		}
		scan.close();
		
	}
	private Template loadTemplate(String filename){
		Template template = new Template(filename);
		
		try {
			Scanner in = new Scanner(new FileReader(filename));
			String line = in.nextLine();
			in.close();
		} catch (FileNotFoundException e) {
			System.out.print("not-a-file.txt");
			e.printStackTrace();
		}
		
		return template;
	}
	private Dictionary loadDictionary(String filename){
		Dictionary dictionary = new Dictionary();
		
		try {
			Scanner in = new Scanner(new FileReader(filename));
			String line = in.nextLine();
			//dictionary.addWord(line);
			in.close();
			if (in.hasNextLine()){
				dictionary.addWord(in.nextLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return dictionary;	
	}
}
