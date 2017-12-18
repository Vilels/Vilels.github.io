package tf;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class Tela extends JPanel implements KeyListener {

	Bird b;
	Timer t;
	int score = 0;
	ArrayList<Tube> tubes;
	Fundo f;
	Sound sound;
	Rectangle2D bb_bird;
	Rectangle2D bb_tubeCima;
	Rectangle2D bb_tubeBaixo;
	boolean perdeu=false;
	
	

	Tela() {
		b = new Bird();
		t = new Timer();
		sound = new Sound(); 
		sound.soundPlay("music/music.wav");
		t.scheduleAtFixedRate(new Anima(), 0, 75);
		addKeyListener(this);
		setFocusable(true);
		f = new Fundo();
		bb_bird = b.getBoundingBox();
		tubes = new ArrayList<Tube>();
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		
		f.desenha(g2d);
		
		b.desenha(g2d);
		//g2d.draw(b.getBoundingBox());
		for (Tube t : tubes) {
			bb_tubeBaixo = t.getBoundingBoxBaixo();
			bb_tubeCima = t.getBoundingBoxCima();
			t.desenha(g2d);
			//g2d.draw(bb_tubeBaixo);
			//g2d.draw(bb_tubeCima);
			if(t.posicao.getTranslateX()<-100){
				tubes.remove(t);
			}
			if(t.posicao2.getTranslateX()<-100){
				tubes.remove(t);
			}
		}
		g2d.drawString("score:" + score, 50, 50);
		System.out.println(tubes.size());

		Rectangle2D bb_bird = b.getBoundingBox();
		for (Tube t : tubes) {
			if (bb_bird.intersects(t.getBoundingBoxCima())) {
				System.out.println("Cima");
				perdeu = true;
			}
			
			if (bb_bird.intersects(t.getBoundingBoxBaixo())) {
				System.out.println("Baixo");
				perdeu = true;
			
			}
		}
		
		if(b.getPosicao().getTranslateY()<-25||b.getPosicao().getTranslateY()>525){
			perdeu=true;
		}
		
		if(perdeu==false){
			repaint();
		}
		
		if (perdeu ==true){
			g2d.drawString("Game Over___score:" + score, 300, 250);
			g2d.drawString("Press R to RESTART", 304, 270);
		}
	}

	class Anima extends TimerTask {
		int nIteracoes = 0;

		public void run() {

			nIteracoes++;

			if (nIteracoes >= 30) {
				Tube t = new Tube();
				tubes.add(t);
				nIteracoes = 0;
				score++;
			}
			if(perdeu==false){
				repaint();
			}
			
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			b.salta();
			break;
			
		case KeyEvent.VK_R:
			new Janela();
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
