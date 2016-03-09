package br.com.devmedia;

public class UsuarioObtemNumerologiaDataNascimentoFixture extends FixtureBase{
	
	public void cadastrarNumerologiaDaDataDenascimentoDoUsuario(){
		usuario.numerologiaDaDataNascimento();
		repository.save(usuario);
	}
	
	public int obterNumerologiaDatanascimentoDoBanco(){
		this.usuario = obterUsuario(usuario.getNome(), usuario.getDataNascimento());
		return usuario.getNumeroDaDataNascimento();
	}
}
