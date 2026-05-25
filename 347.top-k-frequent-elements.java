class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            list.add(entry);
        }
        int ret = quickSelect(k, list);
        List<Integer> retList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= map.get(ret)) retList.add(entry.getKey());
        }
        int[] res = new int[retList.size()];
        int index = 0;
        for (Integer r : retList) res[index++] = r;
        return res;
    }
    
    public int quickSelect(int k, List<Map.Entry<Integer, Integer>> list) {
        Random rand = new Random();
        int pivot = rand.nextInt(list.size());
        Map.Entry<Integer, Integer> pivotEntry = list.get(pivot);
        List<Map.Entry<Integer, Integer>> left = new ArrayList();
        List<Map.Entry<Integer, Integer>> mid = new ArrayList();
        List<Map.Entry<Integer, Integer>> right = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Map.Entry<Integer, Integer> current = list.get(i);
            if (current.getValue() > pivotEntry.getValue()) {
                left.add(current);
            } else if (current.getValue() == pivotEntry.getValue()) {
                mid.add(current);
            } else {
                right.add(current);
            }
        }
        if (left.size() >= k) {
            return quickSelect(k, left);
        } else if (left.size() + mid.size() < k) {
            return quickSelect(k - left.size() - mid.size(), right);
        }
        return pivotEntry.getKey();
    } 
}
