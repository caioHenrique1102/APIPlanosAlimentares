const API_BASE_URL = 'http://localhost:8080';

// Função para trocar de abas
function openTab(evt, tabName) {
    let i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
}

// --- LÓGICA PARA PESSOAS ---
async function carregarPessoas() {
    try {
        const response = await fetch(`${API_BASE_URL}/pessoa/listar`);
        if (!response.ok) throw new Error('Erro ao buscar pessoas.');
        const pessoas = await response.json();
        const tbody = document.querySelector('#pessoasTable tbody');
        tbody.innerHTML = '';
        pessoas.forEach(p => {
            tbody.innerHTML += `
                <tr>
                    <td>${p.nome}</td>
                    <td>${p.altura}</td>
                    <td>${p.idade}</td>
                    <td><button class="btn-excluir" onclick="excluirPessoa('${p.nome}')">Excluir</button></td>
                </tr>`;
        });
    } catch (error) {
        console.error(error);
        alert('Não foi possível carregar as pessoas.');
    }
}

async function salvarPessoa() {
    const nome = document.getElementById('pessoaNome').value;
    const altura = document.getElementById('pessoaAltura').value;
    const idade = document.getElementById('pessoaIdade').value;

    if (!nome || !altura || !idade) {
        alert('Por favor, preencha todos os campos.');
        return;
    }

    await fetch(`${API_BASE_URL}/pessoa/cadastro`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ nome, altura, idade }),
    });

    document.getElementById('pessoaNome').value = '';
    document.getElementById('pessoaAltura').value = '';
    document.getElementById('pessoaIdade').value = '';
    carregarPessoas();
}

async function excluirPessoa(nome) {
    if (!confirm(`Deseja excluir ${nome}?`)) return;
    await fetch(`${API_BASE_URL}/pessoa/deletar/${nome}`, { method: 'DELETE' });
    carregarPessoas();
}


// --- LÓGICA PARA ALIMENTOS ---
async function carregarAlimentos() {
    try {
        const response = await fetch(`${API_BASE_URL}/alimento/listar`);
        if (!response.ok) throw new Error('Erro ao buscar alimentos.');
        const alimentos = await response.json();

        // Tabela
        const tbody = document.querySelector('#alimentosTable tbody');
        tbody.innerHTML = '';
        alimentos.forEach(a => {
            tbody.innerHTML += `
                <tr>
                    <td>${a.nome}</td>
                    <td>${a.quantidade}</td>
                    <td><button class="btn-excluir" onclick="excluirAlimento('${a.nome}')">Excluir</button></td>
                </tr>`;
        });

        // Checkboxes para refeições
        const container = document.getElementById('alimentosCheckboxContainer');
        container.innerHTML = '';
        alimentos.forEach(a => {
            container.innerHTML += `
                <div>
                    <input type="checkbox" id="alimento-${a.id}" name="alimentos" value='${JSON.stringify(a)}'>
                    <label for="alimento-${a.id}">${a.nome}</label>
                </div>`;
        });

    } catch (error) {
        console.error(error);
        alert('Não foi possível carregar os alimentos. Verifique se o endpoint /alimento/listar existe.');
    }
}

async function salvarAlimento() {
    const nome = document.getElementById('alimentoNome').value;
    const quantidade = document.getElementById('alimentoQuantidade').value;

    if (!nome || !quantidade) {
        alert('Por favor, preencha todos os campos.');
        return;
    }

    await fetch(`${API_BASE_URL}/alimento/criar`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ nome, quantidade }),
    });

    document.getElementById('alimentoNome').value = '';
    document.getElementById('alimentoQuantidade').value = '';
    carregarAlimentos();
}

async function excluirAlimento(nome) {
    if (!confirm(`Deseja excluir ${nome}?`)) return;
    await fetch(`${API_BASE_URL}/alimento/deletar/${nome}`, { method: 'DELETE' });
    carregarAlimentos();
}

// --- LÓGICA PARA REFEIÇÕES ---
async function carregarRefeicoes() {
    try {
        const response = await fetch(`${API_BASE_URL}/refeicao/listar`);
        if (!response.ok) throw new Error('Erro ao buscar refeições.');
        const refeicoes = await response.json();
        const tbody = document.querySelector('#refeicoesTable tbody');
        tbody.innerHTML = '';
        refeicoes.forEach(r => {
            const alimentosNomes = r.alimentos.map(a => a.nome).join(', ');
            tbody.innerHTML += `
                <tr>
                    <td>${r.tipoRefeicoes}</td>
                    <td>${alimentosNomes}</td>
                    <td><button class="btn-excluir" onclick="excluirRefeicao(${r.id})">Excluir</button></td>
                </tr>`;
        });
    } catch (error) {
        console.error(error);
        alert('Não foi possível carregar as refeições. Verifique se o endpoint /refeicao/listar existe.');
    }
}

async function salvarRefeicao() {
    const tipoRefeicoes = document.getElementById('refeicaoTipo').value;
    const checkboxes = document.querySelectorAll('#alimentosCheckboxContainer input[type="checkbox"]:checked');
    const alimentos = Array.from(checkboxes).map(cb => JSON.parse(cb.value));

    if (alimentos.length === 0) {
        alert('Selecione pelo menos um alimento.');
        return;
    }

    await fetch(`${API_BASE_URL}/refeicao/criar`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ tipoRefeicoes, alimentos }),
    });

    checkboxes.forEach(cb => cb.checked = false);
    carregarRefeicoes();
}

async function excluirRefeicao(id) {
    if (!confirm(`Deseja excluir esta refeição?`)) return;
    await fetch(`${API_BASE_URL}/refeicao/deletar/${id}`, { method: 'DELETE' });
    carregarRefeicoes();
}


// --- INICIALIZAÇÃO ---
// Carrega os dados iniciais quando a página é carregada
window.onload = () => {
    carregarPessoas();
    carregarAlimentos();
    carregarRefeicoes();
};