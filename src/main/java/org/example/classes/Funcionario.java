package org.example.classes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;

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

    public String formatadorSalario() {
        DecimalFormatSymbols local = new DecimalFormatSymbols(new Locale("pt", "BR"));
        local.setGroupingSeparator('.');
        local.setDecimalSeparator(',');

        DecimalFormat formato = new DecimalFormat("#,##0.00", local);

        return formato.format(salario);
    }

    public void aumentarSalarioEm10Porcento() {
        BigDecimal dezPorcento = this.salario.multiply(new BigDecimal("0.1"));

        this.salario = this.salario.add(dezPorcento).setScale(2, RoundingMode.HALF_UP);
    }

        public BigDecimal quantidadeSalarioMinimo() {
            BigDecimal salarioMinimo = new BigDecimal("1212.00");

            return getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN);
        }

    public String toString() {
        return "Funcionario{" +
                "nome=" + getNome() +
                ", data de nascimento=" + dataNascimentoFormatada(getDataNascimento()) +
                ", salario=" + formatadorSalario() +
                ", funcao='" + funcao + '\'' +
                '}';
    }
}
