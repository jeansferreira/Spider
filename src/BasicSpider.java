import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class BasicSpider {

	FileWriter linksAchados = null;
	FileWriter linksBaixados = null;

	HashSet<String> listaDeLinks = new HashSet<String>();
	HashSet<String> listaDeBaixados = new HashSet<String>();

	public BasicSpider() {
		try {
			File fachados = new File("./Achados.csv");
			if(fachados.exists()){
				BufferedReader bfrachados = new BufferedReader(new FileReader(fachados));
				String linha = "";
				while ((linha = bfrachados.readLine()) != null) {
					listaDeLinks.add(linha);
				}
				bfrachados.close();
			}

			File fbaixados = new File("./Baixados.csv");
			if(fbaixados.exists()){
				BufferedReader bfrbaixados = new BufferedReader(new FileReader(fbaixados));
				String linha = "";
				while ((linha = bfrbaixados.readLine()) != null) {
					listaDeBaixados.add(linha);
				}
				bfrbaixados.close();
			}

			linksAchados = new FileWriter("./Achados.csv", true);
			linksBaixados = new FileWriter("./Baixados.csv", true);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void extrai() {
		// String pagina =
		// Util.openURLtoString("http://www.textfiles.com/etext/FICTION/");

		// System.out.println(pagina);

		// String strs[] = pagina.split("\"");

		// System.out.println("Strings "+strs.length);

		// Document doc = Jsoup.parse(pagina);
		//
		// Elements links = doc.select("a");
		//
		// for(int i = 0; i < links.size(); i++){
		// Attributes att = links.get(i).attributes();
		// Iterator<Attribute> it = att.iterator();
		// for(int j = 0; j < att.size();j++){
		// Attribute atrib = it.next();
		// System.out.println(""+atrib.getKey()+" "+atrib.getValue());
		// }
		// System.out.println("Text "+links.get(i).text());
		//
		// }

		// for(int i = 0; i < strs.length;i++){
		// //System.out.println(""+strs[i]);
		//
		// if(strs[i].charAt(0)!='>'&&strs[i].contains(".txt")){
		//
		// System.out.println("Baixando "+strs[i]);
		// Util.LoadFileUrl(new File("./download/"+strs[i]),
		// "http://www.textfiles.com/etext/FICTION/"+strs[i]);
		//
		// }
		// }

		// ArrayList<String> listaDeLinks = new ArrayList<String>();

		for (int j = 1; j < 16; j++) {
			//String pagina = Util.openURLtoString("http://www.bigdatabusiness.com.br/category/big-data/page/" + j + "/");
			String pagina = Util.openURLtoString("http://www.portaltransparencia.gov.br/ceis/?pagina=" + j);
			Document doc = Jsoup.parse(pagina);
			Elements links = doc.select("a");

			for (int i = 0; i < links.size(); i++) {
				String classvalue = links.get(i).attr("class");
				if (classvalue.equals("post-entry-button")) {
					String href = links.get(i).attr("href");
					System.out.println("href " + href);
					// listaDeLinks.add(href);
					if (!listaDeLinks.contains(href)) {
						listaDeLinks.add(href);
						salvaLogAchados(href);
					} else {
						System.out.println("LINK Já ACHADO");
					}
				}
			}
		}

		// for(int i = 0; i < listaDeLinks.size();i++){
		// String link = listaDeLinks.get(i);
		// String pagina = Util.openURLtoString(link);
		// String splistr[] = link.split("/");
		// File f = new File("./paginas/"+splistr[splistr.length-1]+".html");
		// FileWriter fw;
		// try {
		// fw = new FileWriter(f);
		// fw.write(pagina);
		// fw.close();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

		Iterator<String> it = listaDeLinks.iterator();

		for (Iterator iterator = listaDeLinks.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			String link = string;
			if (!listaDeBaixados.contains(link)) {

				String pagina = Util.openURLtoString(link);
				String splistr[] = link.split("/");
				File f = new File("./paginas/" + splistr[splistr.length - 1] + ".html");
				FileWriter fw;
				try {
					fw = new FileWriter(f);
					fw.write(pagina);
					fw.close();

					salvaLogBaixados(link);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("Link JA BAIXADO");
			}
		}

		// for(int i = 0; i < listaDeLinks.size();i++){
		// String link = listaDeLinks.get(i);
		// String pagina = Util.openURLtoString(link);
		// String splistr[] = link.split("/");
		// File f = new File("./paginas/" + splistr[splistr.length - 1] +
		// ".html");
		// FileWriter fw;
		// try {
		// fw = new FileWriter(f);
		// fw.write(pagina);
		// fw.close();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
	}

	public void salvaLogAchados(String link) {
		try {
			linksAchados.write(link + "\n");
			linksAchados.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void salvaLogBaixados(String link) {
		try {
			linksBaixados.write(link + "\n");
			linksBaixados.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
