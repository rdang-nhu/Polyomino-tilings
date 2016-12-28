import java.util.*;

public class Polyomino{
	// un tuple est stocké comme un tableau de deux entiers
	ArrayList<int[]> cases = new ArrayList<int[]>(); 
	
	// On ajoute également les cases_voisines (utilisé pour générer des polyominos)
	ArrayList<int[]> casesVoisines;
	
	// Constructeur de base
	Polyomino(){
		cases = new ArrayList<int[]>();
		casesVoisines = new ArrayList<int[]>();
	}
	
	// Création à partir d'une chaîne
	Polyomino(String s){ 
		int i = 0;
		int l = s.length();
		while(i < l){
			char car = s.charAt(i);
			i ++;
			if(car == '('){
				String x = "";
				String y = "";
				char lecture = s.charAt(i); 
				i++;
				while(lecture != ','){ //lecture de x
					x = x + lecture;
					lecture = s.charAt(i);
					i++;
				}
				lecture = s.charAt(i); //lecture de la virgule
				i++;
				while(lecture != ')'){ //lecture de y
					y = y + lecture;
					lecture = s.charAt(i);
					i++;
					
				}
				int[] coordonnées = {Integer.parseInt(x),Integer.parseInt(y)}; //fonction qui convertit une chaîne en entier
				cases.add(coordonnées);
			}
		}
	}
	
	public boolean contains(int[] coord){
		boolean res = false;
		for(int i = 0; i < cases.size(); i++){
			if(cases.get(i)[0] == coord[0] && cases.get(i)[1] == coord[1]){
				res = true;
			}
		}
		return res;
	}
	
	public boolean vContains(int[] coord){
		boolean res = false;
		for(int i = 0; i < casesVoisines.size(); i++){
			
			if(casesVoisines.get(i)[0] == coord[0] && casesVoisines.get(i)[1] == coord[1]){
				res = true;
			}
		}
		return res;
	}
	
	public static Polyomino copy(Polyomino p){
		Polyomino res = new Polyomino();
		for(int i = 0; i<p.cases.size();i++){
			int x = p.cases.get(i)[0];
			int y = p.cases.get(i)[1];
			int[] aux = {x,y};
			res.cases.add(aux);
		}
		for(int i = 0; i<p.casesVoisines.size();i++){
			int x = p.casesVoisines.get(i)[0];
			int y = p.casesVoisines.get(i)[1];
			int[] aux = {x,y};
			res.casesVoisines.add(aux);
		}
		return res;
	}
	
	// fonction qui compare 2 polyominos de même aire
	public boolean isEqual(Polyomino p){
		boolean res = true;
		for(int i = 0; i < p.cases.size(); i++){
			if (! this.contains(p.cases.get(i))){
				res = false;
			}
		}
		return res;
	}
	
	public void translater(int x, int y){
		for(int l = 0; l < this.cases.size(); l++){
			this.cases.get(l)[0] += x;
			this.cases.get(l)[1] += y;
		}
	}
	
	public void rotation(int x, int y){ //quart de tour sens trigo
		for(int i = 0; i < this.cases.size(); i++){
			int u =  this.cases.get(i)[0];
			this.cases.get(i)[0] = -this.cases.get(i)[1];
			this.cases.get(i)[1] = u;
		}
	}
	
	public void dilatation(int k){ //debile
		for(int i = 0; i < this.cases.size(); i++){
			this.cases.get(i)[0] = k*this.cases.get(i)[0];
			this.cases.get(i)[1] = k*this.cases.get(i)[0];
		}
	}
	
	public void symetrie_x(){
		for(int i = 0; i < this.cases.size(); i++){
			this.cases.get(i)[0] = -this.cases.get(i)[0];
		}
	}
	
	public void symetrie_y(){
		for(int i = 0; i < this.cases.size(); i++){
			this.cases.get(i)[1] = -this.cases.get(i)[1];
		}
	}
	
	@Override
	
	
	
	public String toString(){
		String s = "";
		for(int j = 0; j < this.cases.size(); j++){
			int[] pr = this.cases.get(j);
			s += "("+pr[0]+",";
			s += pr[1]+")";
		}
		s += "\n";
		return s;
	}
	
	public static void test1(){
		Polyomino p = new Polyomino("[(12,23),(45,600),(123,4500)]/n");
		System.out.print(p.toString());
	}
	
	
	public static void main(String[] args){
		//test1(); //à commenter pour éviter d'avoir toujours le résultat
		
	}
}


