package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

import regras.Factory; 

public class FRJogo extends JFrame{
	  final int LARG_DEFAULT=1200;
	  final int ALT_DEFAULT=850;
	  static FRJogo frame = null; 
	  JPanel tab;  
	  JPanel bot;
	  JPanel hist;
	  Factory fac; 
	  
	  //Cria Novo Jogo
	   private FRJogo(int num) {
		    frameConfig(); 
	        fac = Factory.startFactory(num);  
	        panelsConfig(); 
	   } 
	   public static FRJogo NovoJogo(int num) { 
		   if (frame == null) { 
			   frame = new FRJogo (num); 
		   }
		   return frame; 
	   }
	   
	   //Inicializa Jogo Antigo
	   private FRJogo(File loadJogo) throws IOException {
		   frameConfig(); 
		   fac = Factory.startFactory(loadJogo); 
		   panelsConfig(); 
		   new PNTabuleiro(tab);
		   tab.repaint();
		   
	   }
	   
	   public static FRJogo LoadJogo(File f) throws IOException { 
		   if (frame == null) { 
			   frame = new FRJogo(f); 
		   }
		   return frame; 
	   }
	   
	   
	   
	   private void panelsConfig() { 
		   tab = fac.getTab(); 
		   bot = fac.getBot(); 
		   hist = fac.getHist(); 
		   
			tab.setPreferredSize(new Dimension(700,700));	
			bot.setPreferredSize(new Dimension(350,700));
			hist.setPreferredSize(new Dimension(200,700));
			bot.setBorder(BorderFactory.createTitledBorder("Controles"));
			hist.setBorder(BorderFactory.createTitledBorder("Transacoes Rodada"));
			
			getContentPane().add(tab);
			getContentPane().add(bot);
			getContentPane().add(hist); 
			
			pack(); 
			setVisible(true); 
	   }
	   
	   private void frameConfig() { 
		   setTitle("Banco Imobiliario");
		   setLayout(new FlowLayout());
		    Toolkit tk=Toolkit.getDefaultToolkit();
			Dimension screenSize=tk.getScreenSize();
			
			JMenuBar menu = new JMenuBar();
			JMenu Opcoes = new JMenu("Opcoes");
	        menu.add(Opcoes);
	        JMenuItem salvar = new JMenuItem("Salvar Jogo");
	        JMenuItem terminar = new JMenuItem("Terminar Jogo");
	        
	        Opcoes.add(salvar); 
	        Opcoes.add(terminar); 
	        
	        terminar.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		terminarJogo(); 
	        	}
	        });
	        
	        salvar.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		saveGame(); 
	        	}
	        });
	        setJMenuBar(menu);
	        int sl=screenSize.width;
			int sa=screenSize.height;
			int x=sl/2-LARG_DEFAULT/2;
			int y=sa/2-ALT_DEFAULT/2;
			setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
			setDefaultCloseOperation(EXIT_ON_CLOSE);	
	   }
	   
	  private void saveGame(){ 
		  
		  try{    
	           FileWriter fw=new FileWriter("testout.txt");    
	           fac.getJogoInfo(); 
	           fw.write(fac.getJogoInfo());    
	           fw.close();    
	          }
		  catch(Exception e)
		  {
	        	  System.out.println(e);
	      }    
	          System.out.println("Success...");    
			
	  }
	  
	  private void terminarJogo() { 
		  this.setVisible(false);
		  fac.terminarJogo(); 
		  this.dispose(); 
	  }
	  
	  public static void main(String args[]) {
		     MenuInicial menuIni = new MenuInicial();
		}
}
