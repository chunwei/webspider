package com.imdevice.WebSpider;

import java.net.URL;
import java.util.List;

import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class FeedReeder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url="";
		url="http://www.36kr.com/feed";
		//url="http://www.ifanr.com/feed";
		//url="http://www.cnbeta.com/backend.php";
		try {
            URL feedUrl = new URL(url);

            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));

            //System.out.println(feed);
            System.out.println(feed.getTitle());
            System.out.println(feed.getPublishedDate());
            System.out.println(feed.getFeedType());
            System.out.println("<hr>");
            
            @SuppressWarnings("unchecked")
			List<SyndEntry> entries = feed.getEntries();
            if (entries != null && !entries.isEmpty()) {
            	int i=1;
            	Extractor extractor;
             for (SyndEntry entry : entries) {
              System.out.println(i+" : "+entry.getTitle());
              System.out.println(entry.getPublishedDate());
              System.out.println(entry.getLink());
              @SuppressWarnings("unchecked")
              List<SyndContentImpl>contents=entry.getContents();
              //System.out.println(contents.get(0).getValue());
              System.out.println("<div style='background:#DDD;'>");
              extractor=new Extractor();
              if (contents != null && !contents.isEmpty()){
            	  System.out.println(extractor.getContent(contents.get(0).getValue()));
              }else{
            	  extractor.url=entry.getLink();
            	  System.out.println(extractor.getContent());
              }
              System.out.println("</div>");
              i++;
              if(i>30)break;
             }
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: "+ex.getMessage());
        }

	}

}
