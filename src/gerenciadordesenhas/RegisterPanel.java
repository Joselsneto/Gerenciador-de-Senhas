/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciadordesenhas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author José
 */
public class RegisterPanel extends JPanel{
    private String loginName = "";
    private JTextField loginField = new JTextField(30);
    private String password = "";
    private JPasswordField passwordField = new JPasswordField(30);
    private final String LOGIN_FILE_NAME = "src/login.txt";
    private PrintWriter pw = null;
    
    public RegisterPanel() {
        JPanel registerGrid = new JPanel();
        registerGrid.setLayout(new GridLayout(6,1));
        
        registerGrid.add(new JLabel("Digite um novo nome de usuário:"));
        registerGrid.add(loginField);
        registerGrid.add(new JLabel("Digite uma nova senha:"));
        registerGrid.add(passwordField);
        
        JButton registrar = new JButton("Registrar");
        registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginName = loginField.getText();
                password = passwordField.getText();
                boolean flag = true;
                if(loginName.equals("")) {
                    JOptionPane.showMessageDialog(null, "Nome de usario invalido", "ERRO", JOptionPane.OK_OPTION);
                    flag = false;
                }
                if(password.equals("")) {
                    JOptionPane.showMessageDialog(null, "Senha invalida", "ERRO", JOptionPane.OK_OPTION);
                    flag = false;
                }
                if(flag) {
                    if(registerNewUser()){
                        JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso");
                        //TODO 
                        //Fechar a janela após cadastrar
                        try {
                            pw = new PrintWriter("src/"  + loginName + ".txt");
                            
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(RegisterPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }finally{
                            pw.close();
                        }
                    }
                    
                }
                
            }
        });
        registerGrid.add(new JLabel(""));
        registerGrid.add(registrar);
        add(registerGrid);        
    }
    
    
    private boolean registerNewUser() {
        String originalFile = "";
        
        BufferedWriter bw = null;
        FileWriter fw = null;
        
        BufferedReader br = null;
        FileReader fr = null;
        
        try{
            fr = new FileReader(LOGIN_FILE_NAME);
            br = new BufferedReader(fr);
                    
            String s;
            
            while((s = br.readLine()) != null) {
                originalFile += s + '\n';
                
                StringTokenizer st = new StringTokenizer(s," :");
                String prev = "";
                
                while(st.hasMoreElements()) {
                    String aux = st.nextToken();
                    
                    if(prev.equals("Login")) {
                        Hash auxHash = new Hash(loginName);
                        if(aux.equals(auxHash.getHash())) {
                            JOptionPane.showMessageDialog(null, "Usario já existente","ERRO",JOptionPane.OK_CANCEL_OPTION);
                            return false;
                        }
                    }
                    prev = aux;
                }
                
            }
            
        }
        catch(FileNotFoundException fne) {
            System.out.println(
                "Unable to open file '" + 
                LOGIN_FILE_NAME + "'");  
        } 
        catch(IOException ie) {
            System.out.println(
                "Error reading file '" + 
                LOGIN_FILE_NAME + "'");
        }
        
        try {
            fw = new FileWriter(LOGIN_FILE_NAME);
            bw = new BufferedWriter(fw);
            
            bw.write(originalFile);
//            bw.write("Login: ");
            bw.write(loginName + '\n');
            Hash hashPassword = new Hash(password);
//            bw.write("Password: ");
            bw.write(hashPassword.getHash() + '\n');
            
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
                return false;
            }
        }        
        
        return true;
    }
    
}
