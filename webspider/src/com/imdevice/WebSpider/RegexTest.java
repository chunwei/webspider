package com.imdevice.WebSpider;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class RegexTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url="http://www.leiphone.com/1130-ce6093-wechat.html";
		try {
			Document doc=Jsoup.connect(url).get();
			
			//String punctuation="，、。：；！？‘’“”,;!\'\"";
			//String regex="["+punctuation+"][^"+punctuation+"]{5,}["+punctuation+"]";
			String regex="((<(?i)(li|p|span)[^>]*>)?\\s*<a[^>]*>.*</a>\\s*(</(?i)(li|p|span)>)?\\s*){2,}";
			Pattern pattern;
			try {
				pattern = Pattern.compile(regex);
			} catch (PatternSyntaxException e) {
				throw new IllegalArgumentException("Pattern syntax error: " + regex, e);
			}
			String s=doc.body().html();
			Matcher m = pattern.matcher(s);
			while(m.find()){
					System.out.println(m.group(0));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
