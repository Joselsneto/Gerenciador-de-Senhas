/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciadordesenhas;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
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
public class LoginPanel extends JPanel{
    
    private String loginName;
    private JTextField loginField = new JTextField(30);
    private String password;
    private JPasswordField passwordField = new JPasswordField(30);
    private final String LOGINTEXT = "src/login.txt";
    
    
    public LoginPanel() {
        JPanel loginGrid = new JPanel();
        loginGrid.setLayout(new GridLayout(6,1));
        
        // Adicionando JLabels e JTextFields ao menu principal
        loginGrid.add(new JLabel("Usuário: "));
        loginGrid.add(loginField);
        loginGrid.add(new JLabel("Senha: "));
        loginGrid.add(passwordField);
        loginGrid.add(new JLabel(""));
        
        // Adicionando os Botoes Login e Registrar
        JPanel buttonGrid = new JPanel();
        buttonGrid.setLayout(new GridLayout(1,2));
        
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Registrar");
        
        // Implementação do botão de login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginName = loginField.getText();
                password = passwordField.getText();
                
                // @@@@@
                System.out.println(loginName);
                System.out.println(password);
                login();
                /*if(login()) {
                    //TODO
                    //Colocar para ir para tela do usario
                    JOptionPane.showMessageDialog(null, "Acesso confirmado","Logou",JOptionPane.OK_OPTION);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Usuario ou senha incorreto","ERRO",JOptionPane.ERROR_MESSAGE);
                }*/
            }
        });
        
        // Implementação do Botão de Registrar
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterFrame reg = new RegisterFrame();

            }
        });
        
        buttonGrid.add(registerButton);;
        buttonGrid.add(loginButton);;
        
        loginGrid.add(buttonGrid);
        
        add(loginGrid);
        
    }

    private void login() {
        BufferedReader br = null;
        FileReader fr = null;
        
        try{
            fr = new FileReader(LOGINTEXT);
            br = new BufferedReader(fr);
           
            
            String nameCheck, passCheck;
            String temp;
            
            Hash hashPassword = new Hash(password);
           

            while((nameCheck = br.readLine()) != null && (passCheck = br.readLine()) != null) {
                // System.out.println("Cheguei ate aqui com o user " + nameCheck + " e senha " + passCheck);
                // System.out.println("O user era pra ser " + loginName);
                if (nameCheck.equals(loginName)) {
                    System.out.println(hashPassword.getHash() + " e senha " + passCheck);
                    if (passCheck.equals(hashPassword.getHash())) {
                        
                        ProfileFrame pf = new ProfileFrame(loginName, password);
                        
                        return;
                    } else {
                        JOptionPane.showMessageDialog(null, "Senha incorreta","ERRO",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                
                }
            }
            
            JOptionPane.showMessageDialog(null, "Usuário não foi encontrado","ERRO",JOptionPane.ERROR_MESSAGE);
                
               /*while(st.hasMoreElements()) {
                   String aux = st.nextToken();                   
                   Hash hashAux = new Hash(aux);
                  
                   if(prev.equals("Login")) {

                       if(aux.equals(hashName.getHash())) {
                           flagLogin = true;
                       }
                   }
                   if(prev.equals("Password")) {
               
                       if(aux.equals(hashPassword.getHash())) {
                           flagPassword = true;
                       }
                       if(flagLogin == true && flagPassword == true)
                           return true;
                       flagLogin = false;
                       flagPassword = false;                       
                   }
                   prev = aux;                   
               }*/
               
        }
        
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                LOGINTEXT + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + LOGINTEXT + "'");                  

        }
    }
    
}
