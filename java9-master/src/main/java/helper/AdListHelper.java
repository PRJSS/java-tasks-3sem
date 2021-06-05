package helper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.servlet.ServletContext;
import entity.AdList;
public abstract class AdListHelper {
    private static final String ADS_FILENAME = "WEB-INF/ads.dat";
    private static String ADS_PATH = null;

    public static AdList readAdList(ServletContext context) {
        try {
            ADS_PATH = context.getRealPath(ADS_FILENAME);
            ObjectInputStream in = new ObjectInputStream(new
                    FileInputStream(ADS_PATH));
            return (AdList)in.readObject();
        } catch (Exception e) {
            return new AdList();
        }
    }

    public static void saveAdList(AdList ads) {
        synchronized (ads) {
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ADS_PATH));
                out.writeObject(ads);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
