package webCraler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Index {
	static String projectName;
	static String url;
	static Set<String> queue=new HashSet<String>();
	static Set<String> crawled=new HashSet<String>();
	static Spider spider;
	
	public static void main(String[] args) throws IOException {
		System.out.println("Enter Project Name and URL : ");
		Scanner sc=new Scanner(System.in);
		 projectName=sc.nextLine();
		 url=sc.nextLine();
		for(int i=0;i<25;i++) {
			System.out.print("*");
		}
		spider=new Spider(projectName,url);
		
		crawl();
		
	}

	public static void crawl() throws IOException {
		queue=spider.fileToSet(projectName+"/queue.txt");
		crawled=spider.fileToSet(projectName+"/crawled.txt");
		if(queue.size()>0) {
			System.out.println(queue.size()+" links in the queue");
			start_crawl();
		}
		else {
			System.out.println("All links have been Crawled and Saved To Files");
		}
	}
	
	public static void start_crawl() throws IOException {
		for(String i:queue) {
			spider.crawl_page("First", i);
		}
		crawl();
	}
}
