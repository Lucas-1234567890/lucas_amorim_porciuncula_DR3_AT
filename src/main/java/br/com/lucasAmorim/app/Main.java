package br.com.lucasAmorim.app;

import br.com.lucasAmorim.controller.ApiController;
import br.com.lucasAmorim.repository.MensalistaRepository;
import io.javalin.Javalin;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MensalistaRepository repo = new MensalistaRepository();
        ApiController c = new ApiController(repo);

        Javalin app = Javalin.create().start(0);

        app.get("/hello", c::hello);
        app.get("/status", c::status);
        app.post("/echo", c::echo);
        app.get("/saudacao/{nome}", c::saudacao);

        app.get("/mensalistas", c::listMensalistas);
        app.get("/mensalistas/{matricula}", c::getMensalista);
        app.post("/mensalistas", c::createMensalista);

        int porta = app.port();
        System.out.println("Servidor rodando em http://localhost:" + porta);

        // Salva a porta em um arquivo para o cliente ler
        try (FileWriter fw = new FileWriter("porta.txt")) {
            fw.write(String.valueOf(porta));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
