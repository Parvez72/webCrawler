package webCraler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public interface FileCreation {
	//create Directories
	public default void createDirectory(String path) {
		File dir=new File(path);
		if(dir.exists()) {
			System.out.println("dir is present");
		}
		else {
			dir.mkdir();
			System.out.println("dir is created");
		}
	}
	
	//creating data files
	public default void createFiles(String dir,String baseUrl) throws IOException {
		File queue=new File(dir+"/queue.txt");
		File crawled=new File(dir+"/crawled.txt");
		if(queue.exists()&&crawled.exists()) {
			System.out.println("Files exits in "+dir+" folder");
		}
		else {
			queue.createNewFile();
			PrintWriter file=new PrintWriter(new FileWriter(dir+"/queue.txt"));
			file.println(baseUrl);
			crawled.createNewFile();
			System.out.println("Files created Successfully");
		}
	}
	
	//writing data to the files with append true or false
	public default void writeData(String path,Set<String> data,Boolean append) throws IOException {
		PrintWriter file=new PrintWriter(new FileWriter(path,append));
		for(String line : data)
		file.println(line);
		System.out.println("Successfully written to the File "+path);
		file.close();
	}
	
	//writing data to the files
	public default void writeData(String path,Set<String> data) throws IOException {
		PrintWriter file=new PrintWriter(new FileWriter(path,true));
		for(String line : data)
		file.println(line);
		System.out.println("Successfully written to the File "+path);
		file.close();
	}
	
	//file to set
	public default Set fileToSet(String path) throws IOException {
		BufferedReader file=new BufferedReader(new FileReader(path));
		String line;
		Set<String> links=new HashSet<String>();
		while((line=file.readLine())!=null) {
			links.add(line);
		}
		return links;
		
	}
	
}
