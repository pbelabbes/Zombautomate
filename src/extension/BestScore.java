package extension;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BestScore {
	
	
	public ArrayList<Integer> read(int nbJ){
		
	//lecture du fichier texte	
		ArrayList<Integer> listscore = new ArrayList<Integer>();
		try{
			InputStream ips=new FileInputStream("Bestscore"+nbJ+".txt"); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
//				System.out.println(ligne);
				listscore.add(Integer.parseInt(ligne));
				
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		return listscore;
	}

	private void write(ArrayList<Integer> l , int nbJ){
		
		try {
			Runtime.getRuntime().exec(new String[]{ "sh", "-c", "rm Bestscore"+nbJ+".txt"});
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		//création ou ajout dans le fichier texte
		try {
			FileWriter fw = new FileWriter ("Bestscore"+nbJ+".txt");
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter fichierSortie = new PrintWriter (bw); 
			for (int i : l ){
				fichierSortie.println (i); 
			}
			fichierSortie.close();
		}
		catch (Exception e){
			System.out.println(e.toString());
		}		
	}
	
	
	public int bestscoreplayer(int nbtour, int nbjoueur){
		int pos = -1 ; 
		ArrayList<Integer> listscore = new ArrayList<Integer>();
		listscore = read(nbjoueur);
		
		for (int score : listscore){
			if(nbtour> score){
				pos = listscore.indexOf(score);
				listscore.add(pos,nbtour);
				listscore.remove(3);
				write(listscore, nbjoueur);
				return pos+1;
			}
			
		}

		write(listscore, nbjoueur);
		
					
		return pos+1;
	}


	
	public static void main(String[] args){
		BestScore t = new BestScore();
		t.bestscoreplayer(12, 2);
//		ArrayList<Integer> l  =t.read(2);
//		l.add(15);
//		t.write(l, 2);
//		
//		System.out.println("\n");
//		t.read(2);
//	
	}
}
