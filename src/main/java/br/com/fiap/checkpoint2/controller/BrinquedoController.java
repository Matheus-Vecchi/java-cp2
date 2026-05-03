package br.com.fiap.checkpoint2.controller;

import br.com.fiap.checkpoint2.model.Brinquedo;
import br.com.fiap.checkpoint2.repository.BrinquedoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brinquedos")
public class BrinquedoController {

    private final BrinquedoRepository brinquedoRepository;

    public BrinquedoController(BrinquedoRepository brinquedoRepository) {
        this.brinquedoRepository = brinquedoRepository;
    }

    @GetMapping
    public List<Brinquedo> listar() {
        return brinquedoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brinquedo> buscarPorId(@PathVariable Long id) {
        return brinquedoRepository.findById(id)
                .map(brinquedo -> ResponseEntity.ok(brinquedo))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Brinquedo criar(@RequestBody @Valid Brinquedo brinquedo) {
        return brinquedoRepository.save(brinquedo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brinquedo> atualizar(@PathVariable Long id, @RequestBody @Valid Brinquedo dadosAtualizados) {
        return brinquedoRepository.findById(id)
                .map(brinquedo -> {
                    brinquedo.setNome(dadosAtualizados.getNome());
                    brinquedo.setTipo(dadosAtualizados.getTipo());
                    brinquedo.setClassificacao(dadosAtualizados.getClassificacao());
                    brinquedo.setTamanho(dadosAtualizados.getTamanho());
                    brinquedo.setPreco(dadosAtualizados.getPreco());

                    Brinquedo atualizado = brinquedoRepository.save(brinquedo);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!brinquedoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        brinquedoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}