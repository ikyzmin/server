import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Илья on 27.03.2017.
 */
public class Main {

    public static void main(String[] args) {
        ServerSocket listener = null;
        Socket socket = null;
        try {
            listener = new ServerSocket(5000);
            while (true) {
                socket = listener.accept();
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                float a = dataInputStream.readFloat();
                float b = dataInputStream.readFloat();
                System.out.print("a = "+a+"\nb = "+b);
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeFloat(a * b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (listener != null) {
                try {
                    listener.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
