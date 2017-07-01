var Crossnap = Crossnap || {};

Crossnap.Download = (function() {
	
	function Download() {
		this.inputChave = $('.js-chave');
		this.inputArquivo = $('.js-arquivo');
		this.inputResposta = $('.js-resposta');
		this.btnConfirmar = $('.js-confirmar');
		this.formulario = $('.js-form-loading');
		
	}
	
	Download.prototype.iniciar = function() {
		this.btnConfirmar.on('click', onDownload.bind(this));
	}
	
	function onDownload() {
		var filename = this.inputArquivo.val();
		var nome = filename.split(".")[0];
		var extensao = filename.split(".")[1];
		this.inputResposta.val(filename);
		this.formulario.submit();
		saveFile(this.inputArquivo.data('url') + "download/"+ nome +"/"+ extensao +"",filename);
		
	}
	
	return Download;
	
}());

function saveFile(url,filename) {
	  var xhr = new XMLHttpRequest();
	  xhr.responseType = 'blob';
	  xhr.onload = function() {
	    var a = document.createElement('a');
	    a.href = window.URL.createObjectURL(xhr.response); // xhr.response is a blob
	    a.download = filename; // Set the file name.
	    a.style.display = 'none';
	    document.body.appendChild(a);
	    a.click();
	    delete a;
	  };
	  xhr.open('GET', url);
	  xhr.send();
}

$(function() {
	
	var download = new Crossnap.Download();
	download.iniciar();
	
});