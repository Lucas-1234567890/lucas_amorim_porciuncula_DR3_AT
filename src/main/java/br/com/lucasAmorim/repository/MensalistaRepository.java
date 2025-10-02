package br.com.lucasAmorim.repository;

import br.com.lucasAmorim.model.Mensalista;

import java.util.ArrayList;
import java.util.List;

public class MensalistaRepository {
    private final List<Mensalista> mensalistas = new ArrayList<>();

    public void save(Mensalista m) { mensalistas.add(m); }
    public List<Mensalista> findAll() { return mensalistas; }
    public Mensalista findByMatricula(String matricula) {
        return mensalistas.stream()
                .filter(m -> m.getMatricula().equals(matricula))
                .findFirst()
                .orElse(null);
    }
}
