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

public class Tube {
	BufferedImage img;
	BufferedImage img2;
	AffineTransform posicao;
	AffineTransform posicao2;
	Timer t;
	int posy;

	Tube() {
		posy = 250 + (int) (Math.random() * 150);

		posicao = new AffineTransform();
		posicao.translate(800, posy);

		posicao2 = new AffineTransform();
		posicao2.translate(800, posy - 530);

		try {
			img = ImageIO.read(new File("imagens/tube.png"));
		} catch (IOException e) {
			System.out.println("Erro ao ler o ficheiro: imagens/tube.png");
		}

		try {
			img2 = ImageIO.read(new File("imagens/tube1.png"));
		} catch (IOException e) {
			System.out.println("Erro ao ler o ficheiro: imagens/tube1.png");
		}

		t = new Timer();
		t.scheduleAtFixedRate(new MoveTube(), 0, 40);

	}

	public void desenha(Graphics2D g2d) {
		AffineTransform afi = g2d.getTransform();
		g2d.setTransform(posicao);
		g2d.drawImage(img, -50, -100, 35, 300, 0, 0, 178, 700, null);

		g2d.setTransform(posicao2);
		g2d.drawImage(img2, -50, -100, 35, 300, 0, 0, 178, 800, null);
		g2d.setTransform(afi);

	}

	public Rectangle2D getBoundingBoxBaixo() {
		Rectangle2D.Double bb = new Rectangle2D.Double(posicao.getTranslateX()-50, posicao.getTranslateY() - 100, 80, 400);
		return bb;
	}

	public Rectangle2D getBoundingBoxCima() {
		Rectangle2D.Double bb = new Rectangle2D.Double(posicao.getTranslateX()-50, posicao2.getTranslateY()-100, 80, 400);
		return bb;
	}

	class MoveTube extends TimerTask {

		@Override
		public void run() {
			posicao.translate(-7, 0);
			posicao2.translate(-7, 0);
			
		}

	}
	
	public void perdeu(){
		posicao.translate(0, 0);
		posicao2.translate(0, 0);
	}
	
	

}
