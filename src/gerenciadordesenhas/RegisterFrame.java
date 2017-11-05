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
public class RegisterFrame extends JFrame{
    
    public RegisterFrame() {
        super("Registrar");
        setSize(600,600);
        add(new RegisterPanel());
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
    }
    
}
