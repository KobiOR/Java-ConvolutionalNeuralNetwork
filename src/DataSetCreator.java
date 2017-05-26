/**
 * Created by orrko_000 on 26/05/2017.
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

    public class DataSetCreator {

        static int imagesRead=0;
        static final File dir = new File("C:\\Users\\orrko_000\\Desktop\\DataSetToRead");
        static final String[] EXTENSIONS = new String[]{"gif", "png", "bmp","jpeg","jpg" };

        static final FilenameFilter IMAGE_FILTER = new FilenameFilter()
        {
            @Override
            public boolean accept(final File dir, final String name)
            {
                for (final String ext : EXTENSIONS) {
                    if (name.endsWith("." + ext)) {
                        return (true);
                    }
                }
                return (false);
            }
        };

        public static void start(ArrayList myTrainingInputs, ArrayList myTrainingOutputs) {

            if (dir.isDirectory()) {
                for (final File f : dir.listFiles(IMAGE_FILTER)) {
                    BufferedImage img = null;

                    try {
                        img = ImageIO.read(f);
                        convertImageToArray(img,myTrainingInputs,myTrainingOutputs);
                      System.out.println("Read Image: " + f.getName());
                        imagesRead++;

                    } catch (final IOException e) {
                        // handle errors here
                    }
                }
            }
        }

        private static void convertImageToArray(BufferedImage image,ArrayList inputList,ArrayList outputList) {

            ArrayList<Float> tempList=new ArrayList<Float>();
            final int width = image.getWidth();
            final int height = image.getHeight();

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int pixel = image.getRGB(j, i);
                    float red = (pixel >> 16) & 0xff;//RED
                    float green = (pixel >> 8) & 0xff;//GREEN
                    float blue = (pixel) & 0xff;//BLUE
                    tempList.add(red);
                    tempList.add(green);
                    tempList.add(blue);
                }
                float[] arr=new float[tempList.size()];
                for (int j = 0; j <tempList.size() ; j++) {
                    arr[i]=tempList.get(i);
                }
                inputList.add(arr);
                outputList.add(new float[]{0});
                tempList.clear();
            }


        }

        public int getImageCouter() {
            return imagesRead;
        }
    }

