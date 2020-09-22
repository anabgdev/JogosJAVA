package jogos;

import java.util.Scanner;

public class JogoDaVelha {
	
private int[][] tabuleiro= new int[3][3];
	
    private int rodada=1;
    private int vez=1;
    private int[] jogador1 = new int[2];
    private int[] jogador2 = new int[2];
    public Scanner teclado = new Scanner(System.in);
    protected int[] jogador1Tentaviva = new int[2];
    
	int[] jogadorAux1 = new int[2];
	int[] jogadorAux2 = new int[2];
	int[] computadorAux1 = new int[2];
	int[] computadorAux2 = new int[2];

    
    public void jogarJogoDaVelha(){

    	jogadorAux1[0] = 1;
    	jogadorAux1[1] = 1;
    	
    	jogadorAux2[0] = 2;
    	jogadorAux2[1] = 2;
    	
    	computadorAux1[0] = 3;
    	computadorAux1[1] = 3;
    	
    	computadorAux2[0] = 5;
    	computadorAux2[1] = 5;
    	
        escolherJogadores();
        
        while( jogar() ); //loop 
    }
    
	
    public void zerarTabuleiro(){
        for(int linha = 0 ; linha < 3 ; linha++)
            for(int coluna = 0 ; coluna < 3 ; coluna++)
                tabuleiro[linha][coluna]=0;
    }
    
    public void imprimirTabuleiro(){
        System.out.println();
        for(int lin = 0 ; lin < 3 ; lin++){
            for(int col = 0 ; col < 3 ; col++){
                if(tabuleiro[lin][col]==-2){
                    System.out.print(" X ");
                }
                if(tabuleiro[lin][col]==2){
                    System.out.print(" O ");
                }
                if(tabuleiro[lin][col]==0){
                    System.out.print("   ");
                }
                
                if(col==0 || col==1)
                    System.out.print("|");
            }
            System.out.println();
            if (lin < 2) {
                System.out.println("------------");
            }
        }
    }
    
    public int pegarPosicao(int[] posicao){
        return tabuleiro[posicao[0]][posicao[1]];
    }
    
    public void setPosicao(int[] posicao, int[] jogador){
        if( jogador[1] == 1 || jogador[1] == 3) {
            tabuleiro[posicao[0]][posicao[1]] = -2; //jogador 1
        }else if (jogador[1] == 2 || jogador[1] == 5) {
            tabuleiro[posicao[0]][posicao[1]] = 2; //jogador 2
        }
        imprimirTabuleiro();
    }

    public int verificarLinhas(){
        for(int lin = 0 ; lin < 3 ; lin++){
            if( (tabuleiro[lin][0] + tabuleiro[lin][1] + tabuleiro[lin][2]) == -6)
                return -2;
            if( (tabuleiro[lin][0] + tabuleiro[lin][1] + tabuleiro[lin][2]) == 6)
                return 2;
        }
        return 0;        
    }
    
    public int verificarColunas(){
        for(int col = 0 ; col < 3 ; col++){
            if( (tabuleiro[0][col] + tabuleiro[1][col] + tabuleiro[2][col]) == -6)
                return -2;
            if( (tabuleiro[0][col] + tabuleiro[1][col] + tabuleiro[2][col]) == 6)
                return 2;
        }
        return 0;                
    }
    
    public int verificarDiagonais(){
        if( (tabuleiro[0][0] + tabuleiro[1][1] + tabuleiro[2][2]) == -6)
            return -2;
        if( (tabuleiro[0][0] + tabuleiro[1][1] + tabuleiro[2][2]) == 6)
            return 2;
        if( (tabuleiro[0][2] + tabuleiro[1][1] + tabuleiro[2][0]) == -6)
            return -2;
        if( (tabuleiro[0][2] + tabuleiro[1][1] + tabuleiro[2][0]) == 6)
            return 2;
        return 0;
    }

    public boolean verificarTabuleiro(){
        for(int lin = 0 ; lin < 3 ; lin++)
            for(int col = 0 ; col < 3 ; col++)
                if( tabuleiro[lin][col] == 0 )
                    return false;
        return true;
    }
    
    
    public void jogadorJogar(int[][] tabuleiro2, int[] jogador){
        jogadorVez(tabuleiro2);
        setPosicao(jogador1Tentaviva, jogador);
    }
    
