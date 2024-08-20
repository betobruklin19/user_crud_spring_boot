//Arquivo Javascript

//Validador do campo CPF
$(document).ready(function(){
    $('#cpf').mask('000.000.000-00');
});

//Validador do campo EMAIL
function validateEmail() {
    const emailInput = document.getElementById('email');
    const email = emailInput.value;
    const errorMessage = document.getElementById('email-error');
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!emailRegex.test(email)) {
        errorMessage.textContent = 'Por favor, insira um email válido.';
        emailInput.classList.add('is-invalid');
    } else {
        errorMessage.textContent = '';
        emailInput.classList.remove('is-invalid');
    }
}

document.addEventListener('DOMContentLoaded', function() {
    const emailInput = document.getElementById('email');
    emailInput.addEventListener('input', validateEmail);
});
