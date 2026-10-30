package tn.esprit.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.domain.Client;
import tn.esprit.spring.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Tag(name = "Clients", description = "Gestion des clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @Operation(summary = "Créer un client")
    public ResponseEntity<Client> creer(@RequestBody Client client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.creer(client));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Chercher un client par identifiant")
    public Client obtenirParId(@PathVariable long id) {
        return clientService.obtenirParId(id);
    }

    @GetMapping("/listall")
    @Operation(summary = "Lister les clients")
    public List<Client> listerTous() {
        return clientService.listerTous();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un client")
    public ResponseEntity<Client> modifier(@PathVariable long id, @RequestBody Client client) {
        return ResponseEntity.ok(clientService.modifier(id, client));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un client")
    public ResponseEntity<Void> supprimer(@PathVariable long id) {
        clientService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