    public void jogadorVez(int[][] tabuleiro){
        do {
            do { 
                System.out.print("jogar na linha: ");
                jogador1Tentaviva[0] = teclado.nextInt();
                
                if( jogador1Tentaviva[0] > 3 || jogador1Tentaviva[0] < 1)
                    System.out.println("Está é uma linha inválida. Escolha alguma entre  1, 2 ou 3");
                
            } while( jogador1Tentaviva[0] > 3 ||jogador1Tentaviva[0] < 1);
            
            do{ 
                System.out.print("jogar na coluna: ");
                jogador1Tentaviva[1] = teclado.nextInt();
                
                if(jogador1Tentaviva[1] > 3 ||jogador1Tentaviva[1] < 1)
                    System.out.println("Está é uma coluna inválida. Escolha alguma entre  1, 2 ou 3");
                
            }while(jogador1Tentaviva[1] > 3 ||jogador1Tentaviva[1] < 1);
            
            jogador1Tentaviva[0]--; 
            jogador1Tentaviva[1]--;
            
            if(!verificarTentativa(jogador1Tentaviva))
                System.out.println("Tente novamente. Local ocupado."); 
            
        } while( !verificarTentativa(jogador1Tentaviva) ); 
    }
    
    
    public void jogadorComputador(int[][] tabuleiro2, int[] jogador){
        setPosicao(computadorVez(), jogador);
    }
    public int[] computadorVez(){
    	int[] computadorTentaviva = new int[2];
    	do{  
    		computadorTentaviva[0] = 1 + (int)(3*Math.random());  
    		
    		computadorTentaviva[1] = 1 + (int)(3*Math.random());  
                  		
    		computadorTentaviva[0]--;  
    		
    		computadorTentaviva[1]--;
    		
    		
        }while( !verificarTentativa(computadorTentaviva) );  
    	return computadorTentaviva;
    }
    

    public boolean verificarTentativa(int[] tentativa){
        if(pegarPosicao(tentativa) == 0) {
            return true;
        }else {
            return false;
        }
    }
    
    
    
    

    public void escolherJogadores(){
        System.out.println("Defina o jogador 1: ");
        int escolha1 = jogador(); 
        
        if(escolha1 == 1) {
            jogador1[0] = 1;
            jogador1[1] = 1;
        }else {
        	jogador1[0] = 3;
            jogador1[1] = 3;
        }
        
        System.out.println();
        System.out.println("Defina o jogador 2:");
        
        int escolha2 = jogador(); 
        if(escolha2 == 1) {
        	jogador2[0] = 2;
            jogador2[1] = 2;
        }else {
        	jogador2[0] = 5;
            jogador2[1] = 5;
        }
    }
    public int jogador(){
        int escolha = 0;
        
        do{
            System.out.println("1 -> Pessoa");
            System.out.println("2 -> Computador");
            System.out.println();
            System.out.print("Escolha 1 ou 2: ");
            escolha = teclado.nextInt();
            
            if(escolha != 1 && escolha != 2)
                System.out.println("Opção incorreta!");
        }while(escolha != 1 && escolha != 2);
        
        return escolha;
    }
    
    public boolean verificarVez(int vez) {
    	if(vez % 2 == 1) {
    		return true;
    	}
    	return false;
    }
    
    public boolean jogar(){
    	
        if(verificarVencedor() == 0 ){ 
        	
        	if(verificarVez(vez)) {
        		System.out.println();
                System.out.println("vez do jogador 1 \n");
        	}else {
        		System.out.println();
                System.out.println("vez do jogador 2 \n");
        	}
            
            if(vez%2 == 1) {
            	if(jogador1[0] == jogadorAux1[0]) { 
                	jogadorJogar(tabuleiro, jogador1);
            	}else if(jogador1[0] == computadorAux1[0]) {
            		jogadorComputador(tabuleiro, computadorAux1); 
            	}
            }else {
            	if(jogador2[0] == jogadorAux2[0]) { 
                	jogadorJogar(tabuleiro, jogador2); 
            	}else if (jogador2[0] == computadorAux2[0]) {
            		jogadorComputador(tabuleiro, computadorAux2); 
            	}
            }
            
            if(verificarTabuleiro()){
            	if(verificarVencedor() == -2 ) { 
                	System.out.println();
                    System.out.println("\\o/  Parabéns jogador 1! Você ganhou!");
                }else if(verificarVencedor() == 2){
                	System.out.println();
                    System.out.println("\\o/  Parabéns jogador 2! Você ganhou!");
                }else {
                	 System.out.println("Deu velha! EMPATE!");
                     return false; 
                }
               
            }
            
            vez++;

            return true; 
        } else{
            if(verificarVencedor() == -2 ) { 
            	System.out.println();
                System.out.println("\\o/  Parabéns jogador 1! Você ganhou!");
            }else {
            	System.out.println();
                System.out.println("\\o/  Parabéns jogador 2! Você ganhou!");
            }
            return false;
        }       
    }

    
    
    
    public int verificarVencedor(){
        if(verificarLinhas() == 2)
            return 2;
        if(verificarColunas() == 2)
            return 2;
        if(verificarDiagonais() == 2)
            return 2;
        
        if(verificarLinhas() == -2)
            return -2;
        if(verificarColunas() == -2)
            return -2;
        if(verificarDiagonais() == -2)
            return -2;
        return 0;
    }

}

