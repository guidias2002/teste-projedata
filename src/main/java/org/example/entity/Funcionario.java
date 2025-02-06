package org.example.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario extends Pessoa {

    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    private String formatarData(LocalDate data) {
        DateTimeFormatter formatacaoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatacaoData);
    }

    private String formatarSalario(BigDecimal salario) {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator(',');
        simbolos.setGroupingSeparator('.');
        DecimalFormat formatacaoValor = new DecimalFormat("#,##0.00", simbolos);
        return formatacaoValor.format(salario);
    }

    @Override
    public String toString() {
        return "{" + getNome() + ", " + formatarData(getDataNascimento()) + ", " + formatarSalario(salario) + ", " + funcao + "}";
    }
}
