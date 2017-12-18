package tf;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;


public class Bird {
	int spriteAtiva;
	BufferedImage img;
	AffineTransform posicao;
	Timer t;
	
	Bird(){
		spriteAtiva = 1;
		posicao = new AffineTransform();
		posicao.translate(150, 250);
		try{
			img = ImageIO.read(new File("imagens/bird.png"));
		}
		catch(IOException e) {
			System.out.println("Erro ao ler o ficheiro: imagens/bird.png");
		}
		t = new Timer();
		t.scheduleAtFixedRate(new Anima(), 0, 50);
	}
	
	public void desenha (Graphics2D g2d){
		AffineTransform afi = g2d.getTransform();
		g2d.setTransform(posicao);
		g2d.drawImage(img, -25, -25, 25, 25, spriteAtiva * 71,0, spriteAtiva * 71 +71, 71, null);
		g2d.setTransform(afi);
		
	}
	
	class Anima extends TimerTask {
		public void run(){
			spriteAtiva++;
			if (spriteAtiva==3) spriteAtiva = 0;
			posicao.translate(0,10);
		}
		
	}

	public void salta() {
		
		posicao.translate(0, -60);
		
	}
	
	public void perdeu(){
		posicao.translate(0, 0);
	}
	
	public Rectangle2D getBoundingBox(){
		Rectangle2D.Double bb = new Rectangle2D.Double(
				posicao.getTranslateX()-25,
				posicao.getTranslateY()-25,
				47,38);
		return bb; 
	}

	public AffineTransform getPosicao() {
		return posicao;
	}

}
