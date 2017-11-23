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
public class MainFrame extends JFrame{
    public MainFrame() {
        super("Gerenciador de Senhas");
        setSize(400, 400);
        add(new MainPanel());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
