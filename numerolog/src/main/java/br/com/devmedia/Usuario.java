package br.com.devmedia;

import java.time.LocalDate;
import java.util.function.UnaryOperator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {
		@Id
		private String nome;
		
		private LocalDate dataNascimento;
		private int numeroDoNome;
		private int numeroDataDataNascimento;

		public Usuario(String nome, LocalDate dataNascimento) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		}

		public void numerologiaDoNome() {
		int somaDosCaracteresDoNome = nome.chars()
		.map(c -> valorNumericoDoCaractere((char)c)).sum();
		this.numeroDoNome = reducaoParaBaseDecimal(somaDosCaracteresDoNome);
		}

		public void numerologiaDaDataNascimento() {
		this.numeroDataDataNascimento = reducaoParaBaseDecimal
		(dataNascimento.getDayOfMonth() +
		dataNascimento.getMonthValue() + dataNascimento.getYear());
		}

		public String getNome() {
		return nome;
		}

		public LocalDate getDataNascimento() {
		return dataNascimento;
		}

		public int getNumeroDoNome() {
		return numeroDoNome;
		}

		public int getNumeroDaDataNascimento() {
		return numeroDataDataNascimento;
		}
		int valorNumericoDoCaractere(char caractere) {
		return caractere == ' ' ? 0 :reducaoParaBaseDecimal(valorNumericoComAIniciandoEmUm(caractere));
		}

		int valorNumericoComAIniciandoEmUm(char caractere) {
		return Character.getNumericValue(caractere) - 9;
		}

		int reducaoParaBaseDecimal (int valor) {
			UnaryOperator<Integer> reducao = v -> v <= 9 ? v : this.reducaoParaBaseDecimal(v / 10 + v% 10);
		return reducao.apply(valor);
		}


		
}

