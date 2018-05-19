import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class MainApp {
	
	public static void main(String[] args) {
		
		
//		System.out.println("Helo World");
//		
//		for(int i = 0; i < args.length;i++){
//			System.out.println(""+args[i]);
//		}
		
		System.out.println("ININICANDO.....");
		
		BasicSpider extraxtor = new BasicSpider();
		extraxtor.extrai();
	}

}
