package org.example;

import org.example.entity.Funcionario;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {


        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.

        List<Funcionario> listaFuncionarios = new ArrayList<>();

        listaFuncionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        listaFuncionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.88"), "Operador"));
        listaFuncionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9888.14"), "Coordenador"));
        listaFuncionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        listaFuncionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        listaFuncionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        listaFuncionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        listaFuncionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        listaFuncionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        listaFuncionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));


        // 3.2 – Remover o funcionário “João” da lista.

        listaFuncionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));


        // 3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:

        // a data de nascimento e salário estão sendo formatados no toString da classe Funcionário
        System.out.println("Todos os funcionários com todas suas informações:");
        for(Funcionario funcionario : listaFuncionarios) {
            System.out.println(funcionario);
        }
        System.out.println("--------------------------");


        // 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.

        for(Funcionario funcionario : listaFuncionarios) {
            BigDecimal salarioAtual = funcionario.getSalario();
            BigDecimal aumento = salarioAtual.multiply(BigDecimal.valueOf(0.10));
            BigDecimal salarioComAumento = salarioAtual.add(aumento);

            funcionario.setSalario(salarioComAumento);
        }


        // 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.

        Map<String, List<Funcionario>> funcionariosPorFuncao = listaFuncionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));


        // 3.6 – Imprimir os funcionários, agrupados por função.

        System.out.println("Funcionários por função:");
        funcionariosPorFuncao.forEach((funcao, funcionarios) -> {
            System.out.println("Função: " + funcao);

            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario);
            }
        });
        System.out.println("--------------------------");


        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.

        List<Funcionario> listaAniversariantesMes10e12 = listaFuncionarios.stream()
                .filter(funcionario -> {
                    return funcionario.getDataNascimento().getMonth() == Month.OCTOBER || funcionario.getDataNascimento().getMonth() == Month.DECEMBER;
                }).toList();

        System.out.println("Aniversariantes mês 10 e 12:");
        System.out.println(listaAniversariantesMes10e12);
        System.out.println("--------------------------");


        // 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.

        Funcionario funcionarioMaiorIdade = listaFuncionarios.stream()
                .max(Comparator.comparingInt(f -> Period.between(f.getDataNascimento(), LocalDate.now()).getYears()))
                .orElse(null);

        if (funcionarioMaiorIdade != null) {
            int idade = Period.between(funcionarioMaiorIdade.getDataNascimento(), LocalDate.now()).getYears();
            System.out.println("Funcionário maior idade:");
            System.out.println("Nome: " + funcionarioMaiorIdade.getNome());
            System.out.println("Idade: " + idade + " anos");
            System.out.println("--------------------------");
        }


        // 3.10 – Imprimir a lista de funcionários por ordem alfabética.

        Collections.sort(listaFuncionarios, Comparator.comparing(Funcionario::getNome));

        System.out.println("Lista de funcionários em ordem alfabética:");
        for (Funcionario funcionario : listaFuncionarios) {
            System.out.println(funcionario);
        }
        System.out.println("--------------------------");


        // 3.11 – Imprimir o total dos salários dos funcionários.

        BigDecimal totalSalarios = BigDecimal.ZERO;

        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator(',');
        simbolos.setGroupingSeparator('.');

        DecimalFormat formatacaoValor = new DecimalFormat("#,##0.00", simbolos);
        for (Funcionario funcionario : listaFuncionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }

        System.out.println("Total dos salários dos funcionários: " + formatacaoValor.format(totalSalarios));
        System.out.println("--------------------------");


        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.

        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        System.out.println("Quantos salários mínimos cada funcionário ganha:");
        for (Funcionario funcionario : listaFuncionarios) {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(funcionario.getNome() + " ganha " + salariosMinimos + " salários mínimos.");
        }


    }
}