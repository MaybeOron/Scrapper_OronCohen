import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class YnetBot extends BaseRobot {


    public YnetBot() {
        super("https://www.ynet.co.il/home/0,7340,L-8,00.html");
    }

    @Override
    public Map<String, Integer> getWordsStatistics() {

        Map<String, Integer> map = new HashMap<String, Integer>();

        try {
            Document makoWebsite = Jsoup.connect(getRootWebsiteUrl()).get();
            Elements titleElems = makoWebsite.select(".textDiv");
            for (Element element : titleElems) {
                Document innerArticle = Jsoup.connect(element.selectFirst("a").attr("href")).get();
                Element articleBody = innerArticle.selectFirst(".article-body");
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
            Document makoWebsite = Jsoup.connect(getRootWebsiteUrl()).get();
            Elements mainArticles = makoWebsite.select(".textDiv");
            for (Element element : mainArticles) {
                Document innerArticle = Jsoup.connect(element.selectFirst("a").attr("href")).get();

                Element subArticle = innerArticle.selectFirst(".subTitle");
                Element mainArticle = innerArticle.selectFirst(".mainTitle");
                if (mainArticle != null && subArticle != null) {
                    String[] wordsInsideSubArticle = subArticle.text().split("[\\s,]+");
                    for (String word : wordsInsideSubArticle) {
                        if (word.equals(text)) countAppearances++;
                    }
                    String[] wordsInsideMainArticle = mainArticle.text().split("[\\s,]+");
                    for (String word : wordsInsideMainArticle) {
                        if (word.equals(text)) countAppearances++;
                    }

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
            Document makoWebsite = Jsoup.connect(getRootWebsiteUrl()).get();
            Elements titleElems = makoWebsite.select(".textDiv");
            for (Element element : titleElems) {
                Document innerArticle = Jsoup.connect(element.selectFirst("a").attr("href")).get();
                Element articleBody = innerArticle.selectFirst(".article-body");
                Element article2 = innerArticle.selectFirst(".article");
                if (article2 != null || articleBody != null && articleBody.text().length() > longestTitle.length())
                    if ( articleBody == null) {
                        Element type2Article = article2.selectFirst(".article-header");
                        String type2Title = type2Article.getElementsByTag("h1").text();
                        longestTitle = type2Title;
                    } else {
                        longestTitle = innerArticle.getElementsByClass("mainTitle").get(0).text();
                    }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return longestTitle;
    }
}
