var botao = document.querySelector("#feedback");
botao.addEventListener("click", function(evento) {
	evento.preventDefault();
	window.alert("Raphael Molina");
	var form = document.login;
	var senha = form.senha;
	var usuario = form.usuario;
	usuario.value = senha.value;
	var form = document.fusuario;
	fusuario.value = "Mensagem enviada."
});