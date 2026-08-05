class Solution {
    
    private void solveGraph(List<List<Integer>> adj, boolean[] vis, int k){
        vis[k]=true;
        
        Stack<Integer> stck = new Stack<>();
        stck.push(k);
        while(!stck.isEmpty()){
            int top = stck.pop();
            
            for(int ad:adj.get(top)){
                if(!vis[ad]){
                    vis[ad]=true;
                    stck.push(ad);
                }
            }
        }
    }
    
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> adj = new ArrayList<>(n);
        for(int i=0; i<n; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] invocation:invocations){
            adj.get(invocation[0]).add(invocation[1]);
        }
        
        boolean[] vis = new boolean[n];
        solveGraph(adj, vis, k);
        for(int i=0; i<n; i++){
            if(!vis[i]){
                for(int ad:adj.get(i)){
                    if(vis[ad]){
                        List<Integer> list = new ArrayList<>();
                        for(int i1=0; i1<n; i1++){
                            list.add(i1);
                        }
                        return list;
                    }
                }
            }
        }
        
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<n; i++){
            if(!vis[i]){
                list.add(i);
            }
        }
        return list;
    }
}
