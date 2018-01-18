package webCraler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Spider implements FileCreation {
	String projectName,baseUrl,queueFiles,crawledFiles;
	Set<String> queue=new HashSet<>();
	Set<String> crawled=new HashSet<>();
	
	public Spider(String projectName,String baseUrl) throws IOException {
		this.projectName=projectName;
		this.baseUrl=baseUrl;
		this.queueFiles=projectName+"/queue.txt";
		this.crawledFiles=projectName+"/crawled.txt";
		boot();
		crawl_page("First Spider",baseUrl);
		
	}
	//Booting and calling creating files
	public void boot() throws IOException {
		createDirectory(projectName);
		createFiles(projectName,baseUrl);
		queue=fileToSet(queueFiles);
		crawled=fileToSet(crawledFiles);
	}
	
	public void crawl_page(String spiderName,String url) throws IOException {
		if(!crawled.contains(url)) {
			System.out.println(spiderName+" Now Crawling "+url);
			System.out.println("Queue "+String.valueOf(queue.size())+" | Crawled "+String.valueOf(crawled.size()));
			addLinksToSet(gatherLinks(url));
			queue.remove(url);
			crawled.add(url);
			updateFiles();
		}
	}
	
	public Set gatherLinks(String url) throws IOException {
		LinkFinder lk=new LinkFinder(url);
		return lk.getLinks();
	}
	public void addLinksToSet(Set<String> links) {
		for(String link:links) {
			if(queue.contains(link))
				continue;
			if(crawled.contains(link))
				continue;
			queue.add(link);
		}
	}
	
	public void updateFiles() throws IOException {
		writeData(queueFiles,queue,true);
		writeData(crawledFiles,crawled,true);
	}
}
