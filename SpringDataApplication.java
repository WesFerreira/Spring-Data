package br.com.example.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.example.spring.data.service.CrudCargoService;
import br.com.example.spring.data.service.CrudFuncionarioService;
import br.com.example.spring.data.service.CrudUnidadeTrabalhoService;
import br.com.example.spring.data.service.RelatoriosService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	
private final CrudCargoService cargoService;
private final CrudFuncionarioService funcionarioService;
private final CrudUnidadeTrabalhoService unidadeTrabalhoService;
private final RelatoriosService relatorioService;

	private Boolean system = true;

	public SpringDataApplication(
								CrudCargoService cargoService,
								CrudUnidadeTrabalhoService unidadeTrabalhoService,
								CrudFuncionarioService funcionarioService,
								RelatoriosService relatorioService
								) {
		this.cargoService = cargoService;
		this.unidadeTrabalhoService = unidadeTrabalhoService;
		this.funcionarioService = funcionarioService;
		this.relatorioService = relatorioService;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Scanner scanner = new Scanner(System.in);
		
		while(system) {
			System.out.println("Qual ação você quer executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade de Trabalho");
			System.out.println("4 - Relatorios");
			
			
			//scanner.nextInt() para pegar um valor inteiro que o cliente digitar
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				cargoService.inicial(scanner);
				break;
			case 2:
				funcionarioService.inicial(scanner);
				break;
			case 3:
				unidadeTrabalhoService.inicial(scanner);
				break;
			case 4:
				relatorioService.inicial(scanner);
				break;
			default:
				break;
			}
		}
	}
}
