const checkBoxes = document.querySelectorAll('.check-box');
const deleteBtn = document.getElementById('delete-btn');

// Função para atualizar a visibilidade do botão de deletar
function updateDeleteBtnVisibility() {
    let anyChecked = Array.from(checkBoxes).some(cb => cb.checked);
    if (anyChecked) {
        deleteBtn.removeAttribute('hidden');
    } else {
        deleteBtn.setAttribute('hidden', 'hidden');
    }
}

// Adiciona um event listener a cada checkbox para verificar a seleção
checkBoxes.forEach(checkBox => {
    checkBox.addEventListener('change', updateDeleteBtnVisibility);
});

// Inicializa a visibilidade do botão ao carregar a página
updateDeleteBtnVisibility();
