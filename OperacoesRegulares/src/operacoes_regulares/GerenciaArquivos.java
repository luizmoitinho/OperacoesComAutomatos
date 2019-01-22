package operacoes_regulares;
import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class GerenciaArquivos {
    private String nomeArqI,nomeArqII,caminhoI,caminhoII; 
	ArrayList <Automato> m1 = new ArrayList<Automato>();
	ArrayList <Automato> m2 = new ArrayList<Automato>();
	ArrayList <Automato> uniao = new ArrayList<Automato>();
	ArrayList <Automato> interseccao = new ArrayList<Automato>();
    public boolean recebeArquivos() {
    	try {	
    		lerArquivo(caminhoI,nomeArqI,m1);
    		lerArquivo(caminhoII,nomeArqII,m2);
    		unity(m1,m2,uniao);
    		intersection(m1,m2,interseccao);
    		criaArquivo(uniao,caminhoI,"união.jff");
    		criaArquivo(interseccao,caminhoII,"intersecção.jff");
    		
    		return true;
    	}catch(Exception e){
    		return false;
    	}
    }
 
    public void lerArquivo(String caminho, String nome ,ArrayList<Automato> lista) throws IOException {
	    	FileInputStream arquivo = new FileInputStream(caminho+"\\"+nome+".jff");  
			InputStreamReader input = new InputStreamReader(arquivo);
			BufferedReader br = new BufferedReader(input);
    		String linha,texto="",from="",simbolo="",to="";	
    		int aspas=0;
    		Automato x =  new Automato();
    		do {
    			
    			linha = br.readLine();
    			texto+=linha+"\n";
    			if(linha!=null) {
    			  for(int i=0;i<linha.length();i++){
    			     //ACHA O ID E O NOME
    			      if(linha.charAt(i)=='s' &&linha.charAt(i+1)=='t' && linha.charAt(i+2)=='a' && linha.charAt(i+3)=='t' && linha.charAt(i+4)=='e' && linha.charAt(i+6)=='i' && linha.charAt(i+7)=='d'){
    			         for(int j=i;j<linha.length();j++){
    			            if(linha.charAt(j)=='"')
    			                aspas++;
    			            else if(linha.charAt(j)!='"' && aspas==1)
    			                x.setId(x.getId()+linha.charAt(j));
    			            else if (linha.charAt(j)!='"' && aspas==3)
    			            	x.setNome(x.getNome()+linha.charAt(j));
    			           }
	    			      while(!linha.equals("		</state>&#13;")){
	    	                    if(linha.equals("			<initial/>&#13;"))
	    	                        x.setEstadoInicial(true);
	    	                    if(linha.equals("			<final/>&#13;"))
	    	                       x.setEstadoFinal(true);
	    	                    linha = br.readLine();
	    	                }
	    			     lista.add(x);		     
	    			     x =  new Automato();
    	                aspas=0;
    	             }
    			    //ACHA A TRANSIÇÃO
    		        else if(linha.charAt(i)=='<' && linha.charAt(i+1)=='f' && linha.charAt(i+2)=='r' && linha.charAt(i+3) == 'o' && linha.charAt(i+4)=='m' && linha.charAt(i+5)=='>' ){
    		             i+=6;
    		             //encontra o id do qual o estado lê
    		             while(linha.charAt(i)!='<'){
    		                  from+=linha.charAt(i);
    		                  i++;
    		             }
    		             linha = br.readLine();
    		             for(int ii = 0;ii<linha.length();ii++){
    		                 if (linha.charAt(ii)=='<' && linha.charAt(ii+1) =='t' && linha.charAt(ii+2)=='o' && linha.charAt(ii+3)=='>'){
    		                    ii+=4;
    		                    while(linha.charAt(ii)!='<'){
    		                        to+=linha.charAt(ii);
    		                        ii++;
    		                     }
    		                 }
    		            }
    		            linha = br.readLine();
    		            for(int j=0;j<linha.length();j++){
    		                if(linha.charAt(j)=='<' && linha.charAt(j+1) =='r' && linha.charAt(j+2)=='e' && linha.charAt(j+3)=='a' && linha.charAt(j+4)=='d'&& linha.charAt(j+5)=='>'){
    		                    j+=6;
    		                    while(linha.charAt(j)!='<'){
    		                      simbolo+=linha.charAt(j);
    		                       j++;
    		                     }
    		                }
    		            }
    		            
    		            String id;
    		            for(int j=0;j<lista.size();j++){
    		            	 if(lista.get(j).getId().equals(from)){
    		                        if(lista.get(j).getSimboloI().equals("")){
    		                        	lista.get(j).setSimboloI(simbolo);
    		                        	lista.get(j).setLendoI(to);
    		                        }else if(lista.get(j).getSimboloII().equals("")){
    		                        	lista.get(j).setSimboloII(simbolo);
    		                        	lista.get(j).setLendoII(to);
    		                        }
    		                 }
    		            	
    		            }

    		       }//Fim do else if
    			  }//FIM DO FOR
    			  simbolo = "";
    			  to ="";
    			  from="";
    		   }//FIM DO IF
    		}while(linha!=null);
    		br.close();
    		/*for(int ii=0;ii<lista.size();ii++) {
			JOptionPane.showMessageDialog(null,
												"ID                 : "+lista.get(ii).getId()+
												"\nNome              : "+lista.get(ii).getNome()+
												"\nEstado inicial?   : "+lista.get(ii).isEstadoInicial()+
												"\nEstado final?     : "+lista.get(ii).isEstadoInicial()+
												"\nLendo "+lista.get(ii).getSimboloI()+" vai para ? "+lista.get(ii).getLendoI()+
												"\nLendo "+lista.get(ii).getSimboloII()+" vai para ? "+lista.get(ii).getLendoII());
    		}
    		*/
    		
    }

    public void unity(ArrayList<Automato> a1,ArrayList<Automato> a2, ArrayList<Automato> maquina){
        // Realiza todas as combinações
        String id;
        Automato a3 = new Automato();
    	for(int i=0,cont=0;i<a1.size();i++){
            for(int j=0;j<a2.size();j++){
                if(a1.get(i).isEstadoInicial() && a2.get(j).isEstadoInicial())
                	a3.setEstadoInicial(true);
                if(a1.get(i).isEstadoFinal()==true || a2.get(j).isEstadoFinal()==true)
                	a3.setEstadoFinal(true);
                a3.setNome(a1.get(i).getNome()+a2.get(j).getNome());
                id= Integer.toString(cont);
                a3.setId(id);
                if(a1.get(i).getSimboloI().length()==4)
                    separaSimbolo(a3,a1,i,1);
                else if(a1.get(i).getSimboloII().length()==4)
                    separaSimbolo(a3,a1,i,2);
                if(a2.get(i).getSimboloI().length()==4)
                    separaSimbolo(a3,a2,j,1);
                else if(a2.get(i).getSimboloII().length()==4)
                    separaSimbolo(a3,a2,j,2);
                maquina.add(a3);
                a3 = new Automato();
                cont++;
            }
           
        }

       int p1,p2;
       //Automato armazena = new Automato();
       for(int i=0; i<maquina.size(); i++ ){
            for(int j = 0 ;j<a1.size();j++){
                for(int k = 0 ;k<a2.size();k++){
                    if(a1.get(j).getSimboloI().equals(a2.get(k).getSimboloI()) && ((a1.get(j).getNome()+a2.get(k).getNome()).equals(maquina.get(i).getNome()))){
                        maquina.get(i).setSimboloI(a1.get(j).getSimboloI());
                        p1 = Integer.parseInt(a1.get(j).getLendoI());
                        p2 = Integer.parseInt(a2.get(k).getLendoI());
                        for(int m=0 ;m<maquina.size();m++){
                            if(maquina.get(m).getNome().equals(a1.get(p1).getNome()+a2.get(p2).getNome() ))
                            	maquina.get(i).setLendoI(Integer.toString(m));
                        }
                    }else if(a1.get(j).getSimboloI().equals(a2.get(k).getSimboloII()) && ((a1.get(j).getNome()+a2.get(k).getNome()).equals(maquina.get(i).getNome()))){
                    	maquina.get(i).setSimboloI(a1.get(j).getSimboloI());
                        p1 = Integer.parseInt(a1.get(j).getLendoI());
                        p2 = Integer.parseInt(a2.get(k).getLendoII());
                        for(int m=0 ;m<maquina.size();m++){
                            if(maquina.get(m).getNome().equals((a1.get(p1).getNome()+a2.get(p2).getNome())))
                            	maquina.get(i).setLendoI(Integer.toString(m));
                        }
                    }
                    if(a1.get(j).getSimboloII().equals(a2.get(k).getSimboloII()) && ((a1.get(j).getNome()+a2.get(k).getNome()).equals(maquina.get(i).getNome()))){
                    	maquina.get(i).setSimboloII(a1.get(j).getSimboloII());
                        p1 = Integer.parseInt(a1.get(j).getLendoII());
                        p2 = Integer.parseInt(a2.get(k).getLendoII());
                        for(int m=0 ;m<maquina.size();m++){
                            if(maquina.get(m).getNome().equals((a1.get(p1).getNome()+a2.get(p2).getNome())))
                            	maquina.get(i).setLendoII(Integer.toString(m));
                        }
                    }
                    else if(a1.get(j).getSimboloII().equals(a2.get(k).getSimboloI()) && ((a1.get(j).getNome()+a2.get(k).getNome()).equals(maquina.get(i).getNome()))){
                    	maquina.get(i).setSimboloII(a1.get(j).getSimboloII());
                        p1 = Integer.parseInt(a1.get(j).getLendoII());
                        p2 = Integer.parseInt(a2.get(k).getLendoI());
                        for(int m=0 ;m<maquina.size();m++){
                            if(maquina.get(m).getNome().equals((a1.get(p1).getNome()+a2.get(p2).getNome())));
                            maquina.get(i).setLendoII(Integer.toString(m));
                        }              
                    }

                }
            }
       }
   
       	
    }
    
    public void intersection(ArrayList<Automato> a1,ArrayList<Automato> a2, ArrayList<Automato> maquina){
        // Realiza todas as combinações
        String id;
        Automato a3 = new Automato();
    	for(int i=0,cont=0;i<a1.size();i++){
            for(int j=0;j<a2.size();j++){
                if(a1.get(i).isEstadoInicial() && a2.get(j).isEstadoInicial())
                	a3.setEstadoInicial(true);
                if(a1.get(i).isEstadoFinal() && a2.get(j).isEstadoFinal())
                	a3.setEstadoFinal(true);
                a3.setNome(a1.get(i).getNome()+a2.get(j).getNome());
                id= Integer.toString(cont);
                a3.setId(id);
                if(a1.get(i).getSimboloI().length()==4)
                    separaSimbolo(a3,a1,i,1);
                else if(a1.get(i).getSimboloII().length()==4)
                    separaSimbolo(a3,a1,i,2);
                if(a2.get(i).getSimboloI().length()==4)
                    separaSimbolo(a3,a2,j,1);
                else if(a2.get(i).getSimboloII().length()==4)
                    separaSimbolo(a3,a2,j,2);
                maquina.add(a3);
                a3 = new Automato();
                cont++;
            }
           
        }
       int p1,p2;
       //Automato armazena = new Automato();
       for(int i=0; i<maquina.size(); i++ ){
            for(int j = 0 ;j<a1.size();j++){
                for(int k = 0 ;k<a2.size();k++){
                    if(a1.get(j).getSimboloI().equals(a2.get(k).getSimboloI()) && ((a1.get(j).getNome()+a2.get(k).getNome()).equals(maquina.get(i).getNome()))){
                        maquina.get(i).setSimboloI(a1.get(j).getSimboloI());
                        p1 = Integer.parseInt(a1.get(j).getLendoI());
                        p2 = Integer.parseInt(a2.get(k).getLendoI());
                        
                        for(int m=0 ;m<maquina.size();m++){
                            if(maquina.get(m).getNome().equals(a1.get(p1).getNome()+a2.get(p2).getNome()))
                            	maquina.get(i).setLendoI(Integer.toString(m));
                        }
                    }else if(a1.get(j).getSimboloI().equals(a2.get(k).getSimboloII()) && ((a1.get(j).getNome()+a2.get(k).getNome()).equals(maquina.get(i).getNome()))){
                    	maquina.get(i).setSimboloI(a1.get(j).getSimboloI());
                        p1 = Integer.parseInt(a1.get(j).getLendoI());
                        p2 = Integer.parseInt(a2.get(k).getLendoII());
                        for(int y=0;y<maquina.size();y++){
                            if(maquina.get(y).getNome().equals((a1.get(p1).getNome()+a2.get(p2).getNome()))) 
                            	maquina.get(i).setLendoI(Integer.toString(y));
                            	
                        }
                    }
                    if(a1.get(j).getSimboloII().equals(a2.get(k).getSimboloII()) && ((a1.get(j).getNome()+a2.get(k).getNome()).equals(maquina.get(i).getNome()))){
                    	maquina.get(i).setSimboloII(a1.get(j).getSimboloII());
                        p1 = Integer.parseInt(a1.get(j).getLendoII());
                        p2 = Integer.parseInt(a2.get(k).getLendoII());
                        for(int m=0 ;m<maquina.size();m++){
                            if(maquina.get(m).getNome().equals((a1.get(p1).getNome()+a2.get(p2).getNome())))
                            	maquina.get(i).setLendoII(Integer.toString(m));
                        }
                    }
                    else if(a1.get(j).getSimboloII().equals(a2.get(k).getSimboloI()) && ((a1.get(j).getNome()+a2.get(k).getNome()).equals(maquina.get(i).getNome()))){
                    	maquina.get(i).setSimboloII(a1.get(j).getSimboloII());
                        p1 = Integer.parseInt(a1.get(j).getLendoII());
                        p2 = Integer.parseInt(a2.get(k).getLendoI());
                        for(int m=0 ;m<maquina.size();m++){
                            if(maquina.get(m).getNome().equals((a1.get(p1).getNome()+a2.get(p2).getNome())))
                            maquina.get(i).setLendoII(Integer.toString(m));
                        }              
                    }

                }
            }
       }
    }
   
    
    void  criaArquivo(ArrayList<Automato> x, String caminho,String nome) throws FileNotFoundException{
        FileOutputStream arquivo = new FileOutputStream(caminho+"\\"+nome);
        PrintWriter pr = new PrintWriter(arquivo);
            pr.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><!--Created with JFLAP 6.4.--><structure>&#13;");
            pr.println("	<type>fa</type>&#13;");
            pr.println("	<automaton>&#13;");
            pr.println("		<!--The list of states.-->&#13;");
            int eixoX=100,eixoY=100;
            for(int i=0;i<x.size();i++){
            	pr.println("		<state id= \""+x.get(i).getId()+"\" name=\""+x.get(i).getNome()+"\">&#13;");
            	pr.println("			<x>"+eixoX+"</x>&#13;");

                if(i%2==0)
                	pr.println("			<y>"+(eixoY+50)+"</y>&#13;");
                else
                	pr.println("			<y>"+(eixoY-50)+"</y>&#13;");

                if(x.get(i).isEstadoInicial())
                	pr.println("			<initial/>&#13;");
                if(x.get(i).isEstadoFinal())
                	pr.println("			<final/>&#13;");
                pr.println("		</state>&#13;");
                eixoX+=50;
                eixoY+=10;
            }
            pr.println("		<!--The list of transitions.-->&#13;");
            for(int i=0;i<x.size();i++){
            	pr.println("		<transition>&#13;");
            	pr.println("			<from>"+x.get(i).getId()+"</from>&#13;");
            	pr.println("			<to>"+x.get(i).getLendoI()+"</to>&#13;");
            	pr.println("			<read>"+x.get(i).getSimboloI()+"</read>&#13;");
            	pr.println("		</transition>&#13;");
            	pr.println("		<transition>&#13;");
            	pr.println("			<from>"+x.get(i).getId()+"</from>&#13;");
            	pr.println("			<to>"+x.get(i).getLendoII()+"</to>&#13;");
            	pr.println("			<read>"+x.get(i).getSimboloII()+"</read>&#13;");
            	pr.println("		</transition>&#13;");
            }
            pr.println("	</automaton>&#13;");
            pr.println("</structure>");
            
            pr.close();
    }
    
    
    
    public void separaSimbolo(Automato m1_m2, ArrayList<Automato> m , int posicao,int tipoSimbolo){
        Automato temp = new Automato();
        String simbolo;
        if(tipoSimbolo==1){
        	simbolo = Character.toString(m.get(posicao).getSimboloI().charAt(3));
        	m1_m2.setSimboloI(simbolo);
        	simbolo = Character.toString(m.get(posicao).getSimboloI().charAt(1));
        	m1_m2.setSimboloII(simbolo);
        	
        	temp.setSimboloI(Character.toString(m.get(posicao).getSimboloI().charAt(3)));
        	temp.setSimboloII(Character.toString(m.get(posicao).getSimboloI().charAt(1)));

            m.get(posicao).setSimboloI("");

            m.get(posicao).setSimboloI(temp.getSimboloI());
            m.get(posicao).setSimboloII(temp.getSimboloII());
            
            m.get(posicao).setLendoII(m.get(posicao).getLendoI());

        }
        else{
        	
        	simbolo = Character.toString(m.get(posicao).getSimboloII().charAt(3));
        	m1_m2.setSimboloI(simbolo);
        	simbolo = Character.toString(m.get(posicao).getSimboloII().charAt(1));
        	m1_m2.setSimboloII(simbolo);
        	
        	temp.setSimboloI(Character.toString(m.get(posicao).getSimboloII().charAt(3)));
        	temp.setSimboloII(Character.toString(m.get(posicao).getSimboloII().charAt(1)));

            m.get(posicao).setSimboloII("");

            m.get(posicao).setSimboloI(temp.getSimboloI());
            m.get(posicao).setSimboloII(temp.getSimboloII());
            
            m.get(posicao).setLendoI(m.get(posicao).getLendoII());
        }

    }
    
    public String getCaminhoI() {
		return caminhoI;
	}
	public void setCaminhoI(String caminhoI) {
		this.caminhoI = caminhoI;
	}
	public String getCaminhoII() {
		return caminhoII;
	}
	public void setCaminhoII(String caminhoII) {
		this.caminhoII = caminhoII;
	}
	public String getNomeArqI() {
		return nomeArqI;
	}
	public void setNomeArqI(String nomeArqI) {
		this.nomeArqI = nomeArqI;
	}
	public String getNomeArqII() {
		return nomeArqII;
	}
	public void setNomeArqII(String nomeArqII) {
		this.nomeArqII = nomeArqII;
	}
	
}
