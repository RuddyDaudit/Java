package Ex;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Common {

}

class Slave implements Runnable{
	Socket client;

	public Slave(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		try {
			System.out.println("[START] nouveau client");
			ObjectInputStream input_client = new ObjectInputStream(client.getInputStream());
			ObjectOutputStream output_client = new ObjectOutputStream(client.getOutputStream());

			if(input_client.readUTF()=="create") {

				String nom = input_client.readUTF();
				String login = input_client.readUTF();
				String mdp = input_client.readUTF();
				
				Thread.sleep(2000);
				
				//ajout d'un utilisateur si son login est déjà pris
					
				Serveur.users.addUtilisateur(Serveur.users.getAdd(nom,login,mdp));
				
				output_client.writeUTF("Utilisateur cree");
				
			}else if(input_client.readUTF()=="name") {
				String login = input_client.readUTF();
				Serveur.users.sendName(login);
		}



		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

class Utilisateur {
	private String nom;
	private String login;
	private String mdp;
	static ArrayList <Utilisateur> C = new ArrayList<Utilisateur>();

	public Utilisateur(String nom,String login,String mdp) {
		this.nom = nom;
		this.login = login;
		this.mdp = mdp;
	}
	public Utilisateur() {

	}
	void setNom(String nom) {
		this.nom = nom;
	}
	void setLogin(String login) {
		this.login = login;
	}
	void setMdp(String mdp) {
		this.mdp = mdp;
	}
	String getNom() {
		return this.nom;
	}
	String getLogin(){
		return this.login;
	}
	String getMdp() {
		return this.mdp;
	}
	void addUtilisateur(Utilisateur c) {
		C.add(c);
	}
	void deleteUtilisateur(Utilisateur c) {
		C.remove(c);
	}
	public void sendName(String login){
		//return getLogin() where ID = equals(id);
		System.out.print("toto");
	}

	Utilisateur getAdd(String nom,String login,String mdp) {
		Utilisateur u = new Utilisateur(nom,login,mdp);
		return u;
	}
}

class Message{
	String utilisateur;
	String destinateur;


	public Message(String utilisateur,String destinateur){
		this.utilisateur = utilisateur;
		this.destinateur = destinateur;
	}
}