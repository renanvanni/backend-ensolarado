package org.generation.appEnsolarado.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity //entidade, faz com que o programa trabalhe com tabelas
@Table(name = "tb_tema") //criação da tabela tema no mysql
public class Tema {

	@Id //define id como chave principal
	@GeneratedValue(strategy = GenerationType.IDENTITY) //gera os valores altomaticamente conforme a pessoa posta algo
	private long id;
	
	@NotNull //o campo tem que ser preenchido
	@Size(min = 3, max = 100) //tamanho do tema
	private String nomeTema;
	
	@Size(min = 3, max = 255) //tamanho da descrição
	private String descricao;
	
	private int quantidadePosts;
	
	@OneToMany(mappedBy="tema", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("tema")
	private List<Postagem> postagem;

	//Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeTema() {
		return nomeTema;
	}

	public void setNomeTema(String nomeTema) {
		this.nomeTema = nomeTema;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidadePosts() {
		return quantidadePosts;
	}

	public void setQuantidadePosts(int quantidadePosts) {
		this.quantidadePosts = quantidadePosts;
	}
	
	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
}
