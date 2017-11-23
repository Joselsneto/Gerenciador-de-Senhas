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
public class Encryption {
    
    private final String KEY;
    private final String CHARACTERS = "1234567890!@#$%&*()QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm,;.";
    
    public Encryption(String key) {
        KEY = key;
    }
    
    private char addLetters(char c1, char c2) {
        int pos1 = 0;
        int pos2 = 0;
        for(int i = 0;i < CHARACTERS.length();i++) {
            if(c1 == CHARACTERS.charAt(i)) {
                pos1 = i;
            }
            if(c2 == CHARACTERS.charAt(i)) {
                pos2 = i;
            }
        }
        int realPos = (pos1+pos2)%CHARACTERS.length();
        return CHARACTERS.charAt(realPos);
    }
    
    private char lessLetter(char c1,char c2) {
        int pos1 = 0;
        int pos2 = 0;
        for(int i = 0;i < CHARACTERS.length();i++) {
            if(c1 == CHARACTERS.charAt(i)) {
                pos1 = i;
            }
            if(c2 == CHARACTERS.charAt(i)) {
                pos2 = i;
            }
        }
        int realPos = (pos1-pos2) + CHARACTERS.length();
        realPos %= CHARACTERS.length();
        return CHARACTERS.charAt(realPos);
    }
    
    public String getEncryption(String s) {
        String encrytionString = "";
        for(int i = 0;i < s.length();i++) {
            encrytionString += addLetters(s.charAt(i), KEY.charAt((i)%KEY.length()));
        }
        return encrytionString;
    }
    
    public String getDecrypt(String s) {
        String decryptString = "";
        for(int i = 0;i < s.length();i++) {
            decryptString += lessLetter(s.charAt(i), KEY.charAt((i)%KEY.length()));
        }
        return decryptString;
    }
    
}
