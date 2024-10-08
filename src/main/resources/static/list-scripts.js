    const checkBoxes = document.querySelectorAll('.check-box');
    const deleteBtn = document.getElementById('delete-btn');
    const selectAllCheckBox = document.getElementById('select-all');

    // Função para atualizar a visibilidade do botão de deletar
    function updateDeleteBtnVisibility() {
        let anyChecked = Array.from(checkBoxes).some(cb => cb.checked);
        if (anyChecked) {
            if(deleteBtn){
                        deleteBtn.removeAttribute('hidden');
                    }
                } else {
                    if(deleteBtn){
                        deleteBtn.setAttribute('hidden', 'hidden');
                    }
                }
    }

    // Adiciona um event listener a cada checkbox para verificar a seleção
    checkBoxes.forEach(checkBox => {
        checkBox.addEventListener('change', updateDeleteBtnVisibility);
    });

    // Adiciona um event listener a cada checkbox para verificar a seleção
    checkBoxes.forEach(checkBox => {
        checkBox.addEventListener('change', updateDeleteBtnVisibility);
    });

    // Inicializa a visibilidade do botão ao carregar a página
    updateDeleteBtnVisibility();

    // Adiciona um event listener ao checkbox "Selecionar Todos"
    selectAllCheckBox.addEventListener('change', function() {
        checkBoxes.forEach(checkbox => checkbox.checked = this.checked);
        // Atualiza a visibilidade do botão após selecionar todos
            updateDeleteBtnVisibility();
    });

    // Atualiza a tabela com os resultados da busca via AJAX
    const searchInput = document.getElementById('searchInput');
    if (searchInput) {

        // Função para permitir apenas letras
                function filterInput(event) {
                    const regex = /^[A-Za-zÀ-ÿ\s]*$/; // Permite letras e acentos
                    const inputValue = event.target.value;

                    if (!regex.test(inputValue)) {
                        // Remove caracteres não permitidos
                        event.target.value = inputValue.replace(/[^A-Za-zÀ-ÿ\s]/g, '');
                    }
                }

                // Adiciona o evento input para filtrar a entrada em tempo real
                searchInput.addEventListener('input', filterInput);

        searchInput.addEventListener('keyup', function() {
            const query = this.value;
            $.ajax({
                url: '/user/search',
                type: 'GET',
                data: { query: query }, // Envia a query para o servidor
                success: function(response) {
                    // Atualiza o corpo da tabela com o fragmento HTML recebido
                    const $response = $(response);
                    const newTableBody = $response.find('#userTableBody').html();
                    $('#userTableBody').html(newTableBody);

                    // Atualiza mensagens de erro, se existirem
                    const $alert = $response.find('.alert-danger');
                    if ($alert.length > 0) {
                        $('.alert-danger').remove();
                        $('.card-body').prepend($alert);
                    } else {
                        $('.alert-danger').remove();
                    }
                },
                error: function(xhr, status, error) {
                    console.error('Erro na requisição AJAX:', status, error);
                }
            });
        });
    };

