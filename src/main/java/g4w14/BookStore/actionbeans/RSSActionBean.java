package g4w14.BookStore.actionbeans;

import g4w14.BookStore.beans.RssBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
 
@ManagedBean
@RequestScoped
@Named("RSSAction")
public class RSSActionBean {
   
    public RSSActionBean() {
         getrss();
    }
     
    List<RssBean> feeds;
 
    // Bound to our ui:repeat component
    public List<RssBean> getFeed() {
        return feeds;
    }
 
    private void getrss() {
         
        String rssFeedUrl ="http://topics.nytimes.com/top/features/books/bookreviews/?rss=1";
        int count=3;  // desired number of feeds to retrieve
         
        SimpleDateFormat df =
                new SimpleDateFormat("EEEE MMMM dd, yyyy HH:mm:ss");
 
        try {
            // Connect
            URLConnection feedUrl = new URL(rssFeedUrl).openConnection();
            SyndFeedInput input = new SyndFeedInput();
            // Build the feed list
            SyndFeed feed = input.build(new XmlReader(feedUrl));
 
            List<SyndEntry> feedList = feed.getEntries();
            int feedSize = feedList.size();
 
            // Save only count requested
            if (feedSize > count) {
                feedSize = count;
            }
 
            feeds= new ArrayList<>();
 
            for (int i = 0; i < feedSize; i++) {
 
                // Please see Javadoc for more of SyndEntry members
                SyndEntry entry = (SyndEntry) feedList.get(i);
 
                RssBean rss = new RssBean();
                 
                // Format based on your requirements
                String title = entry.getTitle(); 
                rss.setTitle(title);
                 
                rss.setPubDate(df.format(entry.getPublishedDate()));
                 
                // Do some formatting you may require;
                String description = entry.getDescription().getValue();
               
                rss.setDescription(description);
 
                //  Update
                feeds.add(rss);
            }
           
        } catch (Exception e) {
             
            // Or whatever behaviour your application requires
            feeds = new ArrayList<>();
            RssBean rss = new RssBean();
            rss.setTitle("Error");
            rss.setDescription(e.getMessage());
            feeds.add(rss);
           
        }
         
    }
 
}