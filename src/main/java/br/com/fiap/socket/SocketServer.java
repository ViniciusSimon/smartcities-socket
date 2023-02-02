package br.com.fiap.socket;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.entity.Cliente;

public class SocketServer {

	public static void main(String[] args) throws IOException {
		ServerSocket servidor = new ServerSocket(8080);
		System.out.println("Porta 8080 aberta!");
		// Nessa etapa o programa fica aberto até uma nova conexão
		
		Socket socket = servidor.accept();
		System.out.println("Nova conexão com o cliente " + socket.getInetAddress().getHostAddress());
		
		PrintWriter saida = new PrintWriter(new PrintStream(socket.getOutputStream()));
		
		saida.println("\n\n\nTecle <Enter> para iniciar");
		saida.flush();
		
		Scanner leitor = new Scanner(socket.getInputStream());
		while(leitor.hasNextLine()) {
			saida.println();
			saida.print("\n\tPesquisa por código de Cliente (0 para sair): ");
			saida.flush();
			System.out.println(leitor.nextLine());
			int codigoPesquisa = leitor.nextInt();
			
			if(codigoPesquisa == 0) {
				saida.close();
				socket.close();
			}
			saida.print("\n\tRealizando pesquisa, aguarde... ");
			saida.flush();
			Cliente cliente = createDAO().buscar(codigoPesquisa);
			
			if(cliente != null) {
				saida.print("\n\tRegistro encontrado: " + cliente.getNome());
			} else {
				saida.print("\n\tNenhum registro encontrado");
			}
			saida.flush();
		}
		saida.close();
		leitor.close();
		servidor.close();
		socket.close();
	}
	
	private static ClienteDAO createDAO() {
		
		EntityManager em = Persistence.createEntityManagerFactory("smartcities-socket").createEntityManager();
		
		return new ClienteDAO(em);
	}
	
}
