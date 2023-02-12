package br.com.example.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.example.spring.data.orm.UnidadeTrabalho;
import br.com.example.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {

	private Boolean system = true;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}

	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Qual opção de unidade deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");

			int action = scanner.nextInt();

			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(scanner);
				break;

			default:
				system = false;
				break;
			}
		}
	}

	private void salvar(Scanner scanner) {
		System.out.println("Digite o nome da unidade");
		// scanner.next() -> pega o valor de texto (String) que o cliente passou pelo
		// console
		String nome = scanner.next();

		System.out.println("Digite o endereco");
		// scanner.next() -> pega o valor de texto (String) que o cliente passou pelo
		// console
		String endereco = scanner.next();

		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setDescricao(nome);
		unidadeTrabalho.setEndereco(endereco);

		unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("Salvo");
	}

	private void atualizar(Scanner scanner) {
		System.out.println("digite o id");
		Integer id = scanner.nextInt();

		System.out.println("Digite o nome da unidade");
		String nome = scanner.next();

		System.out.println("Digite o endereço da unidade");
		String endereco = scanner.next();

		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setId(id);
		unidadeTrabalho.setDescricao(nome);
		unidadeTrabalho.setEndereco(endereco);

		unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("Atualizado");
	}

	private void visualizar() {
		Iterable<UnidadeTrabalho> unidades = unidadeTrabalhoRepository.findAll();
		unidades.forEach(System.out::println);
	}

	private void deletar(Scanner scanner) {
		System.out.println("Id");
		Integer id = scanner.nextInt();
		unidadeTrabalhoRepository.deleteById(id);
		System.out.println("Deletado");
	}
	
}
