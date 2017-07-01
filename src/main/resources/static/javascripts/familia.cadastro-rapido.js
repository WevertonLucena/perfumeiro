var Lucena = Lucena || {};

Lucena.FamiliaCadastroRapido = (function() {
	
	function FamiliaCadastroRapido() {
		this.modal = $('#modalCadastroRapidoFamilia');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-familia-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputNomeFamilia = $('#nomeFamilia');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-familia');
	}
	
	FamiliaCadastroRapido.prototype.iniciar = function() {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this))
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}
	
	function onModalShow() {
		this.inputNomeFamilia.focus();
	}
	
	function onModalClose() {
		this.inputNomeFamilia.val('');
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {
		var nomeFamilia = this.inputNomeFamilia.val().trim();
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ nome: nomeFamilia }),
			error: onErroSalvandoFamilia.bind(this),
			success: onFamiliaSalvo.bind(this)
		});
	}
	
	function onErroSalvandoFamilia(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		this.form.find('.form-group').addClass('has-error');
	}
	
	function onFamiliaSalvo(familia) {
		var comboFamilia = $('#familia');
		comboFamilia.append('<option value=' + familia.id + '>' + familia.nome + '</option>');
		comboFamilia.val(familia.id);
		this.modal.modal('hide');
	}
	
	return FamiliaCadastroRapido;
	
}());

$(function() {
	var familiaCadastroRapido = new Lucena.FamiliaCadastroRapido();
	familiaCadastroRapido.iniciar();
});