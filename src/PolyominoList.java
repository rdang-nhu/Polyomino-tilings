import java.util.*;
import java.io.FileReader;
import java.awt.Color;
import java.io.BufferedReader;

public class PolyominoList extends ArrayList<Polyomino>{ //ne gère pas les exceptions en cas de non-lecture du fichier
	PolyominoList(String nom){
		super();
		try
		{
			FileReader fichier = new FileReader(nom);
			BufferedReader br = new BufferedReader(fichier);
			
			String line = br.readLine();
			while(line != null){
				Polyomino p = new Polyomino(line);
				this.add(p);
				line = br.readLine();
			}
			br.close();
		} catch(Exception e){
		}
	}
	
	PolyominoList(){
		super();
	}
	
	
	public void draw(int size, int dilater){
		Random c = new Random();
		Image2d img = new Image2d(1300,500); //régler le paramétrage auto
		
		
		for(int i = 0; i < this.size(); i++){
			// Pour chaque Polyomino de la liste

			Polyomino a = this.get(i);
			a.translater((size*i)%1250, size*(size*i/1250));
			
			
			Color couleur = Color.getHSBColor(c.nextFloat() ,1,1);
					
			
			for(int j = 0; j < a.cases.size(); j++){
				//on récupère tous les carrés et on les dessine de cette couleur
				int[] carré = a.cases.get(j);
				int x = carré[0];
				int y = carré[1];
				int x1 = x;  
				int y1 = 450-y;
				int[] xc = {x1,x1,x1+1,x1+1};
				int[] yc = {y1,y1+1,y1+1,y1};
				img.addPolygon(xc, yc, couleur);
			}
		}
		new Image2dComponent(img);
		new Image2dViewer(img);
	}
	
	@Override
	
	public String toString(){
		String s = "";
		for(int i = 0; i < this.size(); i++){
			Polyomino p = this.get(i);
			s += p.toString()+"\n";
		}
		return s;		
	}
	
	public static PolyominoList fixedPolyomino(int area){
		if(area == 1){
			PolyominoList resultat = new PolyominoList();
			Polyomino a = new Polyomino();
			int[] case1 = {0,0};
			int[] case2 = {0,1};
			int[] case3 = {1,0};
			a.cases.add(case1);
			a.casesVoisines.add(case2);
			a.casesVoisines.add(case3);
			resultat.add(a);
			return resultat;
		}

		PolyominoList resultat = new PolyominoList();
		PolyominoList rec = fixedPolyomino(area-1);
		for(int i = 0; i < rec.size(); i++){
			Polyomino p = rec.get(i);
			for(int j = 0; j < p.casesVoisines.size(); j++){
				
				// On copie p
				Polyomino a = Polyomino.copy(p);
				// on ajoute la case voisine choisie
				a.cases.add(p.casesVoisines.get(j));
				
				//On enlève la case voisine indexée par j
				a.casesVoisines.remove(j);
				
				// On ajoute les nouvelles cases voisines, en évitant de prendre une case déjà occupée
				int x = p.casesVoisines.get(j)[0];
				int y = p.casesVoisines.get(j)[1];
				int[] v1 = {x+1,y};
				int[] v2 = {x,y+1};
				int[] v3 = {x-1,y};
				int[] v4 = {x,y-1};
				
				if(! p.contains(v1) && ! p.vContains(v1)){
					a.casesVoisines.add(v1);
				}
				if(! p.contains(v2) && ! p.vContains(v2)){
					a.casesVoisines.add(v2);
				}
				if(! p.contains(v3) && x > 0 && ! p.vContains(v3)){
					a.casesVoisines.add(v3);
				}
				if(! p.contains(v4) && y > 0 && ! p.vContains(v4)){
					a.casesVoisines.add(v4);
				}
				
				// on vérifie que res ne contient pas déjà le polyomino
				boolean verif = true;
				for(int l = 0; l < resultat.size(); l++){
					if(a.isEqual(resultat.get(l))){
						verif = false;
					}
				}
				if(verif == true) resultat.add(a);
			}
		}
	return resultat;
	
}
	public static void test1(){
		PolyominoList test = new PolyominoList("src/polyominoesINF421.txt");
		test.draw(5,10);
	}

	
	public static void main(String[] args){
		//test1();
		PolyominoList test2 = fixedPolyomino(9);
		System.out.print(test2);
		test2.draw(9,10);
		
	}
}
