package test.solutions;

import java.util.*;

public class LibAndRoads {
    //class of graph
    static class Graph{
        int num; //number of citys
        LinkedList<Integer>[] edges;  //adj list
        int count = 0; //number of edges
        Graph(int num){
            this.num = num;
            edges =(LinkedList<Integer>[]) new LinkedList[num+1]; // just leave index = 0 there; 
            //ArrayList<Individual>[] group = (ArrayList<Individual>[])new ArrayList[4];
        }
        void addEdges(int v, int w){
        		if(edges[v]==null){
        			edges[v] = new LinkedList<>();
        		}
            edges[v].add(w);
            count++;
        }
        Iterable<Integer> adj(int v){
            return edges[v];
        }
        
        int numberOfV(){
            return num;
        }
        
        int numberOfEdges(){
            return count/2;
        }
    }
    
    //class of ConnectComp
    static class ConnectComp{
        boolean[] marked;
        int[] id;
        int count = 0;
        int node = 0;
        ConnectComp(Graph g){
            node = g.numberOfV();
            marked = new boolean[node+1];
            id = new int[node+1];
            for(int v=1;v<=node;v++){
                if(!marked[v]){
                    dfs(g,v);
                    count++;
                }
            }
        }
        public int count(){
            return count;
        }
        public int id(int v){
            return id[v];
        }
        void dfs(Graph g,int v){
            marked[v]=true;
            id[v]=count;
            for(int w:g.adj(v)){
                if(!marked[w]){
                    dfs(g,w);
                }
            }
        }
        
        int numWithsameId(int iden){
            int res=0;
            for(int i=1;i<node+1;i++){
                if(id[i]==iden){
                    res++;
                }
            }
            return res;
        }
        
        int idenEdges(){
            int res=0;
            for(int i=0;i<count;i++){
                res+=numWithsameId(i);
                res = res - 1;
            }
            return res;
        }
        
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int n = in.nextInt(); //number of cities
            int m = in.nextInt(); //number of edges
            long x = in.nextLong(); // library cost
            long y = in.nextLong(); // road cost
            
            Graph graph = new Graph(n);
            
            for(int a1 = 0; a1 < m; a1++){
                int city_1 = in.nextInt();
                int city_2 = in.nextInt();
                graph.addEdges(city_1,city_2);
                graph.addEdges(city_2,city_1);
            }
            
            if(y>=x){
                System.out.println(n*x);
                continue;
            }
            
            ConnectComp cc = new ConnectComp(graph);
            int numOfLib = cc.count();
            System.out.println(numOfLib*x + (n-numOfLib)*y);    
        }
    }
}

