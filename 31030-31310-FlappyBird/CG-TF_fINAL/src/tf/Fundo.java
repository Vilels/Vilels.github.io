package tf;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;


public class Fundo {

	private int spriteAtivaX;
	private int spriteAtivaY;
	private BufferedImage img;
	public AffineTransform posicao;
	private int translateX = 500;
	private int translateY = 500;
	private int posicaoBg;
	private Timer t;

	public Fundo() {
		spriteAtivaX = 1;
		spriteAtivaY = 1;

		posicao = new AffineTransform();
		posicao.translate(translateX, translateY);
		try {
			img = ImageIO.read(new File("imagens/montanha.jpg"));
		} catch (IOException e) {
			System.err.println("imagens/montanha.jpg");
		}

		t = new Timer();
		t.scheduleAtFixedRate(new Anima(), 0, 7);
	}

	public void desenha(Graphics2D g2d) {
		AffineTransform afi = g2d.getTransform();
		g2d.setTransform(posicao);
		g2d.drawImage(img, -515, -500, 800, 525, spriteAtivaX * 0 - 0 + posicaoBg, spriteAtivaY * 0 - 0,
				spriteAtivaX * 1024 + posicaoBg, spriteAtivaY * 1024, null);
		g2d.setTransform(afi);
	}

	class Anima extends TimerTask {

		@Override
		public void run() {
			posicaoBg++;
			if (posicaoBg >= 4264) {
				posicaoBg = 0;
			}
		}

	}
}