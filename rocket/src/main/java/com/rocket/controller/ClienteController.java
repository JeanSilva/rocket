package com.rocket.controller;

import com.rocket.models.cliente.Cliente;
import com.rocket.services.ClienteService;
import com.rocket.services.UploadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }



    @PostMapping("/create")
    public ResponseEntity<Cliente> criarCliente(@Valid @RequestBody Cliente cliente,
                                                @RequestParam("documentoIdentidadeFrente") MultipartFile documentoIdentidadeFrente,
                                                @RequestParam("documentoIdentidadeVerso") MultipartFile documentoIdentidadeVerso,
                                                @RequestParam("comprovanteResidenciaFrente") MultipartFile comprovanteResidenciaFrente,
                                                @RequestParam("comprovanteResidenciaVerso") MultipartFile comprovanteResidenciaVerso,
                                                @RequestParam("selfeRosto") MultipartFile selfRosto){


        //envia os arquivos parra salvar em pasta upLoad
        cliente = criarCliente(cliente, documentoIdentidadeFrente, documentoIdentidadeVerso, comprovanteResidenciaFrente, comprovanteResidenciaVerso, selfRosto).getBody();

        Cliente novoCliente = clienteService.criarCliente(cliente);
        return ResponseEntity.ok(novoCliente);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();


        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/listId/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        clienteService.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }



    //metodo para setar o caminho das imagens no cliente e retornar para salva no banco como string
    public Cliente criaClienteImagem(Cliente cliente,  MultipartFile documentoIdentidadeFrente, MultipartFile documentoIdentidadeVerso,
                                     MultipartFile comprovanteResidenciaFrente, MultipartFile comprovanteResidenciaVerso, MultipartFile selfeRosto) throws IOException {

        UploadService uploadService = new UploadService();

       //caminhos documento id frente verso
        cliente.setDocumentoIdentidadeFrente(uploadService.fazerUpload(documentoIdentidadeFrente));
        cliente.setDocumentoIdentidadeVerso(uploadService.fazerUpload(documentoIdentidadeVerso));

       //caminhos comprovate de residencia frente/ verso
        cliente.setComprovanteReseidenciaFrente(uploadService.fazerUpload(comprovanteResidenciaFrente));
        cliente.setComprovanteReseidenciaVerso(uploadService.fazerUpload(comprovanteResidenciaVerso));

        //caminho da self
        cliente.setSelfRosto(uploadService.fazerUpload(selfeRosto));

        return cliente;


    }

}
