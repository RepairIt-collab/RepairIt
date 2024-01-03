//OBTENIR LES DONNES DE L'URL

// Récupérer les valeurs des paramètres de requête
const urlParams = new URLSearchParams(window.location.search);
const prix = urlParams.get('prix');
const idT = urlParams.get('idT');
const idM = urlParams.get('idM');
var myId
if(idM != null){
  myId = idM
}

console.log(idT)
console.log(prix)
console.log(idM)
// Utiliser les valeurs des données
// Faire quelque chose avec les valeurs des données


// This is your test publishable API key.
const stripe = Stripe("pk_test_51OJ8k3IyLHKyHjYEgQvOCCYVfJcCUZCLKuNtOPhaff7CvTRCRNSw6S30ul6heufRN0HsMHc7kS4VsK7vRxOJ1ajU00X5OrHKcf");

// The items the customer wants to buy
const items = [{ id: "xl-tshirt" }];

let elements;

initialize();
checkStatus();

document
  .querySelector("#payment-form")
  .addEventListener("submit", handleSubmit);

// Fetches a payment intent and captures the client secret
async function initialize() {
  const response = await fetch("/create-payment-intent", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ items }),
  });
  const { clientSecret } = await response.json();
  console.log(clientSecret)

  const appearance = {
    theme: 'stripe',
  };
  elements = stripe.elements({ appearance, clientSecret });

  const paymentElementOptions = {
    layout: "tabs",
  };

  const paymentElement = elements.create("payment", paymentElementOptions);
  paymentElement.mount("#payment-element");
}
var emailAddress = "annaelledetchoua@gmail.com"
async function handleSubmit(e) {
  e.preventDefault();
  setLoading(true);

  const { error } = await stripe.confirmPayment({
    elements,
    confirmParams: {
      // Make sure to change this to your payment completion page
      return_url: "http://localhost:4242/checkout.html",
      receipt_email: emailAddress,
    },
  });

  // This point will only be reached if there is an immediate error when
  // confirming the payment. Otherwise, your customer will be redirected to
  // your `return_url`. For some payment methods like iDEAL, your customer will
  // be redirected to an intermediate site first to authorize the payment, then
  // redirected to the `return_url`.
  if (error.type === "card_error" || error.type === "validation_error") {
    showMessage(error.message);
  } else {
    showMessage("An unexpected error occurred.");
  }

  setLoading(false);
}

// Fetches the payment intent status after payment submission
async function checkStatus() {
  const clientSecret = new URLSearchParams(window.location.search).get(
    "payment_intent_client_secret"
  );

  if (!clientSecret) {
    return;
  }

  const { paymentIntent } = await stripe.retrievePaymentIntent(clientSecret);

  switch (paymentIntent.status) {
    case "succeeded":
      // showMessage("Payment succeeded!");
      terminerPayement()
      break;
    case "processing":
      showMessage("Your payment is processing.");
      break;
    case "requires_payment_method":
      showMessage("Your payment was not successful, please try again.");
      break;
    default:
      showMessage("Something went wrong.");
      break;
  }
}

// ------- UI helpers -------

function showMessage(messageText) {
  const messageContainer = document.querySelector("#payment-message");

  messageContainer.classList.remove("hidden");
  messageContainer.textContent = messageText;

  setTimeout(function () {
    messageContainer.classList.add("hidden");
    messageContainer.textContent = "";
  }, 4000);
}

//FONCTION POUR TERMINER LE PAYEMENT
function terminerPayement(){
  e.preventDefault();
  showMessage("Payment succeeded!");
  const url = "https://127.0.0.1:9001/RepairIt/Client/Maintenance/terminerTache";

  const params = new URLSearchParams();
  params.append('idM', idM);
  params.append('idT', idT);
  params.append('prix',prix)

  fetch(url, {
    method: 'POST',
    body: params
  })
    .then(response => {
      console.log("Données reçues pour creerTaches");
      window.location.reload()
      if (response.ok) {
        window.location.href = "https://127.0.0.1:9001/RepairIt/Client";
        return response.json(); // Renvoyer la réponse JSON
      } else {
        throw new Error('Erreur de la requête creerTaches');
      }
    })
    .then(responseBody => {
      // Utilisez le corps de la réponse ici
      console.log("***********" + responseBody);
    })
    .catch(error => {
      // Gestion des erreurs
      console.error(error);
    });
}

// Show a spinner on payment submission
function setLoading(isLoading) {
  if (isLoading) {
    // Disable the button and show a spinner
    document.querySelector("#submit").disabled = true;
    document.querySelector("#spinner").classList.remove("hidden");
    document.querySelector("#button-text").classList.add("hidden");
  } else {
    document.querySelector("#submit").disabled = false;
    document.querySelector("#spinner").classList.add("hidden");
    document.querySelector("#button-text").classList.remove("hidden");
  }
}