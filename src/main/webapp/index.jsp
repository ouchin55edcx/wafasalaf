
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Demander mon crédit en ligne</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
<div class="container">
    <div class="header">
        <h1>Demander mon crédit en ligne</h1>
        <a href="#" class="back-link">← Retour</a>
    </div>
    <section>
        <div class="steps-content">
            <div class="steps">
                <div class="step active" id="step-1-indicator"><span>1</span> Simuler mon crédit</div>
                <div class="step" id="step-2-indicator"><span>2</span> Mes coordonnées</div>
                <div class="step" id="step-3-indicator"><span>3</span> Mes infos personnelles</div>
            </div>

            <form novalidate id="creditForm" class="content-wrapper" action="${pageContext.request.contextPath}/submitRequest" method="POST">
                <section class="loan-form step-1" id="step-1-form">
                    <div id="loan-form">
                        <div class="form-group">
                            <label for="project">Mon projet</label>
                            <select id="project" name="project">
                                <option value="personal-loan">J'ai besoin d'argent</option>
                                <option value="vehicle-used">Je finance mon véhicule d'occasion</option>
                                <option value="unexpected-expenses">Je Gère mes imprévus</option>
                                <option value="vehicle-new">Je finance mon véhicule neuf</option>
                                <option value="home-equipment">J'équipe ma maison</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="profession">Je suis</label>
                            <select id="profession" name="profession">
                                <option value="fonctionnaire">Fonctionnaire</option>
                                <option value="secteur-prive">Salarié du secteur privé</option>
                                <option value="profession-libre">Profession libérale</option>
                                <option value="commercant">Commerçant</option>
                                <option value="artisan">Artisan</option>
                                <option value="retraite">Retraité</option>
                                <option value="autres">Autres professions</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <div class="input-wrapper">
                                <label for="amount">Montant (en DH)</label>
                                <input class="price" type="number" id="amount" name="amount" value="10000" min="1000" max="50000">
                                <input type="range" id="amount-range" value="10000" min="1000" max="50000">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-wrapper">
                                <label for="duration">Durée (en mois)</label>
                                <input class="price" type="number" id="duration" name="duration" value="24" min="12" max="60">
                                <input type="range" id="duration-range" value="24" min="12" max="60">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-wrapper">
                                <label for="monthly">Mensualités (en DH)</label>
                                <input type="number" id="monthly" name="monthly" step="0.01" required />
                                <input type="range" id="monthly-range" value="482.95" min="100" max="1000" step="0.01" >
                            </div>
                        </div>

                        <div class="btn-submit">Continuer<br><span>Sans engagement</span></div>
                    </div>
                </section>

                <section class="loan-form step-2" id="step-2-form" style="display: none;">
                    <div id="contact-form">
                        <div class="form-group-step2">
                            <input type="email" id="email" name="email" placeholder=" " required>
                            <label for="email">Email*</label>
                        </div>
                        <div class="form-group-step2">
                            <input type="tel" id="phone" name="phone" placeholder=" " required>
                            <label for="phone">Téléphone Mobile*</label>
                        </div>
                        <div  class="btn-submit">Continuer<br><span>Sans engagement</span></div>
                    </div>
                </section>

                <section class="loan-form step-3" id="step-3-form" style="display: none;">
                    <div id="personal-info-form">
                        <label>Civilité</label>
                        <div class="radio-group">
                            <label>
                                <input type="radio" name="civilite" value="Madame" required>
                                <span>Madame</span>
                            </label>
                            <label>
                                <input type="radio" name="civilite" value="Mademoiselle" required>
                                <span>Mademoiselle</span>
                            </label>
                            <label>
                                <input type="radio" name="civilite" value="Monsieur" required>
                                <span>Monsieur</span>
                            </label>
                        </div>

                        <div class="form-group-step2">
                            <input name="nom" type="text" id="nom" placeholder=" " required>
                            <label for="nom">Nom</label>
                        </div>

                        <div class="form-group-step2">
                            <input name="prenom" type="text" id="prenom" placeholder=" " required>
                            <label for="prenom">Prénom</label>
                        </div>

                        <div class="form-group-step2">
                            <input name="CIN" type="text" id="CIN" placeholder=" " required>
                            <label for="CIN">Numéro CIN / Carte de séjour</label>
                        </div>

                        <div class="form-group-step2">
                            <input name="datenaissance" type="text" id="datenaissance" placeholder="YYYY/MM/JJ" required>
                            <label for="datenaissance">Date de naissance</label>
                        </div>

                        <div class="form-group-step2">
                            <input name="datedembauche" type="text" id="datedembauche" placeholder="YYYY/MM/JJ" required>
                            <label for="datedembauche">Date d'embauche / début de l'activité</label>
                        </div>

                        <div class="form-group-step2">
                            <input name="totalrevenue" type="text" id="totalrevenue" placeholder=" " required>
                            <label for="totalrevenue">Total revenus mensuels (net en DH)</label>
                        </div>

                        <label>Avez-vous des crédits en cours?</label>
                        <div class="radio-group" id="credit-radio-group">
                            <label>
                                <input type="radio" name="credits" value="Oui" >
                                <span>Oui</span>
                            </label>

                            <label>
                                <input type="radio" name="credits" value="Non" checked>
                                <span>Non</span>
                            </label>
                        </div>

                        <div id="additional-inputs" style="display: none;">
                            <div class="form-group-step2">
                                <input type="text" id="creditImmo" name="creditImmo" placeholder="" required>
                                <label for="creditImmo"> Mensualité crédit Immo (net en DH)*</label>
                            </div>
                            <div class="form-group-step2">
                                <input type="text" id="otherCredits" name="otherCredits" placeholder=" " required>
                                <label for="otherCredits"> Mensualité autres crédits (net en DH)*</label>
                            </div>
                        </div>

                        <div class="form-group-step2 alpha">
                            <input type="checkbox" id="mustbechecked" required>
                            <p class="checkbox-label">J'ai lu et j'accepte les conditions générales d'utilisation figurant sur les informations légales,
                                notamment la mention relative à la protection des données personnelles</p>
                        </div>

                        <button type="submit" class="form-submit">Demande ce crédit</button>
                    </div>
                </section>
            </form>
            <section>
                <p class="terms">Simulation à titre indicatif et non contractuelle. La mensualité minimale est de 180 dirhams. Un client Wafasalaf peut bénéficier d'une tarification plus avantageuse en fonction de ses conditions préférentielles.
                    <br><br>
                    Conformément à la loi 09-08, vous disposez d'un droit d'accès, de rectification et d'opposition au traitement de vos données personnelles. Ce traitement est autorisé par la CNDP sous le numéro A-GC-206/2014.
                </p>
            </section>
        </div>

        <aside class="recap">
            <h2>Mon récapitulatif</h2>
            <div id="recap-content" class="recap-content">
                <p class="title-recap">Mon projet</p>
                <p class="recap-personel"><strong>Prêt Personnel</strong></p>
            </div>
        </aside>

    </section>
