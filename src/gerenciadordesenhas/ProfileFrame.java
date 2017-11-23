/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciadordesenhas;

import java.awt.Adjustable;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class ProfileFrame extends JFrame {
    
    // JScrollPane scrollPane;
    
    public ProfileFrame(String a, String b) {
        
        super("Gerenciador de Senhas");
        setSize(500, 600);
        JButton buttonCadastrar = new JButton("Cadastrar nova conta");
        add(buttonCadastrar, BorderLayout.SOUTH);
        
        ProfilePanel profilePanel = new ProfilePanel(a, b, this);
        JScrollPane scrollPane = new JScrollPane(profilePanel);
        
        buttonCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Conta empty = new Conta("", "", "");
                empty.setEditable();
                empty.setNew(true);
                profilePanel.listaContas.add(empty);
                profilePanel.reload();
                scrollToBottom(scrollPane);
            }
        });
        
        
        
        add(scrollPane);
        
        
        // add(new ProfilePanel(a, b, this));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    
    }
    
    private void scrollToBottom(JScrollPane scrollPane) {
        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }
    
    public void reload() {
        setSize(500, 601);
        setSize(500, 600);
    }
}