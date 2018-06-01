function cadastrar(e) {
	let form = {};
	form.nome = document.getElementById('nome');
	form.email = document.getElementById('email');
	form.telefone = document.getElementById('telefone');
	form.lista = document.getElementsByName('lista');
	form.listaOrigem = '';
	
	for (var i = 0; i < form.lista.length; i++) {
		if (form.lista[i].checked) {
			form.listaOrigem = form.lista[i].value;
			break;
		}
	}
	
	if (validarForm(form)) {
		
		var data = {};
		data.nome = form.nome.value;
		data.email = form.email.value;
		data.telefone = form.telefone.value.length > 0 ? form.telefone.value : null;
		data.listaOrigem = form.listaOrigem;
		
		doRequest("POST", 'http://localhost:8080/jogador', data, function() {
			alert('Cadastro realizado com sucesso.');
			limparForm();
			
		}, function(e) {
			alert('Houve um problema ao realizar o cadastro:\n\n' + e.replace(/",/g, '"\n'));
		});
	}
	
	e.preventDefault();
}

function validarForm(form) {
	if (!form.nome || form.nome.value.trim().length == 0) {
		alert('O campo nome é obrigatório.');
		return false;
	}
	
	if (!form.email || form.email.value.trim().length == 0) {
		alert('O campo email é obrigatório.');
		return false;
	}
	
	if (!new RegExp(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/i)
			.test(form.email.value)) {
		alert('O campo email não foi preenchido com um valor válido.');
		return false;
	}
	
	if (!form.listaOrigem) {
		alert('Selecione o grupo que deseja fazer parte.');
		return false;
	}
	
	if (form.telefone && form.telefone.value.length > 0 && !new RegExp(/[0-9]{8,9}/i).test(form.telefone.value)) {
		alert('O campo telefone deve ser preenchido apenas com números e possuir entre 8 e 9 digitos.');
		return false;
	}
	
	return true;
}

function limparForm() {
	var form = document.getElementById('form').reset();
}

function doRequest(method, url, payload, cbSuccess, cbFail) {
    var xhttp = new XMLHttpRequest();
    xhttp.open(method, url, true);
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

    xhttp.onload = function (e) {
        if (xhttp.readyState === 4) {
            if (xhttp.status.toString().substring(0,1) == 2) {
            	cbSuccess(xhttp.response);

            } else {
            	cbFail(xhttp.response);
            }
        }
    };
    
    xhttp.onerror = function (e) {
    	cbFail(e);
    };

    xhttp.send(JSON.stringify(payload));
}

function listarJogadoresCadastrados(e) {
	
	limparTabela();
	
	doRequest("GET", 'http://localhost:8080/jogador', null, function(result) {
		
		var jogador = [];
		jogador.nome = undefined;
		jogador.email = undefined;
		jogador.telefone = undefined;
		jogador.codinome = undefined;
		jogador.listaOrigem = undefined;
		
		jogador = JSON.parse(result);
		
		if (jogador.length == 0) {
			alert("Nenhum resultado encontrado.");
			return;
		}
		
		for (var i = 0; i < jogador.length; i++) {
			inserirLinhaDtTable(i+1, jogador[i]);
		}
		
		document.getElementById('div-cadastrados').style.display = ''
		
	}, function(e) {
		alert('Não foi possível realizar esta ação:\n\n' + e.replace(/",/g, '"\n'));
	});
	
	e.preventDefault();
}

function inserirLinhaDtTable(index, jogador) {
	var tbody = document.getElementById('dt-table').getElementsByTagName('tbody')[0];
	var newRow  = tbody.insertRow(tbody.rows.length);
	
	var newCell = newRow.insertCell(0);
	var newText = document.createTextNode(index);
	newCell.appendChild(newText);
	
	newCell = newRow.insertCell(1);
	newText = document.createTextNode(jogador.nome);
	newCell.appendChild(newText);
	
	newCell = newRow.insertCell(2);
	newText = document.createTextNode(jogador.email);
	newCell.appendChild(newText);
	
	newCell = newRow.insertCell(3);
	newText = document.createTextNode(jogador.telefone);
	newCell.appendChild(newText);
	
	newCell = newRow.insertCell(4);
	newText = document.createTextNode(jogador.codinome);
	newCell.appendChild(newText);
	
	newCell = newRow.insertCell(5);
	newText = document.createTextNode(getTextoLista(jogador.listaOrigem));
	newCell.appendChild(newText);
}

function getTextoLista(listaOrigem) {
	if (listaOrigem == 'LIGA_JUSTICA') {
		return 'Liga da Justiça';
		
	} else if (listaOrigem == 'VINGADORES') {
		return 'Vingadores';
	}
	
	return '';
}

function limparTabela() {
	var table = document.getElementById("dt-table");
	for(var i = table.rows.length - 1; i > 0; i--) {
	    table.deleteRow(i);
	}
}
