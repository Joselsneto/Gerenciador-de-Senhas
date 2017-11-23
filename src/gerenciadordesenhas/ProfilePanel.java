package gerenciadordesenhas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class ProfilePanel extends JPanel {// implements EventListener {
    protected LinkedList<Conta> listaContas;
    public JPanel panelContas;
    private Encryption encryption;
    private final String FILENAME;
    private ProfileFrame pf;
    
    private JButton buttonCadastrar;
    
    public ProfilePanel(String loginName, String key, ProfileFrame _pf) {
        
        pf = _pf;
        
        FILENAME = ("src/" + loginName + ".txt");
        encryption = new Encryption(key);
        
        this.setLayout(new BorderLayout());
        listaContas = new LinkedList<Conta>();
        
        //listaContas.push(new Conta("League of Legends", "Kallaseldor", "fjwiqofnweiovjscfioofd"));
        //listaContas.push(new Conta("Gmail", "felipe.kallas", "RasenganMelhorQueChidori"));
        //listaContas.push(new Conta("DOTA", "Sir Kallas", "tg3fr67e87ht38@@"));
        //listaContas.push(new Conta("Blargh", "Felipe", "Jafhejsdnkhjfkln"));
        
        //salvarArquivo();
//        Conta cc = new Conta("a", "b", "c");
//        cc.setEditable();
//        listaContas.push(cc);

        lerArquivo();
        reload();
        
    }
    
    
    
    // Varre o arquivo do usuário criando Contas
    private void lerArquivo() {
        listaContas = new LinkedList<Conta>();
        BufferedReader br = null;
        FileReader fr = null;
        
        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);
            
            String id = "";
            String username = "";
            String password = "";
            String currentString;
            int cnt = 0;
            while((currentString = br.readLine()) != null) {
                if(cnt == 0) {
                    id = currentString;
                }
                if(cnt == 1) {
                    username = encryption.getDecrypt(currentString);
                }
                if(cnt == 2) {
                    password = encryption.getDecrypt(currentString);
                    listaContas.push(new Conta(id,username,password));
                }
                cnt++;
                cnt %= 3;
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProfilePanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProfilePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Reescreve o arquivo do usuário 
    public void salvarArquivo() {
        String originalFile = "";
        
        BufferedWriter bw = null;
        FileWriter fw = null;
        
        
        try {
            fw = new FileWriter(FILENAME);
            bw = new BufferedWriter(fw);
            
            for (int i = 0; i < listaContas.size(); ++i) {
                Conta conta = listaContas.get(i);
                bw.write(conta.getId() + "\n");
                bw.write(encryption.getEncryption(conta.getUsername()) + "\n");
                bw.write(encryption.getEncryption(conta.getPassword()) + "\n");
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(RegisterPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            try {
                if(bw != null) {
                    bw.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }        
        
    }
    
    // Pega todas as contas da lista e coloca no JPanel. Deve ser chamado sempre que for feita alguma atualizacao
    public void reload() {
        this.removeAll();
        
        // repaint();
        // lerArquivo();
        panelContas = new JPanel();
        panelContas.setLayout(new GridLayout(listaContas.size(), 1));
        
        System.out.println("===========");
        for (int i = 0; i < listaContas.size(); ++i) {
            panelContas.add(new PanelConta(listaContas.get(i), this));
            System.out.println(i + " : " + listaContas.get(i).isEditable());
        }
        
        add(panelContas, BorderLayout.CENTER);
        
        
        repaint();
        pf.reload();
        //System.out.println("End: " + this);
        
        // scrollBar = new JScrollPane();
        // add(scrollBar);
    }
    
    public void deletarConta(Conta _conta) {
        for (int i = 0; i < listaContas.size(); ++i) {
            if (_conta == listaContas.get(i)) {
                Conta c1 = listaContas.get(i);
                listaContas.set(i, listaContas.get(0));
                listaContas.set(0, c1);
                
                listaContas.pop();
            }
        }
        
        salvarArquivo();
    }

//    public void actionPerformed(ActionEvent e) {
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        System.out.println("Entrei aqui");
//        repaint();
//    }
    
}
