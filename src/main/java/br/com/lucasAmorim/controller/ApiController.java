package br.com.lucasAmorim.controller;

import br.com.lucasAmorim.model.Mensalista;
import br.com.lucasAmorim.repository.MensalistaRepository;
import com.google.gson.Gson;
import io.javalin.http.Context;

import java.time.Instant;
import java.util.Map;

public class ApiController {
    private final MensalistaRepository repo;
    private final Gson gson = new Gson();

    public ApiController(MensalistaRepository repo) {
        this.repo = repo;
    }

    public void hello(Context ctx) {
        ctx.result("Hello, Javalin!");
    }

    public void status(Context ctx) {
        ctx.json(Map.of("status", "ok", "timestamp", Instant.now().toString()));
    }

    public void echo(Context ctx) {
        var map = gson.fromJson(ctx.body(), Map.class);
        ctx.json(map);
    }

    public void saudacao(Context ctx) {
        String nome = ctx.pathParam("nome");
        ctx.json(Map.of("mensagem", "Olá, " + nome + "!"));
    }

    public void listMensalistas(Context ctx) {
        ctx.json(repo.findAll());
    }

    public void getMensalista(Context ctx) {
        String mat = ctx.pathParam("matricula");
        var m = repo.findByMatricula(mat);
        if (m == null) ctx.status(404).json(Map.of("erro", "não encontrado"));
        else ctx.json(m);
    }

    public void createMensalista(Context ctx) {
        try {
            Mensalista m = gson.fromJson(ctx.body(), Mensalista.class);
            if (m.getMatricula() == null || m.getMatricula().isBlank()) {
                ctx.status(400).json(Map.of("erro", "matricula inválida"));
                return;
            }
            repo.save(m);
            ctx.status(201).json(m);
        } catch (Exception e) {
            ctx.status(400).json(Map.of("erro", "json inválido"));
        }
    }
}
