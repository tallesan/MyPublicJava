import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.RecursiveTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class UrlParse extends RecursiveTask<Set<String>>  {

  private String url;// = "https://skillbox.ru";
  private Set<String> allUrl = new HashSet<>(); //для получения значений(инициируем в конструкторе)
  private static Set<String> tempUrl = new TreeSet<>(); //для проверки выхода из цикла

  public UrlParse(String url) {
    this.url = url;
    allUrl.addAll(parsePage(url));
  }

//получить все ссылки 1 потоком
//  public Set<String> parseAll() {
//    for (; ; ) {
//      sizeUrl = allUrl.size();
//
//      urlPage.clear();
//      System.out.println("Шаг - " + count);
//      count++;
//      System.out.println("Продолжаем грузить");
//      System.out.println("Основных ссылок - " + allUrl.size());
//      countToUrl = 0;
//      for (String search : allUrl) {
//        if (search.contains(".pdf")) { //чтобы не искать в PDF ссылки
//          continue;
//        }
//        urlPage.addAll(parsePage(search));
//      }
//      allUrl.addAll(urlPage);
//      if (sizeUrl == allUrl.size()) {
//        System.out.println("все страницы загружены");
//        System.out.println(allUrl.size());
//        break;
//      }
//    }
//    return allUrl;
//  }

  public Set<String> parsePage(String url) {
    Set<String> urlPage = new TreeSet<>();
    Document document = null;
    try {
      Thread.sleep(500);
      System.out.println(Thread.currentThread());
      document = Jsoup.connect(url).timeout(2000).get();
      Elements elements = document.select("a[href]");
      for (Element element : elements) {
        //Исключам ссылки не содержащие https://skillbox.ru
        //Для быстроты срабатывания лучше уменьшить количество ссылок.
        if ((!element.absUrl("href").contains("https://skillbox.ru"))
        ) {
          continue;
        }
        urlPage.add(element.absUrl("href"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return urlPage;
  }

  @Override
  protected Set<String> compute() {
    List<UrlParse> subTasks = new ArrayList<>();
    try {
      for (String child : allUrl) {
        if (child.contains(".pdf")){
          tempUrl.add(child);
          continue;
        }
        if (!tempUrl.contains(child)) {
          System.out.println(child);
          tempUrl.add(child);
          UrlParse task = new UrlParse(child);
          task.fork();
          subTasks.add(task);
        }
      }
      for (UrlParse parse : subTasks) {
        allUrl.addAll(parse.join());
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return allUrl;
  }
}
