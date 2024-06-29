package org.example;

import org.example.classes.Funcionario;
import org.example.classes.Pessoa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {


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

        listaFuncionarios.removeIf((funcionario -> funcionario.getNome().equals("João")));

        System.out.println("Imprimir funcionários e aumentar salário em 10%:");
        for(Funcionario funcionario : listaFuncionarios) {
            System.out.println(funcionario);
            funcionario.aumentarSalarioEm10Porcento();
        }

        System.out.println("\n\n");

        System.out.println("Funcionários agrupados por função:");
        Map<String, List<Funcionario>> funcionariosPorFuncao = listaFuncionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        funcionariosPorFuncao.forEach((funcao, funcionarios) -> {
            System.out.println("Função: " + funcao);
            funcionarios.forEach(funcionario -> System.out.println(" - " + funcionario));
        });

        System.out.println("\n\n");

        System.out.println("Aniversariantes mês 10 e 12:");
        List<Funcionario> listaAniversariantesMes10e12 = listaFuncionarios.stream()
                .filter(funcionario -> {
                    return funcionario.getDataNascimento().getMonth() == Month.OCTOBER || funcionario.getDataNascimento().getMonth() == Month.DECEMBER;
                })
                .toList();

        System.out.println(listaAniversariantesMes10e12);

        System.out.println("\n\n");

        System.out.println("Imprimir funcionário mais velho:");
        Optional<Funcionario> funcionarioMaisVelho = listaFuncionarios.stream()
                .max(Comparator.comparingInt(funcionario -> Period.between(funcionario.getDataNascimento(), LocalDate.now()).getYears()));

        funcionarioMaisVelho.ifPresent(funcionario ->
                System.out.println("Funcionário mais velho: " + "Nome: " + funcionario.getNome() + ", Idade: " + Period.between(funcionario.getDataNascimento(), LocalDate.now()).getYears()));

        System.out.println("\n\n");

        System.out.println("Lista por ordem alfabética:");
        listaFuncionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);

        System.out.println("\n\n");

        System.out.println("Soma de todos os salários:");
        BigDecimal totalSalarios = listaFuncionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Soma total dos salários: " + totalSalarios);

        System.out.println("\n\n");

        System.out.println("Quantidade de salário mínimo por funcionário:");
        for(Funcionario funcionario : listaFuncionarios) {
            System.out.println(funcionario.getNome() + " recebe " + funcionario.quantidadeSalarioMinimo() + " salários mínimos.");
        }

    }
}