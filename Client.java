package Ex;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
       String name = "Ruddy";
    	try {
            Socket sclient = new Socket("127.0.0.1", 12345);
			ObjectOutputStream output_client = new ObjectOutputStream(sclient.getOutputStream());
			ObjectInputStream input_client = new ObjectInputStream(sclient.getInputStream());
			System.out.println("Connexion [OK] ");
			//Authentification
			output_client.writeUTF(name);	
			sclient.close();
        } catch (Exception e) {
            System.out.println(e);
			e.printStackTrace();
        }
    }
}
