class Solution {
    //This Hashmap will hold 3 things. 
    //1. State of buying/selling (+ or -)
    //2. The index (Grouped together with #1 to serve as our key)
    //3. The Maximum Buy/sell value
    HashMap<Integer, Integer> maxProfit = new HashMap<>();
  
    public int maxProfit(int[] prices) 
    {
        return dfs(prices, 0, true);
    }
  
    // Depth first search for Maximum profit
    public int dfs(int[] prices, int index, boolean buying)
    {
        if(index >= prices.length){return 0;} // edge case of empty price list
        int num = 1;
        if(buying){num *= -1;} //Negative Numbers = Buying, Positive = selling or cooldown
        // Case where we already have max profit
        if(maxProfit.containsKey(index*num)){return maxProfit.get(index*num);} 
        int cooldown = dfs(prices, index+1, buying);
        if(buying)
        {
            int buy = dfs(prices, index+1, !buying) - prices[index];
            maxProfit.put(index*num, Math.max(buy, cooldown));
        }
        else
        {
            int sell = dfs(prices, index+2, !buying) + prices[index];
            maxProfit.put(index*num, Math.max(sell, cooldown));
        }
        return maxProfit.get(index*num);
    }
}
