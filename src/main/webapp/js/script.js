document.addEventListener('DOMContentLoaded', () => {
    // Form elements
    const multiStepForm = document.getElementById('multi-step-form');
    const step1 = document.getElementById('step-1');
    const step2 = document.getElementById('step-2');
    const step3 = document.getElementById('step-3');
    const nextButtons = document.querySelectorAll('.btn-next');
    const finalSubmitButton = document.getElementById('final-submit-button');

    // Input elements
    const amountInput = document.getElementById('amount');
    const durationInput = document.getElementById('duration');
    const monthlyInput = document.getElementById('monthly');
    const projectSelect = document.getElementById('project');
    const professionSelect = document.getElementById('profession');

    // Range elements
    const amountRange = document.getElementById('amount-range');
    const durationRange = document.getElementById('duration-range');
    const monthlyRange = document.getElementById('monthly-range');

    // Recap elements
    const recapSection = document.querySelector('.recap-content');
    const recapContainer = document.querySelector('.recap');

    // Step indicators
    const step1Indicator = document.getElementById('step-1-indicator');
    const step2Indicator = document.getElementById('step-2-indicator');
    const step3Indicator = document.getElementById('step-3-indicator');

    // Constants
    const interestRate = 0.05;

    // Helper functions
    function calculateMonthly(amount, duration) {
        if (!amount || !duration) return 0;
        const monthlyRate = interestRate / 12;
        const monthly = (amount * monthlyRate * Math.pow(1 + monthlyRate, duration)) / (Math.pow(1 + monthlyRate, duration) - 1);
        return isNaN(monthly) || monthly <= 0 ? 0 : monthly.toFixed(2);
    }

    function updateMonthlyLabel(value) {
        const monthlyLabel = document.querySelector('.monthly-label');
        if (!monthlyLabel) return;

        monthlyLabel.textContent = `${value} DH`;
        const rangeWidth = monthlyRange.offsetWidth;
        const thumbPosition = (monthlyRange.value - monthlyRange.min) / (monthlyRange.max - monthlyRange.min) * rangeWidth;
        monthlyLabel.style.left = `${thumbPosition}px`;
    }

    function syncInputs(input, range, callback) {
        input.addEventListener('input', () => {
            range.value = input.value;
            callback();
        });
        range.addEventListener('input', () => {
            input.value = range.value;
            callback();
        });
    }

    function updateDurationBasedOnMonthly() {
        const amount = parseFloat(amountInput.value);
        const monthly = parseFloat(monthlyInput.value);
        const monthlyRate = interestRate / 12;

        if (monthly <= 0 || amount <= 0) {
            durationInput.value = 0;
            durationRange.value = 0;
            return;
        }

        const duration = Math.log(monthly / (monthly - amount * monthlyRate)) / Math.log(1 + monthlyRate);
        durationInput.value = Math.round(duration);
        durationRange.value = durationInput.value;
    }

    function updateMonthlyWhenAmountOrDurationChanges() {
        const amount = parseFloat(amountInput.value);
        const duration = parseFloat(durationInput.value);
        const monthlyValue = calculateMonthly(amount, duration);

        if (monthlyValue) {
            monthlyInput.value = monthlyValue;
            monthlyRange.value = monthlyValue;
            updateMonthlyLabel(monthlyValue);
        }
    }

    // Step navigation functions
    function showStep(stepNumber) {
        [step1, step2, step3].forEach((step, index) => {
            step.style.display = index + 1 === stepNumber ? 'block' : 'none';
        });
        [step1Indicator, step2Indicator, step3Indicator].forEach((indicator, index) => {
            if (index + 1 < stepNumber) {
                indicator.style.backgroundColor = '#02AFBC';
                indicator.classList.remove('active');
            } else if (index + 1 === stepNumber) {
                indicator.classList.add('active');
            } else {
                indicator.classList.remove('active');
            }
        });
    }

    function validateStep(stepNumber) {
        // Add validation logic for each step
        // Return true if validation passes, false otherwise
        switch (stepNumber) {
            case 1:
                return amountInput.value && durationInput.value && monthlyInput.value;
            case 2:
                return document.getElementById('email').value && document.getElementById('phone').value;
            case 3:
                return document.querySelector('input[name="civilite"]:checked') &&
                    document.getElementById('nom').value &&
                    document.getElementById('prenom').value &&
                    document.getElementById('mustbechecked').checked;
            default:
                return true;
        }
    }

    function updateRecap(completedStep) {
        let recapContent = '';

        if (completedStep >= 1) {
            const selectedProject = projectSelect.options[projectSelect.selectedIndex].text;
            const selectedProfession = professionSelect.options[professionSelect.selectedIndex].text;
            recapContent += `
                <h4 class="recap-first p">Mon Projet</h4>
                <p class="recap-project p">${selectedProject}</p><br><br>
                <h4 class="recap-title p">Détail de mon crédit</h4>
                <p class="recap-profession p s"><strong>Vous êtes:</strong> <span class="recap-value">${selectedProfession}</span></p>
                <hr>
                <p class="recap-amount p s"><strong>Montant:</strong> <span class="recap-value">${amountInput.value} DH</span></p>
                <hr>
                <p class="recap-duration p s"><strong>Durée:</strong> <span class="recap-value">${durationInput.value} mois</span></p>
                <hr>
                <p class="recap-monthly p s"><strong>Mensualités:</strong> <span class="recap-value">${monthlyInput.value} DH</span></p>
            `;
        }

        if (completedStep >= 2) {
            const emailValue = document.getElementById('email').value;
            const phoneValue = document.getElementById('phone').value;
            recapContent += `
                <h4 class="recap-perso p">Coordonnées et infos personnelles</h4>
                <p class="recap-email p s"><strong>Email:</strong> <span class="recap-value">${emailValue}</span></p>
                <p class="recap-phone p s"><strong>Téléphone:</strong> <span class="recap-value">${phoneValue}</span></p>
            `;
        }

        recapSection.innerHTML = recapContent;
        recapContainer.style.display = 'block';
    }

    // Event listeners
    syncInputs(amountInput, amountRange, updateMonthlyWhenAmountOrDurationChanges);
    syncInputs(durationInput, durationRange, updateMonthlyWhenAmountOrDurationChanges);
    syncInputs(monthlyInput, monthlyRange, () => {
        monthlyInput.value = monthlyRange.value;
        updateDurationBasedOnMonthly();
    });

    nextButtons.forEach(button => {
        button.addEventListener('click', (e) => {
            const currentStep = parseInt(e.target.dataset.step);
            if (validateStep(currentStep)) {
                const nextStep = currentStep + 1;
                showStep(nextStep);
                updateRecap(currentStep);
            } else {
                showFlashMessage('Veuillez remplir tous les champs obligatoires.', 'error');
            }
        });
    });

    multiStepForm.addEventListener('submit', function (e) {
        e.preventDefault();
        if (validateStep(3)) {
            // Perform final form submission
            showFlashMessage('Formulaire soumis avec succès!', 'success');
            // You can add an AJAX call here to submit the form data
        } else {
            showFlashMessage('Veuillez remplir tous les champs obligatoires.', 'error');
        }
    });

    const creditRadioGroup = document.querySelectorAll('input[name="credits"]');
    const additionalInputsContainer = document.getElementById('additional-inputs');
    const additionalInput1 = document.getElementById('additional-input1');
    const additionalInput2 = document.getElementById('additional-input2');

    creditRadioGroup.forEach(radio => {
        radio.addEventListener('change', () => {
            if (radio.value === 'Oui') {
                additionalInputsContainer.style.display = 'block';
                additionalInput1.required = true;
                additionalInput2.required = true;
            } else {
                additionalInputsContainer.style.display = 'none';
                additionalInput1.value = '';
                additionalInput2.value = '';
                additionalInput1.required = false;
                additionalInput2.required = false;
            }
        });
    });

    function showFlashMessage(message, type) {
        const flashMessageContainer = document.createElement('div');
        flashMessageContainer.textContent = message;
        flashMessageContainer.className = `flash-message ${type}`;

        flashMessageContainer.style.position = 'fixed';
        flashMessageContainer.style.top = '20px';
        flashMessageContainer.style.right = '20px';
        flashMessageContainer.style.zIndex = '1000';
        flashMessageContainer.style.padding = '10px';
        flashMessageContainer.style.color = 'white';
        flashMessageContainer.style.backgroundColor = type === 'error' ? 'red' : 'green';
        flashMessageContainer.style.borderRadius = '5px';
        flashMessageContainer.style.opacity = '0.9';

        document.body.appendChild(flashMessageContainer);

        // Remove flash message after 5 seconds
        setTimeout(() => {
            document.body.removeChild(flashMessageContainer);
        }, 5000);
    }

    // Check for flash message on page load
    const flashMessage = sessionStorage.getItem('flashMessage');
    if (flashMessage) {
        showFlashMessage(flashMessage, 'success');
        sessionStorage.removeItem('flashMessage');
    }

    // Initialize the form
    updateMonthlyWhenAmountOrDurationChanges();
    showStep(1);
});