//Jerome Kim
//November 3, 2020
//Final Project Game
package project_final;

import hsa_ufa.Console;
import java.awt.*;
import java.applet.*;
import java.util.Random;

public class Project_final {

	static Console c = new Console(1200, 800, "Final Project - Jerome Kim"); // set Console size and name

	// Main method
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// set image names
		Image gameback;
		Image poke;
		Image pokeball;
		Image pokepic;
		Image item;
		Image title;
		Image background;

		// declare image files
		background = Toolkit.getDefaultToolkit()
				.getImage(c.getClass().getClassLoader().getResource("images/background.jpg"));

		pokeball = Toolkit.getDefaultToolkit()
				.getImage(c.getClass().getClassLoader().getResource("images/pokeball.png"));

		pokepic = Toolkit.getDefaultToolkit().getImage(c.getClass().getClassLoader().getResource("images/pokepic.png"));

		item = Toolkit.getDefaultToolkit().getImage(c.getClass().getClassLoader().getResource("images/item.png"));

		title = Toolkit.getDefaultToolkit().getImage(c.getClass().getClassLoader().getResource("images/title.png"));

		// declare music files
		AudioClip opening = Applet.newAudioClip(Project_final.class.getResource("opening.wav"));
		AudioClip gamesong = Applet.newAudioClip(Project_final.class.getResource("gamesong.wav"));
		AudioClip closing = Applet.newAudioClip(Project_final.class.getResource("closing.wav"));

		Transparency tp1 = new Transparency(); // create objects
		Transparency tp2 = new Transparency();
		Transparency tp3 = new Transparency();

		c.enableMouse(); // enable mouse input
		c.enableMouseMotion();

