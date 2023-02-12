package br.com.example.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.example.spring.data.orm.Cargo;
import br.com.example.spring.data.orm.Funcionario;
import br.com.example.spring.data.orm.UnidadeTrabalho;
import br.com.example.spring.data.repository.CargoRepository;
import br.com.example.spring.data.repository.FuncionarioRepository;
import br.com.example.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudFuncionarioService {

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final CargoRepository cargoRepository;
	private final FuncionarioRepository funcionarioRepository;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	
	public CrudFuncionarioService(CargoRepository cargoRepository, 
			FuncionarioRepository funcionarioRepository,
			UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.cargoRepository = cargoRepository;
		this.funcionarioRepository = funcionarioRepository;
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}
	
	public void inicial (Scanner scanner) {
		while (system) {
			System.out.println("Qual opção de funcionario deseja executar");
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
		System.out.println("Digite o nome");
		String nome = scanner.next();
		
		System.out.println("Digite o cpf");
		String cpf = scanner.next();
		
		System.out.println("Digite o salario");
		Double salario = scanner.nextDouble();
		
		System.out.println("Digite a data de contratação");
		String dataContratacao = scanner.next();
		
		System.out.println("Digite o cargoId");
		Integer cargoId = scanner.nextInt();
		
		List<UnidadeTrabalho> unidades = unidade(scanner);
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
		
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());
		funcionario.setUnidadeTrabalhos(unidades);
		
		funcionarioRepository.save(funcionario);
		System.out.println("Salvo");
	}
	
	private List<UnidadeTrabalho> unidade(Scanner scanner) {
		Boolean isTrue = true;
		List<UnidadeTrabalho> unidades = new ArrayList<>();
		
		while (isTrue) {
			System.out.println("Digite o unidadeId (Para sair digite 0");
			Integer unidadeId = scanner.nextInt();
			
			if(unidadeId != 0) {
				Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
				unidades.add(unidade.get());
			} else {
				isTrue = false;
			}
		}
		return unidades;
	}
 	
	private void atualizar(Scanner scanner) {
		System.out.println("Digite o nome");
		String nome = scanner.next();
		
		System.out.println("Digite o cpf");
		String cpf = scanner.next();
		
		System.out.println("Digite o salario");
		double salario = scanner.nextDouble();
		
		System.out.println("Digite a data de contratacao");
		String dataContratacao = scanner.next();
		
		System.out.println("Digite o cargoId");
		Integer cargoId = scanner.nextInt();
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());
		
		funcionarioRepository.save(funcionario);
		System.out.println("Atualizado");
	}
	
	private void visualizar() {
		Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();
		funcionarios.forEach(System.out::println);
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Digite o id que deseja deletar");
		Integer id = scanner.nextInt();
		funcionarioRepository.deleteById(id);
		System.out.println("Deletado");
	}
}
