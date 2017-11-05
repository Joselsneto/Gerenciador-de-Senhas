/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciadordesenhas;

/**
 *
 * @author José
 */
public class Hash {
    private final long BASEHASH = 337;
    private final long MODHASH = (long) (1e9 + 7);
    private String hashString;
    
    public Hash(String s) {
        hashString = getHash(s);
    }
    
    private long exp(long x,long a) {
        if(a == 0)
            return 0;
        long res = 1;
        for(int i = 0;i < a;i++) {
            res *= x;
            res %= MODHASH;
        }
        return res;
    }
    
    private String getHash(String s) {

        int res = 0;
        for(int i = 0; i < s.length();i++) {
            res += (s.charAt(i)*(exp(BASEHASH,i)%MODHASH))%MODHASH;
            res %= MODHASH;
        }
        Integer t = res;
        String r = t.toString();
        return r;
    }
    
    public String getHash() {
        return hashString;
    }
    
}
