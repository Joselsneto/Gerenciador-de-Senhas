/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciadordesenhas;

import javax.swing.JPanel;

/**
 *
 * @author José
 */
public class MainPanel extends JPanel{
    public MainPanel() {
        add(new LoginPanel());
    }
}
