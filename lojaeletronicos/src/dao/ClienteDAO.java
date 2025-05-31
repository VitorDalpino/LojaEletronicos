package dao;

import model.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDAO {
    private static final List<Cliente> clientes = new ArrayList<>();
    private static Long nextId = 1L;

    public void salvar(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }

        // Verifica se já existe cliente com mesmo CPF
        if (buscarPorCpf(cliente.getCpf()).isPresent()) {
            throw new IllegalArgumentException("Já existe um cliente com este CPF");
        }

        if (cliente.getId() == null) {
            cliente.setId(nextId++);
            clientes.add(cliente);
        } else {
            atualizar(cliente);
        }
    }

    public void atualizar(Cliente cliente) {
        if (cliente == null || cliente.getId() == null) {
            throw new IllegalArgumentException("Cliente inválido para atualização");
        }

        Optional<Cliente> clienteExistente = buscarPorCpf(cliente.getCpf());
        if (clienteExistente.isPresent() && !clienteExistente.get().getId().equals(cliente.getId())) {
            throw new IllegalArgumentException("Já existe outro cliente com este CPF");
        }

        boolean encontrado = false;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId().equals(cliente.getId())) {
                clientes.set(i, cliente);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new IllegalArgumentException("Cliente não encontrado para atualização");
        }
    }

    public void deletar(Cliente cliente) {
        if (cliente == null || cliente.getId() == null) {
            throw new IllegalArgumentException("Cliente inválido para exclusão");
        }

        boolean removido = clientes.removeIf(c -> c.getId().equals(cliente.getId()));
        if (!removido) {
            throw new IllegalArgumentException("Cliente não encontrado para exclusão");
        }
    }

    public void excluir(Long id) {
        Cliente cliente = buscarPorId(id);
        if (cliente != null) {
            deletar(cliente);
        } else {
            throw new IllegalArgumentException("Cliente não encontrado para exclusão");
        }
    }

    public Cliente buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Optional<Cliente> buscarPorCpf(String cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("CPF não pode ser nulo");
        }
        return clientes.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst();
    }

    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes);
    }
}
