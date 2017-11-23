package gerenciadordesenhas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelConta extends JPanel {
    Conta conta;
    Conta contaAntiga;
    
    // JTextfields referentes aos id, username e password
    JTextField tfId;
    JTextField tfUsername;
    JTextField tfPassword;
    
    JPanel panelButtons;
    
    JButton buttonEdit;
    JButton buttonDelete;
    JButton buttonSave;
    JButton buttonRandom;
    JButton buttonCancel;
    
    ProfilePanel profilePanel;
    Random rand;
    
    private final int tfSize = 18;
    
    private final String CHARACTERS = "1234567890!@#$%&*()QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
    
    public PanelConta(Conta _conta, ProfilePanel _profilePanel) {
        rand = new Random();
        conta = _conta;
        profilePanel = _profilePanel;
        
        this.setLayout(new GridLayout(5, 1));
        
        if (conta.isEditable()) {
            buildEditable();
        } else {
            buildUneditable();
        }
        
        setSize(500, 250);
        
    }
    
    
    
    // Coloca o Panel no modo Uneditable
    private void buildEditable() {
        
        //Inicializando os Textfields
        tfId = new JTextField(tfSize);
        tfId.setText(conta.getId());
        tfId.setEditable(true);
        
        tfUsername = new JTextField(tfSize);
        tfUsername.setText(conta.getUsername());
        tfUsername.setEditable(true);
        
        tfPassword = new JTextField(tfSize);
        tfPassword.setText(conta.getPassword());
        tfPassword.setEditable(true);
        
        // Declaracao dos Botoes
        buttonSave = new JButton("Salvar");
        buttonRandom = new JButton("Gerar Senha Randômica");
        buttonCancel = new JButton("Cancelar");
        
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(conta.isNew()) {
                   profilePanel.deletarConta(conta);
                } else {
                   conta.setUneditable();
               }
                profilePanel.reload();
            }
        });
        
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conta.setId(tfId.getText());
                conta.setUsername(tfUsername.getText());
                conta.setPassword(tfPassword.getText());
                
                conta.setNew(false);
                
                profilePanel.salvarArquivo();
                
                conta.setUneditable();
                profilePanel.reload();
            }
        });
              
        
        Dimension labelDimension = new Dimension(75, 25);
        
        //JLabel e JTextField do ID
        JPanel panelId = new JPanel();
        panelId.setLayout(new BorderLayout());
        JLabel idLabel = new JLabel("  Origem:");
        idLabel.setPreferredSize(labelDimension);
        panelId.add(idLabel, BorderLayout.LINE_START);
        panelId.add(tfId, BorderLayout.CENTER);
        this.add(panelId);
        
        
        //JLabel e JTextField do Username
        JPanel panelUsername = new JPanel();
        panelUsername.setLayout(new BorderLayout());
        JLabel userLabel = new JLabel("  Usuário:");
        userLabel.setPreferredSize(labelDimension);
        panelUsername.add(userLabel, BorderLayout.LINE_START);
        panelUsername.add(tfUsername, BorderLayout.CENTER);
        this.add(panelUsername);
        
        
        //JLabel e JTextField da Senha
        JPanel panelPassword = new JPanel();
        panelPassword.setLayout(new BorderLayout());
        JLabel passLabel = new JLabel("  Senha:");
        passLabel.setPreferredSize(labelDimension);
        
        panelPassword.add(passLabel, BorderLayout.LINE_START);
        panelPassword.add(tfPassword, BorderLayout.CENTER);
        panelPassword.add(buttonRandom, BorderLayout.LINE_END);
        this.add(panelPassword);
        
        buttonRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfPassword.setText(generateRandom());
            }
        });
        
        
        // Botões Edit e Delete
        // Esses JLabels vazios que eu coloco sao pra dar um espaco. GAMBIARRA FTW
        panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(2, 5));
        
        for (int i = 0; i < 5; ++i)
            panelButtons.add(new JLabel());
        panelButtons.add(new JLabel());
        panelButtons.add(buttonSave);
        panelButtons.add(new JLabel());
        panelButtons.add(buttonCancel);
        panelButtons.add(new JLabel());

        this.add(panelButtons);
        
    }
    
    
    
    //Aqui ainda ta tudo cagado
    private void buildUneditable() {
        //Inicializando os Textfields
        tfId = new JTextField(tfSize);
        tfId.setText(conta.getId());
        tfId.setEditable(false);
        
        tfUsername = new JTextField(tfSize);
        tfUsername.setText(conta.getUsername());
        tfUsername.setEditable(false);
        
        tfPassword = new JTextField(tfSize);
        tfPassword.setText(conta.getPassword());
        tfPassword.setEditable(false);
        
        
        // Declaracao dos Botoes
        buttonEdit = new JButton("Editar");
        buttonDelete = new JButton("Deletar");
        
        //Implementacao dos Botoes Edit e Delete
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conta.setEditable();
                profilePanel.reload();
            }
        });
        
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profilePanel.deletarConta(conta);
                profilePanel.reload();
            }
        });
        
        Dimension labelDimension = new Dimension(75, 25);
        
        //JLabel e JTextField do ID
        JPanel panelId = new JPanel();
        panelId.setLayout(new BorderLayout());
        JLabel idLabel = new JLabel("  Origem:");
        idLabel.setPreferredSize(labelDimension);
        panelId.add(idLabel, BorderLayout.LINE_START);
        panelId.add(tfId, BorderLayout.CENTER);
        this.add(panelId);
        
        
        //JLabel e JTextField do Username
        JPanel panelUsername = new JPanel();
        panelUsername.setLayout(new BorderLayout());
        JLabel userLabel = new JLabel("  User:");
        userLabel.setPreferredSize(labelDimension);
        panelUsername.add(userLabel, BorderLayout.LINE_START);
        panelUsername.add(tfUsername, BorderLayout.CENTER);
        this.add(panelUsername);
        
        
        //JLabel e JTextField da Senha
        JPanel panelPassword = new JPanel();
        panelPassword.setLayout(new BorderLayout());
        JLabel passLabel = new JLabel("  Senha:");
        passLabel.setPreferredSize(labelDimension);
        panelPassword.add(passLabel, BorderLayout.LINE_START);
        panelPassword.add(tfPassword, BorderLayout.CENTER);
        this.add(panelPassword);
        
        // Botões Edit e Delete
        // Esses JLabels vazios que eu coloco sao pra dar um espaco. GAMBIARRA FTW
        panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(2, 5));
        
        for (int i = 0; i < 5; ++i)
            panelButtons.add(new JLabel());
        panelButtons.add(new JLabel());
        panelButtons.add(buttonEdit);
        panelButtons.add(new JLabel());
        panelButtons.add(buttonDelete);
        panelButtons.add(new JLabel());

        this.add(panelButtons);
    }
    
    private String generateRandom() {
        String newPassword = "";
        
        for (int i = 0; i < 24; ++i) {
            int index = rand.nextInt() % CHARACTERS.length();
            if (index < 0)
                index += CHARACTERS.length();
            
            newPassword += CHARACTERS.charAt(index);
        }
        
        return newPassword;
    }
    
}
