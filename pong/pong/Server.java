package pong;

import java.util.ArrayList;
import java.util.Vector;
import java.net.*;
import java.io.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Team: Night Owl <br>
 * @author Hassan Ndow, Aleksey Zurkowski, Kevin Whetstone <br>
 *         Date: April 20, 2014 <br>
 *         A multi threaded server that receives login info from client,<br>
 *         messages, and sends them back to all clients<br>
 *         To run the server type below (default port set to 1500) <br>
 *         > java Server
 * 
 */
public class Server extends JFrame implements ActionListener {

	// instance variables
	private Vector<ServerThread> clients;
	private int port;
	String username;
	private static int id;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private JTextArea jtaAreaEast, jtaAreaWest;
	private JLabel jlCount, jlWins;
	private JTextField jtCount, jtWins;
	private JPanel jpServerEast2;
	private JMenuBar jmb; // the menu bar
	private JMenu jmFile, jmHelp; // the menus
	private JMenuItem jmiExit, jmiStart, jmiList, jmiAbout, jmiRule,
			jmiRestart; // the menu items

	/**
	 * Constructor that creates the server gui and starts the game and  server thread
	 * 
	 * @param port
	 */
	public Server(int port) {
		this.port = port;
		clients = new Vector<ServerThread>();

		// JMenuBar objects
		jmb = new JMenuBar();
		jmFile = new JMenu("File");
		jmHelp = new JMenu("Help");
		jmiStart = new JMenuItem("Start game");
		jmiRestart = new JMenuItem("Restart");
		jmiExit = new JMenuItem("Exit");
		jmiAbout = new JMenuItem("About");
		jmiRule = new JMenuItem("Rules");
		jmiList = new JMenuItem("Logged on list");

		// adding JMenuBar objects to the JFrame
		jmFile.add(jmiStart);
		jmFile.add(jmiRestart);
		jmFile.add(jmiList);
		jmFile.add(jmiExit);
		jmHelp.add(jmiAbout);
		jmHelp.add(jmiRule);
		jmb.add(jmFile);
		jmb.add(jmHelp);
		setJMenuBar(jmb);

		// Mnemonic objects
		jmFile.setMnemonic(KeyEvent.VK_F);
		jmHelp.setMnemonic(KeyEvent.VK_H);
		jmiExit.setMnemonic(KeyEvent.VK_X);
		jmiAbout.setMnemonic(KeyEvent.VK_A);
		jmiRule.setMnemonic(KeyEvent.VK_R);
		jmiStart.setMnemonic(KeyEvent.VK_S);
		jmiRestart.setMnemonic(KeyEvent.VK_R);
		jmiList.setMnemonic(KeyEvent.VK_L);

		// Adding ActionListener
		jmiStart.addActionListener(this);
		jmiRestart.addActionListener(this);
		jmiExit.addActionListener(this);
		jmiAbout.addActionListener(this);
		jmiRule.addActionListener(this);
		jmiList.addActionListener(this);

		// WEST BORDER SIDE FOR PONG GAME
		JPanel jpServerWest = new JPanel(new GridLayout(0, 1));
		JPanel jpServerEast = new JPanel(new BorderLayout());
		// JPanel jpServerEast2 = new JPanel(new BorderLayout());
		JPanel jpServerSouth = new JPanel(new GridLayout(2, 0));

		// starts the pong game
		jpServerWest.add(Pong.getInstance());

		add(jpServerWest, BorderLayout.CENTER);

		// EAST BORDER SIDE FOR CLIENT CHAT
		jtaAreaEast = new JTextArea("Chat here:\n", 30, 20);

		jtaAreaEast.setEditable(false);

		jpServerEast.add(new JScrollPane(jtaAreaEast));

		add(jpServerEast, BorderLayout.EAST);

		// SOUTH BORDER SIDE FOR LABELS FOR COUNTS OF WINS AND ON
		jlCount = new JLabel("Counts of game: ");
		jtCount = new JTextField(10);
		jlCount.setHorizontalAlignment(JLabel.RIGHT);
		jtCount.setHorizontalAlignment(JTextField.CENTER);

		jlWins = new JLabel("Number of Wins");
		jtWins = new JTextField(5);
		jlWins.setHorizontalAlignment(JLabel.RIGHT);
		jtWins.setHorizontalAlignment(JTextField.CENTER);

		jpServerSouth.add(jlCount);
		jpServerSouth.add(jtCount);
		jpServerSouth.add(jlWins);
		jpServerSouth.add(jtWins);
		add(jpServerSouth, "South");

		setSize(800, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// starts the thread
		try {
			ServerSocket ss = new ServerSocket(port);
			jtaAreaEast.append("Welcom to the Pong game.\n This port is: "
					+ port);
			jtaAreaEast.append("\n");
			while (true) {

				Socket cs = ss.accept();
				ServerThread st = new ServerThread(cs);
				clients.add(st);
				st.start();
				jtaAreaEast.append(st.username + " connected to port: " + port
						+ "\n");

			}
		} catch (IOException ioe) {
			System.out.println("Error with input/output: " + ioe.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent ae) {
		Object choice = ae.getSource();

		if (choice.equals(jmiExit)) {
			System.exit(0);
		}
		if (choice.equals(jmiRestart)) {
		}
		if (choice.equals(jmiStart)) {
			pickingPlayers();
		}
		if (choice.equals(jmiAbout)) {
			JOptionPane
					.showMessageDialog(
							null,
							"121 Final Project: Pong"
									+ "\n"
									+ "\nDeveloped by:\n\t Hassan Ndow, Kevin Whetstone,\n\t Aleksey Zurkowski, and Abdullah Alam",
							"Pong", JOptionPane.INFORMATION_MESSAGE);
		}
		if (choice.equals(jmiRule)) {
			JOptionPane
					.showMessageDialog(
							null,
							"To start this game press the 'G' key.\nEach player has control "
									+ "of two paddles. \nPlayer 1 has control of the left paddle [moves vertically with the 'W' and 'S' keys] and the "
									+ "top paddle [moves horizontally with the 'A' and 'D' paddle].\nPlayer 2 has control of the right paddle [moves veritically"
									+ " with the 'UP' and 'DOWN' keys] and the bottom paddle [moves horizontally with the 'LEFT' and 'RIGHT' keys."
									+ "]\n\nTo chat type in a message in the text area and click send.\nTo private message someone type a message and click private.\n" +
									"Type in the person's name and then click ok.\nTo view a list of people logged in, on the server click 'File' and then 'logged on list.'", "Pong",
							JOptionPane.INFORMATION_MESSAGE);
		}

		// displays users who log in
		if (choice.equals(jmiList)) {
			String userList = "";
			for (int i = clients.size(); --i >= 0;) {
				ServerThread c = clients.get(i);
				if (c.socket.isConnected() == false) {
					try {
						c.oos.close();
						c.ois.close();
						c.socket.close();
					} catch (IOException ioe) {

					}
					clients.remove(i);
				}

				else {

					userList += c.username + " " + "\n";
				}
			}

			JOptionPane.showMessageDialog(null, userList);

		}

	}

	/**
	 * Selects number of players
	 */
	public void pickingPlayers() {
		int numPlayers = 0;

		String[] palyers = new String[] { "Player 1", "Player 2", "Player 3",
				"Player 4" };

		String[] choices = { "Player 1", "Player 2", "Player 3", "Player 4" };

		// Input dialog box for asking for how many players to join before games
		// actually starts.
		String playerPick = (String) JOptionPane.showInputDialog(null,
				"How many player do you wish to play with?",
				"Welcome to PONG game", JOptionPane.QUESTION_MESSAGE, null,
				choices, choices[0]);

		// When player doesnt pick, it will display error.
		// example would be, cancel and red x button
		if (playerPick == null) {
			JOptionPane.showMessageDialog(null, "You didn't picked a player!");
			System.exit(0);
		} else // executes when a player is picking number of players.
		{
			// Attributes
			String funfact = null;
			numPlayers = 0;

			switch (playerPick) {
			case "Player 1":
				System.out.println("Player 1 only to play Pong Game");
				numPlayers = 1;
				System.out.println(numPlayers);
				break;
			case "Player 2":
				System.out.println("Player 2 only to play Pong Game");
				numPlayers = 2;
				System.out.println(numPlayers);
				break;
			case "Player 3":
				System.out.println("Player 3 only to play Pong Game");
				numPlayers = 3;
				System.out.println(numPlayers);
				break;
			case "Player 4":
				String name = "Player 4 only to play Pong Game\n";
				numPlayers = 4;
				System.out.println(numPlayers);
				jtaAreaEast.append(name);
				break;
			}
		}

		// Testing to see if it is working, after choosing player a server
		// allows clients to join the game and starts the game.
		System.out.println("Last message " + numPlayers);

	}

	/**
	 * Team: Night Owl <br>
	 * @author Hassan Ndow <br>
	 *         Date: April 20, 2014 <br>
	 *         A multi threaded server that that receives login info from client,<br>
	 *         messages, and sends them back to all clients
	 * 
	 */
	class ServerThread extends Thread {

		// instance variables
		Socket socket;
		ObjectOutputStream oos;
		ObjectInputStream ois;
		String message;
		int identification;
		String username;
		ArrayList test;

		
		/**
		 * Constructor that creates i/o and get the username
		 * @param socket
		 */
		ServerThread(Socket socket) {
			this.socket = socket;
			identification = id++;

			try {
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());

			} catch (IOException ioe) {
				System.out.println("IO Error: " + ioe.getMessage());
			}

			try {
				username = (String) ois.readObject();

			} catch (IOException ioe) {
				System.out.println("Client disconnected");

			} catch (ClassNotFoundException cnfe) {
				System.out.println("Class could not be found: "
						+ cnfe.getMessage());

			}

		}

		
		/* (non-Javadoc)
		 * Sends an broadcasts the messages to all connected clients
		 * @see java.lang.Thread#run()
		 */
		public void run() {
			while (true) {

				try {
					Object next = ois.readObject();
					
					//if client presses send, broadcast to all clients
					if (next instanceof String) {

						message = (String) next;
						jtaAreaEast.append("\n" + message);

						System.out.println("Client sent: " + message);

						for (int i = clients.size(); --i >= 0;) {
							ServerThread c = clients.get(i);
							if (c.socket.isConnected() == false) {
								try {
									c.oos.close();
									c.ois.close();
									c.socket.close();
								} catch (IOException ioe) {

								}
								clients.remove(i);
							}

							else {
								try {
									c.oos.writeObject(message);

								} catch (IOException ioe) {
									System.out.println("error");

								}

							}
						}

					}
					
					//if client presses private, send to appropiate client
					else if (next instanceof ArrayList<?>) {
						test = (ArrayList<?>) next;

						for (int i = clients.size(); --i >= 0;) {
							ServerThread c = clients.get(i);
							if (c.socket.isConnected() == false) {
								try {
									c.oos.close();
									c.ois.close();
									c.socket.close();
								} catch (IOException ioe) {

								}
								clients.remove(i);
							}

							else {
								try {
									if (c.username
											.equalsIgnoreCase((String) test
													.get(0)))
										c.oos.writeObject((String) test.get(1));

								} catch (IOException ioe) {
									System.out.println("error");

								}

							}
						}
					}
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					System.out.println(this.username + " has disconnected.");
					break;
				}
			}

			// remove and close the client
			for (int i = 0; i < clients.size(); ++i) {
				ServerThread client = clients.get(i);
				if (client.identification == identification) {
					clients.remove(i);
					return;
				}
			}

			try {
				oos.close();
				ois.close();
				socket.close();
			} catch (IOException ioe) {

			}

		}
	}

	
	/**
	 * Main method runs the server
	 * @param args
	 */
	public static void main(String[] args) {
		Server s = new Server(1500);
	}
}
