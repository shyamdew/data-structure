/*
Leet Code
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,
Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
*/
class TwoSumProblem {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> m = new HashMap<Integer,Integer>();
        int i = 0;
        int[] a = new int[2];
        for(int indiv : nums){
            if(m.containsKey(indiv)){                
                a[0] = m.get(indiv);
                a[1] = i;
                break;
            } else {
                m.put(target-indiv,i);
            }
            i++;
        }
        return a;
    }
}
