package edu.neu.coe.info6205.assignment3;

import edu.neu.coe.info6205.union_find.UF_HWQUPC;

import java.util.Random;

public class UnionFindClient {
    public static void main (String[] args){
        int n = 100000;
        int connections = count(n);
        System.out.println("n value: "+n+" and pairs generated are "+connections);
    }

    public static int count (int n){
        Random random = new Random();
        UF_HWQUPC uf= new UF_HWQUPC(n, true);
        int pairsGenerated = 0;
        while (uf.components()!=1){
            int s1 = random.nextInt(n);
            int s2 = random.nextInt(n);
            pairsGenerated++;
            if(!uf.connected(s1, s2)){
                uf.union(s1,s2);
            }
        }
        return pairsGenerated;
    }
}
