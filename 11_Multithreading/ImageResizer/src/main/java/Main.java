import java.io.File;

public class Main {

  public static void main(String[] args) {
    String srcFolder = "C:\\TestScr";
    String dstFolder = "C:\\TestNew";

    File srcDir = new File(srcFolder);

    long start = System.currentTimeMillis();

    int newWidth = 300;
    int nbThreads = Thread.getAllStackTraces().keySet().size(); //количество потоков
    int processors = Runtime.getRuntime().availableProcessors();//количество ядер
    System.out.println("Количество ядер - " + processors);
    System.out.println("Количество возможных потоков - " + nbThreads);

    File[] files = srcDir.listFiles();

    int step = files.length / processors + 1;
    int stepArr = 0;

    for (int i = 0; i < processors; i++) {
      if ((stepArr + step) > files.length) {
        step = files.length - stepArr;
      }
      System.out.println("Процесс - " + i);
      File[] files1 = new File[step];
      System.arraycopy(files, stepArr, files1, 0, step);
      ImageResizer resizer = new ImageResizer(files1, start, newWidth, dstFolder);
      new Thread(resizer).start();

      stepArr = stepArr + step;
    }
  }
}
