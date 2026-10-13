class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> ans = new HashSet<>();
        helper(nums,ans,0);
        return new ArrayList<>(ans);
    }
    public void helper(int[] nums,Set<List<Integer>> ans,int idx){
        if(idx == nums.length){
            List<Integer> temp = new ArrayList<>();
            for(int num : nums){
                temp.add(num);
            }
            ans.add(temp);
            return;
        }
        for(int i = idx; i < nums.length; i++){
            swap(nums,i,idx);
            helper(nums,ans,idx+1);
            swap(nums,i,idx);
        }
    }
    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}