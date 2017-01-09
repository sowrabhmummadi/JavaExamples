package ms.sweexp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

class Readexpenses {
	String places[] = { "BLEKING", "GALGAMARKEN", "Frukthuset", "CITY GROSS",
			"WILLYS L", "WILLYS K", "MCDONALD S", "RED LIGHT", "ATM" };
	String line, lis[] = new String[places.length], others;
	boolean flag;
	Float total[] = new Float[places.length], t;

	public Readexpenses() {

		for (int k = 0; k < places.length; k++)
			total[k] = 0f;

		try {
			int i = 0;
			BufferedReader br = new BufferedReader(new FileReader(
					"C:\\My Stuff\\expenses.txt"));
			while ((line = br.readLine()) != null) {
				flag = true;
				for (i = 0; i < places.length && flag; i++) {
					if (line.contains(places[i])) {
						flag = false;
						lis[i] = lis[i] + "\n" + line;
						String[] values = line.split("\t");
						total[i] = total[i] + Float.parseFloat(values[4].replaceAll("\"|,",""));
					}

				}
			}

		} catch (Exception e) {
		}

	}

	public void writeFile() {
		int i = 0;
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(
					"C:\\My Stuff\\expenses1.csv"));
			
			while (i < lis.length) {
				if(lis[i]!=null){
				lis[i] = lis[i].replaceAll("\t", ",");
				bw.write(places[i] + " : \n " + lis[i] + "\n total :"
						+ total[i] + "\n");}
				i++;
			}
			System.out.println("done");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

public class Main {

	public static void main(String[] args) {
		System.out.println("hello world");
		Readexpenses r = new Readexpenses();
		r.writeFile();

	}

}
