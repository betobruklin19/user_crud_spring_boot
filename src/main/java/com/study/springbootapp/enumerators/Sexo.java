package com.study.springbootapp.enumerators;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Sexo{
        Masculino("Masculino"),
        Feminino("Feminino");
        private final String valor;

        // Construtor para atribuir o valor ao campo
        Sexo(String valor) {
            this.valor = valor;
        }

        // Método para encontrar um enum com base no valor
        public static Sexo fromValor(String valor) {
            for (Sexo sexo : Sexo.values()) {
                if (sexo.getValor().equalsIgnoreCase(valor)) {
                    return sexo;
                }
            }
            throw new IllegalArgumentException("Valor inválido: " + valor);
        }

        // Método para obter o valor associado
        @JsonValue
        public String getValor() {
            return valor;
        }
    }
