/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciadordesenhas;

import javax.swing.JFrame;

/**
 *
 * @author José
 */
public class userFrame extends JFrame{
    public userFrame(String s) {
        super("Tela de usuario");
        setSize(600,600);
        add(new userPanel(s));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
