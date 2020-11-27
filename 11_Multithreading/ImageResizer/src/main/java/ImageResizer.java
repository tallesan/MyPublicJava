import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.imgscalr.AsyncScalr;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;

public class ImageResizer implements Runnable {


  private File[] files;
  private long start;
  private int newWidth;
  private String dstFolder;

  public ImageResizer(File[] files, long start, int newWidth, String dstFolder) {
    this.files = files;
    this.start = start;
    this.newWidth = newWidth;
    this.dstFolder = dstFolder;
  }

  public void run() {
    System.out.println("Передали файлов - " + files.length);
    try {
      for (File file : files) {
        BufferedImage image = ImageIO.read(file);
        if (image == null) {
          continue;
        }

        BufferedImage result = Scalr.resize(image, Method.ULTRA_QUALITY ,newWidth);

//        int newWidth = 300;
//        int newHeight = (int) Math.round(
//            image.getHeight() / (image.getWidth() / (double) newWidth)
//        );
//        BufferedImage newImage = new BufferedImage(
//            newWidth, newHeight, BufferedImage.TYPE_INT_RGB
//        );
//
//        int widthStep = image.getWidth() / newWidth;
//        int heightStep = image.getHeight() / newHeight;
//
//        for (int x = 0; x < newWidth; x++) {
//          for (int y = 0; y < newHeight; y++) {
//            int rgb = image.getRGB(x * widthStep, y * heightStep);
//            newImage.setRGB(x, y, rgb);
//          }
//        }

        File newFile = new File(dstFolder + "/" + file.getName());
        ImageIO.write(result, "jpg", newFile);

//        result.flush();
      }
    } catch (Exception ex) {
      ex.printStackTrace();

    }
    long timeEx = System.currentTimeMillis() - start;
    System.out.println("Время выполнения - " + timeEx + "ms");
    System.out.printf("%s finished... \n", Thread.currentThread().getName());

    System.out.println();

  }

}
