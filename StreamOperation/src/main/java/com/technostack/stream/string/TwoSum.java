package com.technostack.stream.string;

public class TwoSum {
    public static void main(String[] args) {
        int[] input = {0, -1, 2, -3, 1};
        int target = -2;

       int[] val =  twoSum(input,target);
       for(int print : val){
           System.out.println(print+" ");
       }
    }

    private static int[] twoSum(int[] input, int target) {
        if(input.length == 0){
            return new int[]{-1,-1};
        }

        for(int i = 0;i<input.length;i++){
            for(int j = i+1;j< input.length;j++){
                if(input[i]+input[j] == target){
                    return new int[] {i,j};
                }
            }
        }
        return new int[] {-1,-1};
    }
}
