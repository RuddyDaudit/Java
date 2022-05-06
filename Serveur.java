package Ex;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Serveur {

    private static int port;// =33333
    private static int poolSize;// =10
    public boolean isFinished = false;
    ExecutorService pool;
    public ServerSocket serveur;
    static Utilisateur users;

    //Constructeur pour le serveur
    public Serveur(int port, int poolSize){
        try {
            this.serveur = new ServerSocket(port);
            this.pool = Executors.newFixedThreadPool(poolSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Getters et Setters
    public static int getPort() {
        return port;
    }
    public static void setPort(int port) {
        Serveur.port = port;
    }
    public static int getPoolSize() {
        return poolSize;
    }
    public static void setPoolSize(int poolSize) {
        Serveur.poolSize = poolSize;
    }
    public Boolean getIsFinished() {
        return isFinished;
    }
    public void setIsFinished (boolean isFinished) {
        this.isFinished = isFinished;
    }
    public ExecutorService getPool() {
        return pool;
    }
    public void setPool(ExecutorService pool) {
        this.pool = pool;
    }
    
 

    
    public void manageRequest(){
        System.out.println("En attente de connexion...");
        try {
            while (isFinished == false) {
                this.pool.execute(new Slave(serveur.accept()));
                System.out.println("Connexion [OK]");
            }
            System.out.println("Fermeture du serveur");
            pool.shutdown();
            serveur.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Serveur s;
        try {
            s = new Serveur(12345, 10);
            s.manageRequest();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}