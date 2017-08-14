package test.solutions;

import java.util.Arrays;

public class LivingPeople {
	static class Person{
		int birth;
		int death;
		Person(int birth,int death){
			this.birth=birth;
			this.death=death;
		}
	}
	
	static int maxAliveYear(Person[] person,int min,int max){
		int[] births = sortedYear(person, true);
		int[] deaths = sortedYear(person,false);
		
		int birthindex =0, deathindex = 0;
		int currentlive =0,maxAlive = 0, maxAliveyear = min;
		
		while(birthindex < births.length){
			if(births[birthindex]<=deaths[deathindex]){
				currentlive++;
				if(currentlive>maxAlive){
					maxAlive = currentlive;
					maxAliveyear = births[birthindex];
				}
				birthindex++;
			}else{
				currentlive--;
				deathindex++;
			}
		}
		return maxAliveyear;
		
	}
	private static int[] sortedYear(Person[] p, boolean b) {
		// TODO Auto-generated method stub
		int[] res= new int[p.length];
		for(int i=0;i<p.length;i++){
			res[i]=b?p[i].birth:p[i].death;
		}
		Arrays.sort(res);
		return res;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