		// loop whole game
		while (true) {
			c.setFont(new Font("MS Gothic", Font.BOLD, 25)); // font
			int playerx = c.getDrawWidth() / 2 - 60; // set variables and reset when game restarted
			int playery = c.getDrawHeight() / 2 - 50;
			int pokeballx[] = { 25, 1145, 25, 1145 };
			int pokebally[] = { 25, 25, 745, 745 };
			int num1 = 4;
			int num2 = 4;
			int playerspeed = 6;
			int count = 0;
			int score = 0;
			int numb = 3;
			int pokemon;
			int pokenumb = 0;
			int itemnumb = 0;
			int mouseClick = 0;
			int tp = 0;
			int difficulty = 0;
			int evolcount1 = 10;
			int evolcount2 = 20;
			tp1.transp = 0;
			tp2.transp = 0;
			tp3.transp = 0;
			boolean direction1 = false;
			boolean direction2 = false;
			boolean hit = false;
			boolean item_on = false;
			boolean wallhit1 = false;
			boolean wallhit2 = false;

			Random rng = new Random(); // random location for item
			int itemx = rng.nextInt(1140) + 30;
			int itemy = rng.nextInt(670) + 100;
			int backnumb = rng.nextInt(4) + 1; // random background

			closing.stop();
			opening.loop(); // play opening music

			// starting screen
			while (true) {
				synchronized (c) {
					c.clear();
					c.setColor(Color.BLACK);
					c.drawImage(background, 0, 0, 1200, 800); // display images
					c.drawImage(title, 200, 50);
					c.drawString("Click here to start", 425, 300); // display text
					c.drawString("Use Arrow Keys to Move", 400, 620);
					c.drawString("Avoid the Poke Balls!", 405, 660);
					c.drawString("Collect Items to Evolve", 390, 700);
					c.drawString("WATCH OUT FOR NEW POKE BALLS ADDED", 320, 760);
					c.setColor(new Color(0, 0, 0, tp));
					c.drawRect(400, 250, 310, 70);
				}
				Thread.sleep(1);
				mouseClick = c.getMouseClick();
				// if mouse cursor on text show box
				if ((c.getMouseX() > 400) && (c.getMouseX() < 710) && (c.getMouseY() > 250) && (c.getMouseY() < 320))
					tp = 255; // changing transparency
				else // if not remove box
					tp = 0;
				// when clicked move to next screen
				if ((c.getMouseX() > 400) && (c.getMouseX() < 710) && (c.getMouseY() > 250) && (c.getMouseY() < 320)
						&& (mouseClick != 0))
					break;
			}

			mouseClick = 0; // reset mouse click value

			// difficulty choosing screen
			while (true) {
				synchronized (c) {
					c.clear();
					c.setColor(Color.BLACK);
					c.drawImage(background, 0, 0, 1200, 800); // image
					c.drawString("Difficulty", 500, 100); // text
					c.drawString("Easy", 540, 200);
					c.drawString("Medium", 525, 275);
					c.drawString("Hard", 540, 350);
					c.setColor(new Color(0, 0, 0, tp1.transp));
					c.drawRect(515, 170, 100, 50);
					c.setColor(new Color(0, 0, 0, tp2.transp));
					c.drawRect(500, 245, 130, 50);
					c.setColor(new Color(0, 0, 0, tp3.transp));
					c.drawRect(515, 320, 100, 50);

				}
				Thread.sleep(1);
				mouseClick = c.getMouseClick();
				// if mouse cursor on text show box
				if ((c.getMouseX() > 515) && (c.getMouseX() < 615) && (c.getMouseY() > 170) && (c.getMouseY() < 220))
					tp1.transp = 255;
				else if ((c.getMouseX() > 500) && (c.getMouseX() < 630) && (c.getMouseY() > 245)
						&& (c.getMouseY() < 295))
					tp2.transp = 255;
				else if ((c.getMouseX() > 515) && (c.getMouseX() < 615) && (c.getMouseY() > 320)
						&& (c.getMouseY() < 370))
					tp3.transp = 255;
				else {
					tp1.transp = 0; // if not remove box
					tp2.transp = 0;
					tp3.transp = 0;
				}
				// when clicked set difficulty and move to next screen
				if ((c.getMouseX() > 515) && (c.getMouseX() < 615) && (c.getMouseY() > 170) && (c.getMouseY() < 220)
						&& (mouseClick != 0)) {
					difficulty = 0;
					break;
				} else if ((c.getMouseX() > 500) && (c.getMouseX() < 630) && (c.getMouseY() > 245)
						&& (c.getMouseY() < 295) && (mouseClick != 0)) {
					difficulty = 10;
					break;
				} else if ((c.getMouseX() > 515) && (c.getMouseX() < 615) && (c.getMouseY() > 320)
						&& (c.getMouseY() < 370) && (mouseClick != 0)) {
					difficulty = 30;
					break;
				}
			}

			// Pokemon choosing screen
			while (true) {
				c.clear();
				c.setBackgroundColor(Color.BLACK); // set background
				c.clear();
				c.setColor(Color.WHITE);
				c.println("What Pokemon do you want?"); // text
				c.println("Please type the number.");
				c.println("1. Bulbasaur");
				c.println("2. Charmander");
				c.println("3. Squirtle");
				c.drawImage(pokepic, 250, 200); // image
				c.fillOval(580, 280, 8, 20);
				pokemon = c.readInt(); // get which Pokemon

				switch (pokemon) { // choices for Pokemon

				case 1:
					pokenumb = 1; // set image number depending on the choice
					c.println("You chose Bulbasaur!"); // display chosen Pokemon
					break;
				case 2:
					pokenumb = 2;
					c.println("You chose Charmander!");
					break;
				case 3:
					pokenumb = 3;
					c.println("You chose Squirtle!");
					break;
				default:
					c.println(pokemon + " is not an option."); // display if not an option
					Thread.sleep(1000);
					continue;
				}

				c.println("Press C to continue"); // move to next screen if c pressed
				if (c.getChar() == 'c')
					break;
			}

			c.setFont(new Font("Monospaced", Font.BOLD, 15));
			opening.stop();
			gamesong.loop(); // change song

			// In game
			while (true) {
				gameback = Toolkit.getDefaultToolkit() // random background chosen
						.getImage(c.getClass().getClassLoader().getResource("images/gameback" + backnumb + ".png"));
				poke = Toolkit.getDefaultToolkit() // Pokemon image chosen
						.getImage(c.getClass().getClassLoader().getResource("images/poke" + pokenumb + ".png"));
				synchronized (c) {
					c.clear();
					c.drawImage(gameback, 0, 0, 1400, 800); // background
					for (int i = 0; i < pokeballx.length - numb || i < pokebally.length - numb; i++) { // draw Poke
																										// Balls
						c.drawImage(pokeball, pokeballx[i], pokebally[i], 30, 30);
					}
					if (backnumb == 1)
						c.setColor(Color.BLACK);
					else if ((backnumb == 2) || (backnumb == 3) || (backnumb == 4))
						c.setColor(Color.WHITE);
					c.drawString("Score: " + score, 5, 20); // display score and items left to evolve
					c.drawImage(poke, playerx, playery, 60, 50);
					c.drawImage(item, itemx, itemy, 30, 30);
					if (evolcount1 > 0)
						c.drawString("Items needed for 1st evolution: " + evolcount1, 5, 40);
					if (evolcount2 > 0)
						c.drawString("Items needed for 2nd evolution: " + evolcount2, 5, 60);

				}
				Thread.sleep(10);
				pokeballx[0] += num1; // move Poke Balls
				pokeballx[1] -= num1;
				pokeballx[2] += num1;
				pokeballx[3] -= num1;
				pokebally[0] += num2;
				pokebally[1] += num2;
				pokebally[2] -= num2;
				pokebally[3] -= num2;

				if (c.isKeyDown(Console.VK_LEFT) && playerx > 5) { // control Pokemon location
					playerx -= playerspeed;
					direction1 = true;
				} else if (c.isKeyDown(Console.VK_RIGHT) && playerx < 1140) {
					playerx += playerspeed;
					direction2 = true;
				}
				if (c.isKeyDown(Console.VK_UP) && playery > 25)
					playery -= playerspeed;
				else if (c.isKeyDown(Console.VK_DOWN) && playery < 750)
					playery += playerspeed;
				// Poke Balls collision detection with side walls
				if ((pokeballx[0] <= 0) || (pokeballx[0] >= c.getDrawWidth() - 30))
					wallhit1 = true;
				// Poke Balls collision detection with top and bottom walls
				else if ((pokebally[0] <= 0) || (pokebally[0] >= c.getDrawHeight() - 30))
					wallhit2 = true;
				// Pokemon collision detection with Poke Balls
				for (int i = 0; i < pokeballx.length - numb || i < pokebally.length - numb; i++) {
					if ((playerx < (pokeballx[i] + 20)) && (pokeballx[i] < (playerx + 50))
							&& (playery < (pokebally[i] + 20)) && (pokebally[i] < (playery + 40))) {
						hit = true; //
					}
				}
				// Pokemon collision detection with item
				if ((playerx < (itemx + 30)) && (itemx < (playerx + 60)) && (playery < (itemy + 30))
						&& (itemy < (playery + 50)))
					item_on = true;

				if (wallhit1 == true) { // if hit side walls
					count++; // bounce number increase
					num1 = make_it_negative(num1); // balls bounce
					wallhit1 = false;
					if (count == 3) // balls added until 4
						numb -= 1;
					else if (count == 6)
						numb -= 1;
					else if (count == 9)
						numb -= 1;
					// increase ball speed depending on the difficulty chosen
					if ((count % 10 == 0) && (count <= difficulty)) {
						if (num1 > 0)
							num1 += 2;
						else if (num1 < 0)
							num1 -= 2;
						if (num2 > 0)
							num2 += 2;
						else if (num2 < 0)
							num2 -= 2;
					}
				}

				if (wallhit2 == true) { // if hit top and bottom walls
					count++; // bounces number increase
					num2 = make_it_negative(num2); // balls bounce
					wallhit2 = false;
					if (count == 3) // balls added until 4
						numb -= 1;
					else if (count == 6)
						numb -= 1;
					else if (count == 9)
						numb -= 1;
					// increase ball speed depending on the difficulty chosen
					if ((count % 10 == 0) && (count <= difficulty)) {
						if (num1 > 0)
							num1 += 2;
						else if (num1 < 0)
							num1 -= 2;
						if (num2 > 0)
							num2 += 2;
						else if (num2 < 0)
							num2 -= 2;
					}
				}

				if ((direction1 == true) && (pokenumb >= 10)) { // flip image to left side
					pokenumb -= 9;
					direction1 = false;
				}

				if ((direction2 == true) && (pokenumb <= 9)) { // flip image to right side
					pokenumb += 9;
					direction2 = false;
				}

				if (item_on == true) { // if collected item
					itemnumb++; // number of items collected increase
					evolcount1--; // items needed until evolution decreases
					evolcount2--;
					score += 500; // score goes up
					item_on = false;
					itemx = rng.nextInt(1140) + 30; // random location for next item
					itemy = rng.nextInt(670) + 100;
					if (itemnumb == 10) { // first evolution
						pokenumb += 3; // image change
						playerspeed = 8; // speed increase
					} else if (itemnumb == 20) { // second evolution
						pokenumb += 3; // image change
						playerspeed = 10; // speed increase
					}
				}

				if (hit == true) { // if hit by Poke Balls game end
					break;
				}
			}

			gamesong.stop();

			c.setFont(new Font("MS Gothic", Font.BOLD, 20));
			c.setBackgroundColor(Color.WHITE);
			c.clear();
			c.drawImage(background, 0, 0, 1200, 800); // background image
			c.setColor(Color.BLACK);
			c.drawString("You were captured by a Poke Ball..", 400, 100); // display game ended
			// display text depending on the evolution
			switch (pokenumb) {
			case 1:
			case 2:
			case 3:
			case 10:
			case 11:
			case 12:
				c.drawString("You did not evolve..", 470, 150);
				break;
			case 4:
			case 13:
				c.drawString("You evolved to Ivysaur!", 450, 150);
				break;
			case 5:
			case 14:
				c.drawString("You evolved to Charmeleon!", 450, 150);
				break;
			case 6:
			case 15:
				c.drawString("You evolved to Wortortle!", 450, 150);
				break;
			case 7:
			case 16:
				c.drawString("You evolved to Venesaur!", 450, 150);
				break;
			case 8:
			case 17:
				c.drawString("You evolved to Charizard!", 450, 150);
				break;
			case 9:
			case 18:
				c.drawString("You evolved to Blastoise!", 450, 150);
				break;
			}
			// display item number and score
			c.drawString("You collected " + itemnumb + " item(s) and your score was " + score + ".", 325, 200);
			c.drawString("Press R to replay", 480, 250); // display text to replay or close
			c.drawString("Press X to close", 485, 300);
			c.drawImage(poke, 300, 400);
			c.drawImage(pokeball, 650, 400, 200, 200); // display image depending on the Pokemon
			Thread.sleep(1000);
			closing.loop(); // song changed
			while (true) {
				if (c.getChar() == 'r') // r to replay
					break;
				else if (c.getChar() == 'x') { // x to close
					closing.stop();
					c.close();
				}
			}
		}

	}

	// Method to return number as negative integer
	static int make_it_negative(int x) {
		return -x;
	}
}
