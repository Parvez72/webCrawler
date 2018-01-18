package webCraler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LinkFinder {
	protected Set<String> links=new HashSet<>(); 
	public LinkFinder(String url) {
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements tags=doc.select("a[href]");
			for(Element tag:tags)
			links.add(tag.attr("abs:href"));//abs = absolute
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("There was a problem getting links from "+url);
			
		}

	}
	
	public Set getLinks() {
		return links;
	}
}
