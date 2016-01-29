package exercise.com.leo.base.boilerpipe;

import java.net.MalformedURLException;
import java.net.URL;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;

public class ExtracterText {

	public static void main(String[] args) {
		try {
			
			
			URL url = new URL("https://www.haosou.com/s?ie=utf-8&shb=1&src=sug-store&q=18516075217");
			// NOTE: Use ArticleExtractor unless DefaultExtractor gives better
			// results for you
			String text = ArticleExtractor.INSTANCE.getText(url);

			System.out.println(text);
		} catch (BoilerpipeProcessingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