</div>

<script>
    document.addEventListener("DOMContentLoaded", () => {
        const steps = document.querySelectorAll(".step");
        const formSections = document.querySelectorAll("section.loan-form");
        const continueButtons = document.querySelectorAll(".btn-submit");
        const recapContent = document.getElementById("recap-content");
        const stepIndicators = document.querySelectorAll(".steps .step");
        let currentStep = 0;

        // Form data object to capture filled values
        const formData = {};

        // Function to update the recap section
        const updateRecap = () => {
            recapContent.innerHTML = '';  // Clear existing content
            Object.keys(formData).forEach((key) => {
                const fieldElement = document.createElement('p');
                fieldElement.classList.add("recap-personel");
                fieldElement.innerHTML = `<strong>${key}</strong>: ${formData[key]}`;
                recapContent.appendChild(fieldElement);
            });
        };

        // Function to navigate to a particular section
        const showSection = (index) => {
            formSections.forEach((section, i) => {
                section.style.display = i === index ? "block" : "none";
            });
            steps.forEach((step, i) => {
                step.classList.toggle("active", i === index);
            });
            currentStep = index;
        };

        // Check if all required fields in a section are filled
        const isSectionValid = (sectionIndex) => {
            const inputs = formSections[sectionIndex].querySelectorAll("input, select, textarea");
            return Array.from(inputs).every(input => input.checkValidity());
        };

        // Enable navigation between sections if all sections are filled
        const toggleStepNavigation = () => {
            stepIndicators.forEach((step, index) => {
                step.classList.toggle("navigable", isSectionValid(index));
            });
        };

        // Real-time form data collection
        const inputs = document.querySelectorAll("input, select, textarea");
        inputs.forEach((input) => {
            input.addEventListener("input", () => {
                const name = input.name;
                if (name) {
                    formData[name] = input.value;
                    updateRecap();
                    toggleStepNavigation();
                }
            });
        });

        // Initial display
        showSection(currentStep);

        // Continue button event listener for moving to the next section
        continueButtons.forEach((button, index) => {
            button.addEventListener("click", () => {
                if (isSectionValid(currentStep)) {
                    if (currentStep < formSections.length - 1) {
                        showSection(currentStep + 1);
                    }
                } else {
                    alert("Please fill all the fields in this section before continuing.");
                }
            });
        });

        // Step navigation by clicking on step titles
        stepIndicators.forEach((step, index) => {
            step.addEventListener("click", () => {
                if (isSectionValid(index)) {
                    showSection(index);
                }
            });
        });

        // Form submit handler to display data
        /*
        document.getElementById("creditForm").addEventListener("submit", (event) => {
            event.preventDefault();
            if (isSectionValid(formSections.length - 1)) {
                alert(`Form Submitted Successfully!\nForm Data: ${JSON.stringify(formData, null, 2)}`);
        } else {
            alert("Please fill all the fields in the last section before submitting.");
        }
    });
    */

        // Range input handlers for syncing with number inputs
        const amountInput = document.getElementById("montant");
        const amountRange = document.getElementById("amount-range");
        const durationInput = document.getElementById("duree");
        const durationRange = document.getElementById("duration-range");
        const mensualitesInput = document.getElementById("mensualites");
        const mensualitesRange = document.getElementById("monthly-range");

        const syncInputs = (inputElement, rangeElement) => {
            inputElement.addEventListener("input", () => {
                rangeElement.value = inputElement.value;
                formData[inputElement.name] = inputElement.value;
                updateRecap();
            });
            rangeElement.addEventListener("input", () => {
                inputElement.value = rangeElement.value;
                formData[inputElement.name] = rangeElement.value;
                updateRecap();
            });
        };

        syncInputs(amountInput, amountRange);
        syncInputs(durationInput, durationRange);
        syncInputs(mensualitesInput, mensualitesRange);

        // Show/Hide additional fields based on radio selection
        const creditRadioGroup = document.getElementById("credit-radio-group");
        const additionalInputsContainer = document.getElementById("additional-inputs");

        creditRadioGroup.addEventListener("change", (event) => {
            if (event.target.value === "Oui") {
                additionalInputsContainer.style.display = "block";
            } else {
                additionalInputsContainer.style.display = "none";
            }
        });
    });
</script>
</body>
</html>