class Solution {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int left = 0;
        int right = 0;
        int ans = 0;
        while (left <= right && right < fruits.length) {
            int current = fruits[right];
            map.put(current, map.getOrDefault(current, 0) + 1);
            while (map.size() > 2) {
                map.put(fruits[left], map.get(fruits[left]) - 1);
                if (map.get(fruits[left]) == 0) map.remove(fruits[left]);
                left += 1;
            }
            ans = Math.max(ans, right - left + 1);
            right += 1;
        }
        return ans;
    }
}
