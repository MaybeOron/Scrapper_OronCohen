import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class WallaBot extends BaseRobot {


    public WallaBot() {
        super("https://www.walla.co.il/");
    }

    @Override
    public Map<String, Integer> getWordsStatistics() {

        Map<String, Integer> map = new HashMap<String, Integer>();

        try {
            Document wallaWebsite = Jsoup.connect(getRootWebsiteUrl()).get();
            Element bigTitle = wallaWebsite.selectFirst(".with-roof");
            Document bigInnerArticle = Jsoup.connect(bigTitle.getElementsByTag("a").get(0).attr("href")).get();
            Element bigArticleBody = bigInnerArticle.selectFirst(".article-content");
            if (bigArticleBody != null) {
                String[] words = bigArticleBody.text().split("[\\s,]+");
                for (String word : words) {
                    if (word.startsWith("\"") || word.startsWith(")") || word.startsWith("(") || word.startsWith("'"))
                        word = word.substring(1);
                    if (word.endsWith("\"") || word.endsWith(")") || word.endsWith("(") || word.endsWith("'") || word.endsWith("."))
                        word = word.substring(0, word.length() - 1);
                    if (!map.containsKey(word))
                        map.put(word, 1);
                    else
                        map.put(word, map.get(word) + 1);
                }
            }

            Element smallTitlesBox = wallaWebsite.selectFirst(".right");
            Elements smallTitlesAtag = smallTitlesBox.getElementsByTag("a");
            for (Element element : smallTitlesAtag) {
                Document innerArticle = Jsoup.connect(element.attr("href")).get();
                Element articleBody = innerArticle.selectFirst(".article-content");
                if (articleBody != null) {
                    String[] words = articleBody.text().split("[\\s,]+");
                    for (String word : words) {
                        if (word.startsWith("\"") || word.startsWith(")") || word.startsWith("(") || word.startsWith("'"))
                            word = word.substring(1);
                        if (word.endsWith("\"") || word.endsWith(")") || word.endsWith("(") || word.endsWith("'") || word.endsWith("."))
                            word = word.substring(0, word.length() - 1);
                        if (!map.containsKey(word))
                            map.put(word, 1);
                        else
                            map.put(word, map.get(word) + 1);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public int countInArticlesTitles(String text) {

        int countAppearances = 0;
        try {
            Document wallaWebsite = Jsoup.connect(getRootWebsiteUrl()).get();

            Element bigTitle = wallaWebsite.selectFirst(".with-roof");
            Document bigInnerArticle = Jsoup.connect(bigTitle.getElementsByTag("a").get(0).attr("href")).get();
            Element bigArticleHeader = bigInnerArticle.getElementsByTag("header").get(2);
            String[] wordsInsideMainArticle = bigArticleHeader.text().split("[\\s,]+");
            for (String word : wordsInsideMainArticle) {
                if (word.equals(text)) countAppearances++;
            }

            Element smallTitlesBox = wallaWebsite.selectFirst(".right");
            Elements smallTitlesAtag = smallTitlesBox.getElementsByTag("a");
            for (Element element : smallTitlesAtag) {
                Document innerArticle = Jsoup.connect(element.attr("href")).get();
                Element articleHeader = innerArticle.getElementsByTag("header").get(2);
                String[] wordsInsideSmallArticle = articleHeader.text().split("[\\s,]+");
                for (String word : wordsInsideSmallArticle) {
                    if (word.equals(text)) countAppearances++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return countAppearances;
    }

    @Override
    public String getLongestArticleTitle() {

        String longestTitle = "";

        try {
            Document wallaWebsite = Jsoup.connect(getRootWebsiteUrl()).get();

            Element bigTitle = wallaWebsite.selectFirst(".with-roof");
            Document bigInnerArticle = Jsoup.connect(bigTitle.getElementsByTag("a").get(0).attr("href")).get();
            Element bigArticleBody = bigInnerArticle.selectFirst(".article-content");
            if (bigArticleBody != null && bigArticleBody.text().length() > longestTitle.length())
                longestTitle = bigInnerArticle.getElementsByTag("h1").get(0).text();


            Element smallTitlesBox = wallaWebsite.selectFirst(".right");
            Elements smallTitlesAtag = smallTitlesBox.getElementsByTag("a");
            for (Element element : smallTitlesAtag) {
                Document innerArticle = Jsoup.connect(element.attr("href")).get();
                Element articleBody = innerArticle.selectFirst(".article-content");
                if (articleBody != null && articleBody.text().length() > longestTitle.length()) {
                    longestTitle = innerArticle.getElementsByTag("h1").get(0).text();

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return longestTitle;
    }
}
